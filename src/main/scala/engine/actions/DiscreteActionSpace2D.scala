package engine.actions

import engine.utils.DiscreteEntity

import scala.collection.immutable.HashMap

class DiscreteActionSpace2D extends DiscreteEntity[Option[String]]{

  /**
   * The actions
   */
  val actionsMap = HashMap[Int, String](0 -> "Down", 1 -> "Right", 2 -> "Up", 3 -> "Left")

  /**
   *
   * @param aIdx
   * @return
   */
  override def get(aIdx: Int): Option[String] = {
    actionsMap.get(aIdx)
  }

  /**
   * Return the
   *  @return
   */
  override def size: Int = actionsMap.size

}
