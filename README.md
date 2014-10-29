debug
=====

293 project for converting a string into an integer.

@author Shaun Howard

To use, must supply a string number with the correct syntax to the Numbers.stringToInteger() method.

The program will parse the string into an integer if the syntax is correct, otherwise, an exception will be thrown
by the code when the string fails.

Thus, the error handling component is covered by throwing an exception at the point in the code where the error is found
during processing.

A class was added called NumbersError that checks for most bad input where NumbersSyntaxChecker lacked, which was
mostly in everything.

The differentiation between these classes is determining if the number is in the proper number format (SyntaxChecker) vs
whether the input given was bad input (Error). Things like doubles are checked in the Error class. Things like order of
numbering and places are checked in the SyntaxChecker class.

Defects found include incorrect processing of:
One word numbers
Two word numbers
Hundred preceding thousand
Million preceding thousand
Negative numbers
Adjacent values that were the same place (i.e. hundred hundred)
Invalid order of string
Double negatives
Double zeroes
Weird spaces (beginning, adjacent, end)






