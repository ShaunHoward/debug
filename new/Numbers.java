import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class Numbers {

	/**
	 * The primary method of this class, which converts a string to an int.
	 * @param inputString - the input to convert to an int
	 * @return the integer representation of the string.
	 */
	public static int stringToInteger(String inputString){
		//Convert everything to lower case so its consistent.
		inputString = inputString.toLowerCase();
		
		//First handle the zero or naught case as they cannot be with other words. 
		if(inputString.equals("zero") || inputString.equals("naught")){
			return 0;
		}
		
		//We will multiply the final value by this to ensure it handles the negative. Its just reducing complexity.
		int negativeMultiplier = 0;

		//Checks if it starts with negative, then removes it
		if(inputString.startsWith("negative")){
			inputString = inputString.replaceFirst("negative", "");
			negativeMultiplier = -1;
		}
		//Checks if it starts with minus, then removes it
		else if(inputString.startsWith("minus")){
			inputString = inputString.replaceFirst("minus", "");
		}
		
		//If it all works out, send it to the next method.
		return computeInteger(inputString);
	}
	
	/**
	 * Calls the explode, check syntax, and compute methods.
	 * @param inputString The string to convert to int.
	 * @return The integer representation of the string.
	 */
	private static int computeInteger(String inputString) {
		List<SpecialWords> words = explodeString(inputString);
        SpecialWords keyWord = null;
		
		//Check the remaining syntax, to make sure it will not yield strange results.
		NumbersSyntaxChecker syntaxChecker = new NumbersSyntaxChecker();
		syntaxChecker.checkSyntax(words);

        keyWord = words.get(words.size()-1);
		
		//Then add them all together to be returned.
		return computeInteger(words);
	}
	
	/**
	 * Computes the integer from the list of special words.
     *
	 * @param words - the list of words to convert to an integer
	 * @return The integer corresponding to these words.
	 */
	private static int computeInteger(List<SpecialWords> words) {
		int currentValue = 0;
		int totalValue = 0;

		//loop through each word, performing the action that is required for that word.
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
	 * @param s The string to separate.
	 * @return The words that make up the string.
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
	 * @param args The phrase to turn into a number.
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
