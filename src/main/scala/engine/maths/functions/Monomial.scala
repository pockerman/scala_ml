package engine.maths.functions

/**
 * Models a scalar Monomial
 */
class Monomial(degree: Int, factor: Double) {

  val d = degree;
  val f = factor;

  /**
   * Default constructor
   */
  def this() {
    this(1, 1.0)
  }

  /**
   * compute the value of the monomial on the given point
   * @param x
   * @return
   */
  def value(x: Double):Double= f*scala.math.pow(x,d)

  /**
   * Returns the degree of the monomial
   * @return
   */
  def getDegree():Int= d

  /**
   * Returns the factor of the monomial
   * @return
   */
  def getFactor():Double = f

}
