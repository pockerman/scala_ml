package engine.datasets

import breeze.linalg.{DenseMatrix, DenseVector}

class MatrixVectorHolder[V, N](mat: DenseMatrix[V], vec: DenseVector[N] ) {

  var matrix = mat;
  var vector = vec;

}
