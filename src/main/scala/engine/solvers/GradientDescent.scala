package engine.solvers

import breeze.linalg.DenseVector
import engine.solvers.GradientDescentInput


class GradientDescent() extends OptimizerBase {

  /**
   * Returns the model parameters
   */
  def getParameters: DenseVector[Double];

  /**
   * perform on step of the optimizer
   */
  def step();

}
