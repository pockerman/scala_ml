package engine.worlds
import scala.collection.mutable.ArrayBuffer

/**
 * Abstract class for representing
 * discrete worlds
 */
abstract class DiscreteWorld[State, DiscreteActionSpace] {

  /**
   * The discrete states of the world
   */
  val states_ = new ArrayBuffer[State]();

  /**
   * Returns the number of states in the discrete world
   * @return
   */
  def nStates: Int = states_.length

  /**
   *
   * @return
   */
  def nActions: Int;

  /**
   * Sample an action from the action space
   * @return
   */
  def sampleAction: Int

  /**
   * Add a new state in the list of states
   * @param state
   */
  def addState(state: State): Unit = {

    if(state == null){
      throw new NullPointerException("Attempt to add null state")
    }

    states_ += state
  }

  /**
   * Set the sIdx-th state
   * @param sIdx
   * @param state
   */
  def setState(sIdx: Int, state: State): Unit = {

    if(state == null){
      throw new NullPointerException("Attempt to add null state")
    }

    states_(sIdx) = state;
  }

  /**
   *
   * @param state
   * @param action
   * @return
   */
  def getDynamics(state: State, action: Int): Array[Tuple4[Double, State, Double, Boolean]];

}
