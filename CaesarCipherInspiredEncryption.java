import java.io.*;

/*
 * Using the Ceasar Cipher to Encrypt messages.
 * Using all lowercase.
 * Limitation: Uppercase will not work.
 * a = 97 through z = 122
 * Move letters 3 spaces to the right.
 * a -> d
 * b -> e
 * c -> f
 * 
 * Supported non lowercase characters: space, exclamation mark, comma, period, question mark.
 */
public class CaesarCipherInspiredEncryption {
    public static void main(String[] args) throws Exception {
        // replace "your text goes here" with the text you would like to encrypt
        String originalText = "your text goes here";
        // after running program a file named "EncryptedTextFile.txt" will be created in the root directory
        writeToFile(originalText, useCeasarCipher(originalText));
    }

    /**
     * Testing lowercase character / non character inputs and the numeric values associated with them.
     * Making sure the application can wrap around to the beginning of alphabet.
     */
    public static void testingInputs() {
        String abc = "abc";
        String xyz = "xyz";
        String text = "";
        StringBuilder stringBuilder = new StringBuilder();

        System.out.println("[Line of Text] -> " + abc);
        for (int i = 0; i < abc.length(); i++) {
            System.out.println("Character at index " + i + " is " + abc.charAt(i));
            int value = (int) abc.charAt(i);
            System.out.println("Numerical value for " + abc.charAt(i) + " is " + value);
            System.out.println();
        }

        char period = '.';
        int valueOfPeriod = (int) period;
        System.out.println("The value of a \".\" : " + valueOfPeriod);

        char exclamationMark = '!';
        int valueOfExclamationMark = (int) exclamationMark;
        System.out.println("The value of a \"!\" : " + valueOfExclamationMark);

        char whiteSpace = ' ';
        int valueOfWhiteSpace = (int) whiteSpace;
        System.out.println("The value of a white space \" \" : " + valueOfWhiteSpace);
    }

    /**
     * Used to encrypt text.
     * 
     * @param input Desired user input.
     * @return The encrypted text.
     * @throws Exception Needed for StringBuilder.
     */
    public static String useCeasarCipher(String input) throws Exception {
        StringBuilder stringBuilder = new StringBuilder();

        int value = 0;
        char currentChar = ' ';
        for (int i = 0; i < input.length(); i++) {
            currentChar = input.charAt(i);
            value = (int) currentChar;

            // Test for space, period, comma and exclamationMark
            // space = 32 , exclamationMark = 33 , comma = 44 , period = 46, question mark =
            // 63
            if (isNonCharacter(value)) {
                stringBuilder.append(returnNonCharacter(value));
            }
            // lowercase character
            else {
                stringBuilder.append(returnEncrptedCharacter(value));
            }
        }
        return stringBuilder.toString();
    }

    /**
     * Uses the numeric value to determine if a character is a not a lowercase character.
     * space = 32 , exclamationMark = 33 , comma = 44 , period = 46, question mark = 63
     * 
     * @param value
     * @return true or false which determines if the character is not a lowercase character
     */
    public static boolean isNonCharacter(int value) {
        boolean flag = false;

        if (value == 32 || value == 33 || value == 44 || value == 46 || value == 63) {
            flag = true;
        }
        return flag;
    }

    /**
     * 
     * @param value Numeric value for non lowercase character.
     * @return String representing a non lowercase character. Using a String so the result can be appended to StringBuilder.
     * @throws Exception Unknown / unsupported non lowercase character. Displays numeric value.
     */
    public static String returnNonCharacter(int value) throws Exception {
        String nonCharacter;
        // space
        if (value == 32) {
            nonCharacter = " ";
        }
        // exclamationMark
        else if (value == 33) {
            nonCharacter = "!";
        }
        // comma
        else if (value == 44) {
            nonCharacter = ",";
        }
        // period
        else if (value == 46) {
            nonCharacter = ".";
        } else if (value == 63) {
            nonCharacter = "?";
        } else {
            throw new Exception("Unknown Non Character. Numeric value is: " + value);
        }

        return nonCharacter;
    }

    /**
     * Returns the encrypted character for lowercase character.
     * 
     * @param value Lowercase character.
     * @return Encrypted lowercase character.
     * @throws Exception If numeric value of encrypted character is unkown or does not fall between 97 - 122 .
     */
    public static String returnEncrptedCharacter(int value) throws Exception {
        // if new value is greater than z, wrap around
        int valueOfZ = 122;
        int newValue = value + 3;
        int wrappedAroundValue;
        char encryptedCharacter;

        // need to wrap around
        if (newValue > valueOfZ) {
            // lowercase values start at 97
            // using 96 as starting point
            wrappedAroundValue = 96 + (newValue - valueOfZ);
            encryptedCharacter = (char) wrappedAroundValue;
        }
        // number does not exceed value of Z, add 3 and update character
        else if (newValue <= valueOfZ) {
            encryptedCharacter = (char) newValue;
        } else {
            throw new Exception("Unknown character numeric value is: " + newValue);
        }
        return String.valueOf(encryptedCharacter);
    }

    /**
     * Writes the original text and encrypted text to a file (EncryptedTextFile.txt).
     * 
     * @param originalText  Original text / message.
     * @param encryptedText Encrypted text resulting from original text / message.
     * @throws IOException Throws in the case of not enough space or error creating file.
     */
    public static void writeToFile(String originalText, String encryptedText) throws IOException {
        File encryptedTextFile = new File("EncryptedTextFile.txt");
        FileWriter fileWriter = new FileWriter(encryptedTextFile, true);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.println("-------------------------------------");
        printWriter.println("Original Text: " + originalText);
        printWriter.println(" ");
        printWriter.println("Encrypted Text: " + encryptedText);
        printWriter.println("-------------------------------------");
        printWriter.println(" ");

        // close
        printWriter.close();
        fileWriter.close();
    }
}
