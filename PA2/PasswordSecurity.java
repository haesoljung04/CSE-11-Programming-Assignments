/**
 * Name: Haesol Jung
 * Email: haj008@ucsd.edu
 * PID: A17348180
 * Sources used: Textbook, Lecture Notes, Prior Knowledge, 
 * Oracle Doc, write-up
 * 
 * This file is used to complete Programming Assignment 2.
 * It will receive input from the user and output the strength
 * of the password and also output any suggestions to make the 
 * password strong.
 */

 //Import Scanner library
import java.util.Scanner;

/**
 * This class stores the password of the user and organizes 
 * the type and amount of characters in the input. It can 
 * display the strength of the password and any suggestions
 * if needed.
 * 
 */
public class PasswordSecurity {

    /**Constants (Magic Numbers) */
    private static final String PW_PROMPT = "Please enter a password: ";
    private static final String PW_TOO_SHORT = "Password is too short";
    private static final String PW_VERY_WEAK = "Password strength: very weak";
    private static final String PW_WEAK = "Password strength: weak";
    private static final String PW_MEDIUM = "Password strength: medium";
    private static final String PW_STRONG = "Password strength: strong";
    private static final String SUGGESTION_PROMPT = "Here is a suggested" 
        + " stronger password: ";
    private static final String LETTER_RULE_SUGGESTION = "Cse";
    private static final String SYMBOL_RULE_SUGGESTION = "@!";
    private static final int MIN_PW_LENGTH = 8;
    private static final int VERY_WEAK_THRESHOLD = 1;
    private static final int WEAK_THRESHOLD = 2;
    private static final int MEDIUM_THRESHOLD = 3;
    private static final int STRONG_THRESHOLD = 4;
    private static final int LETTER_COUNT_THRESHOLD = 2;
    private static final int DIGIT_INTERVAL = 4;
    private static final int MOD_FACTOR = 10;
    private static final char ARBITRARY_CHAR = 'a';

    /**
     * This method prints to the console the password strength
     * and a suggested strong password if the password is not 
     * strong.
     * @param args allows compiler to pass command line arguments
     * in the program
     */
    public static void main (String[] args) {
        //Create a Scanner object
        Scanner input = new Scanner(System.in);
        //Prompt the user for password
        System.out.print(PW_PROMPT);
        //Read and store user input and length
        String password = input.nextLine();
        int pwLength = password.length();
        //If password is less than 8 characters, print and exit
        if (pwLength < MIN_PW_LENGTH) {
            System.out.println(PW_TOO_SHORT);
            return;
        }
        //Determine if the password contains Uppercase letters
        int uppercase = 0;
        for (int i = 0; i < pwLength; i++) {
           if (Character.isUpperCase(password.charAt(i))) {
              uppercase++;
           }
        }
        //Determine if the password contains lowercase letters
        int lowercase = 0;
        for (int i = 0; i < pwLength; i++) {
           if (Character.isLowerCase(password.charAt(i))) {
              lowercase++;
           }
        }
        //Determine if the password contains numbers
        int number = 0;
        for (int i = 0; i < pwLength; i++) {
           if (Character.isDigit(password.charAt(i))) {
              number++;
              break;
           }
        }
        //Determine if the password contains symbols
        int symbol = 0;
        for (int i = 0; i < pwLength; i++) {
           if (Character.isLetterOrDigit(password.charAt(i))) {
           }
           else {
               symbol++;
               break;
           }
        }
        /** Instance variables */
        char firstUppercase = ARBITRARY_CHAR;
        int position = 0;
        char highestLowercase = ARBITRARY_CHAR;
        //Suggestion 1 code applies if there are fewer than 2 letters
        if ((uppercase + lowercase) < LETTER_COUNT_THRESHOLD) {
            String suggestion1 = (LETTER_RULE_SUGGESTION + password);
            password = suggestion1;
        }
        
        /* Suggestion 2: using character array by converting password 
         * into character array, replacing lowercase with uppercase 
         * and back into string
         */
        else if (lowercase == 0) {
            for (int i = 0; i < pwLength; i++) {
                if (Character.isUpperCase(password.charAt(i))) {
                    firstUppercase = password.charAt(i);
                    position = i;
                    break;
                }
            }
        char lowercaseReplacement = Character.toLowerCase(firstUppercase);
        char[] passwordArray = password.toCharArray();
        passwordArray[position] = lowercaseReplacement;
        String suggestion2 = String.valueOf(passwordArray);
        password = suggestion2;
        }
        /* Suggestion 3
         * Same procedure of correction as suggestion 2
         */
        else if (uppercase == 0) {
            for (int i = 0; i < pwLength; i++) {
                if (password.charAt(i) > highestLowercase) {
                    highestLowercase = password.charAt(i);
                }
            }
        char uppercaseReplacement = Character.toUpperCase(highestLowercase);  
        int lastOccurence = password.lastIndexOf(highestLowercase);
        char[] passwordArray = password.toCharArray();
        passwordArray[lastOccurence] = uppercaseReplacement;
        String suggestion3 = String.valueOf(passwordArray);
        password = suggestion3; 
        }
        //Suggestion 4
        /* Create a StringBuilder object and insert k every 4
         * characters.
         * Special case: if the current length of the password mod 4
         * is zero, then put k at the end of the password as well
         */
        StringBuilder built = new StringBuilder(password);
        if (number == 0) {
            int k = pwLength % MOD_FACTOR;
            int currentLength = password.length();
            for (int i = DIGIT_INTERVAL; i < currentLength; i += (DIGIT_INTERVAL + 1)) {
                built.insert(i, k);
                currentLength++;
            }
            if (password.length() % DIGIT_INTERVAL == 0) {
                built.append(k);
            }
        String suggestion4 = built.toString();
        password = suggestion4;
        }
        //Suggestion 5: using String Concatenation
        if (symbol == 0) {
            password = password.concat(SYMBOL_RULE_SUGGESTION); 
        }
        /*Print calculated password strength based on how
         * many categories out of the 4 the password fulfills 
         */ 
        if (uppercase > 1) {
            uppercase = 1;
        }
        if (lowercase > 1) {
            lowercase = 1;
        }
        int pwStrength = (uppercase + lowercase + number + symbol);
        switch (pwStrength) {
            case VERY_WEAK_THRESHOLD:
                System.out.println(PW_VERY_WEAK);
                break;
            case WEAK_THRESHOLD:
                System.out.println(PW_WEAK);
                break;
            case MEDIUM_THRESHOLD:
                System.out.println(PW_MEDIUM);
                break;
            case STRONG_THRESHOLD:
                System.out.println(PW_STRONG);
                return;
        }
        //Print strong suggested password
        System.out.println(SUGGESTION_PROMPT + password);
    }
}