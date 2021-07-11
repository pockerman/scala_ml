package engine.rl.algos.dp

import breeze.linalg.{DenseVector, max}
import breeze.numerics.abs
import engine.rl.AlgorithmBase
import engine.rl.utils.Policy
import engine.worlds.DiscreteEnvironment

class IterativePolicyEvaluator(environment: DiscreteEnvironment,
                               nMaxItrs: Int, tolerance: Double,
                               var policy: Policy[Int, Int],
                               val gamma: Double) extends AlgorithmBase(environment = environment,
                                                                        nMaxItrs = nMaxItrs, tolerance = tolerance) {

  /**
   * The value function table
   */
  var v: DenseVector[Double] = null

  /**
   * Specify the necessary actions to execute before
   * starting the training iterations
   */
  override def actionsBeforeTrainingIterations: Unit = {
    super.actionsBeforeTrainingIterations
    this.state = this.environment.reset
    this.v = DenseVector.zeros[Double](this.environment.nStates)
  }

  /**
   * Specify the necessary actions to execute after the training
   * iterations finish. This class has no specified actions
   */
  override def actionsAfterTrainingIterations: Unit = {}

  /**
   * Do one iteration step
   */
  override  def step: Unit = {

    var delta = 0.0

    // we loop over the states of the environment
    for(s <- 0 until this.environment.nStates) {

      val oldVs = this.v(s)

      // loop over actions for states. This is
      // the first sum
      var valueS = 0.0
      val actionProbs = this.policy.actionProbabilities(state = s)

      for (item <- actionProbs.indices) {

        val actionIdx = actionProbs(item)._1
        val prob = actionProbs(item)._2
        valueS = this.getValueForState(state = s, action = actionIdx, actionProb = prob)
      }

      delta = max(delta, abs(oldVs - valueS))
      this.v(s) = valueS
    }

    // update the residual
    this.itrCtrl.residual = delta
  }

  /**
   * Calculate the state value function for the given action
   */
  def getValueForState(state: Int, action: Int, actionProb: Double): Double = {

    val dynamics = this.environment.getDynamics(state = state, action = action)
    var value = 0.0
    for(item <- dynamics.indices) {

      val prob = dynamics(item)._1
      val nextState = dynamics(item)._2
      val reward = dynamics(item)._3
      value += actionProb * prob * (reward + gamma * this.v(nextState))
    }

    value
  }



}
