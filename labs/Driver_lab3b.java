/**
 * File: Driver_lab3b.java
 * Author: Kerry Lyon
 * Course: MSCS 630L 711
 * Assignment: Lab 3
 * Due Date: February 6, 2022
 * Version: 1.0
 *
 * This lab Hill cryptosystem was an early susbtitution cipher
 * based on linear algebra and modular arithmetic. For part 3b,
 * plaintext will processed down to its bytes.
 */



import java.lang.Math;
import java.util.Scanner;



/**
 * Driver_lab3b
 *
 * This class take a plaintext P composed of n symbols from the ASCII table,
 * and expresses that plaintext in a matrix of four rows and a variable number of columns,
 * whose elements are hexadecimal bytes placed in column-major order.
 */
public class Driver_lab3b {
  
  /**
   * getHexMatP
   *
   * This function receives the substitution character and a plaintext
   * string with a length less than or equal to 16. The resulting
   * matrix is repeatedly printed each time it loops.
   *
   * Parameters:
   *  char s: substitution character
   *  String p: plaintext
   *
   * Return value: at least 4 columns of the matrix
   */
  public static int[][] getHexMatP(char s, String p) {
    
    int[][] result = new int[4][4];
    
    for (int i = 0; i < 16; i++) {
        
      if (i < p.length()) {
        
        result[i % 4][i / 4] = (int) p.charAt(i);
                  
      } else {
        
        result[i % 4][i / 4] = (int) s;
        
      }
            
    }
        
    return result;
    
  }
  
  public static void main(String[] args) {
        
    try {
            
      Scanner input = new Scanner(System.in);
      
      String character = input.nextLine();
      char subChar = character.charAt(0);
      String line = input.nextLine();
      int len = (int)Math.ceil(Double.valueOf(line.length()) / 16);
      int[][] matrix = new int[4][4];
      int j = 0;
      
      // Since the string is divided into 16 character sections, a string of less than
      // that must be accounted for in case the input string or final loop is smaller.
      for (int i = 0; i < len; i++) {
        
        if (len > 1) {
                    
          if (i < len - 1) {
            
            matrix = getHexMatP(subChar, line.substring(j, j += 16));
            
          } else {
            
            matrix = getHexMatP(subChar, line.substring(j));
            
          }
          
        } else {
          
          matrix = getHexMatP(subChar, line);
        
        }
        
        for (int row = 0; row < matrix.length; row++) {
          
          for (int col = 0; col < matrix[row].length; col++) {
            
            System.out.print(Integer.toHexString(matrix[row][col]).toUpperCase() + " ");
            
          }
          
          System.out.println();
          
        }
        
        System.out.println();
        
      }
    
    } catch (Exception e) {
      
      System.out.println("Error. Failed the unit test.");
      e.printStackTrace();
      
    }

  }
  
}