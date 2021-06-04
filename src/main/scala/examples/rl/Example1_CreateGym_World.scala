package examples.rl
import me.shadaj.scalapy.py

import scala.collection.immutable.HashMap
import scala.collection.mutable


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

  val P = py.Dynamic.global.dict(env.P)
  val dynamics = P.bracketAccess(0).bracketAccess(0)


  for(i <- 0 until env.observation_space.n.as[Int]){
    println(P)
  }

  println(dynamics)

}
