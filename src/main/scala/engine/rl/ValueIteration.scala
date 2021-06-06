package engine.rl


import scala.collection.mutable
import breeze.linalg.{DenseVector, max, sum}
import engine.worlds.DiscreteEnvironment

import scala.util.control.Breaks.{break, breakable}

/**
 * Simple Value iteration algorithm
 */
class ValueIteration(env: DiscreteEnvironment,
                     gamma: Double, maxIterations: Int, tolerance: Double,
                     trainMode: TrainMode.Value=TrainMode.DEFAULT) {

  val rewards = new mutable.HashMap[Tuple3[Int, Int, Int], Double]()
  val transits = new mutable.HashMap[Tuple2[Int, Int], mutable.HashMap[Int,Int]]
  var stateValues = DenseVector.zeros[Double](env.nStates)
  var residual = 1.0
  var state = env.reset

  /**
   * Train the model
   */
  def train:  Unit = {


    this.rewards.clear()
    this.transits.clear()
    this.stateValues = DenseVector.zeros[Double](env.nStates)

    if(this.trainMode == TrainMode.STOCHASTIC){
      this.stochasticStep
      this.buildStateValues
    }
    else {
      this.defaultStep
    }
  }

  /**
   *
   */
  def defaultStep: Unit ={

    breakable {

      for(itr <- Range(0, maxIterations)){

        println("> Learning iteration " + itr)
        println("> Learning residual " + residual)

        // stop condition
        var  delta = 0.0

        // update each state
        for( s <- 0 until env.nStates){

          // Do a one-step lookahead to find the best action
          val value = oneStepLookahead(state=s)
          val bestActionValue = breeze.linalg.max(value)
          delta = math.max(delta, math.abs(bestActionValue - stateValues(s)))
          stateValues(s) = bestActionValue
        }

        residual = delta

        if(residual < tolerance) break;
      }
    }
  }


  /**
    * Do a stochastic step in the environment by
    * sampling an action from the  action space
   */
  def stochasticStep: Unit = {

      for (itr <- Range(0, maxIterations)) {

        println("> Learning iteration " + itr)

        val action = this.env.sampleAction
        val stepResult = this.env.step(action)

        val newState = stepResult._1
        val reward = stepResult._2
        val isDone = stepResult._3

        this.rewards.update((this.state, action, newState), reward)

        if(transits.contains((this.state, action))){
          if(transits((this.state, action)).contains(newState)){
            val currentTransitVal = transits((this.state, action)).get(newState).get
            val map = transits((this.state, action))
            map.update(newState, currentTransitVal + 1)
            this.transits.update((this.state, action), map)
          }
          else{
            val map = mutable.HashMap[Int, Int](newState -> 1)
            this.transits.update((this.state, action), map)
          }
        }
        else {
          this.transits.addOne((this.state, action), collection.mutable.HashMap[Int, Int](newState -> 1))
        }

        if(isDone){
          this.state = this.env.reset
        }
        else{
          this.state = newState
        }
      }
  }

  /**
   * Select action
   */
  def selectAction(state: Int): Int={

    var bestAction = -1
    var bestValue = Double.MinValue

    for(action <- 0 until  this.env.nActions) {
      val actionValue = calcActionValue(state, action)

      if(bestValue < actionValue) {
        bestValue = actionValue
        bestAction = action
      }
    }

    bestAction
  }

  /**
   *
   */
  protected  def oneStepLookahead(state: Int): DenseVector[Double] = {

    val values = DenseVector.zeros[Double](env.nActions)

    for(action <- 0 until env.nActions) {

      val dynamics = env.getDynamics(state = state, action = action)

      for(item <- dynamics.indices){

        val prob = dynamics(item)._1
        val next_state = dynamics(item)._2
        val reward = dynamics(item)._3
        values(action) += prob * (reward + gamma * stateValues(next_state))
      }
    }
    values
  }

  /**
   * build the state values from rewards
   * and transits
   */
  protected def buildStateValues: Unit= {


    for (stateIdx <- 0 until this.env.nStates) {

      val localStateValues = new collection.mutable.ArrayBuffer[Double]
      var actionValue = 0.0
      for (action <- 0 until this.env.nActions) {

        if (this.transits.contains((stateIdx, action))) {

          val targetCountsMap = this.transits((stateIdx, action))
          val total = sum(targetCountsMap.values)

          for ((tgtState, count) <- targetCountsMap) {

            val rewardsKey = (stateIdx, action, tgtState)
            val reward = this.rewards(rewardsKey)

            actionValue = reward + this.gamma * this.stateValues(tgtState)
            actionValue += (count / total) * actionValue
            localStateValues.addOne(actionValue)
          }
        }
        stateValues(state) = max(localStateValues)
      }
    }
  }

  /**
   * Calculate value for the given state and action
   * @param state
   * @param action
   * @return
   */
  protected def calcActionValue(stateIdx: Int, action: Int): Double = {

    var actionValue = 0.0
    val values = DenseVector.zeros[Double](env.nActions)
    if(this.trainMode == TrainMode.DEFAULT){

      val dynamics = env.getDynamics(state = stateIdx, action = action)

      for(item <- dynamics.indices){

        val prob = dynamics(item)._1
        val nextState = dynamics(item)._2
        val reward = dynamics(item)._3
        values(action) += prob * (reward + gamma * stateValues(nextState))
      }

      actionValue = breeze.linalg.max(values)

    }
    else {

      val targetCountsMap = this.transits((stateIdx, action))
      val total = sum(targetCountsMap.values)

      for ((tgtState, count) <- targetCountsMap) {

        val rewardsKey = (stateIdx, action, tgtState)
        val reward = this.rewards(rewardsKey)

        actionValue = reward + this.gamma * this.stateValues(tgtState)
        actionValue += (count / total) * actionValue
      }
    }

    actionValue
  }
}
