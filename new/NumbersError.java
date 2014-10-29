import java.util.List;

/**
 * Error handling class for the numbers project.
 *
 * @author Shaun Howard
 */
public class NumbersError {

    /**
     * Checks the syntax of the input string where
     * negative signs and spaces still reside. Throws
     * an exception when any spaces/negatives are used
     * incorrectly.
     *
     * @param value - the string to check for incorrect
     *              negative or space usage
     */
    public static void checkStringSyntax(String value){
        checkSpaceAtFront(value);
        checkSpaceAtEnd(value);
        checkAdjacentSpaces(value);
        checkMultipleNegatives(value);
    }

    /**
     * Checks for a space at the end of the string.
     * Throws exception if there is one.
     *
     * @param value - the string to check for space at
     *              end of
     */
    public static void checkSpaceAtEnd(String value) {
        if (value.lastIndexOf(" ") == value.length()-1) {
            throw new IllegalArgumentException("Cannot have space at end.");
        }
    }

    /**
     * Checks for a space at the front of the string.
     * Throws exception if there is one.
     *
     * @param value - the string to check for space at
     *              front of
     */
    public static void checkSpaceAtFront(String value) {
        if (value.indexOf(" ") == 0){
            throw new IllegalArgumentException("Cannot have space at front.");
        }
    }

    /**
     * Checks for spaces next to each other in the string.
     * Throws an exception if spaces are next to each other.
     *
     * @param value - the string to check for adjacent spaces
     */
    public static void checkAdjacentSpaces(String value) {
       int currSpace = value.indexOf(" ");
        boolean onSpace = false;
        boolean adjacentSpaces = false;
        for (int i = 0; i < value.length(); i++) {
            if (value.charAt(i) == ' ' && onSpace){
                throw new IllegalArgumentException("Cannot have adjacent spaces.");
            } else if (value.charAt(i) == ' ') {
                onSpace = true;
            } else {
                onSpace = false;
            }
        }
    }

    /**
     * Checks if negative is after beginning of word or does not have
     * space after it.
     * Exception is throw if so.
     *
     * @param value - the string to check for the improper use in
     * @param negWord - the negative word to check for in the string
     */
    public static void checkMisplacedNegative(String value, String negWord) {
        int negIndex = value.indexOf(negWord);
        int indexAfterNeg = negIndex + negWord.length();
        if (negIndex > 0){
            throw new IllegalArgumentException("Must put negative at front.");
        }
        if (indexAfterNeg < value.length() && value.charAt(indexAfterNeg) != ' '){
            throw new IllegalArgumentException("Must have space after negative.");
        }
    }

    /**
     * Checks the exploded list of enums for the proper syntax.
     *
     * @param enums - the list to check for the proper syntax
     */
    public static void checkExplodedSyntax(List<SpecialWords> enums){
        checkZeroString(enums);
        //Check adjacent ones values.
        checkAdjacentValues(enums, 1, 9);
        //Check adjacent teens values.
        checkAdjacentValues(enums, 9, 19);
        //Check adjacent nty values.
        checkAdjacentValues(enums, 19, 90);
        //Check hundred modifier.
        checkHundredModifier(enums);
        //Check thousand modifier.
        //Check million modifier.
        //Check thousand - million

    }

    /**
     * Checks if the word "hundred" has a modifier.
     * Throws an exception if not.
     *
     * @param enums - the enums to check if hundred has a modifier in
     */
    public static void checkHundredModifier(List<SpecialWords> enums) {
        boolean hadHundred = false;
        for (int i = enums.size() - 1; i >= 0; i--) {
            if (enums.get(i).value == 100){
                hadHundred = true;
            } else if (hadHundred && !isInRange(enums.get(i).value, 0, 9)) {
                throw new IllegalArgumentException("Need proper hundreds modifier.");
            } else {
                hadHundred = false;
            }
        }
        throwExceptionWhenTrue(hadHundred);
    }

    /**
     * Throws an exception when the given boolean is true.
     *
     * @param boolToCheck - the boolean to check
     */
    private static void throwExceptionWhenTrue(boolean boolToCheck) {
        if (boolToCheck){
            throw new IllegalArgumentException("Improper modifier on number.");
        }
    }

    /**
     * Checks if the given value is in the selected range.
     *
     * @param value - value to range check
     * @param start - start of range
     * @param end - end of range
     * @return whether the value is in the given range
     */
    public static boolean isInRange(int value, int start, int end){
        if (start < value && value <= end) {
            return true;
        }
        return false;
    }

    /**
     * Checks if the list contains zero or naught with
     * other numbers. Throws an exception if so.
     *
     * @param enums - the list to check for zero and
     *              other numbers
     */
    public static void checkZeroString(List<SpecialWords> enums){
        if (enums.contains(SpecialWords.zero)) {
            enums.remove(SpecialWords.zero);
            if (!enums.isEmpty()) {
                throw new IllegalArgumentException("Zero must be alone.");
            }
        }
        if (enums.contains(SpecialWords.naught)) {
            enums.remove(SpecialWords.naught);
            if (!enums.isEmpty()) {
                throw new IllegalArgumentException("Naught must be alone.");
            }
        }
    }

    /**
     * Checks for adjacent values in the string.
     * Exception is thrown if adjacent values are found.
     *
     * @param enums - the enums to check for adjacent values
     */
    public static void checkAdjacentValues(List<SpecialWords> enums, int rangeStart, int rangeEnd){
        boolean hadValue = false;
        for (SpecialWords word: enums){
            if (isInRange(word.value, rangeStart, rangeEnd)){
                if (hadValue) {
                    throw new IllegalArgumentException("Cannot have adjacent values.");
                }
                hadValue = true;
            } else {
                hadValue = false;
            }
        }

    }

    /**
     * Checks for multiple negatives in the string.
     * Exception is thrown if multiple are found.
     *
     * @param value - the string to check for multiple negatives
     */
    public static void checkMultipleNegatives(String value){
        int count = 0;
        for (int i = 0; i < value.length(); i++) {
            if (value.contains("minus")) {
                checkMisplacedNegative(value, "minus");
                value = value.replaceFirst("minus", "");
                count++;
            }
            if (value.contains("negative")){
                checkMisplacedNegative(value, "negative");
                value = value.replaceFirst("negative", "");
                count++;
            }
        }
        if (count > 1){
            throw new IllegalArgumentException("Cannot have multiple negatives.");
        }
    }
}
