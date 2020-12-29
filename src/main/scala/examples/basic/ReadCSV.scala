package examples.basic

import java.io.File
import breeze.linalg._


object ReadCSV {

  def main(args: Array[String]):Unit={

    // read CSV data
    val data = csvread(file=new File("some fil"), separator=',', skipLines=1);

    // write matrix in CSV format


    System.out.println("data \n" + data(0 to 3, ::));

  }

}
