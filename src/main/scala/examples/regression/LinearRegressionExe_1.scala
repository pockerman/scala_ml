package examples.regression
import engine.maths.functions.Polynomial
import engine.maths.functions.MeanSquaredError
import engine.maths.functions.LossFunction
import engine.models.LinearRegression
import engine.utils.{LineDataSetLoader, SuperVisedTrainerInput, SupervisedTrainer}



object LinearRegressionExe_1 {

  def main(args: Array[String]):Unit={

    // read in the data set

    val x = LineDataSetLoader.lineSplit(0.0, 10.0, 100)

    System.out.println("Number of training examples: " + x.size)
    val coeffs = Array[Double](1.0, 2.0)
    val poly = new Polynomial(coeffs)
    val y = poly.values(x)

    val model = new LinearRegression(coeffs)
    val errMse = new MeanSquaredError;
    val loss = new LossFunction(model, errMse)

    val trainingInput = new SuperVisedTrainerInput
    trainingInput.maxNIterations = 10
    trainingInput.lossFunction = loss
    trainingInput.model = model

    // use gradient descent to minimize
    val trainer = new SupervisedTrainer(trainingInput)
    trainer.train()
    //System.out.println("Wins with change choice: " + change_wins)
    //System.out.println("Probability of winning with initial guess: " + first_choice_wins.asInstanceOf[Double] / N_ITRS)
    //System.out.println("Probability of winning with change guess: " + change_wins.asInstanceOf[Double] / N_ITRS)
  }

}
