package engine.maths.functions

import breeze.linalg.DenseVector
import engine.models.SupervisedParametricModelBase
import engine.maths.functions.ErrorFunction

/**
 * A wrapper class for ErrorFunction and
 * Function objects
 */
class LossFunction(hypothesisFunction: SupervisedParametricModelBase, errorFunction: ErrorFunction) {

  /**
   * The monomials modeling the Polynomial
   */
  var hFunction = hypothesisFunction

  /**
   * The maximum degree of the polynomial
   */
  var errFunction = errorFunction

  /**
   * Compute the value of the Loss function
   */
  def value(y: Double, x: Double): Double = {

    var hval = hFunction.value(x)
    var vecY = DenseVector[Double](y);
    var vecX = DenseVector[Double](hval);
    var result = errFunction.compute(vecY, vecX)
    result;
  }

  /**
   * Compute the total error between
   * the two inputs
   * @param x
   * @param y
   * @return
   */
  def totalError(x: DenseVector[Double], y: DenseVector[Double]): Double ={

    require(x.size == y.size)
    var err = 0.0


  }


  def coeffGrad(y: Double, x: Double): Double={
    var result = 0.0;
    result
  }

}
