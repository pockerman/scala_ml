package engine.solvers

import breeze.linalg.DenseVector

abstract class OptimizerBase {

  /**
   * Returns the model parameters
   */
  def getParameters: DenseVector[Double];

  /**
   * perform on step of the optimizer
   */
  def step();

}
