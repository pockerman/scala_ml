package engine.utils
import scala.reflect.ClassTag
import breeze.linalg.{DenseMatrix, DenseVector}
import engine.datasets.{MatrixVectorHolder, HeightsWeightsDataSet}

import scala.io.Source

class CSVDataSetLoader {

  val DataDirectory = "data/"

  def loadRepHeightWeights(): MatrixVectorHolder[Double, Double]={


    val fileName = "rep_height_weights.csv"
    val file = Source.fromFile(DataDirectory + fileName)

    val matrix = DenseMatrix.zeros[Double](file.getLines().length, 2)
    val vector = DenseVector.zeros[Double](file.getLines().length)

    val row_counter = 0
    for(line <- file.getLines){
      val cols = line.split(",").map(_.trim)

      matrix(row_counter, 0) = 1.0
      matrix(row_counter, 1) = cols(4).toDouble
      vector(row_counter) = cols(5).toDouble
    }

    // finally  close the file
    file.close
    new MatrixVectorHolder[Double, Double](matrix, vector);
  }

  def loadRepHeightWeightsFullData():HeightsWeightsDataSet={


    val file = Source.fromFile("data/rep_height_weights.csv")
    val lines = file.getLines.toVector
    val splitLines = lines.map { _.split(',') }

    def fromList[T:ClassTag](index:Int, converter:(String => T)):DenseVector[T] =
    DenseVector.tabulate(lines.size) { irow => converter(splitLines(irow)(index)) }

    val genders = fromList(1, elem => elem.replace("\"", "").head)
    val weights = fromList(2, elem => elem.toDouble)
    val heights = fromList(3, elem => elem.toDouble)
    val reportedWeights = fromList(4, elem => elem.toDouble)
    val reportedHeights = fromList(5, elem => elem.toDouble)

    new HeightsWeightsDataSet(weights, heights, reportedWeights, reportedHeights, genders)
  }

}
