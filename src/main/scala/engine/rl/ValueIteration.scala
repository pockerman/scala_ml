package engine.rl

import scala.collection.mutable.ArrayBuffer
import breeze.linalg.{DenseVector, max}
import engine.worlds.DiscreteWorld

import scala.collection.mutable

class ValueIteration[DiscreteActionSpace](world: DiscreteWorld[Int, DiscreteActionSpace],
                                          gamma: Double,
                                          maxIterations: Int,
                                          tolerance: Double) {

  val rewards = new mutable.HashMap[Tuple3[Int, Int, Int], Double]()
  val transits = new mutable.HashMap[Tuple3[Int, Int, Int], Int]
  val stateValues = DenseVector.zeros[Double](world.nStates)
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
    var  delta = 0.

    // update each state
    for( s <- 0 until world.nStates){

      // Do a one-step lookahead to find the best action
      val value = oneStepLookahead(state=s)
      val bestActionValue = breeze.linalg.max(value)
      delta = math.max(delta, math.abs(bestActionValue - stateValues(s)))
      stateValues(s) = bestActionValue
    }

    residual = delta
  }

  protected  def oneStepLookahead(state: Int): DenseVector[Double] = {

    val values = DenseVector.zeros[Double](world.nActions)

    for(action <- 0 until world.nActions) {

      val dynamics = world.getDynamics(state = state, action = action)

      for(item <- 0 until dynamics.length){

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
