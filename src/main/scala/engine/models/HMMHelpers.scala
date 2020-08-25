package engine.models

import breeze.linalg.{DenseMatrix, DenseVector}

object HMMHelpers {


  def forward(obs: Array[String], A: DenseMatrix[Double],
             B: DenseMatrix[Double], pi: DenseVector[Double],
             obsToIdx: Map[String, Int]):DenseMatrix[Double]={

    var alpha = DenseMatrix.zeros[Double](obs.length, A.rows);

    val startIdx = obsToIdx.get(obs(0)).get

    // initialize alpha// initialize alpha
    for( i <- 0 until A.rows  ){
      val value = pi(i)*B(i, startIdx);
      alpha(0, i) = value;
    }

    for(t <- 1 until obs.length ){
      for(j <- 0 until A.rows ){
        alpha(t,j) = 0.0;

        var value = 0.0
        for( i <- 0 until A.rows  ){
          val alphaPrevious = alpha(t-1, i);
          val transProb = A(i,j);
          value +=  alphaPrevious*transProb
        }

        alpha(t, j) = value
        alpha(t,j) *=  B(j, obsToIdx.get(obs(t)).get)
      }
    }

    alpha;
  }

  def backward(obs: Array[String], A: DenseMatrix[Double],
               B: DenseMatrix[Double],
               obsToIdx: Map[String, Int]) : DenseMatrix[Double]={

    var beta = DenseMatrix.zeros[Double](obs.length, A.rows);

    val lastRowIdx = obs.length -1;

    for( i <- 0 until beta.cols ){
      beta(lastRowIdx, i) = 1.0
    }
    
    // calculate matrix
    // we loop backwards in the observation array
    // start at the position one before the end
    // proceed until t is -1. Move back one step at the time

    for( t <- obs.length -2 until -1 by -1){
      for( i <- 0 until A.rows  ){

        var betaVal = 0.0;

        for( j <- 0 until A.rows ){

          val observation = obs(t+1)
          val idx = obsToIdx.get(observation).get
          betaVal += B(j, idx)*A(i, j)*beta(t+1, j)
        }

        beta(t, i) = betaVal
      }
    }

    beta
  }
}
