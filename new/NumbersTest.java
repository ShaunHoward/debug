import static org.junit.Assert.*;

import org.junit.Test;


public class NumbersTest {

    //Test one word cases
    @Test
    public void testOneWordNumbers(){
        assertEquals(Numbers.stringToInteger("two"), 2);
        assertEquals(Numbers.stringToInteger("ten"), 10);
        assertEquals(Numbers.stringToInteger("nineteen"), 19);
        assertEquals(Numbers.stringToInteger("twenty"), 20);
        assertEquals(Numbers.stringToInteger("fifty"), 50);
        assertEquals(Numbers.stringToInteger("ninety"), 90);
    }

    //Test two word cases
    @Test
    public void testTwoWordNumbers(){
        assertEquals(Numbers.stringToInteger("two hundred"), 200);
        assertEquals(Numbers.stringToInteger("one thousand"), 1000);
        assertEquals(Numbers.stringToInteger("ten thousand"), 10000);
        assertEquals(Numbers.stringToInteger("seventy five"), 75);
        assertEquals(Numbers.stringToInteger("three million"), 3000000);
    }

    //Test hundred thousand case
    @Test (expected=IllegalArgumentException.class)
    public void testHundredThousand(){
        Numbers.stringToInteger("ten hundred thousand");
    }

    //Test negative numbers
    @Test
    public void testNegativeNumbers(){
        assertEquals(Numbers.stringToInteger("negative two hundred"), -200);
        assertEquals(Numbers.stringToInteger("Minus one thousand"), -1000);
        assertEquals(Numbers.stringToInteger("negative ten thousand"), -10000);
        assertEquals(Numbers.stringToInteger("minus seventy five"), -75);
        assertEquals(Numbers.stringToInteger("NEGATIVE three million"), -3000000);
    }

    //Test million thousand
    @Test (expected=IllegalArgumentException.class)
    public void testMillionThousand(){
        Numbers.stringToInteger("ten million thousand");
    }

    //Test number with all fields (million..thousand..hundred)
    @Test
    public void testAllFieldsNumber(){
        assertEquals(Numbers.stringToInteger("NEGATIVE three million five hundred twenty " +
                "five thousand four hundred thirty three"), -3525433);
    }

    //Test adjacent values (exception)
    @Test(expected=IllegalArgumentException.class)
    public void testAdjacentValues(){
        Numbers.stringToInteger("four thousand thousand");
    }

    //Test wrong order (exception)
    @Test(expected=IllegalArgumentException.class)
    public void testInvalidOrder(){
        Numbers.stringToInteger("four thousand five million");
    }

    //Test double negative
    @Test(expected=IllegalArgumentException.class)
    public void testDoubleNegative(){
        Numbers.stringToInteger("negative minus four thousand");
    }

    //Test double zero
    @Test(expected=IllegalArgumentException.class)
    public void testDoubleZero(){
        Numbers.stringToInteger("zero naught");
    }

    @Test(expected=IllegalArgumentException.class)
    public void testBeginningSpace(){
        Numbers.stringToInteger(" four thousand");
    }

    @Test(expected=IllegalArgumentException.class)
    public void testMiddleAdjacentSpaces(){
        Numbers.stringToInteger("four   thousand");
    }

    @Test(expected=IllegalArgumentException.class)
    public void testEndSpace(){
        Numbers.stringToInteger("four thousand ");
    }

    @Test
    public void testOnes() {
        assertEquals(Numbers.stringToInteger("two"), 2);
        assertEquals(Numbers.stringToInteger("eight"), 8);
    }

    @Test
    public void testTeens() {
        assertEquals(Numbers.stringToInteger("twelve"), 12);
        assertEquals(Numbers.stringToInteger("eighteen"), 18);
    }

    @Test
    public void testTens() {
        assertEquals(Numbers.stringToInteger("twenty"), 20);
        assertEquals(Numbers.stringToInteger("eighty"), 80);
    }

    @Test
    public void testComplexTens() {
        assertEquals(Numbers.stringToInteger("twenty two"), 22);
        assertEquals(Numbers.stringToInteger("twenty eight"), 28);
        assertEquals(Numbers.stringToInteger("minus twenty two"), -22);
    }

    @Test
    public void testHundreds() {
        assertEquals(Numbers.stringToInteger("two hundred"), 200);
        assertEquals(Numbers.stringToInteger("eight hundred"), 800);
        assertEquals(Numbers.stringToInteger("minus two hundred"), -200);
    }

    @Test
    public void testComplexHundreds() {
        assertEquals(Numbers.stringToInteger("two hundred twenty"), 220);
        assertEquals(Numbers.stringToInteger("eight hundred thirty one"), 831);
        assertEquals(Numbers.stringToInteger("minus two hundred eighty three"), -283);
    }

    @Test
    public void testThousands() {
        assertEquals(Numbers.stringToInteger("two thousand"), 2000);
        assertEquals(Numbers.stringToInteger("minus two thousand"), -2000);
        assertEquals(Numbers.stringToInteger("two thousand three hundred"), 2300);
        assertEquals(Numbers.stringToInteger("two thousand three hundred ten"), 2310);
        assertEquals(Numbers.stringToInteger("two thousand three hundred thirteen"), 2313);
    }

    @Test
    public void testCapitalization() {
        //The zeros
        //All caps
        assertEquals(Numbers.stringToInteger("NAUGHT"), 0);
        assertEquals(Numbers.stringToInteger("ZERO"), 0);
        //Alternating Caps
        assertEquals(Numbers.stringToInteger("NAUght"), 0);
        assertEquals(Numbers.stringToInteger("ZeRo"), 0);

        //first letters capitalized
        assertEquals(Numbers.stringToInteger("Forty Five"), Numbers.stringToInteger("forty five"));
        assertEquals(Numbers.stringToInteger("Nine Hundred Sixty Three"), Numbers.stringToInteger("nine hundred sixty three"));
        assertEquals(Numbers.stringToInteger("Four Hundred Thousand Ninety Seven"), Numbers.stringToInteger("four hundred thousand ninety seven"));

        //Random Caps
        assertEquals(Numbers.stringToInteger("sixTy SeVEN"), Numbers.stringToInteger("SixtY SEVEN"));
        assertEquals(Numbers.stringToInteger("FoUr THOusAnd"), Numbers.stringToInteger("four THOUSAND"));
    }

    @Test
    public void testLessThan100Inputs() {
        //The zero cases
        assertEquals(Numbers.stringToInteger("naught"), 0);
        assertEquals(Numbers.stringToInteger("zero"), 0);

        //The 1-9 key words
        assertEquals(Numbers.stringToInteger("two"), 2);
        assertEquals(Numbers.stringToInteger("eight"), 8);

        //The 10-19 key words.
        assertEquals(Numbers.stringToInteger("ten"), 10);
        assertEquals(Numbers.stringToInteger("twelve"), 12);
        assertEquals(Numbers.stringToInteger("fifteen"), 15);

        //The n * 10 key words.
        assertEquals(Numbers.stringToInteger("twenty"), 20);
        assertEquals(Numbers.stringToInteger("sixty"), 60);

        //combinations of n*10 and 1-9 words.
        assertEquals(Numbers.stringToInteger("twenty eight"), 28);
        assertEquals(Numbers.stringToInteger("thirty seven"), 37);
        assertEquals(Numbers.stringToInteger("forty six"), 46);
        assertEquals(Numbers.stringToInteger("fifty five"), 55);
        assertEquals(Numbers.stringToInteger("sixty four"), 64);
        assertEquals(Numbers.stringToInteger("seventy three"), 73);
        assertEquals(Numbers.stringToInteger("eighty two"), 82);
        assertEquals(Numbers.stringToInteger("ninety one"), 91);
        assertEquals(Numbers.stringToInteger("ninety nine"), 99);
    }

    @Test
    public void testLessThan1000Inputs() {
        assertEquals(Numbers.stringToInteger("one hundred"), 100);
        assertEquals(Numbers.stringToInteger("three hundred four"), 304);
        assertEquals(Numbers.stringToInteger("five hundred sixteen"), 516);
        assertEquals(Numbers.stringToInteger("seven hundred fifty"), 750);
        assertEquals(Numbers.stringToInteger("nine hundred eighty three"), 983);
    }

    @Test
    public void testLessThan10000000Inputs() {
        assertEquals(Numbers.stringToInteger("one thousand"), 1000);
        assertEquals(Numbers.stringToInteger("three thousand five"), 3005);
        assertEquals(Numbers.stringToInteger("five thousand sixteen"), 5016);
        assertEquals(Numbers.stringToInteger("seven thousand fifty"), 7050);
        assertEquals(Numbers.stringToInteger("nine thousand eighty three"), 9083);

        assertEquals(Numbers.stringToInteger("ten thousand two"), 10002);
        assertEquals(Numbers.stringToInteger("eleven thousand thirteen"), 11013);
        assertEquals(Numbers.stringToInteger("thirteen thousand eighty"), 13080);
        assertEquals(Numbers.stringToInteger("fifteen thousand eighty three"), 15083);

        assertEquals(Numbers.stringToInteger("one thousand two hundred three"), 1203);

        assertEquals(Numbers.stringToInteger("twenty thousand four"), 20004);
        assertEquals(Numbers.stringToInteger("fifty thousand nineteen"), 50019);
        assertEquals(Numbers.stringToInteger("sixty thousand eighty"), 60080);
        assertEquals(Numbers.stringToInteger("eighty thousand seventy three"), 80073);

        assertEquals(Numbers.stringToInteger("twenty eight thousand two"), 28002);
        assertEquals(Numbers.stringToInteger("thirty five thousand seventeen"), 35017);
        assertEquals(Numbers.stringToInteger("thirty nine thousand sixty"), 39060);
        assertEquals(Numbers.stringToInteger("forty nine thousand eighty three"), 49083);

        assertEquals(Numbers.stringToInteger("one hundred thousand two"), 100002);
        assertEquals(Numbers.stringToInteger("three hundred four thousand fifteen"), 304015);
        assertEquals(Numbers.stringToInteger("five hundred twelve thousand twenty"), 512020);
        assertEquals(Numbers.stringToInteger("six hundred fifty thousand thirty seven"), 650037);
    }

    @Test
    public void testLessThen1000000000Inputs() {
        assertEquals(Numbers.stringToInteger("one million"), 1000000);
        assertEquals(Numbers.stringToInteger("two million five"), 2000005);
        assertEquals(Numbers.stringToInteger("three million four hundred"), 3000400);

        assertEquals(Numbers.stringToInteger("fourteen million two hundred twelve"), 14000212);
        assertEquals(Numbers.stringToInteger("fifty million two thousand"), 50002000);

        assertEquals(Numbers.stringToInteger("six hundred million thirteen thousand five"), 600013005);
        assertEquals(Numbers.stringToInteger("seven hundred five million forty thousand eighteen"), 705040018);
        assertEquals(Numbers.stringToInteger("nine hundred sixteen million twenty five thousand thirty"), 916025030);
        assertEquals(Numbers.stringToInteger("eight hundred twenty million nine hundred thousand sixty seven"), 820900067);
        assertEquals(Numbers.stringToInteger("nine hundred eighty seven million six hundred fifty four thousand three hundred twenty one"), 987654321);

    }

    @Test
    public void testNegativeInputs() {
        //The zero cases
        assertEquals(Numbers.stringToInteger("negative four"), -4);
        assertEquals(Numbers.stringToInteger("minus eight"), -8);

        assertEquals(Numbers.stringToInteger("negative sixteen"), -16);
        assertEquals(Numbers.stringToInteger("minus thirteen"), -13);

        assertEquals(Numbers.stringToInteger("negative twenty"), -20);
        assertEquals(Numbers.stringToInteger("minus fifty"), -50);

        assertEquals(Numbers.stringToInteger("negative thirty two"), -32);
        assertEquals(Numbers.stringToInteger("minus forty seven"), -47);

        assertEquals(Numbers.stringToInteger("negative one hundred"), -100);
        assertEquals(Numbers.stringToInteger("minus five hundred"), -500);

        assertEquals(Numbers.stringToInteger("negative two hundred four"), -204);
        assertEquals(Numbers.stringToInteger("minus three hundred seventeen"), -317);

        assertEquals(Numbers.stringToInteger("negative six hundred fifty"), -650);
        assertEquals(Numbers.stringToInteger("minus eight hundred eighty nine"), -889);

        assertEquals(Numbers.stringToInteger("negative seven thousand"), -7000);
        assertEquals(Numbers.stringToInteger("minus eight thousand five"), -8005);
        assertEquals(Numbers.stringToInteger("negative nine thousand eighty nine"), -9089);
        assertEquals(Numbers.stringToInteger("minus five thousand six hundred"), -5600);
        assertEquals(Numbers.stringToInteger("negative four thousand eight hundred twelve"), -4812);

        assertEquals(Numbers.stringToInteger("minus two million"), -2000000);
        assertEquals(Numbers.stringToInteger("negative three million six"), -3000006);
        assertEquals(Numbers.stringToInteger("minus four million seventeen"), -4000017);

        assertEquals(Numbers.stringToInteger("negative twelve million two hundred fifty three"), -12000253);
        assertEquals(Numbers.stringToInteger("minus fifty million four thousand"), -50004000);
        assertEquals(Numbers.stringToInteger("negative sixty seven million eighteen thousand six"), -67018006);
        assertEquals(Numbers.stringToInteger("minus two hundred million five hundred thousand twelve"), -200500012);
        assertEquals(Numbers.stringToInteger("negative three hundred ten million four hundred four thousand fifty"), -310404050);
        assertEquals(Numbers.stringToInteger("minus nine hundred twenty million seven hundred fourteen thousand sixty one"), -920714061);
    }

    @Test
    public void testIllegalNonwordInputs() {
        //non Words
        String[] nonWords = {
                "", "?", "!", ",", "+", "=", "-", ".", "@", "#", "$", "%", "^",
                "1", "2", "3", "4", "5", "6", "7", "8", "9", "11", "25", "58",
                "asdfkjgh", "...", "qwerty", "asdfourdfsj"};
        //Loop through the non words, making sure each one fails
        for (int i = 0; i < nonWords.length; i++) {
            try {
                Numbers.stringToInteger(nonWords[i]);
                fail(nonWords[i] + " Succeeded when it should have thrown an exception.");
            } catch (IllegalArgumentException e) {
                //This one correctly fails
            }
        }

    }

    @Test
    public void testMisspelledInputs() {
        //Misspelled words
        String[] misspelledInputs = {
                "on", "to", "thre", "tent", "fiveteen",
                "thirty tree", "five thousand eight hundred for",
                "minuseight", "negative sevn", "ninegative ne"};
        //Loop through the misspelled inputs, making sure each one fails
        for (int i = 0; i < misspelledInputs.length; i++) {
            try {
                Numbers.stringToInteger(misspelledInputs[i]);
                fail(misspelledInputs[i] + " Succeeded when it should have thrown an exception for being misspelled.");
            } catch (IllegalArgumentException e) {
                //This one correctly fails
            }
        }

    }

    @Test
    public void testIllegalSyntaxInputs() {
        //Illegal syntax
        String[] illegalInputs = {
                "one six", "five two", "four eight", //ones digit twice.
                "nine fifteen", "seven sixteen", "five eighteen", //ones followed by teens.
                "ten four", "seventeen six", "nineteen eight", //teens followed by ones digit.
                "one twenty", "seven twenty", "four sixty", //ones followed by *n*tys.
                "thirty twelve", "forty sixteen", "ninty eleven", //ntys followed by teens.
                "twenty thirty", "forty ninty", "sixty fifty", //ntys followed by ntys.
                "hundred", "hundred nine", "hundred sixteen", "hundred forty", //No modifier to hundred.
//                "twelve hundred", "forty hundred", "seventy seven hundred", //2 digit prefix before hundred
//                "one hundred hundred", "eight hundred four hundred nineteen", //two hundreds
                "thousand one", "thousand eighteen", "thousand twenty four",  //no prefix to thousands
                "one thousand fifty million", "twelve thousand ninteen million three",
                "eighty thousand four hundred nine million", "one hundred thousand million", //Thousand before million
                "million", "million one", "million fifty", "million sixteen", //no prefix to million
                "one hundred million thousand"
        };
        //Loop through the illegal inputs, making sure each one fails
        for (int i = 0; i < illegalInputs.length; i++) {
            try {
                Numbers.stringToInteger(illegalInputs[i]);
                fail(illegalInputs[i] + " Succeeded when it should have thrown an exception for having illegal syntax.");
            } catch (IllegalArgumentException e) {
                //This one correctly fails!
            }
        }
    }

    @Test
    public void testIllegalNegativeInputs() {
        //words with negatives misplaced
        String[] illegalInputs = {
                "negative", "minus", //minus and negative alone
                "minus zero", "negative naught", //Negative 0
                "four minus hundred eight", "four hundred minus eight", "four hundred eight minus", //minuses within.
                "four negative hundred eight", "four hundred negative eight", "four hundred eight negative", //Negatives within.
                "minus minus four", "minus four minus", "four minus minus", //two minuses.
                "negative negative four", "negative four negative", "four negative negative", //two negatives.
                "negative minus six", "minus negative eight", "negative four minus",
                "minus seven negative", "eight minus negative", "nine negative minus", //one minus, one negative.
                " minus six", "negative  ten", "ten negative ", //Weird spaces.
                "one million negative twelve thousand eight" //negative within a long input.

        };
        //Loop through the inputs, making sure each one fails
        for (int i = 0; i < illegalInputs.length; i++) {
            try {
                Numbers.stringToInteger(illegalInputs[i]);
                fail(illegalInputs[i] + " Succeeded when it should have thrown an exception for having illegal minuses or negatives.");
            } catch (IllegalArgumentException e) {
                //This one correctly fails
            }
        }
    }

    @Test
    public void testIllegallySpacedInputs() {
        //words with spaces misplaced
        String[] illegalInputs = {
                " one", "sixteen ", " eighty ", //extra spaces before or after string.
                "  ninty", "forty  ", "  forteen  ", //extra double spaces
                "sixty  five", "ninty hundred  two", "eighty  hundred six", //Double spaces within an input.
                "two  million  fifteen", "sixty  seven million  three", "twelve million  four  thousand", //Multiple double spaces.

                "twentyfour", "fourhundred five", "two thousandsix", "sevenmillionfour" //missing spaces.

        };
        //Loop through the badly spaced inputs, making sure each one fails
        for (int i = 0; i < illegalInputs.length; i++) {
            try {
                Numbers.stringToInteger(illegalInputs[i]);
                fail(illegalInputs[i] + " Succeeded when it should have thrown an exception for having bad spacing.");
            } catch (IllegalArgumentException e) {
                //This one correctly fails
            }
        }
    }
}