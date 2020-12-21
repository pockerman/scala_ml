package engine.utils

import breeze.linalg.DenseVector

import scala.io.Source

class CSVDataSetLoader {

  val DataDirectory = "data/"

  def loadRepHeightWeights(): MatrixVectorHolder[Double, Double]={


    val fileName = "rep_height_weights.csv"
    val file = Source.fromFile(DataDirectory + fileName)
    val lines = file.getLines.toVector
    val splitLines = lines.map { _.split(',') }

    def fromList[T:ClassTag](index:Int, converter:(String => T)):DenseVector[T] =
      DenseVector.tabulate(lines.size) { irow => converter(splitLines(irow)(index)) }

    val genders = fromList(1, elem => elem.replace("\"", "").head)
    val weights = fromList(2, elem => elem.toDouble)
    val heights = fromList(3, elem => elem.toDouble)
    val reportedWeights = fromList(4, elem => elem.toDouble)
    val reportedHeights = fromList(5, elem => elem.toDouble)

    new MatrixVectorHolder[Double, Double](weights, heights);
  }

}
