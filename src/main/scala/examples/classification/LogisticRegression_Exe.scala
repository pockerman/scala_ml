package examples.classification

import breeze.linalg._
import breeze.numerics._
import breeze.optimize._
import breeze.stats._
import engine.models.LogisticRegression
import engine.utils.{CSVDataSetLoader, VectorUtils}
import spire.algebra.NormedVectorSpace.InnerProductSpaceIsNormedVectorSpace
import spire.implicits.rightModuleOps

object LogisticRegression_Exe extends App{

  println(s"Starting application: ${LogisticRegression_Exe.getClass.getName}")

    // load the data
  val data = CSVDataSetLoader.loadRepHeightWeightsFullData
  val recaledHeights = VectorUtils.standardize(data.heights);
  val rescaledWeights = VectorUtils.standardize(data.weights);
  val rescaledHeightsAsMatrix = recaledHeights.toDenseMatrix.t
  val rescaledWeightsAsMatrix = rescaledWeights.toDenseMatrix.t

  val featureMatrix = DenseMatrix.horzcat(DenseMatrix.ones[Double](rescaledHeightsAsMatrix.rows, 1),
    rescaledHeightsAsMatrix, rescaledWeightsAsMatrix)

  println(s"Feature matrix shape (${featureMatrix.rows}, ${featureMatrix.cols})")

  val targets = data.genders.values.map{gender => if(gender == 'M') 1.0 else 0.0}

  println(s"Targets vector shape (${targets.size}, )")

  // logistic regression model
  val lr = new LogisticRegression;

  // initialize the model
  lr.init(numFeatures=2)
  lr.train(x=featureMatrix, y=targets)

  val optimalParams = lr.parameters
  println(s"Optimal parameters ${optimalParams}")
  println("Done...")

}
