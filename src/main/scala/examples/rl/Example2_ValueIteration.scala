package examples.rl

import engine.rl.{TrainMode, ValueIteration}
import engine.worlds.FrozenLake

object Example2_ValueIteration extends App {

  val GAMMA = 0.9
  val TEST_EPISODES = 20

  val env = new FrozenLake("v0")
  env.make
  val valItr = new ValueIteration(env=env, gamma=GAMMA,
                                  maxIterations=20, tolerance=1.0e-4,
                                  trainMode = TrainMode.STOCHASTIC)

  valItr.train



}
