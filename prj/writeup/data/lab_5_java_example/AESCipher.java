/**
 * File: AESCipher.java
 * Author: Kerry Lyon
 * Course: MSCS 630L 711
 * Assignment: Lab 5
 * Due Date: March 6, 2022
 * Version: 1.0
 *
 * This lab deals builds on lab 4 and introduces
 * AES encryption.
 */

import java.util.Arrays;

/**
 * AESCipher
 *
 * This class contains the methods for producing a ciphertext.
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
        
    System.out.println("6) aesRoundKeys - KeyHex: " + KeyHex);
    
    // Validate input.
    if(!KeyHex.matches("^[0-9A-F]+$")) {
      
      throw new IllegalArgumentException("Input must be in an uppercase hex format.");
        
    }

    if(KeyHex.length() != 32) {
      
      throw new IllegalArgumentException("Input must be 32 hex characters (128-bits).");
      
    }
        
    String[][] Ke = toMatrix(KeyHex);
    System.out.println("7) aesRoundKeys - Ke - toMatrix: " + Arrays.deepToString(Ke));

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
          // System.out.println("8) W[j][i] - XorHex: " + W[j][i]);
            
        }
          
      } else {
        
        // If column index j is a multiple of 4, starting a new round.
        String rcon = aesRcon(j/4);
        System.out.println("9) rcon - aesRcon: " + rcon);
        
        String[] Wnew = {          
          XorHex(rcon, aesSBox(W[j-1][1])),
          aesSBox(W[j-1][2]),
          aesSBox(W[j-1][3]),
          aesSBox(W[j-1][0])};
        
        System.out.println("10) Wnew - XorHex: " + Arrays.toString(Wnew));
            
        for (int i = 0; i < 4; i++) {
          
          W[j][i] = XorHex(W[j-4][i], Wnew[i]);
          // System.out.println("11) W[j][i] - XorHex: " + W[j][i]);
            
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
      System.out.println("12) aesRoundKeys -  row: " + row);
      
    }

    return outKeys;
    // System.out.println("12) outKeys: " + outKeys);
    
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
      
    System.out.println("12) aesSBox - inHex: " + inHex);
    
    return SBox
      [Integer.parseInt(inHex.substring(0, 1), 16)]
      [Integer.parseInt(inHex.substring(1), 16)];
      
    // System.out.println("13) SBox - aesSBox: " + SBox[Integer.parseInt(inHex.substring(0, 1), 16)][Integer.parseInt(inHex.substring(1), 16)]);
                
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
      
    System.out.println("14) aesRcon - round: " + round);
    
    return RCon[round];
      
    // System.out.println("15) RCon - aesRcon: " + RCon[round]);
        
  }
  
  
  
  /**
   * XorHex
   *
   * This method performs the XOR operation.
   *
   * Parameters:
   *  String hex: location value
   *
   * Return value: hex value
   */
  private static String XorHex(String ... hex) {
      
    System.out.println("16) XorHex - hex: " + Arrays.toString(hex));
    
    int ret = Integer.parseInt(hex[0], 16);
    
    for (int i = 1; i < hex.length; i++) {
      
        ret = ret ^ Integer.parseInt(hex[i], 16);
        
    }
    
    return String.format("%02X", ret & 0xFF);
      
    // System.out.println("17) String.format - XorHex: " + String.format("%02X", ret & 0xFF));
        
  }
  
  
  
  /**
   * AESStateXOR
   *
   * This method performs the "Add Round Key" operation. 
   *
   * Parameters:
   *  String[][] sHex: four by four matrix of pairs of hex digits
   *  String[][] keyHex: four by four matrix
   *
   * Return value: XOR of the corresponding input matrix entries
   *  (four by four matrix of pairs of hex digits)
   */
  public static String[][] AESStateXOR(String[][] sHex, String[][] keyHex) {
      
    System.out.println("18) AESStateXOR - sHex: " + Arrays.deepToString(sHex) + ", keyHex: " + Arrays.deepToString(keyHex));
    
    String[][] outHex = new String[4][4];
    
    for (int i = 0; i < 4; i++) {
      
      for (int j = 0; j < 4; j++) {
        
        outHex[i][j] = XorHex(sHex[i][j], keyHex[i][j]);
          
      }
        
    }
    
    return outHex;
      
  }
  
  
  
  /**
   * AESNibbleSub
   *
   * This method performs the "Substitution" operation by running the input matrix
   * entries through the AES SBox.
   *
   * Parameters:
   *  String[][] inHex: four by four matrix of pairs of hex digits
   *
   * Return value: four by four matrix of pairs of hex digits
   */
  public static String[][] AESNibbleSub(String[][] inHex) {
      
    System.out.println("19) AESNibbleSub - inHex: " + Arrays.deepToString(inHex));
    
    String[][] outHex = new String[4][4];
    
    for (int i = 0; i < 4; i++) {
      
      for (int j = 0; j < 4; j++) {
        
        outHex[i][j] = aesSBox(inHex[i][j]);
          
      }
        
    }
    
    return outHex;
      
  }
  
  
  
  /**
   * AESShiftRow
   *
   * This method performs the "Shift Row" operation
   *
   * Parameters:
   *  String [][] inStateHex: four by four matrix of pairs of hex digits
   *
   * Return value: shifted four by four matrix of pairs of hex digits
   */
  public static String[][] AESShiftRow(String[][] inStateHex) {
      
    System.out.println("20) AESShiftRow - inStateHex: " + Arrays.deepToString(inStateHex));
    
    String[][] outHex = new String[4][4];
    
    for (int i = 0; i < 4; i++) {
      
      for (int j = 0; j < 4; j++) {
        
        outHex[i][j] = inStateHex[(i + j) % 4][j];
          
      }
        
    }
    
    return outHex;
      
  }
  
  
  
  /**
   * GMul
   *
   * This method performs the Galois Field multiplication
   * for the AESMixColumn function.
   *
   * Parameters:
   *  String a: string
   *  String b: string
   *
   * Return value: string
   */
  private static String GMul(String a, String b) {
      
    System.out.println("21) GMul - a: " + a + ", b: " + b);
    
    int p = 0;
    int ai = Integer.parseInt(a, 16);
    int bi = Integer.parseInt(b, 16);

    for (int i = 0; i < 8; i++) {
      
      if ((bi & 0x01) == 1) {
        
        p = (p ^ ai) & 0xFF;
          
      }

      boolean hi_bit_set = ((ai & 0x80) == 0x80);
      
      ai <<= 1;
      
      if (hi_bit_set) {
        
        ai = (ai ^ 0x1B) & 0xFF;
          
      }
      
      bi = (bi >> 1) & 0x7F;
        
    }

    return String.format("%02X", p);
      
  }
  
  
  
  /**
   * toMatrix
   *
   * This method converts a string to a four by four matrix.
   *
   * Parameters:
   *  String value: string
   *
   * Return value: four by four matrix
   */
  private static String[][] toMatrix(String value) {
      
    System.out.println("22) toMatrix - value: " + value);
    
    String[][] ret = new String[4][4];
    
    for (int i = 0; i < 4; i++) {
      
      for (int j = 0; j < 4; j++) {
        
        ret[i][j] = value.substring((i*4 + j) * 2, (i*4 + j + 1) * 2);
          
      }
        
    }
    
    return ret;
      
  }
  
  
  
  /**
   * fromMatrix
   *
   * This method converts a four by four matrix to a string.
   *
   * Parameters:
   *  String[][] value: four by four matrix
   *
   * Return value: string
   */
  private static String fromMatrix(String[][] value) {
      
    System.out.println("23) fromMatrix - value: " + Arrays.deepToString(value));
    
    String out = "";
    
    for (int i = 0; i < 4; i++) {
      
      for (int j = 0; j < 4; j++) {
        
        out += value[i][j];
          
      }
        
    }
    
    return out;
      
  }
  
  
  
  /**
   * AESMixColumn
   *
   * This method performs the "Mix Column" operation.
   *
   * Parameters:
   *  String[][] inStateHex: four by four matrix of pairs of hex digits
   *
   * Return value: four by four matrix of pairs of hex digits
   */
  public static String[][] AESMixColumn(String[][] inStateHex) {
      
    System.out.println("24) AESMixColumn - inStateHex: " + Arrays.deepToString(inStateHex));
    
    String[][] out = new String[4][4];
    
    for (int i = 0; i < 4; i++) {
      
      out[i][0] = XorHex(GMul("02", inStateHex[i][0]), GMul("03", inStateHex[i][1]), inStateHex[i][2], inStateHex[i][3]);
      out[i][1] = XorHex(inStateHex[i][0], GMul("02", inStateHex[i][1]), GMul("03", inStateHex[i][2]), inStateHex[i][3]);
      out[i][2] = XorHex(inStateHex[i][0], inStateHex[i][1], GMul("02", inStateHex[i][2]), GMul("03", inStateHex[i][3]));
      out[i][3] = XorHex(GMul("03", inStateHex[i][0]), inStateHex[i][1], inStateHex[i][2], GMul("02", inStateHex[i][3]));
        
    }

    return out;
      
  }
  
  
  
  /**
   * AES
   *
   * This method performs AES encryption.
   *
   * Parameters:
   *  String pTextHex: plaintext block
   *  String keyHex: system key
   *
   * Return value: ciphertext (all in upper case)
   */
  public static String AES(String pTextHex, String keyHex) {
    
    String[] keyExp = aesRoundKeys(keyHex);
    
    System.out.println("1) AES: " + Arrays.toString(keyExp));

    String[][] state = toMatrix(pTextHex);
    state = AESStateXOR(state, toMatrix(keyExp[0]));
    
    System.out.println("2) AES - state - AESStateXOR: " + Arrays.deepToString(state));

    for (int i = 1; i < 10; i++) {
      
      state = AESNibbleSub(state);
      state = AESShiftRow(state);
      state = AESMixColumn(state);
      state = AESStateXOR(state, toMatrix(keyExp[i]));
        
    }

    state = AESNibbleSub(state);
    System.out.println("3) AES - state - AESNibbleSub: " + Arrays.deepToString(state));
    
    state = AESShiftRow(state);
    System.out.println("4) AES - state - AESShiftRow: " + Arrays.deepToString(state));
    
    state = AESStateXOR(state, toMatrix(keyExp[keyExp.length - 1]));
    System.out.println("5) AES - state - AESStateXOR: " + Arrays.deepToString(state));

    // Needs an array returned for the unit tests
    return fromMatrix(state);
    
    // String s = fromMatrix(state);
    // String[] strArray = {s};

    // return strArray;
      
  }
  
}