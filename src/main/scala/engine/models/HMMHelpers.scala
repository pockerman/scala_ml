package engine.models

import breeze.linalg.{DenseMatrix, DenseVector}

object HMMHelpers {


  def forward(obs: Array[String], A: DenseMatrix[Double],
             B: DenseMatrix[Double], pi: DenseVector[Double],
             obsToIdx: Map[String, Int]):DenseMatrix[Double]={

    var alpha = DenseMatrix.zeros[Double](obs.length, A.rows);

    val startIdx = obsToIdx.get(obs(0)).get

    // initialize alpha// initialize alpha
    for( i <- 0 to A.rows ){
      val value = pi(i)*B(i, startIdx);
      alpha(0, i) = value;
    }

    for(t <- 1 to obs.length){
      for(j <- 0 to A.rows){
        alpha(t,j) = 0.0;

        var value = 0.0
        for( i <- 0 to A.rows){
          val alphaPrevious = alpha(t-1, i);
          val transProb = A(i,j);
          value += alpha(t,j) + alphaPrevious*transProb
        }


        alpha(t,j) *=  B(j, obsToIdx.get(obs(t)).get)
      }
    }

    alpha;
  }
}
