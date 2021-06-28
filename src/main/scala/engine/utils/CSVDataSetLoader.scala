package engine.utils
import scala.reflect.ClassTag
import breeze.linalg.{DenseMatrix, DenseVector}
import engine.datasets.{MatrixVectorHolder, HeightsWeightsDataSet}

import scala.io.Source

object CSVDataSetLoader {

  val DataDirectory = "data/"

  def loadRepHeightWeights(): MatrixVectorHolder[Double, Double]={


    val fileName = "rep_height_weights.csv"
    val file = Source.fromFile(DataDirectory + fileName)

    val lines = file.getLines().toVector
    val matrix: DenseMatrix[Double] = DenseMatrix.zeros[Double](lines.length, 2)
    val vector: DenseVector[Double] = DenseVector.zeros[Double](matrix.rows)


    var row_counter: Int = 0
    for(line <- lines){
      val cols = line.split(",")
      matrix(row_counter, 0) = 1.0
      matrix(row_counter, 1) = cols(4).toDouble
      vector(row_counter) = cols(5).toDouble
      row_counter += 1
    }

    // finally  close the file
    file.close

    System.out.println("Matrix set rows ", matrix.rows)
    System.out.println("Vector set size ", vector.size)
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
