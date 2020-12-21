package examples.br

import breeze.linalg._

object exe1 {

  def main(args: Array[String]):Unit={

    // create a vector of doubles with 5 entries
    val x = DenseVector.zeros[Double](5);

    System.out.println("x(0) is " + x(0));
    System.out.println("Size of x is " + x.length);

    // create a DenseMatrix
    val matrix = DenseMatrix.zeros[Double](5, 6);

    System.out.println("Number of rows/columns " + matrix.rows + "/" + matrix.cols);

  }

}
