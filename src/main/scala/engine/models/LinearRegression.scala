package engine.models

import breeze.linalg._
import engine.maths.functions.Polynomial

/**
 *
 * LinearRegression class. Implements linear regression on the
 */
class LinearRegression(coeffs: Array[Double]) extends SupervisedParametricModelBase {

  /**
   * The hypothesis function
   */
  var polynomial: Polynomial = _createModelFromCoeffs(coeffs);

  /**
   * Build from the polynomial
   */
  def this(x: Polynomial){
    this(x.getCoeffsAsDenseVector)
  }

  /**
   * Build from the DenseVector
   */
  def this(x: DenseVector[Double]){
    this(x.toArray)
  }

  /**
   * Returns the model parameters
   */
  override  def getParameters: DenseVector[Double] = polynomial.getCoeffsAsDenseVector;

  /**
   * Predict the regression value for the point
   */
  def predict(x: DenseVector[Double]):Double=0.0

  /**
   * Build the underlying model form the given coefficients
   */
  def _createModelFromCoeffs(coeffs: Array[Double]): Polynomial ={
    require(coeffs.length > 0)
    new Polynomial(coeffs)
  }

}
