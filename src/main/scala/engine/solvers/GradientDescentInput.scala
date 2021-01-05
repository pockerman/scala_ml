package engine.solvers


/**
 * wraps the input required by the
 */
class GradientDescentInput(rate: Double, iterations: Int, tolerance: Double, itrsInfoFlag: Boolean) {

  /**
   * The learning rate
   */
  val eta: Double = rate;

  /**
   * Number of iterations
   */
  val nItrs: Int = iterations;

  /**
   * Exit tolerance
   */
  val tol: Double = tolerance;

  /**
   * Flag indicating if iteration info is displayed
   */
  val showItrInfo: Boolean = itrsInfoFlag;

}
