package examples.basic

import breeze.linalg._

object MatrixVectorCreatorExe {

  def main(args: Array[String]):Unit={

    System.out.println("Creating DenseVector...")

    // create a vector of size 5
    val x = DenseVector.zeros[Double](5);

    // we can access the vector elements using
    // indices from 0 to c.length-1
    System.out.println("The 0-th vector element is="+x(0))

    // change the i-th element
    val i = 1
    x(i) = 5;

    System.out.println("The "+i+"-th vector element is="+x(i))
    System.out.println("Creating DenseMatrix...")

    // create matrix
    val m = DenseMatrix.zeros[Double](5, 5)

    System.out.println("Number of rows="+m.rows)
    System.out.println("Number of columns="+m.cols)

    // change the last row
    //m(4, ::) := DenseVector(1.,2.,3.,4.,5.).t
  }

}
