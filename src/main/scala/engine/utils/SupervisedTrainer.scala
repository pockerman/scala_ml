package engine.utils

import breeze.linalg.DenseVector


class SupervisedTrainer(input: SuperVisedTrainerInput) {

  val inPut = input

  def train(x: DenseVector[Double], y: DenseVector[Double]) = {

    var res: Double = Double.MaxValue
    for(itr <- Range(1, inPut.maxNIterations)){
      System.out.println("At iteration " + itr + " residual="+res)

       // get the model predictions
       var predict = inPut.model.values(x)

      // compute the error
      var error = inPut.lossFunction.totalError(predict, y)

      // update the model coeffs
      inPut.optimizer.step()
      inPut.model.updateParameters(input.optimizer.getParameters)

      // update
      res = Math.abs(error - res)



    }
  }



}
