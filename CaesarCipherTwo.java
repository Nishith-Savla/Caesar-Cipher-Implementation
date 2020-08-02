import edu.duke.*;
import java.util.Scanner;
/**
 * Encrypts and decrypts messages with the two keys specified
 * 
 * @author Nishith
 * @version 01-08-2020
 */
public class CaesarCipherTwo {
    private String alphabet, shiftedAlphabet1, shiftedAlphabet2;
    private int key1, key2;
    /**
     * CaesarCipherTwo Constructor
     *
     * @param key1 The first key for the cipher
     * @param key2 The second key for the cipher
     */
    public CaesarCipherTwo(int key1, int key2) {
        this.key1 = key1;
        this.key2 = key2;
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
    }

    /**
     * Encrypts the message letter by letter with alternate key
     *
     * @param input The string to encrypt 
     * @return The string encrypted with two keys
     */
    public String encryptTwoKeys(String input) {
        StringBuilder encrypted = new StringBuilder(input);
        for(int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            boolean isLower = false;
            if(Character.isLowerCase(currChar)) {
                currChar = Character.toUpperCase(currChar);
                isLower = true;
            }
            int idx = alphabet.indexOf(currChar);
            if(idx != -1) {
                char newChar;
                if(i % 2 == 0) newChar = shiftedAlphabet1.charAt(idx);
                else newChar = shiftedAlphabet2.charAt(idx);
                if(isLower) newChar = Character.toLowerCase(newChar);
                encrypted.setCharAt(i, newChar);
            }
        }
        return encrypted.toString();
    }

    /**
     * Reads the string from a file or stdin, encrypts it with the two keys and prints it
     */
    public void getEncrytedString() {
        Scanner input = new Scanner(System.in);
        System.out.println("Do you want to choose a file(Yes/No)? ");
        String chooseFromFile = input.nextLine(), message;
        while (!(chooseFromFile.equalsIgnoreCase("No") || chooseFromFile.equalsIgnoreCase("Yes") )){ 
            System.out.print("Please enter Yes, No or Exit: "); 
            chooseFromFile = input.nextLine();
        }
        if (chooseFromFile.equalsIgnoreCase("Yes")) { 
            message = new FileResource().asString();
        }
        else { 
            System.out.println("Enter the message to encrypt: ");
            message = input.nextLine();
        }
        String encrypted = encryptTwoKeys(message);
        System.out.println("Encrypted message: " + encrypted);
    }

    /**
     * Decrypts the message with the complement of the keys of the object
     *
     * @param input The string to decrypt
     * @return The decrypted string
     */
    public String decryptTwoKeys(String input) {
        CaesarCipherTwo cc2 = new CaesarCipherTwo(26-key1, 26-key2);
        return cc2.encryptTwoKeys(input);
    }

    /**
     * Reads the encrypted string from a file or stdin, decrypts it and prints it
     */
    public void getDecrytedString() {
        Scanner input = new Scanner(System.in);
        System.out.println("Do you want to choose a file(Yes/No)? ");
        String chooseFromFile = input.nextLine(), encrypted;
        while (!(chooseFromFile.equalsIgnoreCase("No") || chooseFromFile.equalsIgnoreCase("Yes") )){ 
            System.out.print("Please enter Yes or No: "); 
            chooseFromFile = input.nextLine();
        }
        if (chooseFromFile.equalsIgnoreCase("Yes")) { 
            FileResource fr = new FileResource();
            StringBuilder sb = new StringBuilder();
            for(String line : fr.lines()) {
                sb.append(line).append("\n");
            }
            encrypted = sb.toString();
        }
        else { 
            System.out.println("Enter the encrypted message: ");
            encrypted = input.nextLine();
        }
        System.out.println("Decrypted message: \n" + decryptTwoKeys(encrypted));
    }

}
