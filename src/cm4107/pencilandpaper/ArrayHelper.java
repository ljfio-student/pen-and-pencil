/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cm4107.pencilandpaper;

/**
 *
 * @author luke
 */
public class ArrayHelper {

  public static String toString(boolean[] array) {
    String output = "[";

    for (int i = 0; i < array.length; i++) {
      output += array[i] ? "1" : "0";

      if (i != array.length - 1) {
        output += ", ";
      }
    }

    output += "]";

    return output;
  }

  public static String toString(boolean[][] outside) {
    String output = "";

    for (int j = 0; j < outside.length; j++) {
      boolean[] array = outside[j];

      for (int i = 0; i < array.length; i++) {
        output += array[i] ? "1" : "0";

        if (i != array.length - 1) {
          output += ", ";
        }
      }

      output += "\n";
    }

    return output;
  }

  public static String toString(double[] array) {
    String output = "[";

    for (int i = 0; i < array.length; i++) {
      output += String.format("%.2f", array[i]);

      if (i != array.length - 1) {
        output += ", ";
      }
    }

    output += "]";

    return output;
  }

  public static String toString(double[][] outside) {
    String output = "";

    for (int j = 0; j < outside.length; j++) {
      double[] array = outside[j];

      for (int i = 0; i < array.length; i++) {
        output += String.format("%.2f", array[i]);

        if (i != array.length - 1) {
          output += ", ";
        }
      }

      output += "\n";
    }

    return output;
  }

  public static boolean[] combine(boolean[]... arrays) {
    int size = 0;
    int count = 0;

    for (boolean[] array : arrays) {
      size += array.length;
    }

    boolean[] output = new boolean[size];

    for (boolean[] array : arrays) {
      for (boolean value : array) {
        output[count++] = value;
      }
    }

    return output;
  }
}
