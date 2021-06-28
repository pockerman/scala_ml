package engine.models

import breeze.linalg.{DenseMatrix, DenseVector}
import breeze.linalg._
import breeze.numerics.{exp, log1p, sigmoid}
import breeze.optimize.{DiffFunction, minimize}

object LogisticRegression{

  def L(x: DenseMatrix[Double], y: DenseVector[Double], parameters: DenseVector[Double]): Double = {
    val xBeta = x * parameters
    val expXBeta = exp(xBeta)
    val targets_time = y *:* xBeta
    -sum(targets_time - log1p(expXBeta))
  }


  def gradL(x: DenseMatrix[Double], y: DenseVector[Double], parameters: DenseVector[Double]): DenseVector[Double]={
    val xBeta = x * parameters
    val probs = sigmoid(xBeta)
    x.t * (probs - y)
  }

}


/**
 * LogisticRegression class. Implements the logistic regression
 * classifier
 */
class LogisticRegression {

  /**
   * The model parameters
   */
  var parameters: DenseVector[Double] = null

  /**
   * Flag indicating if the interception term is used
   */
  var useIntecept: Boolean=true;

  /**
   *
   * @param numFeatures
   * @param useIntercept
   */
  def this(numFeatures: Int, useIntercept: Boolean=true){
    this()
    init(numFeatures = numFeatures, useIntercept = useIntercept)
  }

  /**
   *
   * @param numFeatures
   * @param useIntercept
   */
  def init(numFeatures: Int, useIntercept: Boolean=true): Unit = {

    val totalFeatures = if(useIntercept) numFeatures + 1 else numFeatures
    this.parameters = DenseVector.zeros[Double](totalFeatures)
    this.useIntecept = useIntercept
  }

  /**
   * Train the model on the training set
   * @param x
   * @param y
   */
  def train(x: DenseMatrix[Double], y: DenseVector[Double])={

    // set up the optimization
    val f = new DiffFunction[DenseVector[Double]] {
      def calculate(parameters: DenseVector[Double]) = (LogisticRegression.L(x, y, parameters=parameters),
        LogisticRegression.gradL(x, y, parameters = parameters))
    }

    this.parameters = minimize(f, this.parameters)
  }

  /**
   * @param x
   * @return
   */
  def predict(x: DenseVector[Double]): Double = {

    require(parameters != null)

    if(!useIntecept){
      require(x.size == parameters.size)
      sum(parameters * x)
    }
    else{
      require(x.size == parameters.size -1 )
      sum(parameters.slice(0, x.size) * x) + parameters(0)
    }
  }

}
