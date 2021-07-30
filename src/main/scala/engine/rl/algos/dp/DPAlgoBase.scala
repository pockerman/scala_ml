package engine.rl.algos.dp

import breeze.linalg.DenseVector
import engine.rl.AlgorithmBase
import engine.rl.utils.Policy
import engine.utils.CSVDataWriter
import engine.worlds.DiscreteEnvironment

import scala.collection.mutable.ListBuffer

abstract class DPAlgoBase(environment: DiscreteEnvironment,
                 nMaxItrs: Int,
                 tolerance: Double,
                 var policy: Policy[Int, Int],
                 var gamma: Double) extends AlgorithmBase(environment = environment, nMaxItrs = nMaxItrs,
                                                          tolerance = tolerance){

  /**
   * The value function table
   */
  var v: DenseVector[Double] = null

  /**
   * Specify the necessary actions to execute before
   * starting the training iterations
   */
  override def actionsBeforeTrainingIterations: Unit = {
    super.actionsBeforeTrainingIterations
    this.v = DenseVector.zeros[Double](this.environment.nStates)
  }

  /**
   * Specify the necessary actions to execute after the training
   * iterations finish. This class has no specified actions
   */
  override def actionsAfterTrainingIterations: Unit = {}

  /**
   * Save the value function. By default it uses CSV
   * with format State Id, Value Func
   * @param filename
   */
  def save(filename: String): Unit = {

    val writer = new CSVDataWriter(filename = filename, Array("State Id", "Value Func"))
    val listOfRecords = new ListBuffer[Array[String]]()

    for(s <- 0 until this.environment.nStates){
      listOfRecords += Array(s.toString, v(s).toString)
    }

    writer.writeRows(listOfRecords)
  }

}
