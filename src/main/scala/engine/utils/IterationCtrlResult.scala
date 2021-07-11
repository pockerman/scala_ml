package engine.utils

class IterationCtrlResult(val tolerance: Double, var residual: Double,
                          var nItrs: Int, val nMaxItrs: Int, val nProcs: Int = 1) {


  def converged: Boolean = residual < tolerance

}
