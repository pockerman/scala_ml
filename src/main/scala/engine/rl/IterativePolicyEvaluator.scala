package engine.rl

import breeze.linalg.DenseVector
import engine.worlds.DiscreteEnvironment

class IterativePolicyEvaluator(environment: DiscreteEnvironment,
                               nMaxItrs: Int, tolerance: Double) extends AlgorithmBase(environment = environment,
  nMaxItrs = nMaxItrs, tolerance = tolerance) {

  /**
   *
   */
  var v: DenseVector[Double] = null


  override def actionsBeforeTrainingIterations: Unit = {
    super.actionsBeforeTrainingIterations
    this.state = environment.reset
    this.v = DenseVector.zeros[Double](this.environment.nStates)
  }

  /**
   *
   */
  override  def step: Unit = {

    var delta = 0.0
    # we loop over the states of the environment
    for(s <- 0 until this.environment.nStates) {
      val oldVs = v(s)

      # loop over actions for states. This is
      # the first sum
      value_s = 0.0
      for action, action_prob in enumerate(self._policy[s]):

      # this is the second sum
      for prob, next_state, reward, done in self.train_env.P[s][action]:
        value_s += action_prob * prob * (reward + self.gamma * self.v[next_state])

      # update the residual
      delta = max(delta, np.abs(old_value_s - value_s))

      # update the value function table
      self.v[s] = value_s
      self.itr_control.residual = delta


    }




  }

  /**
   *
   */
  override def actionsAfterTrainingIterations: Unit = {}

}
