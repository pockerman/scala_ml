package engine.utils


class SupervisedTrainer(input: SuperVisedTrainerInput) {

  val inPut = input

  def train(): Uint = {

    var res: Double = Double.MaxValue
    for(itr <- Range(1, inPut.maxNIterations)){
      System.out.println("At iteration ", itr, " residual="res)
    }
  }

}
