package examples.rl


import engine.rl.algos.dp.IterativePolicyEvaluator
import engine.rl.utils.DiscreteUniformPolicy
import engine.worlds.{DiscreteEnvironment, FrozenLake}

class Agend(env: DiscreteEnvironment,
            gamma: Double, maxIterations: Int, tolerance: Double,
            policy: DiscreteUniformPolicy)
  extends IterativePolicyEvaluator(environment = env, gamma = gamma,
    nMaxItrs = maxIterations, tolerance = tolerance, policy = policy)

object Example3_IterativePoicyEvaluation_FrozenLake extends App{



  val GAMMA = 1.0

  // the environment
  val env = new FrozenLake("v0")
  env.make

  // initial policy to use
  var policy = new DiscreteUniformPolicy(env.nStates, env.nActions, prob = -1.0);

  // the agent
  val agend = new Agend(env = env, gamma = GAMMA,
                        maxIterations = 100, tolerance = 1.0e-8, policy = policy)

  var itrResult = agend.train

  println(s"Converged ${itrResult.converged}")
  println(s"Total iterations ${itrResult.nItrs}")
  println(s"Residual ${itrResult.residual}")

  agend.save("iterative_policy_evaluation_frozen_lake_v0.csv")
}
