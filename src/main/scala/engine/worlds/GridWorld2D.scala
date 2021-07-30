package engine.worlds
import engine.actions.DiscreteActionSpace2D

object GridWorld2D{
  class State{

  }
}

class GridWorld2D extends DiscreteWorld[GridWorld2D.State, DiscreteActionSpace2D] {
  /**
   *
   * @return
   */
  override def nActions: Int = {
    val actions = 10;
    actions
  }

  /**
   * Sample an action from the action space
   *
   * @return
   */
  override def sampleAction: Int = {2}

  /**
   *
   * @param state
   * @param action
   * @return
   */
override def getDynamics(state: GridWorld2D.State, action: Int): Array[(Double, GridWorld2D.State, Double, Boolean)] = {
  null
}
}
