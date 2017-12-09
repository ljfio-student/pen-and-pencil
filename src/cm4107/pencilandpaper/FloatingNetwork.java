/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cm4107.pencilandpaper;

import java.util.Arrays;

/**
 *
 * @author luke
 */
public class FloatingNetwork {

  private double[][] matrix;

  private final double learningRate = 0.01;
  private final int maxIterations = 20000;

  public FloatingNetwork(int inputSize, int outputSize) {
    this.matrix = new double[outputSize][inputSize];
    
    for (int o = 0; o < outputSize; o++) {
      for (int i = 0; i < inputSize; i++) {
        this.matrix[o][i] = Math.random();
      }
    }
  }

  public void train(double[][] inputs, double[][] outputs) {
    boolean learning;
    int count = 0;

    do {
      learning = false;
      
      for (int i = 0; i < inputs.length; i++) {
        double[] guess = this.recall(inputs[i]);

        if (!Arrays.equals(outputs[i], guess)) {
          learning = true;

          for (int e = 0; e < guess.length; e++) {
            double error = outputs[i][e] - guess[e];

            for (int w = 0; w < this.matrix[e].length; w++) {
              this.matrix[e][w] += this.learningRate * error * inputs[i][w];
            }
          }
        }
      }
    } while (learning && (count++ < this.maxIterations));
  }

  public double[] recall(double[] input) {
    double[] result = new double[this.matrix.length];

    // Iterate through the output columns
    for (int outputIndex = 0; outputIndex < result.length; outputIndex++) {
      double sum = 0;

      // Integrate the results of neuron firing
      for (int inputIndex = 0; inputIndex < input.length; inputIndex++) {
        sum += input[inputIndex] * this.matrix[outputIndex][inputIndex];
      }

      // Compare the result against the threshold
      result[outputIndex] = 1 / (1 + Math.pow(Math.E, -sum));
    }

    return result;
  }
  
  @Override
  public String toString() {
    return ArrayHelper.toString(this.matrix);
  }
}
