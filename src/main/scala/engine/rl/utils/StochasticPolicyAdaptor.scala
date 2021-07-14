package engine.rl.utils
import breeze.linalg.DenseVector

class StochasticPolicyAdaptor extends PolicyAdaptorBase[Int, Int] {

  override def adapt(state: Int, stateActions: DenseVector[Double], policy: Policy[Int, Int]): Unit = {
  }


}
