package engine.utils

class IterationController(var tolerance: Double, var maxItrs: Int) {

  var residual = 1.0
  var currentItr = 0

  def reset: Unit = {
    residual = 1.0
    currentItr = 0
  }


  /**
   *
   */
  def continueItrs: Boolean = {

    if (residual < tolerance) {
      println(s"Converged!! Residual=${residual}. Tolerance=${tolerance}")
      false
    }
    else if(currentItr >= maxItrs){
      false
    }
    else{
      currentItr += 1
      true
    }
  }

}
