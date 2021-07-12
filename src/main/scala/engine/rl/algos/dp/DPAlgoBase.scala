package engine.rl.algos.dp

import breeze.linalg.DenseVector
import engine.rl.AlgorithmBase
import engine.rl.utils.{Policy, PolicyAdaptorBase}
import engine.worlds.DiscreteEnvironment

abstract class DPAlgoBase(environment: DiscreteEnvironment,
                 nMaxItrs: Int,
                 tolerance: Double,
                 var policy: Policy[Int, Int],
                 var gamma: Double) extends AlgorithmBase(environment = environment, nMaxItrs = nMaxItrs,
                                                          tolerance = tolerance){

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
    this.v = DenseVector.zeros[Double](this.environment.nStates)
  }

  /**
   * Specify the necessary actions to execute after the training
   * iterations finish. This class has no specified actions
   */
  override def actionsAfterTrainingIterations: Unit = {}

}
