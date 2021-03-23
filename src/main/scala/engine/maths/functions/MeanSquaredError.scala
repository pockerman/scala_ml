package engine.maths.functions

import breeze.linalg.DenseVector

class MeanSquaredError extends ErrorFunction {

  /**
   *
   * @param x
   * @param y
   * @return
   */
  def value(x: Double, y: Double):Double = (x-y)*(x-y);

  /**
   * Compute the error between the x, y vectors
   */
  override def compute(x: DenseVector[Double], y: DenseVector[Double]):Double = {

    require(x.size == y.size);
    var result = 0.0

    for( i <- 0 until x.size ){
      result += value(x(i), y(i))
    }

    result /= x.size
    result
  }

}
