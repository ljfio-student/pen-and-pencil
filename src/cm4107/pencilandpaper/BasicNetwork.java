package cm4107.pencilandpaper;

public class BasicNetwork {
  private final boolean[][] matrix;
  
  public BasicNetwork(int inputSize, int outputSize) {
    this.matrix = new boolean[outputSize][inputSize];
  }
  
  public void train(boolean[] input, boolean[] output) {
    for (int inputIndex = 0; inputIndex < input.length; inputIndex++) {
      for (int outputIndex = 0; outputIndex < output.length; outputIndex++) {
        boolean result = input[inputIndex] && output[outputIndex];
        
        this.matrix[outputIndex][inputIndex] = result ? true : this.matrix[outputIndex][inputIndex];
      }
    }
  }
  
  public boolean[] recall(boolean[] input) {
    boolean[] output = new boolean[this.matrix.length];
    
    // Calculate the threshold
    int threshold = 0;
    
    for (int inputIndex = 0; inputIndex < input.length; inputIndex++) {
      threshold += input[inputIndex] ? 1 : 0;
    }
    
    // Iterate through the output columns
    for (int outputIndex = 0; outputIndex < output.length; outputIndex++) {
      int sum = 0;
      
      // Integrate the results of neuron firing
      for (int inputIndex = 0; inputIndex < input.length; inputIndex++) {
        boolean fire = input[inputIndex] && this.matrix[outputIndex][inputIndex];
        sum += fire ? 1 : 0;       
      }
      
      // Compare the result against the threshold
      output[outputIndex] = (sum >= threshold);
    }
    
    return output;
  }
  
  @Override
  public String toString() {
    return ArrayHelper.toString(this.matrix);
  }
} 