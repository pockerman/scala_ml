package engine.maths.functions

/**
 * Abstract base class to model scalar functions
 */
abstract class ScalarFunction {

  /**
   * The value of the Polynomial at x
   */
  def value(x: Double):Double;

  /**
   * Compute the gradient of the Polynomial
   */
  def getGrad(x: Double): Double = getGrad(x, 1);

  /**
   * Returns the order-th order gradient
   */
  def getGrad(x: Double, order: Integer): Double;

  /**
   * Returns the gradient with respect to the coefficient
   */
  def coeffGrad(x: Double, coeffIdx: Integer): Double = coeffGrad(x, coeffIdx, 1)

  /**
   * Returns the order-th coefficient gradient
   */
  def coeffGrad(x: Double, coeffIdx: Integer, order: Integer): Double;

}
