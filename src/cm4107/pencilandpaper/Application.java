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
public class Application {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
//    test16Synapse();
//    test36Synapse();
//      testWeighedSynapse();
//    testMultiInputSynapse();
    testFloatingSynapse();
  }
  
  private static void testFloatingSynapse() {
    System.out.println("weighted synapse");
    FloatingNetwork network = new FloatingNetwork(6, 6);
    
    double[] input = {1.0, 0, 1.0, 0, 1.0, 0};
    double[] output = {0, 1.0, 0, 1.0, 0, 1.0};
    
    System.out.println("first input: " + Arrays.toString(input));
    System.out.println("first output: " + Arrays.toString(output));
    
    double[] secondInput = {1.0, 1.0, 0, 1.0, 0, 0};
    double[] secondOutput = {0, 0, 1.0, 1.0, 0, 0};
    
    System.out.println("second input: " + ArrayHelper.toString(secondInput));
    System.out.println("second output: " + ArrayHelper.toString(secondOutput));
    
    network.train(new double[][]{input, secondInput}, new double[][]{output, secondOutput});

    System.out.println("matrix: \n" + network);
    
    double[] recall = network.recall(input);
    
    System.out.println("recall first: " + ArrayHelper.toString(recall));
    
    recall = network.recall(secondInput);
    
    System.out.println("recall second: " + ArrayHelper.toString(recall));
  }
  
  private static void test16Synapse() {
    System.out.println("16 synapse");
    
    boolean[] input = {true, false, true, false};
    boolean[] output = {false, false, true, true};
    
    System.out.println("input: " + ArrayHelper.toString(input));
    System.out.println("output: " + ArrayHelper.toString(output));
    
    // 16 synapse
    BasicNetwork network16 = new BasicNetwork(4, 4);
    
    // Train
    network16.train(input, output);
    
    // Recall
    boolean[] recalled = network16.recall(input);
    System.out.println("recalled: " + ArrayHelper.toString(recalled));
    
    // Incomplete
    boolean[] incomplete = {true, false, false, false};
    System.out.println("incomplete: " + ArrayHelper.toString(incomplete));
    
    recalled = network16.recall(incomplete);
    System.out.println("recalled: " + ArrayHelper.toString(recalled));
    
    // Noisy
    boolean[] noisy = {true, false, true, true};
    System.out.println("noisy: " + ArrayHelper.toString(noisy));
    
    recalled = network16.recall(noisy);
    System.out.println("recalled: " + ArrayHelper.toString(recalled));
  }
  
  private static void test36Synapse() {
    // 36 synapse
    System.out.println("36 synapse");
    BasicNetwork network36 = new BasicNetwork(6, 6);
    
    boolean[] input = {true, false, true, false, true, false};
    boolean[] output = {false, true, false, true, false, true};
    
    System.out.println("input: " + ArrayHelper.toString(input));
    System.out.println("output: " + ArrayHelper.toString(output));
    
    network36.train(input, output);
    
    System.out.println(network36);
    
    // Train second association
    boolean[] secondInput = {true, true, false, true, false, false};
    boolean[] secondOutput = {false, false, true, true, false, false};
    
    System.out.println("second input: " + ArrayHelper.toString(secondInput));
    System.out.println("second output: " + ArrayHelper.toString(secondOutput));
    
    network36.train(secondInput, secondOutput);
    
    System.out.println(network36);
    
    // Recall
    boolean[] recalled = network36.recall(input);
    System.out.println("recalled: " + ArrayHelper.toString(recalled));
    
    // Test distorted
    boolean[] distorted = {true, false, false, false, true, false};
    System.out.println("distorted: " + ArrayHelper.toString(distorted));
    
    recalled = network36.recall(distorted);
    System.out.println("recalled: " + ArrayHelper.toString(recalled));
  }
  
  private static void testWeighedSynapse() {
    System.out.println("weighted synapse");
    WeightedNetwork network = new WeightedNetwork(6, 6);
    
    double[] input = {1.0, 0, 1.0, 0, 1.0, 0};
    double[] output = {0, 1.0, 0, 1.0, 0, 1.0};
    
    System.out.println("input: " + Arrays.toString(input));
    System.out.println("output: " + Arrays.toString(output));
    
    network.train(input, output);
    
    System.out.println(network);
    
    double[] recall = network.recall(input);
    
    System.out.println("recall: " + Arrays.toString(recall));
    
    double[] secondInput = {1.0, 1.0, 0, 1.0, 0, 0};
    double[] secondOutput = {0, 0, 1.0, 1.0, 0, 0};
    
    network.train(secondInput, secondOutput);
    
   
    System.out.println(network);
    
    recall = network.recall(input);
    
    System.out.println("recall: " + Arrays.toString(recall));
  }
  
  private static void testMultiInputSynapse() {
    boolean[] inputTrees = {true, false, true, true, true, false, false, false, true};
    boolean[] inputFlowers = {true, false, true, false, true, false, true, false, true};
    boolean[] inputRocks = {false, true, false, true, true, true, false, true, false};
    
    boolean[] input = ArrayHelper.combine(inputTrees, inputFlowers, inputRocks);
    
    System.out.println("input: " + Arrays.toString(input));
    
    boolean[] output = {true, false, false, false, true, false, false, true, true };
    
    System.out.println("output: " + Arrays.toString(output));
    
    BasicNetwork network = new BasicNetwork(input.length, output.length);
    
    network.train(input, output);
    
    System.out.println(network);
    
    boolean[] recall = network.recall(input);
    
    System.out.println("recall: " + Arrays.toString(recall));
  }
}
