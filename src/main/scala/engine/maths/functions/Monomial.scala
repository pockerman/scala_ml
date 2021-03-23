package engine.maths.functions

/**
 * Models a scalar Monomial
 */
class Monomial(degree: Int, factor: Double) extends ScalarFunction {

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
   */
  def getFactor():Double = f

  /**
   * Compute the gradient of the monomial
   */
  def getGrad(x: Double): Double= getGrad(x, 1)//d*f*scala.math.pow(x, d-1)

  /**
   * Return the order-th order gradient
   */
  def getGrad(x: Double, order: Int): Double = {

    if(order > degree){
      return 0.0
    }

    var newOrder = order;
    var newCoeff = order*factor;
    for( o <- 0 to order){
      newCoeff *= order - 1
      newOrder -= 1
    }

    new Monomial(newOrder, newCoeff).value(x)
  }

  /**
   * Returns the gradient with respect to the coefficient
   */
  def coeffGrad(x: Double): Double = f*scala.math.pow(x, d)

  /**
   * Returns the order-th coefficient gradient
   */
  def coeffGrad(x: Double, order: Integer): Double = {

      if ( order > 1 ) 0.0
      else coeffGrad(x)
  }

}
