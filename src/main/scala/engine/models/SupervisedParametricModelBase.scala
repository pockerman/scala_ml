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

}
