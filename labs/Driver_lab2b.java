/**
 * File: Driver_lab2a.java
 * Author: Kerry Lyon
 * Course: MSCS 630L 711
 * Assignment: Lab 2
 * Due Date: January 30, 2022
 * Version: 1.0
 *
 * This lab introduces the Euclidean Algorithm used to
 * find the greatest common divisor between two integers.
 * This file is part B and contains the euclidAlgExt method.
 */



import java.util.Arrays;
import java.util.Scanner;



/**
 * Driver_lab2b
 *
 * This class takes two integers and finds the
 * greatest common divisor and extends the algorithm
 * to keep a record of values (the integer coefficients).
 */
public class Driver_lab2b {

  /**
   * euclidAlgExt
   *
   * This function used the Modulo Operator
   * and recursion on two integers.
   *
   * Parameters:
   *   a: positive integer
   *   b: positive integer
   *
   * Return value: an array of integers including the gcd and two coefficients
   */
  public static long[] euclidAlgExt(long a, long b) {
            
    long x = 0, prevX = 1, y = 1, prevY = 0, i;
        
    while (b != 0) {
            
      long quotient = a / b;
      long remainder = a % b;

      a = b;
      b = remainder;

      i = x;
      x = prevX - quotient * x;
      prevX = i;

      i = y;
      y = prevY - quotient * y;
      prevY = i; 
    
    }
    
    return new long[] {a, prevX, prevY};
    
  }

  public static void main(String[] args) {
    
    try {
      
      Scanner input = new Scanner(System.in);
      long[] divisor;
      
      while (input.hasNextLong()) {
        
        long num1 = input.nextLong();
        long num2 = input.nextLong();
        
        divisor = euclidAlgExt(num1, num2);
                              
        System.out.println(Arrays.toString(divisor).replaceAll("[\\[|\\]|\\,]", ""));
                
      }
      
    } catch (Exception e) {
      
      System.out.println("Error. Failed the unit test.");
      e.printStackTrace();
      
    }

  }

}
