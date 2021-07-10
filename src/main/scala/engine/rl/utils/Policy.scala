package engine.rl.utils

import scala.collection.mutable.ArrayBuffer

/**
 *
 * Policy trait for constructing policies
 * @tparam StateTp
 * @tparam ActionTp
 */
trait Policy[StateTp, ActionTp] {

  def actionProbabilities(state: StateTp): ArrayBuffer[(Int, Double)]

}
