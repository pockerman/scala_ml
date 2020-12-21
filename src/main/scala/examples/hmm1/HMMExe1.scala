package examples.hmm1
import java.io.File

import breeze.linalg.{DenseMatrix, DenseVector}
import engine.models.{HiddenMarkovModel, HiddenMarkovModelConfig}


object HMMExe1 {

  def main(args: Array[String]):Unit={

    // create transition probability matrix
    val A = DenseMatrix((0.7,0.3), (0.4,0.6))

    // create the emission probability matrix
    val B = DenseMatrix((0.5, 0.4, 0.1), (0.1, 0.3, 0.6))

    // the initialization vector
    var pi  = DenseVector.zeros[Double](2)
    pi(0) = 0.6
    pi(1) = 0.4

    val o = Array("normal", "cold", "dizzy")
    val states = Array("Healthy", "Fever")
    var hmmConfig = new HiddenMarkovModelConfig(A, B, pi, states)
    var hmm = new HiddenMarkovModel(hmmConfig)



  }
}
