import java.util.List;

/**
 * Error handling class for the numbers project.
 *
 * @author Shaun Howard
 */
public class NumbersError {

    public static void checkStringSyntax(String value){
        checkSpaceAtFront(value);
        checkSpaceAtEnd(value);
        checkAdjacentSpaces(value);
        checkMultipleNegatives(value);
    }

    private static void checkSpaceAtEnd(String value) {
    }

    private static void checkSpaceAtFront(String value) {

    }

    private static void checkAdjacentSpaces(String value) {
       int currSpace = value.indexOf(" ");
        boolean onSpace = false;
        boolean adjacentSpaces = false;
        for (int i = 0; i < value.length(); i++) {
            if (value.charAt(i) == ' ' && onSpace){
                throw new IllegalArgumentException();
            } else if (value.charAt(i) == ' ') {
                onSpace = true;
            } else {
                onSpace = false;
            }
        }
    }

    private static void checkMisplacedNegative(String value, String negWord) {

        if (value.indexOf(negWord) > 0){
            throw new IllegalArgumentException();
        }
    }

    /**
     * Checks the exploded list of enums for the proper syntax.
     *
     * @param enums - the list to check for the proper syntax
     */
    public static void checkExplodedSyntax(List<SpecialWords> enums){
        checkZeroString(enums);
        checkMultipleOnes(enums);
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
                throw new IllegalArgumentException();
            }
        }
        if (enums.contains(SpecialWords.naught)) {
            enums.remove(SpecialWords.naught);
            if (!enums.isEmpty()) {
                throw new IllegalArgumentException();
            }
        }
    }

    public static void checkMultipleOnes(List<SpecialWords> enums){


    }

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
            throw new IllegalArgumentException();
        }
    }
}
