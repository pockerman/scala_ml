package engine.models

import breeze.linalg.{DenseMatrix, DenseVector}

/**
 *
 * Configuration class for Hidden Markov Model
 *
 * @param numObs
 * @param numStates
 * @param numSymbols
 * @param maxItrs
 * @param eps
 */
class HiddenMarkovModelConfig(var trans: DenseMatrix[Double], var emissions: DenseMatrix[Double],
                              var initStates: DenseVector[Double], var states: Array[String]) {


  var A = trans;
  var B = emissions;
  var pi = initStates;
  var stateNames = states;

}
