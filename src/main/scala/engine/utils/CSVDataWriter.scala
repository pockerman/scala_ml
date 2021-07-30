package engine.utils

import java.io.{BufferedWriter, FileWriter}
import scala.jdk.CollectionConverters
import scala.collection.mutable.ListBuffer
import au.com.bytecode.opencsv.CSVWriter

class CSVDataWriter(val filename: String, val schema: Array[String], val delimiter: Char=',') {

  val fileHandler  = new BufferedWriter(new FileWriter(filename))
  val csvWriter = new CSVWriter(fileHandler)


  def writeRows(rows: ListBuffer[Array[String]]): Unit = {

      require(rows(0).length == schema.length)

      csvWriter.writeNext(schema)
      val data = rows.toList
      for( i <- 0 until data.length) {
        csvWriter.writeNext(data(i))
      }


      close
  }

  /**
   * Close the stream
   */
  def close: Unit = {
    fileHandler.close()
  }

}
