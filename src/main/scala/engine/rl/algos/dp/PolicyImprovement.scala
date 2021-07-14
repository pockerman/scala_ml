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
                         policy: Policy[Int, Int], gamma: Double,
                         valFunc: DenseVector[Double],
                         val policyAdaptor: PolicyAdaptorBase[Int, Int],
                        ) extends DPAlgoBase(environment = environment,
                         nMaxItrs = 1, tolerance = 1.0e-4, policy = policy, gamma = gamma){

  this.v = valFunc


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
