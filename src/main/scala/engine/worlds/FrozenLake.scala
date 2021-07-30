package engine.worlds
import me.shadaj.scalapy.py


/**
 * Frozen lake world wrapper
 */
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

    if(this.env == null){
      throw new NullPointerException("Environment not established. Have you called 'make' ")
    }

    val state = this.env.reset()
    state.as[Int]
  }

  /**
   * Sample an action
   */
  override  def sampleAction: Int = {
    this.env.action_space.sample().as[Int]
  }

  /**
   * Apply the action in the world
   */
  override def step(action: Int): (Int, Double, Boolean, AnyRef) = {
    val stepResult: Tuple3[Int, Double, Boolean] = this.env.step(action).as[(Int, Double, Boolean)]
    new Tuple4[Int, Double, Boolean, AnyRef](stepResult._1, stepResult._2, stepResult._3, null)
  }

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
    val result = dynamicsTuple.bracketAccess(action).as[Seq[(Double, Int, Double, Boolean)]]
    result
  }

}
