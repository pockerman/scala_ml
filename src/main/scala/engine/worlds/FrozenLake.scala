package engine.worlds
import engine.states.State
import me.shadaj.scalapy.py
import me.shadaj.scalapy.readwrite.Reader.doubleReader


class FrozenLake(val version: String) extends DiscreteEnvironment {

  // load the gym module
  private val gym = py.module("gym")
  private var env: me.shadaj.scalapy.py.Dynamic = null

  /**
   * Make the environment
   */
  override def make: Unit = this.env = gym.make(this.name)

  /**
   *  Reset the environment
   */
  override def reset: Int = {

    val state = this.env.reset()
    state.as[Int]
  }

  /**
   *
   * @param action
   */
  override def step(action: Int): (Int, Double, Boolean, AnyRef) = null

  /**
   * Whether the current episode within the environment has finished
   * @return
   */
  override def finishedEpisode:Boolean = true

  /**
   * Returns the name of the environment
   */
  override def name: String = {"FrozenLake-" + version}

  /**
   * Returns the number of states
   */
  override  def nStates: Int = this.env.observation_space.n.as[Int]

  /**
   * Returns the number of actions
   */
  override  def nActions: Int = this.env.action_space.n.as[Int];

  /**
   *
   */
  override def getDynamics(state: Int, action: Int): Seq[(Double, Int, Double, Boolean)] = {

    val P = py.Dynamic.global.dict(this.env.P)
    val dynamicsTuple = P.bracketAccess(state)
    val result = dynamicsTuple.bracketAccess(action).as[Seq[Tuple4[Double, Int,Double, Boolean]]]
    result
  }

}
