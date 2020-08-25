package examples.models.example2

import breeze.linalg.{DenseMatrix, DenseVector}
import engine.models.HMMHelpers

object Example2 {

  def main(args: Array[String]):Unit={

    // create transition probability matrix
    val A = DenseMatrix((0.7, 0.3),
                        (0.4, 0.6))

    // create the emission probability matrix
    val B = DenseMatrix((0.5, 0.4, 0.1),
                        (0.1, 0.3, 0.6))

    // the initialization vector
    var pi  = DenseVector.zeros[Double](2)
    pi(0) = 0.6
    pi(1) = 0.4

    val o = Array("a", "b", "c")
    val obsToIdx = Map("a"->0, "b"->1, "c"->2)

    val beta = HMMHelpers.backward(o, A, B, pi, obsToIdx)

    System.out.println("beta matrix: ")
    System.out.println(beta)

    // calculate probability
    var p = 0.0
    for(i <- 0 until A.rows){
      p += pi(i) * B(i, obsToIdx.get(o(0)).get) * beta(1, i)
    }

    System.out.println("Probability: ", p)


  }

}
