/**
 * File: Driver_lab4.java
 * Author: Kerry Lyon
 * Course: MSCS 630L 711
 * Assignment: Lab 4
 * Due Date: February 20, 2022
 * Version: 1.0
 *
 * This lab deals with AES and how it produces keys.
 * In particular, generating 11 rounds of keys.
 */



import java.io.BufferedReader;
import java.io.InputStreamReader;



/**
 * Driver_lab4
 *
 * This class will test the AESCipher class and methods by
 * calling aesRoundKeys() and providing valid input.
 */
 public class Driver_lab3b {
  
  /**
   * main
   *
   * This method streams in the system key, calls the aesRoundKeys() method,
   * and outputs the 11 round keys, one in each row, all in upper case.
   *
   * Parameters:
   *  args: the array of command line argument values
   *
   * Return value: none
   */
  public static void main(String[] args) {
        
    try {
      
      InputStreamReader isr = new InputStreamReader(System.in);
      BufferedReader br = new BufferedReader(isr);
      String input = br.readLine();
      String[] result = AESCipher.aesRoundKeys(input);
      
      for (String str : result) {
        
        System.out.println(str);
          
      }
          
    } catch (Exception e) {
      
      System.out.println("Error. Failed the unit test.");
      e.printStackTrace();
      
    }

  }
  
}