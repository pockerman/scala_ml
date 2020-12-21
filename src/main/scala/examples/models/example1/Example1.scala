package examples.models.example1

import breeze.linalg.{DenseMatrix, DenseVector}
import engine.models.{HMMHelpers}

object Example1 {

  def main(args: Array[String]):Unit={

    // create transition probability matrix
    val A = DenseMatrix((0.5, 0.25, 0.25),
                        (0.1, 0.8, 0.1),
                        (0.3, 0.1, 0.6))

    // create the emission probability matrix
    val B = DenseMatrix((0.16, 0.26, 0.58),
                        (0.25, 0.28, 0.47),
                        (0.2, 0.1, 0.7))

    // the initialization vector
    var pi  = DenseVector.zeros[Double](3)
    pi(0) = 0.7
    pi(1) = 0.15
    pi(2) = 0.15

    val o = Array("a", "b", "a", "c", "b", "a")
    val obsToIdx = Map("a"->0, "b"->1, "c"->2)

    val alpha = HMMHelpers.forward(o, A, B, pi, obsToIdx)

    System.out.println("alpha matrix: ")
    System.out.println(alpha)

    // calculate probability
    var p = 0.0
    for(i <- 0 until A.rows){
      p += alpha(o.length-1, i)
    }

    System.out.println("Probability: ", p)

  }

}
