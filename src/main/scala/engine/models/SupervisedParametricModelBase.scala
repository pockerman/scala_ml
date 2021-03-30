package engine.models

import breeze.linalg.DenseVector

/**
 * Abstract class for supervised model
 */
abstract class SupervisedParametricModelBase {

  /**
   * Returns the model parameters
   */
  def getParameters: DenseVector[Double];

  /**
   * Update the parameters of the model
   */
  def updateParameters(params: DenseVector[Double]);

  /**
   * The value of the Polynomial at x
   */
  def value(x: Double):Double;

  def values(x: DenseVector[Double]): DenseVector[Double] ={
    val result = DenseVector.zeros[Double](x.size)
    for(i <- Range(0, x.size)){
      result(i) = value(x(i))
    }
    result
  }

}
