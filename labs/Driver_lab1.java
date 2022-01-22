/**
 * File: Driver_lab1.java
 * Author: Kerry Lyon
 * Course: MSCS 630L 711
 * Assignment: Lab 1
 * Due Date: January 23, 2022
 * Version: 1.0
 * 
 * The purpose of this lab is to convert plain text to integers.
 * This file contains the str2int method.
 */



import java.util.Arrays;
import java.util.Scanner;



/**
 * Driver_lab1
 * 
 * This class converts a plain text string 
 * to an array of integers.
 */
public class Driver_lab1 {
  
  /**
   * str2int
   *
   * This function takes a string and converts each character
   * into a number from 0 - 25 and sets
   * whitespace to the number 26.
   * 
   * Parameters:
   *   plainText: the string from the next line of the input file
   * 
   * Return value: an array of numbers.
   */
  public static int[] str2int(String plainText) {
    
    char letter;
    int num;
    int len = plainText.length();
    int[] numArray = new int[len];
    
    for (int i = 0; i < len; i++) {
          
      letter = plainText.charAt(i);
      
      if (Character.isWhitespace(letter)) {
        
        num = 26;
        
      } else {
        
        letter = Character.toLowerCase(letter);
        num = letter - 97;
                
      }
      
      numArray[i] = num;
      
    }
    
    return numArray;
    
  }
  
  public static void main(String[] args) {
    
    Scanner input = new Scanner(System.in);
    String line;
    int [] cypherArray;    
    
    while (input.hasNext()) {
            
      line = input.nextLine();
      
      cypherArray = str2int(line);
      
      System.out.println(Arrays.toString(cypherArray).replaceAll("[\\[|\\]|\\,]", ""));
      
    }
    
    // last line is empty - how do you prevent the carriage return or get rid of it

  }

}