package engine.models

import breeze.linalg._

import breeze.optimize.{DiffFunction, minimize}
import engine.maths.functions.{Polynomial, ScalarFunction}

/**
 *
 */
object LinearRegression
{
  def L(x: DenseMatrix[Double], y: DenseVector[Double], parameters: DenseVector[Double]): Double = {
    val yHat = x * parameters
    var value = 0.0
    for( i <- 0 until yHat.size){
      val diff =  y(i) - yHat(i)
      value += diff * diff
    }
    value
  }


  def gradL(x: DenseMatrix[Double], y: DenseVector[Double], parameters: DenseVector[Double]): DenseVector[Double]={


    val yHat = x * parameters

    // we have as many components as columns
    val gradients = DenseVector.zeros[Double](x.cols)

    for( i <- 0 until yHat.size){
      var diff =  y(i) - yHat(i)

      for( c <- 0 until gradients.size){
         diff *= x(i, c)
         gradients(c) += diff
      }
    }
    -2.0 * gradients
  }
}

/**
 *
 * LinearRegression class. Implements linear regression on the
 */
class LinearRegression extends SupervisedParametricModelBase{

  /**
   * The hypothesis function
   */
  var polynomial: Polynomial = null  ;

  def this(coeffs: Array[Double]){
    this()
    this.polynomial = _createModelFromCoeffs(coeffs)
  }

  /**
   * Build from the DenseVector
   */
  def this(x: DenseVector[Double]){
    this(x.toArray)
  }

  /**
   * Build from the polynomial
   */
  def this(x: Polynomial){
    this(x.getCoeffsAsDenseVector)
  }

  def this(numFeatures: Int, useIntercept: Boolean){
    this()

    val totalNumFeatures = if(useIntercept) numFeatures +1 else numFeatures;
    val coeffs = DenseVector.zeros[Double](totalNumFeatures)
    this.polynomial = _createModelFromCoeffs(coeffs.toArray)
  }


  /**
   * Train the model on the training set
   * @param x
   * @param y
   */
  def train(x: DenseMatrix[Double], y: DenseVector[Double])={

    // set up the optimization
    val f = new DiffFunction[DenseVector[Double]] {
      def calculate(parameters: DenseVector[Double]) = (LinearRegression.L(x, y, parameters=parameters),
        LinearRegression.gradL(x, y, parameters = parameters))
    }


    val parameters = minimize(f, this.polynomial.getCoeffsAsDenseVector)
    polynomial.updateCoeff(coeffs = parameters)
  }

  /**
   * Returns the model parameters
   */
  override  def getParameters: DenseVector[Double] = polynomial.getCoeffsAsDenseVector;

  /**
   * Return the value of the model at the given point
   */
  def value(x: Double): Double  = polynomial.value(x)

  /**
   * Get the order-th order gradient
   */
  def getGrad(x: Double, order: Integer): Double = polynomial.getGrad(x, order)

  /**
   * Returns the order-th derivative of the function for
   * the coeffIdx-th coefficient at point x
   */
  def coeffGrad(x: Double, coeffIdx: Integer, order: Integer): Double = polynomial.coeffGrad(x, coeffIdx, order)

  /**
   * Predict the regression value for the point
   */
  def predict(x: DenseVector[Double]): Double = {
    this.polynomial.value(x)
  }

  /**
   * Build the underlying model form the given coefficients
   */
  def _createModelFromCoeffs(coeffs: Array[Double]): Polynomial ={
    require(coeffs.length > 0)
    new Polynomial(coeffs)
  }

  /**
   * Update the parameters of the model
   */
  override def updateParameters(params: DenseVector[Double]): Unit = {}
}
