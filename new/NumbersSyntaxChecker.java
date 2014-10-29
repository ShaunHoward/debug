import java.util.List;

/**
 * Class for checking the syntax of the numbers parsed from the
 * input number string.
 *
 * @author Henry, Shaun Howard
 *
 */
public class NumbersSyntaxChecker {
	//I keep track of which types of numbers can be in this place, if its wrong it will cause an error.
	private boolean canBeMultiplier;
	private boolean canBeHundreds;
	private boolean canBeTens;
	private boolean canBeOnes;
    private boolean canBeMillion;
	
	/**
	 * Initialize The syntax checker with its default values for the booleans.
	 */
	public NumbersSyntaxChecker(){
        canBeMillion = true;
		canBeMultiplier = true;
		canBeHundreds = true;
		canBeTens = true;
		canBeOnes = true;
	}
	
	/**
	 * This method will check to make sure the syntax of the string is correct.
	 * @param words The words that comprise the string.
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
	 * A method which determines a case for the given value.
     *
     * @param value - the int value to determine the case of
	 */
	private void processWord(int value) {

        //Check if over 90 case (100, 1000, 1000000).
        checkOver90(value);

        //Then we check the ones case.
        if(value <= 9){
            onesCase();
        }
        //After checking ones, check tens case.
        else if(value <= 19){
            teensCase();
        }
        //Last we check the n*10 words, where n <= 9, not including 10.
        else if(value <= 90){
            ntyCase();
        }
	}

    /**
     * Checks the given value to see if its value
     * is an over 90 case.
     *
     * @param value - value to check if over 90
     */
    private void checkOver90(int value){
        //We begin by checking the hundreds case.
        if(value == 100){
            hundredCase();
        }
        //Next we check the thousands case.
        else if(value == 1000){
            thousandCase();
        }
        //Next we check the millions case.
        else if(value == 1000000){
            millionCase();
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
            canBeOnes = false;
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
        if (canBeTens) {
            canBeTens = false;
            canBeOnes = false;
            canBeHundreds = false;
            canBeMultiplier = true;
        } else {
            throw new IllegalArgumentException();
        }
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
		//Tens and ones can go after 100 always, so set those to true.
		if(canBeHundreds){
			canBeHundreds = false;
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
		if(canBeMultiplier){
            canBeMillion = false;
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
		if(canBeMultiplier && canBeMillion){
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
