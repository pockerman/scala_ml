package engine.models

import breeze.linalg._
import engine.maths.functions.Polynomial

/**
 *
 * LinearRegression class. Implements linear regression on the
 */

class LinearRegression {


  var polynomial: Polynomial = null;

  /**
   * Fit the model with the given data
   * @param x
   * @param y
   */
  def fit(x: DenseMatrix[Double], y: DenseVector[Double]):Unit={

  }

  /**
   * Predict the regression value for the point
   * @param x
   * @return
   */
  def predict(x: DenseVector[Double]):Double=0.0

}
