package engine.maths.functions

import breeze.linalg.DenseVector


/**
 * Represents a polynomial. A polynomial is modeled as
 * a list of monomials
 */
class Polynomial {


  var monomials: Array[Monomial] = null

  //def monomials: Array[Monomial] = monomials

  def value(x: DenseVector[Double]):Double= {

    var result: Double = 0.0

    /*for( idx <- 0 to x.size) {
      result += monomials[idx].value(x[idx]);
    }*/
    return result
  }

  /**
   * Set the monomials used by this polynomial
   * @param m
   */
  def setMonomials(m:Array[Monomial])=monomials=m


}
