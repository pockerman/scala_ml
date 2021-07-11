package engine.rl

import engine.utils.{IterationController, IterationCtrlResult}

abstract class AlgorithmBase(var environment: Any, nMaxItrs: Int, tolerance: Double) {

  // require that the environment is not null
  require(environment != null)

  /**
   *
   */
  var state: Any = null

  /**
   *
   */
  var itrCtrl = new IterationController(maxItrs = nMaxItrs, tolerance = tolerance)

  /**
   *
   * @return
   */
  def itrCtrl: IterationController = this.itrCtrl


  /**
   *
   * @param self
   * @param **
   */
  def train: IterationCtrlResult= {

    itrCtrlRsult = new IterationCtrlResult(tolerance=itrCtrl.tolerance,
                                             residual=itrCtrl.residual,
                                             nItrs = 0, nMaxItrs = itrCtrl.maxItrs,
                                             nProcs = 1)

    this.actionsBeforeTrainingIterations

    while(itrCtrl.continueItrs)
      this.step

    this.actionsAfterTrainingIterations

    // update the control result
    itrCtrlRsult.nItrs = itrCtrl.currentItr
    itrCtrlRsult.residual = itrCtrl.residual

    itrCtrlRsult
  }

  /**
   * Any actions before starting the iterations
   */
  def actionsBeforeTrainingIterations: Unit = {
    itrCtrl.reset
  }

  /**
   *
   */
  def step: Unit

  /**
   *
   */
  def actionsAfterTrainingIterations: Unit


}
