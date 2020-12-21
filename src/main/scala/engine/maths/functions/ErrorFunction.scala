package engine.maths.functions

import breeze.linalg._

abstract class ErrorFunction {

  def compute(x: Double, y: Double):Double;
  
}
