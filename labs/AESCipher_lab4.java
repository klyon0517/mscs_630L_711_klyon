/**
 * File: AESCipher.java
 * Author: Kerry Lyon
 * Course: MSCS 630L 711
 * Assignment: Lab 4
 * Due Date: February 20, 2022
 * Version: 1.0
 *
 * This lab deals with AES and how it produces keys.
 * In particular, generating 11 rounds of keys.
 */



/**
 * AESCipher
 *
 * This class contains the methods for producing the 11 round keys.
 * It accepts a length 16-hex string representation of the system key.
 */
public class AESCipher {
  
  private static final String[] RCon = {"8D", "01", "02", "04", "08", "10", "20", "40", "80", "1B", "36"};
  private static final String[][] SBox = {
    {"63", "7C", "77", "7B", "F2", "6B", "6F", "C5", "30", "01", "67", "2B", "FE", "D7", "AB", "76"},
    {"CA", "82", "C9", "7D", "FA", "59", "47", "F0", "AD", "D4", "A2", "AF", "9C", "A4", "72", "C0"},
    {"B7", "FD", "93", "26", "36", "3F", "F7", "CC", "34", "A5", "E5", "F1", "71", "D8", "31", "15"},
    {"04", "C7", "23", "C3", "18", "96", "05", "9A", "07", "12", "80", "E2", "EB", "27", "B2", "75"},
    {"09", "83", "2C", "1A", "1B", "6E", "5A", "A0", "52", "3B", "D6", "B3", "29", "E3", "2F", "84"},
    {"53", "D1", "00", "ED", "20", "FC", "B1", "5B", "6A", "CB", "BE", "39", "4A", "4C", "58", "CF"},
    {"D0", "EF", "AA", "FB", "43", "4D", "33", "85", "45", "F9", "02", "7F", "50", "3C", "9F", "A8"},
    {"51", "A3", "40", "8F", "92", "9D", "38", "F5", "BC", "B6", "DA", "21", "10", "FF", "F3", "D2"},
    {"CD", "0C", "13", "EC", "5F", "97", "44", "17", "C4", "A7", "7E", "3D", "64", "5D", "19", "73"},
    {"60", "81", "4F", "DC", "22", "2A", "90", "88", "46", "EE", "B8", "14", "DE", "5E", "0B", "DB"},
    {"E0", "32", "3A", "0A", "49", "06", "24", "5C", "C2", "D3", "AC", "62", "91", "95", "E4", "79"},
    {"E7", "C8", "37", "6D", "8D", "D5", "4E", "A9", "6C", "56", "F4", "EA", "65", "7A", "AE", "08"},
    {"BA", "78", "25", "2E", "1C", "A6", "B4", "C6", "E8", "DD", "74", "1F", "4B", "BD", "8B", "8A"},
    {"70", "3E", "B5", "66", "48", "03", "F6", "0E", "61", "35", "57", "B9", "86", "C1", "1D", "9E"},
    {"E1", "F8", "98", "11", "69", "D9", "8E", "94", "9B", "1E", "87", "E9", "CE", "55", "28", "DF"},
    {"8C", "A1", "89", "0D", "BF", "E6", "42", "68", "41", "99", "2D", "0F", "B0", "54", "BB", "16"}};
  
  /**
   * aesRoundKeys
   *
   * This method produces the 11 round keys.
   *
   * Parameters:
   *  String KeyHex: System key (16-hex string representation)
   *
   * Return value: 11-row string array representation of all the round keys
   */
  public static String[] aesRoundKeys(String KeyHex) {
    
    // Validate input.
    if(!KeyHex.matches("^[0-9A-F]+$")) {
      
      throw new IllegalArgumentException("Input must be in an uppercase hex format.");
        
    }

    if(KeyHex.length() != 32) {
      
      throw new IllegalArgumentException("Input must be 32 hex characters (128-bits).");
      
    }
    
    String[][] Ke = new String[4][4];
    
    for (int i = 0; i < 4; i++) {
      
      for (int j = 0; j < 4; j++) {
        
        Ke[i][j] = KeyHex.substring((i*4 + j) * 2, (i*4 + j + 1) * 2);
          
      }
        
    }

    String[][] W = new String[44][4];

    // Make AES key to be the first four columns of W.
    for (int i = 0; i < 4; i++) {
      
      W[i] = Ke[i];
        
    }

    for (int j = 4; j < 44; j++) {
      
      if (j % 4 != 0) {
        
        // If column index j is NOT a multiple of 4, XOR the fourth past and last column.
        for(int i = 0; i < 4; i++) {
          
          W[j][i] = XorHex(W[j-4][i],W[j-1][i]);
            
        }
          
      } else {
        
        // If column index j is a multiple of 4, starting a new round.
        String rcon = aesRcon(j/4);
        
        String[] Wnew = {          
          XorHex(rcon, aesSBox(W[j-1][1])),
          aesSBox(W[j-1][2]),
          aesSBox(W[j-1][3]),
          aesSBox(W[j-1][0])};
            
        for (int i = 0; i < 4; i++) {
          
          W[j][i] = XorHex(W[j-4][i], Wnew[i]);
            
        }
          
      }
        
    }
    
    // Every round key is composed of 4 successive readings of the columns W.
    String[] outKeys = new String[11];
    
    for (int i = 0; i < 11; i++) {
      
      String row = "";
      
      for( int j = 0; j < 4; j++) {
        
        row += String.join("", W[4 * i + j]);
          
      }
      
      outKeys[i] = row;
    }

    return outKeys;
    
  }
  
  
  
  /**
   * aesSBox
   *
   * This method reads the SBox.
   *
   * Parameters:
   *  String inHex: SBox location
   *
   * Return value: SBox value
   */
  public static String aesSBox(String inHex) {
    
    return SBox
      [Integer.parseInt(inHex.substring(0, 1), 16)]
      [Integer.parseInt(inHex.substring(1), 16)];
                
  }
  
    
  
  /**
   * aesRcon
   *
   * This method gets each round's constant.
   *
   * Parameters:
   *  int round: the round number
   *
   * Return value: round's constant
   */
  public static String aesRcon(int round) {
    
    return RCon[round];
        
  }
  
  
  
  /**
   * XorHex
   *
   * This method performs the XOR operation.
   *
   * Parameters:
   *  String hex1: location value
   *  String hex2: location value
   *
   * Return value: hex value
   */
  private static String XorHex(String hex1, String hex2) {
    
    return String.format("%02X",
    Integer.parseInt(hex1, 16)
    ^ Integer.parseInt(hex2, 16));
    
  }
  
}