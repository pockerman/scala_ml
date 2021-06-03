package engine.worlds

import me.shadaj.scalapy.py
import engine.actions.Action
import engine.states.State

/**
 * Base class for deriving RL environments
 */
trait EnvironmentBase {

  /**
   * Make the environment
   */
  def make: Unit;

  /**
   *
   * @param action
   */
  def step(action: Action): Tuple4[State, Double, Boolean, AnyRef]
  
  /**
   * Whether the current episode within the environment has finished
   * @return
   */
  def finishedEpisode:Boolean;
}
