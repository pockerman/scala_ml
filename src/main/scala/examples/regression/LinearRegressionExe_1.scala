package examples.regression
import breeze.linalg.{DenseMatrix, DenseVector}
import engine.maths.functions.Polynomial
import engine.maths.functions.MeanSquaredError
import engine.maths.functions.LossFunction
import engine.models.LinearRegression
import engine.utils.{LineDataSetLoader, SuperVisedTrainerInput, SupervisedTrainer}




object LinearRegressionExe_1 {

  def main(args: Array[String]):Unit={

    // data set
    val x = LineDataSetLoader.lineSplit(0.0, 10.0, 100)

    System.out.println("Number of training examples: " + x.size)

    val coeffs = Array[Double](1.0, 2.0)
    val poly = new Polynomial(coeffs)
    val y = poly.values(x)



    // the feature matrix
    val featureMatrix = DenseMatrix.horzcat(DenseMatrix.ones[Double](x.size, 1), x.toDenseMatrix.t)

    // model
    val model = new LinearRegression(numFeatures = 1, useIntercept = true)

    model.train(x=featureMatrix,y=y)

    println(s"Polynomial coeffs ${poly.getCoeffsAsDenseVector}")
    println(s"Linear regressor coeffs ${model.getParameters}")
  }

}
