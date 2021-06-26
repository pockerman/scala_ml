package engine.utils

import breeze.linalg.DenseVector
import breeze.stats.{mean, stddev}


object VectorUtils {

  /**
   *
   * @param v standardize the vector values
   * @return
   */
  def standardize(v: DenseVector[Double])= (v-mean(v))/stddev(v)

}
