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
   * The value of the Polynomial at x
   */
  def value(x: Double):Double= {

    var result: Double = 0.0

    /*for( idx <- 0 to x.size) {
      result += monomials[idx].value(x[idx]);
    }*/
    return result
  }


  private def createMonomials(coeffs: Array[Double]): Array[Monomial] = {

    //
    var mon: Array[Monomial] = new Array[Monomial](coeffs.length)

    var degree: Int = 0
    for( c <- coeffs) {
      mon.update(degree, new Monomial(degree, c))
      degree += 1
    }
    mon
  }


}
