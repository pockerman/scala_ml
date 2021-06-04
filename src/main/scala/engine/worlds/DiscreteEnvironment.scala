package engine.worlds

/**
 * A discrete environment has a discrete number of
 * states and a discrete number of actions
 */
abstract class DiscreteEnvironment extends EnvironmentBase[Int, Int] {

  /**
   * Returns the number of states
   */
  def nStates: Int;

  /**
   * Returns the number of actions
   */
  def nActions: Int;

  /**
   *
   */
  def getDynamics(state: Int, action: Int): Seq[(Double, Int, Double, Boolean)]

}
