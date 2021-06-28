package engine.datasets

import breeze.linalg.DenseVector

/**
 * Wrapper for the heights weights dataset
 * @param weights
 * @param heights
 * @param reportedWeights
 * @param reportedHeights
 * @param genders
 */
class HeightsWeightsDataSet (val weights:DenseVector[Double], val heights:DenseVector[Double],
                             val reportedWeights:DenseVector[Double],
                             val reportedHeights:DenseVector[Double],
                             val genders:DenseVector[Char]){

  val npoints: Int = heights.length;

  require(weights.length == npoints)
  require(reportedWeights.length == npoints)
  require(genders.length == npoints)
  require(reportedHeights.length == npoints)


  val filename = "data/rep_height_weights.csv"



}
