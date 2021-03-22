package engine.maths.functions

import breeze.linalg.DenseVector


/**
 * Represents a polynomial. A polynomial is modeled as
 * a list of monomials
 */
class Polynomial(coeffs: Array[Double]) {

  /**
   * The monomials modeling the Polynomial
   */
  var monomials = createMonomials(coeffs)

  /**
   * The maximum degree of the polynomial
   */
  var maxDegree = 0

  /**
   * The value of the Polynomial at x
   */
  def value(x: Double):Double= {

    var result: Double = 0.0
    for(mon <-  monomials) {
      result += mon.value(x);
    }
    result
  }

  /**
   * Compute the gradient of the Polynomial
   */
  def getGrad(x: Double): Double= {

    var result: Double = 0.0
    for(mon <-  monomials) {
      result += mon.getGrad(x);
    }
    result
  }

  /**
   * Returns the order-th order gradient
   */
  def getGrad(x: Double, order: Integer): Double= {

    if (order > maxDegree){
      return 0.0
    }

    var result = 0.0
    for(mon <- monomials){
      result += mon.getGrad(x, order)
    }
    result
  }

  /**
   * Returns the gradient with respect to the coefficient
   */
  def coeffGrad(x: Double, coeffIdx: Integer): Double = coeffGrad(x, coeffIdx, 1)

  /**
   * Returns the order-th coefficient gradient
   */
  def coeffGrad(x: Double, coeffIdx: Integer, order: Integer): Double = {

     val result = monomials.apply(coeffIdx).coeffGrad(x, order)
     result
  }

  /**
   * Returns the coefficients as a DenseVector
   */
  def getCoeffsAsDenseVector(): DenseVector[Double] = {

    val result = DenseVector.zeros[Double](monomials.length);

    var i = 0
    for(mon <- monomials){
       result(i) = mon.getFactor()
       i += 1
    }

    result
  }

  /**
   * Create monomials from the Array of coefficients
   */
  private def createMonomials(coeffs: Array[Double]): Array[Monomial] = {

    //
    val mon: Array[Monomial] = new Array[Monomial](coeffs.length)

    var degree: Int = 0
    for( c <- coeffs) {
      mon.update(degree, new Monomial(degree, c))
      maxDegree = degree
      degree += 1
    }

    mon
  }


}
