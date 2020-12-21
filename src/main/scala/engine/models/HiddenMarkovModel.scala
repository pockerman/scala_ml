package engine.models
import breeze.linalg._

/**
 * Hidden Markov Model
 * @param A transition probability matrix
 * @param B emission probability matrix
 * @param pi initialization vector
 */
class HiddenMarkovModel(var configHmm: HiddenMarkovModelConfig  ) {

  var config = configHmm;


  //def viterbiPath(val o: DenseVector[Int]): Uint{

  //}

  def viterbiPath(o: DenseVector[Double]): Double = {

    return 0.0
  }

  def viterbiPath(o: Array[String]): Double = {

    var delta = DenseMatrix.zeros[Double](o.length, config.A.rows)

    // initialize delta
    for(r <- 0 to config.A.rows){
      delta(0, r) = config.pi(r)*config.B(r, r)
    }
    return 0.0
  }

}
