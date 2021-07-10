package examples.rl

import engine.rl.TrainMode
import engine.rl.algos.dp.{IterativePolicyEvaluator}
import engine.worlds.{DiscreteEnvironment, FrozenLake}
import me.shadaj.scalapy.py

import scala.util.control.Breaks.{break, breakable}

class Agend(env: DiscreteEnvironment,
            gamma: Double, maxIterations: Int, tolerance: Double ) extends IterativePolicyEvaluator(environment = env, gamma = gamma,
                                                                                                    nMaxItrs = maxIterations, tolerance = tolerance,
                                                                                          ){

}

object Example3_IterativePoicyEvaluation {


  val tensorboardX = py.module("tensorboardX")
  val writer = tensorboardX.SummaryWriter(comment="-v-iteration")

  val GAMMA = 0.9
  val TEST_EPISODES = 20

  val env = new FrozenLake("v0")
  env.make
  val testEnv = new FrozenLake("v0")
  testEnv.make

  val agend = new Agend(env = env, gamma = GAMMA,
                        maxIterations = 20, tolerance = 1.0e-4)

  var bestReward = 0.0
  var iterNo = 0

  breakable {
    while (true) {

      iterNo += 1
      agend.train(false)

      var reward = 0.0
      println("Playing episode " + iterNo)

      for (i <- 0 until TEST_EPISODES) {
        reward += agend.play_episode(env = testEnv)
      }

      reward /= TEST_EPISODES.toDouble

      writer.add_scalar("reward", reward, iterNo)
      if (reward > bestReward) {
        println("Best reward updated " + bestReward + "->" + reward)
        bestReward = reward
      }

      if (reward > 0.80) {
        println("Solved in " + iterNo + " iterations!")
        break
      }

    }
  }

  writer.close()


}
