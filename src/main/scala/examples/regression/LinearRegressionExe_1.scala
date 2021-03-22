package examples.regression
import scala.util.Random
import engine.utils.CSVDataSetLoader


object LinearRegressionExe_1 {

  def main(args: Array[String]):Unit={

    // read in the data set
    val data = new CSVDataSetLoader().loadRepHeightWeights

    System.out.println("Number of training examples: " + data.matrix.rows)
    //System.out.println("Wins with change choice: " + change_wins)
    //System.out.println("Probability of winning with initial guess: " + first_choice_wins.asInstanceOf[Double] / N_ITRS)
    //System.out.println("Probability of winning with change guess: " + change_wins.asInstanceOf[Double] / N_ITRS)
  }

}
