package examples.rl

import engine.rl.utils.DiscreteUniformPolicy
import engine.rl.algos.dp.PolicyIteration
import engine.worlds.FrozenLake

class Agent extends PolicyIteration(environment = new FrozenLake("v0"),
  nMaxItrs = 100, nPolicyEvalItrs = 100, gamma=1.0, policy=null,
  policyAdaptor = null, tolerance = 1.0e-7){

}


object Example5_PolicyIteration_FrozenLake {

  val environment = new FrozenLake("v0")
  val policy = DiscreteUniformPolicy(nStates = environment.nStates,
                                      nActions = environment.nActions prob = null)
  val policyAdaptor = StochasticAdaptorPolicy()

}
