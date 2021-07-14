package engine.rl.algos.dp

import breeze.linalg.{DenseVector, max, sum}
import breeze.numerics.abs
import engine.rl.TrainMode
import engine.rl.utils.{Policy, PolicyAdaptorBase}
import engine.worlds.DiscreteEnvironment

import scala.collection.mutable
import scala.util.control.Breaks.{break, breakable}

/**
 * Simple Value iteration algorithm
 */
class ValueIteration(environment: DiscreteEnvironment,
                     gamma: Double, nMaxItrs: Int,
                     tolerance: Double,
                     policy: Policy[Int, Int],
                     policyAdaptor: PolicyAdaptorBase[Int, Int]) extends DPAlgoBase (environment = environment,
  gamma = gamma, nMaxItrs = nMaxItrs, tolerance = tolerance, policy = policy){

  //
  val policyImprovement = new PolicyImprovement(environment=environment, gamma=gamma,
                                                policy=policy, policyAdaptor=policyAdaptor,
    valFunc = this.v)
  //policyImprovement.v = this.v

  /**
   * TDo one step
   */
  override def step:  Unit = {


    var delta = 0.0
    for(s <- 0 until this.environment.nStates){

      val v = this.v(s)
      this.v(s) = max(Utils.stateActionsFromV(env=this.environment, v=this.v, state=s, gamma=this.gamma))
      delta = max(delta, abs(this.v(s) - v))
    }

    this.itrCtrl.residual = delta

    policyImprovement.v = this.v
    policyImprovement.step
    this.policy = policyImprovement.policy

  }

}
