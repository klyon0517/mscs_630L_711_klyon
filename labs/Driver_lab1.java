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



// import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;



/**
 * Driver_lab1
 * 
 * This class uses a hashmap to convert a
 * plain text string to an array of integers.
 */
public class Driver_lab1 {
    
  /* public static int[] str2int(String plainText) {
    
    for (int i = 0; i < plainText.length(); i++) {
          
      letter = plainText.charAt(i);
      num = char2int.get(Character.toLowerCase(letter));
      
    }
    
    return numArray;
    
  } */
  
  public static void main(String[] args) {
    
    Scanner input = new Scanner(System.in);
    String plainText;
    char letter;
    int num;
    
    // ArrayList<Integer> cypherText = new ArrayList<Integer>();
    
    HashMap<Character, Integer> char2int = new HashMap<Character, Integer>();
    
    char2int.put('a', 0);
    char2int.put('b', 1);
    char2int.put('c', 2);
    char2int.put('d', 3);
    char2int.put('e', 4);
    char2int.put('f', 5);
    char2int.put('g', 6);
    char2int.put('h', 7);
    char2int.put('i', 8);
    char2int.put('j', 9);
    char2int.put('k', 10);
    char2int.put('l', 11);
    char2int.put('m', 12);
    char2int.put('n', 13);
    char2int.put('o', 14);
    char2int.put('p', 15);
    char2int.put('q', 16);
    char2int.put('r', 17);
    char2int.put('s', 18);
    char2int.put('t', 19);
    char2int.put('u', 20);
    char2int.put('v', 21);
    char2int.put('w', 22);
    char2int.put('x', 23);
    char2int.put('y', 24);
    char2int.put('z', 25);
    
    while (input.hasNext()) {
      
      plainText = input.nextLine();
      
      // str2int(line);
            
      for (int i = 0; i < plainText.length(); i++) {
        
        letter = plainText.charAt(i);
        
        if (Character.isWhitespace(letter)) {
          
          System.out.print(26 + " ");
          
        } else {
          
          num = char2int.get(Character.toLowerCase(letter));
          System.out.print(num + " ");
          // cypherText.add(num);
          // System.out.println(num);
          
        }
        
      }
            
      System.out.println();
      
    }    

  }

}