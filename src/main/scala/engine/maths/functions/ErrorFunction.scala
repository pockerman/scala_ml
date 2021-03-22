package engine.maths.functions

import breeze.linalg._

abstract class ErrorFunction {

  /**
   * Compute the error between x, y
   */
  def compute(x: Double, y: Double):Double;

  /**
   * Compute the error between the x, y vectors
   */
  def compute(x: DenseVector[Double], y: DenseVector[Double]):Double;
  
}
