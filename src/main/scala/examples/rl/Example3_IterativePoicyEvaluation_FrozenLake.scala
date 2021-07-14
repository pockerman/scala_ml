package examples.rl


import engine.rl.algos.dp.IterativePolicyEvaluator
import engine.rl.utils.DiscreteUniformPolicy
import engine.worlds.{DiscreteEnvironment, FrozenLake}
import me.shadaj.scalapy.py


object Example3_IterativePoicyEvaluation_FrozenLake extends App{

  class Agend(env: DiscreteEnvironment,
              gamma: Double, maxIterations: Int, tolerance: Double,
              policy: DiscreteUniformPolicy)
    extends IterativePolicyEvaluator(environment = env, gamma = gamma,
      nMaxItrs = maxIterations, tolerance = tolerance, policy = policy)

  val GAMMA = 1.0
  val env = new FrozenLake("v0")
  env.make

  var policy = new DiscreteUniformPolicy(env.nStates, env.nActions, prob = -1.0);

  val agend = new Agend(env = env, gamma = GAMMA,
                        maxIterations = 20, tolerance = 1.0e-4, policy = policy)

  var itrResult = agend.train

  println(s"Converged ${itrResult.converged}")
  println(s"Total iterations ${itrResult.nItrs}")
  println(s"Residual ${itrResult.residual}")
}
