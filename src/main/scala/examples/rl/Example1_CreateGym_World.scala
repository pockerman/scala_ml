package examples.rl
import me.shadaj.scalapy.py


object Example1_CreateGym_World extends App {

  println("Run Create OpenAI Gym World")

  // the name of the environment
  final val ENV_NAME = "FrozenLake-v0"

  // the gym Python module
  val gym = py.module("gym")

  // the environment
  val env = gym.make(ENV_NAME)

  println("Number of actions " + env.action_space.n)
  println("Number of states " + env.observation_space.n)

}
