package engine.maths.functions

class MeanSquaredError extends ErrorFunction {

  def compute(x: Double, y: Double):Double = (x-y)*(x-y);

}
