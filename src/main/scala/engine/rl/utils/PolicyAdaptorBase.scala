package engine.rl.utils

import breeze.linalg.DenseVector

/**
 * PolicyAdaptorBase trait specifies the interface
 * for policy adaptors
 *
 * @tparam StateTp
 * @tparam ActionTp
 */
trait PolicyAdaptorBase[StateTp, ActionTp] {

  /**
   * Given the state and the state actions adapt the supplied
   * policy
   * @param state
   * @param stateActions
   * @param policy
   */
  def adapt(state: StateTp, stateActions: DenseVector[Double], policy: Policy[StateTp, ActionTp])

}
