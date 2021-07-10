package engine.rl.algos.dp

import breeze.linalg.{DenseVector, max}
import breeze.numerics.abs
import engine.rl.AlgorithmBase
import engine.rl.utils.{Policy, PolicyAdaptorBase}
import engine.worlds.DiscreteEnvironment

/**
 * Policy improvement algorithm
 * @param environment
 * @param v
 * @param policy
 * @param policyAdaptor
 * @param gamma
 */
class PolicyImprovement (environment: DiscreteEnvironment,
                         var v: DenseVector[Double],
                         val policy: Policy[Int, Int],
                         val policyAdaptor: PolicyAdaptorBase[Int, Int],
                         val gamma: Double) extends AlgorithmBase(environment = environment,
                         nMaxItrs = 1, tolerance = 1.0e-4){

  /**
   * Specify the necessary actions to execute before
   * starting the training iterations
   */
  override def actionsBeforeTrainingIterations: Unit = {
    super.actionsBeforeTrainingIterations
    this.state = this.environment.reset
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

    for(s <- 0 until this.environment.nStates) {

      val stateActions = Utils.stateActionsFromV(env = this.environment, v = this.v, state=s, gamma=this.gamma)
      this.policyAdaptor.adapt(s, stateActions, this.policy)
    }

    // set the residual so that we always converge
    this.itrCtrl.residual = 1.0e-5
  }

}
