package examples.regression
import scala.util.Random


object Example1Regression {

  def main(args: Array[String]):Unit={

    val N_ITRS = 1000
    val doors = new Array[String](3)
    doors(0) = "A"
    doors(1) = "B"

    var first_choice_wins = 0
    var change_wins = 0

    val rand = new Random()

    var itr = 0
    while ( {itr < N_ITRS }) {
      val winner_idx = rand.nextInt(doors.length)
      val pick_idx = rand.nextInt(doors.length)
      System.out.println("You chose: " + doors(pick_idx) + " Winner door: " + doors(winner_idx))
      if (winner_idx == pick_idx) {
        first_choice_wins += 1
      }
      else {
        change_wins += 1
      }

      itr += 1
    }

    System.out.println("Wins with original choice: " + first_choice_wins)
    System.out.println("Wins with change choice: " + change_wins)
    System.out.println("Probability of winning with initial guess: " + first_choice_wins.asInstanceOf[Double] / N_ITRS)
    System.out.println("Probability of winning with change guess: " + change_wins.asInstanceOf[Double] / N_ITRS)
  }

}
