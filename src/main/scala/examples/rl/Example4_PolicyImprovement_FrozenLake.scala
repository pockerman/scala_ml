package examples.rl

import breeze.linalg.DenseVector
import engine.rl.algos.dp.{IterativePolicyEvaluator, PolicyImprovement}
import engine.rl.utils.{DiscreteUniformPolicy, Policy, PolicyAdaptorBase, StochasticPolicyAdaptor}
import engine.worlds.{DiscreteEnvironment, FrozenLake}



object Example4_PolicyImprovement_FrozenLake extends App {

  class ImprovementAgent(environment: DiscreteEnvironment,
                         policy: Policy[Int, Int], gamma: Double,
                         valFunc: DenseVector[Double],
                         policyAdaptor: PolicyAdaptorBase[Int, Int]) extends
    PolicyImprovement(environment = environment, valFunc = valFunc,
      policyAdaptor = policyAdaptor, gamma = gamma, policy = policy)

  class IterativeAgend(env: DiscreteEnvironment,
                       gamma: Double, maxIterations: Int, tolerance: Double,
              policy: DiscreteUniformPolicy)
    extends IterativePolicyEvaluator(environment = env, gamma = gamma,
      nMaxItrs = maxIterations, tolerance = tolerance, policy = policy)

  val GAMMA = 1.0
  val env = new FrozenLake("v0")
  env.make

  var policy = new DiscreteUniformPolicy(env.nStates, env.nActions, prob = -1.0);

  val iterativeAgend = new IterativeAgend(env = env, gamma = GAMMA,
                                 maxIterations = 20, tolerance = 1.0e-4, policy = policy)

  var itrResult = iterativeAgend.train

  println(s"Converged ${itrResult.converged}")
  println(s"Total iterations ${itrResult.nItrs}")
  println(s"Residual ${itrResult.residual}")

  val policyAdaptor = new StochasticPolicyAdaptor()
  val improvementAgent = new ImprovementAgent(environment = env, policy = policy,
    valFunc = iterativeAgend.v, gamma = GAMMA, policyAdaptor = policyAdaptor)

  itrResult = improvementAgent.train

  println(s"Converged ${itrResult.converged}")
  println(s"Total iterations ${itrResult.nItrs}")
  println(s"Residual ${itrResult.residual}")

}
