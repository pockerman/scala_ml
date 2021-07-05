package engine.utils

class IterationCtrlResult(tolerance: Double, residual: Double,
                          nItrs: Int, nMaxItrs: Int, nProcs: Int = 1) {


  def converged: Boolean = residual < tolerance

}
