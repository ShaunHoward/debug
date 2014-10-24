import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Numbers {
	/**
	 * The primary method of this class, which converts a string to an int.
	 * @param inputString The input to convert to an int.
	 * @return The integer representation of the string.
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
		
		//Check the remaining syntax, to make sure it will not yield strange results.
		NumbersSyntaxChecker syntaxChecker = new NumbersSyntaxChecker();
		syntaxChecker.checkSyntax(words);
		
		//Then add them all together to be returned.
		return computeInteger(words);
	}
	
	/**
	 * Deals with all of the words before the desired value.
	 * Used for dealing with millions, and thousands all seperately
	 * @param words The list of words left to deal with.
	 * @param keyWord The last value to deal with.
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
			else{
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
		int lastSpace = s.indexOf(' ');
		List<String> words = new ArrayList<String>();
		//Loop through the string
		for(int i = 0; i < s.length(); i++){
			//If its a space
			if(s.charAt(i) == ' '){
				//Add the substring to the list.
				words.add(s.substring(lastSpace, i));
				lastSpace = i;
			}
		}
		//Add last word, which didn't have a space
		words.add(s.substring(lastSpace));
		List<SpecialWords> enumWords = new ArrayList<SpecialWords>();
		for(int j = 0; j <= words.size(); j++){
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
