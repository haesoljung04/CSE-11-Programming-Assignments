/**
 * Name: Haesol Jung
 * Email: haj008@ucsd.edu
 * PID: A17348180
 * Sources used: Textbook, Lecture Notes, Prior Knowledge, 
 * Oracle Doc, write-up
 * 
 * This file carries out the tasks for CSE 11 Performance Task 3.
 * It will essentially serve as a calculator that will use numbers
 * in string format as parameters and do operations with them.
 */

 /**
 * This class is a number(in string format) manipulator that has 
 * the ability to extract whole and decimal numbers, append/prepend
 * zeros, strip leading and trailing zeros, add single digits,
 * check for carry ons, and add/multiply numbers. It returns these
 * values; 
 */
public class Calculator {

    /** Constants (Magic Numbers) */
    private static final char DECIMAL = '.';

    /**
     * This method takes a number in string format as a parameter
     * and returns the whole number portion of the number. In a
     * special case where there is no whole number portion, this
     * will return "0"
     * 
     * @param number   is a number in string format 
     * @return whole number portion of number in string format
     */
        public static String extractWholeNumber(String number) {
            int strLength = number.length();
            StringBuilder wholeNumber = new StringBuilder();
            if (number.charAt(0) == DECIMAL) {
                return "0";
            }
            for (int i = 0; i < strLength; i++) {
                if (Character.isDigit(number.charAt(i))) {
                    wholeNumber.append(number.charAt(i));
                }
                else {
                    break;
                }
            }   
            return wholeNumber.toString();
        }

        /**
         * This method takes a number in string format as a parameter
         * and returns the decimal number portion of the number. In a
         * special case where there is no decimal number portion, this
         * will return "0"
         * 
         * @param number   is a number in string format 
         * @return decimal number portion of number in string format
         */
        public static String extractDecimal(String number) {
            int strLength = number.length();
            StringBuilder decimalNumber = new StringBuilder();
            int decimalCount = 0;
            int position = 0;
            //Determine if the number has a decimal
            for (int i = 0; i < strLength; i++) {
                if (number.charAt(i) == '.') {
                    decimalCount = 1;
                    position = i;
                    break;
                }
            }
            if (decimalCount == 0) {
                return "0";
            }
            else if (decimalCount == 1 && position == strLength - 1) {
                return "0";
            }
            else {
                for (int i = position + 1; i < strLength; i++) {
                    if (Character.isDigit(number.charAt(i))) {
                        decimalNumber.append(number.charAt(i));
                    }
                    else {
                        return "0";
                    }

                }
            }
            return decimalNumber.toString();
        }
        
        /**
         * This method takes a number in string format and prepends
         * numZeros number of zeros to the given number
         * 
         * @param number   is a number in string format 
         * @param numZeros  is the number of prepended zeros in
         * integer format
         * @return a number with numZeros number of zeros prepended
         * to the number in string format
         */
        public static String prependZeros(String number, int numZeros) {
            StringBuilder prependedNumber = new StringBuilder();
            if (numZeros < 0) {
                return number;
            }
            for (int i = 0; i < numZeros; i++) {
                prependedNumber.append('0');
            }
            prependedNumber.append(number);
            return prependedNumber.toString();
        }

        /**
         * This method takes a number in string format and appends
         * numZeros number of zeros to the given number
         * 
         * @param number   is a number in string format 
         * @param numZeros  is the number of appended zeros in int
         * format
         * @return a number with numZeros number of zeros appended
         * to the number in string format
         */
        public static String appendZeros(String number, int numZeros) {
            StringBuilder appendedNumber = new StringBuilder(number);
            if (numZeros < 0) {
                return number;
            } 
            for (int i = 0; i < numZeros; i++) {
                appendedNumber.append('0');
            }
            return appendedNumber.toString();
        }

        /**
         * This method takes a number in string format and strips
         * all the leading and trailing zeros from the number
         * 
         * @param number   is a number in string format 
         * @return a number with no trailing or leading zeros
         */
        public static String stripZeros(String number) {
            int strLength = number.length();
            int strLength1 = 0;
            int decCount = 0;
            int decPosition = 0;
            int leadZeroCount = 0;
            int trailZeroCount = 0;
            //Determine if the number has a decimal
            for (int i = 0; i < strLength; i++) {
                if (number.charAt(i) == DECIMAL) {
                    decCount = 1;
                    decPosition = i;
                    break;
                }
            }
            //Strips leading zeros for decimal numbers
            String woutLeadZeros = number;
            if (number.charAt(0) == '0') {
                if (decCount == 1) {
                    //Counting leading zeros
                    for (int i = 0; i < decPosition - 1; i++) {
                        if (number.charAt(i) == '0') {
                        leadZeroCount++;
                        }
                        else {
                            break;
                        }
                    }
                    woutLeadZeros = number.substring(leadZeroCount);
                    strLength1 = woutLeadZeros.length();
                    //Update position of decimal
                    for (int i = 0; i < strLength1; i++) {
                        if (woutLeadZeros.charAt(i) == DECIMAL) {
                            decPosition = i;
                            break;
                        }
                    }
                    if (number.charAt(strLength - 1) != '0') {
                        return woutLeadZeros;
                    }
                }
            }
            //strips trailing zeros for decimal numbers
            if (number.charAt(strLength - 1) == '0') {
                if (decCount == 1) {
                    //Stripping trailing zeros when there were leading zeros
                    if (leadZeroCount > 0) {
                        for (int i = strLength1 - 1; 
                        i > decPosition + 1; i--) {
                            if (woutLeadZeros.charAt(i) == '0') {
                                trailZeroCount++;
                            }
                            else {
                                break;
                            }
                        }
                    String woutZeros = woutLeadZeros.substring(0, 
                    (strLength1) - trailZeroCount);
                    return woutZeros;
                    }
            
                    //Strip trailing zeros when there are no leading zeros
                    else {
                        for (int i = strLength - 1; 
                        i > decPosition + 1; i--) {
                            if (number.charAt(i) == '0') {
                            trailZeroCount++;
                            }
                            else {
                                break;
                            }
                        }
                    String woutZeros = number.substring(0, 
                    (strLength) - trailZeroCount);
                    return woutZeros;
                    }
                }
            }
            //Strips leading zeros for whole numbers
            if (decCount == 0 && number.charAt(0) == '0') {
                for (int i = 0; i < strLength; i++) {
                    if (number.charAt(i) == '0') {
                        leadZeroCount++;
                    }
                    else {
                        break;
                    }
                    if (leadZeroCount == strLength) {
                        return "0";
                    }
                }
            String woutZeros = number.substring(leadZeroCount, strLength);
            return woutZeros;
            }
            return number;
        }

        /**
         * This method takes two digits in character format and
         * a boolean value and adds them all together to return
         * the rightmost digit in character format
         * 
         * @param firstDigit   is a number in character format
         * @param secondDigit  is another number in character format
         * @param carryIn  is a boolean value that is true if there
         * is a carry and false if not
         * @return sum of two digits(and boolean value if there is a 
         * carry) in character format
         */
        public static char addDigits(char firstDigit, 
        char secondDigit, boolean carryIn) {
            int sum = 0;
            int digit1 = Character.getNumericValue(firstDigit);
            int digit2 = Character.getNumericValue(secondDigit);
            if (carryIn) {
                sum = digit1 + digit2 + 1;
            }
            else {
                sum = digit1 + digit2;
            }
            String mySum = sum  + "";
            int mySumLength = mySum.length();
            char rightDigit = mySum.charAt(mySumLength - 1);
            return rightDigit;
        }

        /**
         * This method takes two digits in character format and
         * a boolean value and adds them all together and returns
         * true if there is a carry and false if not
         * 
         * @param firstDigit   is a number in character format
         * @param secondDigit  is another number in character format
         * @param carryIn  is a boolean value that is true if there
         * is a carry and false if not
         * @return true if there is a carry from previous column 
         * and false if not
         */
        public static boolean carryOut(char firstDigit, 
        char secondDigit, boolean carryIn) {
            int sum = 0;
            int digit1 = Character.getNumericValue(firstDigit);
            int digit2 = Character.getNumericValue(secondDigit);
            if (carryIn) {
                sum = digit1 + digit2 + 1;
            }
            else {
                sum = digit1 + digit2;
            }
            String mySum = sum  + "";
            int sumLength = mySum.length();
            if (sumLength == 2) {
                return true;
            }
            else {
                return false;
            }
        }

        /**
         * This method takes two numbers in string format and 
         * sums them together and returns a double in string format
         * @param firstNumber   is a number in string format
         * @param secondNumber  is another number in string format
         * @return sum of two numbers as a double in string format
         */
        public static String add(String firstNumber, String secondNumber) {
            //Declaring strings and keep tracking of their lengths
            String dec1 = extractDecimal(firstNumber);
            String dec2 = extractDecimal(secondNumber);
            int decLength1 = dec1.length();
            int decLength2 = dec2.length();
            int decLengthDiff = Math.abs(decLength1 - decLength2);
            //Prepending and appending zeros for correct alignment in addition
            if (decLength1 > decLength2) {
                dec2 = appendZeros(dec2, decLengthDiff);
            }
            else if (decLength1 < decLength2) {
                dec1 = appendZeros(dec1, decLengthDiff);
            }
            String whole1 = extractWholeNumber(firstNumber);
            String whole2 = extractWholeNumber(secondNumber);
            int wholeLength1 = whole1.length();
            int wholeLength2 = whole2.length();
            int wholeLengthDiff = Math.abs(wholeLength1 - wholeLength2);
            if (wholeLength1 > wholeLength2) {
                whole2 = prependZeros(whole2, wholeLengthDiff);
            }
            else if (wholeLength1 < wholeLength2) {
                whole1 = prependZeros(whole1, wholeLengthDiff);
            }
            String fullNumber1 = whole1 + "." + dec1;
            String fullNumber2 = whole2 + "." + dec2;
            int totalLength = fullNumber1.length();
            StringBuilder fullNumber = new StringBuilder();
            boolean carryHolder = false;
            //Adding each digit of the aligned strings
            for (int i = totalLength - 1; i >= 0; i--) {
                if (fullNumber1.charAt(i) == DECIMAL) {
                    fullNumber.append(".");
                    continue;
                }
                //This condition accounts for lack of initial carry
                if (i == totalLength - 1) {
                    fullNumber.append(addDigits(fullNumber1.charAt(i), 
                    fullNumber2.charAt(i), false));
                    if (carryOut((fullNumber1.charAt(i)), 
                    (fullNumber2.charAt(i)), false)) {
                        carryHolder = true;
                        continue;
                    }
                    else {
                        carryHolder = false;
                        continue;
                    }
                }
                //Accounting for when the last added digits have a 2 digit sum
                if (i == 0 && carryHolder) {
                    fullNumber.append(addDigits(fullNumber1.charAt(i), 
                    fullNumber2.charAt(i), true));
                    if (carryOut((fullNumber1.charAt(i)), 
                    (fullNumber2.charAt(i)), true)) {
                            fullNumber.append("1");
                    }
                    break;
                }
                else if (i == 0 && carryHolder == false) {
                    fullNumber.append(addDigits(fullNumber1.charAt(i), 
                    fullNumber2.charAt(i), false));
                    if (carryOut((fullNumber1.charAt(i)), 
                    (fullNumber2.charAt(i)), true)) {
                            fullNumber.append("1");
                    }
                    break;
                }
                //Adding digits when there is a carry
                if (carryHolder) {
                    fullNumber.append(addDigits(fullNumber1.charAt(i), 
                    fullNumber2.charAt(i), true));
                    if (carryOut((fullNumber1.charAt(i)), 
                    (fullNumber2.charAt(i)), false)) {
                        carryHolder = true;
                        continue;
                    }
                    else {
                        carryHolder = false;
                        continue;
                    }
                }
                //Adding digits when there is no carry
                else {
                    fullNumber.append(addDigits(fullNumber1.charAt(i), 
                    fullNumber2.charAt(i), false));
                    if (carryOut((fullNumber1.charAt(i)), 
                    (fullNumber2.charAt(i)), false)) {
                        carryHolder = true;
                        continue;
                    }
                    else {
                        carryHolder = false;
                        continue;
                    }
                }
            } 
            //Reverse order of appended digits and apply format corrections
            fullNumber = fullNumber.reverse();
            String sum = fullNumber.toString();
            if (sum.charAt(0) == DECIMAL) {
                sum = prependZeros(sum, 1);
            }
            if (sum.charAt(sum.length() - 1) == DECIMAL) {
                sum = sum + "0";
            }
            sum = stripZeros(sum);
            return sum;
        }
        
        /**
         * This method takes a number in string format and multiplies
         * it with a given number and returns the product
         * 
         * @param number   is a number in string format
         * @param numTimes  is the number of times number will be added
         * @return product of number in string format added numTimes
         */
        public static String multiply(String number, int numTimes) {
            if (numTimes < 0) {
                return number;
            }
            if (numTimes == 0) {
                return "0.0";
            }
            if (numTimes == 1) {
                String itself = add(number, "0"); 
                return itself;
            }
            //Updating stringbuilder with new sum every iteration
            String newNumber = number;
            StringBuilder product = new StringBuilder();
            for (int i = 1; i < numTimes; i++) {
            product.replace(0, product.length(), add(number, newNumber));
            newNumber = product.toString();
            }
            return product.toString();
        }
        public static void main(String[] args) {
            System.out.print(Calculator.extractWholeNumber("000000"));
        }
}
