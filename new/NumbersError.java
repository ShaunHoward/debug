/**
 * Error handling class for the numbers project.
 *
 * @author Shaun Howard
 */
public class NumbersError {

    /**
     * Checks the string value for the proper syntax.
     *
     * @param value - the string to check for the proper syntax
     */
    public static void checkStringSyntax(String value){
        checkZeroString(value);
        checkMultipleOnes(value);
        checkMultipleNegatives(value);
    }

    /**
     * Checks if a string contains zero or naught with
     * other numbers. Throws an exception if so.
     *
     * @param value - the string to check for zero and
     *              other numbers
     */
    public static void checkZeroString(String value){
        if (value.contains("zero") || value.contains("naught")){
            value = value.replaceFirst("zero", "");
            value = value.replaceFirst("naught", "");
            if (!value.isEmpty()) {
                throw new IllegalArgumentException();
            }
        }
    }

    public static void checkMultipleOnes(String value){
//        if (value)
    }

    public static void checkMultipleNegatives(String value){
        int count = 0;
        for (int i = 0; i < value.length(); i++) {
            if (value.contains("minus") || value.contains("negative")) {
                value = value.replaceFirst("minus", "");
                value = value.replaceFirst("negative", "");
                count++;
            }
        }
        if (count > 1){
            throw new IllegalArgumentException();
        }
    }
}
