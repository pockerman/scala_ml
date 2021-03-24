package engine.utils

import breeze.linalg.DenseVector

object LineDataSetLoader {

  /**
   * Split the interval [start, end] into nPoints
   */
  def lineSplit(start: Double, end: Double, nPoints: Integer): DenseVector[Double] ={

    require(end > start)
    require(nPoints > 0)

    val points = DenseVector.zeros[Double](nPoints)

    val dx = (end - start)/nPoints

    for(i <- Range(0, nPoints)){
      points(i) = i*start*dx
    }

    points(points.size -1) = end
    points
  }

}
