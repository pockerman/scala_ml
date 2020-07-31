package engine.models
import breeze.linalg._

/**
 * Hidden Markov Model
 * @param A transition probability matrix
 * @param B emission probability matrix
 * @param pi initialization vector
 */
class HiddenMarkovModel(var A: DenseMatrix[Double], var B: DenseMatrix[Double], var pi: DenseVector[Double]  ) {

  var A_ = A;
  var B_ = B;
  var pi_ = pi;


  def viterbiPath(val o: DenseVector[Int]): Uint{

  }

  def viterbiPath(val o: DenseVector[Double]): Uint{

  }

}
