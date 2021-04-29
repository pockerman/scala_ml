package engine.utils

abstract class DiscreteEntity[A] {

  /**
   * Get the idx-th entry of the discrete
   * entity
   * @param idx
   * @return
   */
  def get(idx: Int):A;

  /**
   * Returns the size of the discrete entity
   * @return
   */
  def size: Int;

}
