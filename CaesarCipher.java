import edu.duke.FileResource;
import java.util.Scanner;
/**
 * Encrypts and decrypts messages with the key specified
 * 
 * @author Nishith
 * @version 01-08-2020
 */
public class CaesarCipher {
    private String alphabet, shiftedAlphabet;
    private int mainKey;
    /**
     * CaesarCipher Constructor
     *
     * @param key The key for the caesar cipher
     */
    public CaesarCipher(int key) {
        if (key > 25) key -= 26;
        mainKey = key;
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
    }

    /**
     * Encrypts the message with the key of the object
     *
     * @param input The string to encrypt
     * @return The encrypted string
     */
    public String encrypt(String input) {
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
                char newChar = shiftedAlphabet.charAt(idx);
                if(isLower) newChar = Character.toLowerCase(newChar);
                encrypted.setCharAt(i, newChar);
            }
        }
        return encrypted.toString();
    }

    /**
     * Reads the string from a file or stdin, encrypts it and prints it
     */
    public void getEncryptedString() {
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
        String encrypted = encrypt(message);
        System.out.println("Encrypted message: " + encrypted);
        if(message.equals(encrypted)) System.out.println("As the key was a multiple of 26, "
        + "the encrypted message is same as the original one");
    }

    /**
     * Decrypts the message with the complement of the key of the object
     *
     * @param input The string to decrypt
     * @return The decrypted string
     */
    public String decrypt(String input) {
        CaesarCipher cc = new CaesarCipher(26-mainKey);
        return cc.encrypt(input);
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
        System.out.println("Decrypted message: \n" + decrypt(encrypted));
    }
    
    public static void main(String[] args) {
        System.out.println("KEY: ");
        CaesarCipher cc = new CaesarCipher(new Scanner(System.in).nextInt());
        System.out.println("\f");
        cc.getEncryptedString();
    }
}
