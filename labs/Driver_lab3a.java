/**
 * File: Driver_lab3a.java
 * Author: Kerry Lyon
 * Course: MSCS 630L 711
 * Assignment: Lab 3
 * Due Date: February 6, 2022
 * Version: 1.0
 *
 * This lab Hill cryptosystem was an early susbtitution cipher
 * based on linear algebra and modular arithmetic. For part 3a, the 
 * corresponding determinant in modulo m is returned as an integer.
 */



import java.util.Scanner;



/**
 * Driver_lab3a
 *
 * This class explores the algorithm of cofactor expansion to
 * calculate the determinant of any square modular matrix
 * limited to signed 32-bit integers.
 */
public class Driver_lab3a {
  
  /**
   * cofModDet
   *
   * This function receives the modulo integer and a square matrix
   * and performs a cofactor expansion to calculate the determinant.
   *
   * Parameters:
   *  int m: positive ineger
   *  int[][] A: two-dimensional Aay (matrix)
   *
   * Return value: the corresponding determinant in modulo m (as an integer)
   */
  public static int cofModDet(int m, int[][] A) {
        
    int determinantMod;
    int len = A.length;
    
    if (len == 1) {
      
      determinantMod = A[0][0] % m;
      
      if (determinantMod < 0) {
      
        determinantMod += m;
      
      }
      
      return determinantMod;
    
    }
    
    if (len == 2) {
            
      int det1 = (((A[0][0] % m) + m) % m) * (((A[1][1] % m) + m) % m);            
      int det2 = (((A[0][1] % m) + m) % m) * (((A[1][0] % m) + m) % m);
            
      determinantMod = (((det1 - det2) % m) + m) % m;
      
      return determinantMod;
      
    }
    
    int d = 0, sign = -1;
    
    for (int skipCol = 0; skipCol < len; ++skipCol) {
      
      int[][] matrix = new int[len - 1][len - 1];
      
      for (int row = 0; row < len - 1; ++row) {
        
        int matrixCol = 0;
        
        for (int col = 0; col < len; ++col)
          
        if (col != skipCol) matrix[row][matrixCol++] = A[row + 1][col];
        
      }
      
      d += (sign *= -1) * (((A[0][skipCol] % m) + m) % m) * cofModDet(m, matrix);
      
    }
    
    determinantMod = ((d % m) + m) % m;
    
    return determinantMod;
    
  }  
  
  public static void main(String[] args) {
    
    try {
            
      Scanner input = new Scanner(System.in);
      int m = input.nextInt();
      int matrixSize = input.nextInt();
      int[][] A = new int[matrixSize][matrixSize];
      
      while (input.hasNextInt()) {
        
        for (int row = 0; row < matrixSize; row++) {
          
          for (int col = 0; col < matrixSize; col++) {
            
            A[row][col] = input.nextInt();        
            
          }
          
        }
        
      }
      
      System.out.println(cofModDet(m, A));
      
    } catch (Exception e) {
      
      System.out.println("Error. Failed the unit test.");
      e.printStackTrace();
      
    }

  }
  
}