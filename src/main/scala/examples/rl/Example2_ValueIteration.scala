package examples.rl

import engine.rl.{TrainMode, ValueIteration}
import engine.worlds.FrozenLake

import scala.util.control.Breaks.{break, breakable}
import me.shadaj.scalapy.py

object Example2_ValueIteration extends App {

  def play_episode(env: FrozenLake, agend: ValueIteration): Double ={

    var totalReward = 0.0
    var state = env.reset

    breakable {
      while (true) {

        val action = agend.selectAction(state = state)
        val stepResult = env.step(action)
        val newState = stepResult._1
        val reward = stepResult._2
        val isDone = stepResult._3

        totalReward += reward
        if(isDone) {
          break
        }
        state = newState

      }
    }

    totalReward
  }

  val tensorboardX = py.module("tensorboardX")
  val writer = tensorboardX.SummaryWriter(comment="-v-iteration")

  val GAMMA = 0.9
  val TEST_EPISODES = 20

  val env = new FrozenLake("v0")
  val testEnv = new FrozenLake("v0")
  env.make
  val valItr = new ValueIteration(env=env, gamma=GAMMA,
                                  maxIterations=20, tolerance=1.0e-4,
                                  trainMode = TrainMode.STOCHASTIC)

  var bestReward = 0.0
  var iterNo = 0

  breakable {
    while (true) {

      iterNo += 1
      valItr.train

      var reward = 0.0
      for (i <- 0 until TEST_EPISODES) {
        reward += play_episode(env = testEnv, agend = valItr)
      }
      reward /= TEST_EPISODES
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
