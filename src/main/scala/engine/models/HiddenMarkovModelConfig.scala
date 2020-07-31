package engine.models

/**
 *
 * Configuration class for Hidden Markov Model
 * @param numObs
 * @param numStates
 * @param numSymbols
 * @param maxItrs
 * @param eps
 */
class HiddenMarkovModelConfig(val numObs: Int, val numStates: Int, val numSymbols: Int,
                              val maxItrs: Int, val eps: Double) {


  val nOns = numObs;
  val nStates = numStates;
  val nSymbols = numSymbols;
  val mItrs = maxItrs;
  val tol = eps;

}
