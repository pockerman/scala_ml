package engine.rl


import scala.collection.mutable
import breeze.linalg.{DenseVector, max}
import engine.worlds.DiscreteEnvironment


class ValueIteration(env: DiscreteEnvironment,
                     gamma: Double, maxIterations: Int, tolerance: Double,
                     trainMode: TrainMode.Value=TrainMode.DEFAULT) {

  val rewards = new mutable.HashMap[Tuple3[Int, Int, Int], Double]()
  val transits = new mutable.HashMap[Tuple3[Int, Int, Int], Int]
  val stateValues = DenseVector.zeros[Double](env.nStates)
  var residual = 1.0

  def train:  Unit = {

    for(i <-0 until maxIterations){

      if(tolerance - residual < 0){
        step
      }
    }
  }

  def step: Unit ={

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
  }

  protected  def oneStepLookahead(state: Int): DenseVector[Double] = {

    val values = DenseVector.zeros[Double](env.nActions)

    for(action <- 0 until env.nActions) {

      val dynamics = env.getDynamics(state = state, action = action)

      for(item <- dynamics.indices){

        val prob = dynamics(item)._1
        val next_state = dynamics(item)._2
        val reward = dynamics(item)._3
        values(action) += prob * (reward + gamma * stateValues(next_state))

        if(rewards.contains((state, action, next_state))){
          rewards.update((state, action, next_state), reward)
          transits.update((state, action, next_state), 1)
        }
        else{
          rewards.addOne((state, action, next_state), reward)
          transits.addOne((state, action, next_state), 1)
        }
      }
    }
    values
  }

}
