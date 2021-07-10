package engine.rl.algos.dp

import breeze.linalg.DenseVector
import engine.worlds.DiscreteEnvironment

import scala.collection.mutable
import scala.collection.mutable.HashMap

object Utils {

  /**
   * Given the state index returns the list of actions under the
   * provided value functions
   */
  def stateActionsFromV(env: DiscreteEnvironment, v: DenseVector[Double],
                        gamma: Double, state: Int): DenseVector[Double] = {

    val q = DenseVector.zeros[Double](env.nStates)

    for (a <- 0 until env.nActions) {

      val dynamics = env.getDynamics(state = state, action = a)
      var value = 0.0
      for (item <- dynamics.indices) {

        val prob = dynamics(item)._1
        val nextState = dynamics(item)._2
        val reward = dynamics(item)._3

        q(a) += prob * (reward + gamma * v(nextState))

      }
    }
    q
  }


  /**
   * Returns the state-action value function for the
   * approximated value function
   *
   * @param env
   * @param v
   * @param gamma
   */
  def qFromV(env: DiscreteEnvironment, v: DenseVector[Double], gamma: Double): mutable.HashMap[Int, DenseVector[Double]] = {

    val qMap = new mutable.HashMap[Int, DenseVector[Double]]()

    for (s <- 0 until env.nStates) {
      qMap.addOne(s, stateActionsFromV(env = env, v = v, gamma = gamma, state = s))
    }

    qMap
  }

}
