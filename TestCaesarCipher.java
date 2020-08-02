import edu.duke.FileResource;
import java.util.Scanner;
/**
 * Breaks the encrypted caesar cipher message without the key
 * 
 * @author Nishith
 * @version 01-08-2020
 */
public class TestCaesarCipher {
    /**
     * Finds the index with the maximum value in the array
     *
     * @param values The array to find the maximum value of
     * @return The index of the maximum value
     */
    public int maxIndex(int[] values) {
        int maxIndex = 0;
        for(int i = 0; i < values.length; i++) {
            if(values[i] > values[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    /**
     * Counts the number of times every letter occurs in the string 
     *
     * @param message The string to count the letters of
     * @return The array containing the counts
     */
    public int[] countLetters(String message) {
        String alphs = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for(int k = 0; k < message.length(); k++) {
            char ch = Character.toLowerCase(message.charAt(k));
            int index = alphs.indexOf(ch);
            if(index != -1) {
                counts[index]++;
            }
        }
        return counts;
    }

    /**
     * Trys to decrypts the ceasar cipher encrypted message without the key 
     * according to the letter passed
     *
     * @param encrypted The encrypted message
     * @param ch The letter to consider as most occured
     * @return The decrpyted string 
     */
    public String breakCaesarCipher(String encrypted, char ch) {
        String alphs = "abcdefghijklmnopqrstuvwxyz";
        int genMostOccuredLetter = alphs.indexOf(ch) + 1;
        int[] freqs = countLetters(encrypted);
        int maxDex = maxIndex(freqs);
        int dKey = maxDex - genMostOccuredLetter;
        if(maxDex < genMostOccuredLetter) {
            dKey = 26 - (genMostOccuredLetter - maxDex);
        }
        CaesarCipher cc = new CaesarCipher(26-dKey);
        return cc.encrypt(encrypted);
    }
    
    /**
     * Tests the breakCaesarCipher() method with the input of the user
     */
    public void simpleTests() {
        char[] lettersAsPerFreq = {'e', 't', 'a', 'r', 'i', 'o', 'n', 's', 'h', 'd', 'l', 'u', 'w', 'm', 'f', 'c', 'g', 'y', 'p', 'b', 'k', 'v', 'j', 'x', 'q', 'z'};
        Scanner input = new Scanner(System.in);
        System.out.println("Do you want to choose a file(Yes/No)? ");
        String chooseFromFile = input.nextLine(), encrypted;
        while (!(chooseFromFile.equalsIgnoreCase("No") || chooseFromFile.equalsIgnoreCase("Yes") )){
            System.out.print("Please enter Yes, No or Exit: "); 
            chooseFromFile = input.nextLine();
        }
        if (chooseFromFile.equalsIgnoreCase("Yes")) {
            encrypted = new FileResource().asString();
        }
        else {
            System.out.println("Enter the encrypted string: ");
            encrypted = input.nextLine();
        }
        int pos = 0;
        do{
            System.out.println(breakCaesarCipher(encrypted, lettersAsPerFreq[pos]));
            System.out.print("Is the method successfully decrypted(Yes/No/Exit)? ");
            String success = input.nextLine();
            while (!(success.equalsIgnoreCase("No") || success.equalsIgnoreCase("Yes") || success.equalsIgnoreCase("Exit"))){ 
                System.out.print("Please enter Yes or No: "); 
                success = input.nextLine();
            }
            if (success.equalsIgnoreCase("No")) pos++;
            else if (success.equalsIgnoreCase("Yes")) break;
            else {
                System.out.println("Exiting the program...");
                System.exit(0);
            }
        } while(pos < lettersAsPerFreq.length);
        if(pos < lettersAsPerFreq.length) {
            System.out.println("The message could not be decrypted :(");
        }
    }
}
