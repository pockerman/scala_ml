package engine.utils

import engine.maths.functions.LossFunction
import engine.models.SupervisedParametricModelBase
import engine.solvers.OptimizerBase

class SuperVisedTrainerInput {

  var maxNIterations: Integer = 10
  var tolerance: Double = 1.0e-4
  var model: SupervisedParametricModelBase = null
  var lossFunction: LossFunction = null
  var optimizer: OptimizerBase = null


}
