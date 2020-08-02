
/**
 * Breaks the encrypted caesar cipher message without the keys
 * 
 * @author Nishith
 * @version 02-08-2020
 */
public class TestCaesarCipherTwo {
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

    public String halfOfString(String message, int start) {
        StringBuilder halfString = new StringBuilder();
        for(int i = start; i < message.length(); i++) {
            if((i-start)%2==0) halfString.append(message.charAt(i));
        }
        return halfString.toString();
    }

    public int getKey(String s) {
        // String alphs = "abcdefghijklmnopqrstuvwxyz";
        int genMostOccuredLetter = 4;
        int[] freqs = countLetters(s);
        int maxDex = maxIndex(freqs);
        int dKey = maxDex - genMostOccuredLetter;
        if(maxDex < genMostOccuredLetter) {
            dKey = 26 - (genMostOccuredLetter - maxDex);
        }
        return dKey;
    }

    public String breakCaesarCipherTwo(String encrypted) {
        String stringKey1 = halfOfString(encrypted, 0);
        int dKey1 = getKey(stringKey1);
        String stringKey2 = halfOfString(encrypted, 1);
        int dKey2 = getKey(stringKey2);
        CaesarCipherTwo cc2 = new CaesarCipherTwo(dKey1, dKey2);
        StringBuilder decryptedString = new StringBuilder();
        for(int i = 0, s1Counter = 0, s2Counter = 0; i < encrypted.length(); i++) {
            if(i%2==0) {
                decryptedString.append(stringKey1.charAt(s1Counter));
                s1Counter++;
            } else {
                decryptedString.append(stringKey2.charAt(s2Counter));
                s2Counter++;
            }
        }
        return cc2.decryptTwoKeys(decryptedString.toString());
    }
}
