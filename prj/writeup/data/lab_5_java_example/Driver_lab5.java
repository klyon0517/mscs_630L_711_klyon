/**
 * File: Driver_lab5.java
 * Author: Kerry Lyon
 * Course: MSCS 630L 711
 * Assignment: Lab 5
 * Due Date: March 6, 2022
 * Version: 1.0
 *
 * This lab deals builds on lab 4 and introduces
 * AES encryption.
 */



import java.io.BufferedReader;
import java.io.InputStreamReader;



/**
 * Driver_lab5
 *
 * This class will test the AESCipher class and methods by
 * calling AES() and providing valid input (system key and plaintext block).
 */
 public class Driver_lab5 {
  
  /**
   * main
   *
   * This method streams in the system key and plaintext block,
   * calls the AES() method, and outputs the ciphertext, all in upper case.
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
      String key = br.readLine();
      String text = br.readLine();
      System.out.println(AESCipher.AES(text, key));
                
    } catch (Exception e) {
      
      System.out.println("Error. Failed the unit test.");
      e.printStackTrace();
      
    }

  }
  
}