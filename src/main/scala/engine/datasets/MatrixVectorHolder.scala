package engine.datasets

import breeze.linalg.{DenseMatrix, DenseVector}

/**
 * Matrix-vector holder
 * @param mat
 * @param vec
 * @tparam V
 * @tparam N
 */
class MatrixVectorHolder[V, N](mat: DenseMatrix[V], vec: DenseVector[N] ) {

  require(mat.rows == vec.length);

  var matrix = mat;
  var vector = vec;

}
