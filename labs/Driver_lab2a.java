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
 * This file is part A and contains the euclidAlg method.
 */



import java.util.*;
import java.lang.*;



/**
 * Driver_lab2a
 *
 * This class takes two integers and finds the
 * greatest common divisor and prints it.
 */
public class Driver_lab2a {

  /**
   * euclidAlg
   *
   * This function used the Modulo Operator
   * and recursion on two integers.
   *
   * Parameters:
   *   a: positive integer
   *   b: positive integer
   *
   * Return value: the greatest common divisor as an integer.
   */
  public static long euclidAlg(long a, long b) {
    
    if (a == 0) {
      
      return b;
      
    }
      
    return euclidAlg(b%a, a);

  }

  public static void main(String[] args) {
    
    try {
      
      Scanner input = new Scanner(System.in);
      long divisor;
      
      while (input.hasNextInt()) {
        
        long num1 = input.nextInt();
        long num2 = input.nextInt();
        
        divisor = euclidAlg(num1, num2);
                              
        System.out.println(divisor);
        
      }
      
    } catch (Exception e) {
      
      System.out.println("Error. Failed the unit test.");
      e.printStackTrace();
      
    }

  }

}
