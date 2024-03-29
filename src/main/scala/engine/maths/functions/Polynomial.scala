package engine.maths.functions

import breeze.linalg.DenseVector


/**
 * Represents a polynomial. A polynomial is modeled as
 * a list of monomials
 */
class Polynomial(coeffs: Array[Double]) extends ScalarFunction {

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
   *
   * @param x
   * @return
   */
  def value(x: DenseVector[Double]):Double= {

    require(x.size == this.monomials.size)
    var result = 0.0
    for(mon <- 0 until monomials.size) {
      result += monomials(mon).value(x(mon));
    }
    result
  }


  /**
   * Compute the values of of the Polynomial at the given point
   */
  def values(x: DenseVector[Double]): DenseVector[Double] = {
    val result = DenseVector.zeros[Double](x.size)
    for(i <- Range(0, x.size)){
      result(i) = value(x(i))
    }
    result
  }

  /**
   * Compute the gradient of the Polynomial
   */
  override def getGrad(x: Double): Double= {

    var result: Double = 0.0
    for(mon <-  monomials) {
      result += mon.getGrad(x);
    }
    result
  }

  /**
   * Returns the order-th order gradient
   */
  override def getGrad(x: Double, order: Integer): Double= {

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
   * Returns the order-th coefficient gradient
   */
  override  def coeffGrad(x: Double, coeffIdx: Integer, order: Integer): Double = {

     val result = monomials.apply(coeffIdx).coeffGrad(x, order)
     result
  }

  /**
   * Update the coefficients
   */
  def updateCoeff(coeffs: DenseVector[Double]): Unit = {

    require(coeffs.size == monomials.length)

    for(m <-0 until monomials.size){
      monomials(m).setFactor(coeffs(m))
    }

  }

  /**
   * Returns the coefficients as a DenseVector
   */
  def getCoeffsAsDenseVector: DenseVector[Double] = {

    val result = DenseVector.zeros[Double](monomials.length);

    var i = 0
    for(mon <- monomials){
       result(i) = mon.getFactor
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
