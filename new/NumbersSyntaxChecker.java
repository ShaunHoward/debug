import java.util.List;

/**
 * 
 * @author Henry
 *
 */
public class NumbersSyntaxChecker {
	//I keep track of which types of numbers can be in this place, if its wrong it will cause an error.
	private boolean doesntHaveMillion;
	private boolean canBeMultiplier;
	private boolean doesntHaveThousand;
	private boolean doesntHaveHundred;
	private boolean canBeHundreds;
	private boolean canBeTens;
	private boolean canBeOnes;
	
	/**
	 * Initialize The syntax checker with its default values for the booleans.
	 */
	public NumbersSyntaxChecker(){
		doesntHaveMillion = true;
		canBeMultiplier = true;
		doesntHaveThousand = true;
		doesntHaveHundred = true;
		canBeHundreds = true;
		canBeTens = true;
		canBeOnes = true;
	}
	
	/**
	 * This method will check to make sure the syntax of the string is correct.
	 * @param words The words that comprise the string.
	 * @return True if correct, false if not.
	 */
	public void checkSyntax(List<SpecialWords> words) {
		//The input can't be empty. Also zero can't be in the string if they are not alone.
		if(words.size() == 0 || words.contains(SpecialWords.zero)){
			throw new IllegalArgumentException();
		}
		//Iterate through the words.
		for(int i = 0; i < words.size(); i++){
			SpecialWords currentWord = words.get(i);
			processWord(currentWord.value);
		}//Ending for loop
	}

	/**
	 * A method which directs to the correct case method depending on the input value.
	 */
	private void processWord(int value) {
        //Next we check if it can be hundred.
        if(value == 100){
            hundredCase();
        }
        //Next we check if it can be 1000
        else if(value == 1000){
            thousandCase();
        }
        //Lastly we check if it can be 1000000
        else if(value == 1000000){
            millionCase();
        }
        //First we check the words that just take up the ones place.
        else if(value <= 9){
            onesCase();
        }
        //Next we check ten and the teens, as they occupy the tens and ones place.
        else if(value <= 19){
            teensCase();
        }
        //Next we check the n*10 words. not including 10.
        else if(value <= 90){
            ntyCase();
        }
	}

	/**
	 * How to handle the booleans when the word contains a value between 0 and 9.
	 */
	private void onesCase(){
		if(canBeOnes){
			//If the tens place is still open, a hundred can still go after.
			if(canBeTens){
				canBeHundreds = true;
			}
			canBeMultiplier = true;
			canBeTens = false;
		}
		else{
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * How to handle the booleans when the word contains a value between 10 and 19.
	 */
	private void teensCase() {
		canBeTens = false;
		canBeOnes = false;
		canBeHundreds = false;
		canBeMultiplier = true;	
	}
	
	/**
	 * How to handle when the value of the word is between 20 and 90.
	 */
	private void ntyCase() {
		//The ones place will be open, but you can't have things like twenty hundred.
		if(canBeTens){
			canBeOnes = true;
			canBeTens = false;
			canBeMultiplier = true;
		}
		else{
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * How to handle the booleans when the word contains the value of 100.
	 */
	private void hundredCase() {
		//Tens and ones can go after 100 always, so i set those to true.
		if(canBeHundreds && doesntHaveHundred){
			canBeHundreds = false;
			doesntHaveHundred = false;
			canBeTens = true;
			canBeOnes = true;
		}
		else{
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * How to handle the booleans when the word has the value of 1000.
	 */
	private void thousandCase() {
		//It pretty much resets it, except we can't have any more thousands and million can't be after here.
		if(canBeMultiplier && doesntHaveThousand){
			doesntHaveThousand = false;
			doesntHaveMillion = false;
			doesntHaveHundred = true;
			canBeHundreds = false;
			canBeTens = true;
			canBeOnes = true;
			canBeMultiplier = false;
		}
		else{
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * How to handle the booleans when the word holds the value of 1000000.
	 */
	private void millionCase() {
		//It pretty much resets it, but no more millions.
		if(canBeMultiplier && doesntHaveMillion){
			doesntHaveThousand = false;
			doesntHaveMillion = false;
			doesntHaveHundred = true;
			canBeHundreds = false;
			canBeTens = true;
			canBeOnes = true;
			canBeMultiplier = false;
		}
		else{
			throw new IllegalArgumentException();
		}
	}
}
