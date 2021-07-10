package engine.rl.utils

import scala.collection.mutable.ArrayBuffer

class DiscreteUniformPolicy(val nStates: Int, val nActions: Int, val prob: Double) extends Policy[Int, Int] {

  var result: ArrayBuffer[(Int, Double)] = null

  def actionProbabilities(state: Int): ArrayBuffer[(Int, Double)] = {

    require(state < nStates)

    if(result == null){
      result = new ArrayBuffer[(Int, Double)](nActions)

      for(a <- 0 until nActions){
        result.addOne((a, 1.0/nActions))
      }
    }

    result
  }

}
