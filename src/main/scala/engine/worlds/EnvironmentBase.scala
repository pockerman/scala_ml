package engine.worlds

import me.shadaj.scalapy.py
import engine.actions.Action
import engine.states.State

/**
 * Base class for deriving RL environments
 */
trait EnvironmentBase[State, Action] {

  /**
   * Make the environment
   */
  def make: Unit;

  /**
   *  Reset the environment
   */
  def reset: State;

  /**
   *
   * @param action
   */
  def step(action: Action): (State, Double, Boolean, AnyRef);

  /**
   * Whether the current episode within the environment has finished
   * @return
   */
  def finishedEpisode:Boolean;

  /**
   * Returns the name of the environment
   */
  def name: String;
}
