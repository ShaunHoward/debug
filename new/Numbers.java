import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Class for converting a string into an integer, if possible.
 *
 * @author Henry, Shaun Howard
 */
public class Numbers {

	/**
	 * The primary method of this class, which converts a string to an int.
	 * @param inputString - the input to convert to an int
	 * @return the integer representation of the string
	 */
	public static int stringToInteger(String inputString){
		//Convert everything to lower case so its consistent.
		inputString = inputString.toLowerCase();
		
		//First handle the zero or naught case as they cannot be with other words. 
		if(inputString.equals("zero") || inputString.equals("naught")){
			return 0;
		}

        //Check if the input string has erroneous syntax.
        NumbersError.checkStringSyntax(inputString);
		
		//We will multiply the final value by this to ensure it handles the negative.
		int negativeMultiplier = 1;

		//Checks if it string starts with negative, then removes it.
		if(inputString.startsWith("negative") || inputString.startsWith("minus")){
			inputString = inputString.replace("negative", "");
            inputString = inputString.replace("minus", "");
			negativeMultiplier = -1;
            inputString = inputString.trim();
		}

		//Send to compute rest of number at this point.
		return negativeMultiplier * computeInteger(inputString);
	}
	
	/**
	 * Calls the explode, check syntax, and compute methods.
	 * @param inputString - the string to convert to int
	 * @return the integer representation of the string
	 */
	private static int computeInteger(String inputString) {
        //Gather list of special words from exploding the string.
		List<SpecialWords> words = explodeString(inputString);
		
		//Check the remaining syntax, to make sure it will not yield strange results.
		NumbersSyntaxChecker syntaxChecker = new NumbersSyntaxChecker();
		syntaxChecker.checkSyntax(words);
		
		//Computer the actual integer with the checked special words.
		return computeInteger(words);
	}
	
	/**
	 * Computes the integer from the list of special words.
     *
	 * @param words - the list of words to convert to an integer
	 * @return the integer corresponding to these words
	 */
	private static int computeInteger(List<SpecialWords> words) {
		int currentValue = 0;
		int totalValue = 0;

		//Loop through each word, performing the action that is required for that word.
		for(int i = 0; i < words.size(); i++){

            SpecialWords currentWord = words.get(i);
			if(currentWord.value < 100){
				currentValue += currentWord.value;
			}
			else if(currentWord.value == 100){
				currentValue *= currentWord.value;
			}
            else if (currentWord.value >= 1000){
                currentValue *= currentWord.value;
                totalValue += currentValue;
                currentValue = 0;
            }
		}

		totalValue += currentValue;
		
		return totalValue;
	}

	/**
	 * A method to separate a string into its words.
     * Gathers the enumerated types of numbers into a list
     * and returns the enum list.
	 * @param s - the string to separate
	 * @return the words that make up the string as enums
	 */
	private static List<SpecialWords> explodeString(String s){
        List<String> words = Arrays.asList(s.split(" "));
		List<SpecialWords> enumWords = new ArrayList<SpecialWords>();
        System.out.println(words.toString());

		for(int j = 0; j < words.size(); j++){
			enumWords.add(SpecialWords.valueOf(words.get(j)));
		}
		return enumWords;
	}

	/**
     * Turns a string into a number.
     *
	 * @param args - the phrase to turn into a number
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		StringBuilder inputStringBuilder = new StringBuilder();
        while (in.hasNext()) {
            inputStringBuilder.append(in.nextLine());
        }
        in.close();
        System.out.println(stringToInteger(inputStringBuilder.toString()));
        System.exit(7);

	}

}
