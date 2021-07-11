package engine.rl.algos.dp

import breeze.linalg.DenseVector
import engine.rl.AlgorithmBase
import engine.rl.utils.{Policy, PolicyAdaptorBase}
import engine.worlds.DiscreteEnvironment

class PolicyIteration(environment: DiscreteEnvironment,
                      nMaxItrs: Int, tolerance: Double,
                      nPolicyEvalItrs: Int,
                      val policy: Policy[Int, Int],
                      val policyAdaptor: PolicyAdaptorBase[Int, Int],
                      val gamma: Double) extends AlgorithmBase(environment = environment, nMaxItrs = nMaxItrs, tolerance = tolerance){

  var policyEvaluator = new IterativePolicyEvaluator(environment = environment,
                                                     nMaxItrs = nPolicyEvalItrs, tolerance = tolerance,
                                                     policy = policy, gamma = gamma)
  var policyImprovement = new PolicyImprovement(environment = environment, gamma = gamma, policy = policy,
                                                policyAdaptor = policyAdaptor, v = policyEvaluator.v)

  /**
   * Specify the necessary actions to execute before
   * starting the training iterations
   */
  override def actionsBeforeTrainingIterations: Unit = {
    super.actionsBeforeTrainingIterations
    policyEvaluator.actionsBeforeTrainingIterations
    policyImprovement.actionsBeforeTrainingIterations
  }

  /**
   * Specify the necessary actions to execute after the training
   * iterations finish. This class has no specified actions
   */
  override def actionsAfterTrainingIterations: Unit = {
    super.actionsAfterTrainingIterations
    policyEvaluator.actionsAfterTrainingIterations
    policyImprovement.actionsAfterTrainingIterations
  }

  /**
   * Do one iteration step
   */
  override  def step: Unit = {

    // make a copy of the policy already obtained
    val oldPolicy = this.policyEvaluator.policy

    // evaluate the policy
    this.policyEvaluator.train

    // update the value function to
    // improve for
    this.policyImprovement.v = this.policyEvaluator.v

    // improve the policy
    this.policyImprovement.train

    var newPolicy = this.policyImprovement.policy

    // check of the two policies are the same
    if(oldPolicy == newPolicy) {
      this.itrCtrl.residual = this.itrCtrl.tolerance*10**-1
    }

    this.policyEvaluator.policy = newPolicy
  }

}
