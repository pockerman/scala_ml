package engine.models

import breeze.linalg._
import engine.maths.functions.{Polynomial, ScalarFunction}

/**
 *
 * LinearRegression class. Implements linear regression on the
 */
class LinearRegression(coeffs: Array[Double]) extends SupervisedParametricModelBase /*with ScalarFunction*/ {

  /**
   * The hypothesis function
   */
  var polynomial: Polynomial = _createModelFromCoeffs(coeffs);

  /**
   * Build from the DenseVector
   */
  def this(x: DenseVector[Double]){
    this(x.toArray)
  }

  /**
   * Build from the polynomial
   */
  def this(x: Polynomial){
    this(x.getCoeffsAsDenseVector)
  }

  /**
   * Returns the model parameters
   */
  override  def getParameters: DenseVector[Double] = polynomial.getCoeffsAsDenseVector;

  /**
   * Return the value of the model at the given point
   */
  def value(x: Double): Double  = polynomial.value(x)

  /**
   * Get the order-th order gradient
   */
  def getGrad(x: Double, order: Integer): Double = polynomial.getGrad(x, order)

  /**
   * Returns the order-th derivative of the function for
   * the coeffIdx-th coefficient at point x
   */
  def coeffGrad(x: Double, coeffIdx: Integer, order: Integer): Double = polynomial.coeffGrad(x, coeffIdx, order)

  /**
   * Predict the regression value for the point
   */
  //def predict(x: DenseVector[Double]):Double=0.0

  /**
   * Build the underlying model form the given coefficients
   */
  def _createModelFromCoeffs(coeffs: Array[Double]): Polynomial ={
    require(coeffs.length > 0)
    new Polynomial(coeffs)
  }

}
