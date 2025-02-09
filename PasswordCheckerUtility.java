/**
 * @author Ian Green
 * This class takes a String input, a password, and checks if the password is valid.
 * It checks things such as length, sequence, and characters in the password
 */


import java.util.*;
import java.io.*;
import java.util.regex.*;
public class PasswordCheckerUtility {

/**
 * 
 * @param password is the String user entered for their password
 * @return true if the password passes all exceptions
 * @throws Exception If the password doesn't pass a requirement, an exception will be thrown
 */
	public static boolean isValidPassword (String password) throws Exception {
		isValidLength(password);
		hasUpperAlpha(password);
		checkLower(password);
		checkDigit(password);
		checkSpecial(password);
		checkSequence(password);
		isWeak(password);
		return true;
	}
	
	/**
	 * 
	 * @param password is the String user entered for their password
	 * @return false if the password isn't in between 6-9 characters in length, but also passes the length (10 or greater characters in length)
	 * @throws Exception If the password doesn't pass a requirement, an exception will be thrown
	 */
	public static boolean isWeakPassword(String password) throws Exception {
		isValidPassword(password);
		if((password.length() >= 6 && password.length() <= 9)) {
			throw new WeakPasswordException("The password is OK but weak - it contains fewer than 10 characters");
		}
		return false;
	}
	
	/**
	 * 
	 * @param passwords is the String user entered for their password
	 * @return Array List of invalid passwords
	 */
	public static ArrayList<String> getInvalidPasswords(ArrayList<String> passwords){
		ArrayList<String> invalidPasswords = new ArrayList<>();
		for (String password : passwords) {
			try {
				isValidPassword(password);
			} catch (Exception e) {
				invalidPasswords.add(password + " " + e.getMessage());
			}
		}
		return invalidPasswords;
	}
	
	/**
	 * 
	 * @param password is the String user entered for their password
	 * @throws LengthException if the password is less than 6 characters
	 */
	private static void isValidLength(String password) throws LengthException {
			if(password.length() < 6) {
				throw new LengthException("The password must be at least 6 characters long");
			}
	}
	/**
	 * 
	 * @param password is the String user entered for their password
	 * @throws NoUpperAlphaException if the password doesn't contain an upper alpha character
	 */
	private static void hasUpperAlpha(String password) throws NoUpperAlphaException {
			boolean upperCase = false;
			char c;
			for (int i = 0; i < password.length(); i++) {
				c = password.charAt(i);
				if(Character.isUpperCase(c)) {
					upperCase = true;
					break;
				}
			}
			if(!upperCase) {
				throw new NoUpperAlphaException("The password must contain at least one uppercase alphabetic character");
			}
	}
	/**
	 * 
	 * @param password is the String user entered for their password
	 * @throws NoLowerAlphaException if the password doesn't contain a lower alpha character
	 */
	private static void checkLower(String password) throws NoLowerAlphaException{
			boolean lowerCase = false;
			char c;
			for (int i = 0; i < password.length(); i++) {
				c = password.charAt(i);
				if(Character.isLowerCase(c)) {
					lowerCase = true;
					break;
				}
			}
			if(!lowerCase) {
				throw new NoLowerAlphaException("The password must contain at least one lowercase alphabetic character");
			}
	}
	/**
	 * 
	 * @param password is the String user entered for their password
	 * @throws NoDigitException if the password doesn't contain a digit
	 */
	private static void checkDigit(String password) throws NoDigitException{
			boolean digit = false;
			char c;
			for (int i = 0; i < password.length(); i++) {
				c = password.charAt(i);
				if(Character.isDigit(c)) {
					digit = true;
					break;
				}
			}
			if(!digit) {
				throw new NoDigitException("The password must contain at least one digit");
			}
	}
	/**
	 * 
	 * @param password is the String user entered for their password
	 * @throws NoSpecialCharacterException if the password doesn't contain a special character
	 */
	private static void checkSpecial(String password) throws NoSpecialCharacterException {
			Pattern pattern = Pattern.compile("[a-zA-Z0-9]*");
			Matcher matcher = pattern.matcher(password);
			if(matcher.matches()) {
				throw new NoSpecialCharacterException("The password must contain at least one special character");
			}
	}
	/**
	 * 
	 * @param password is the String user entered for their password
	 * @throws InvalidSequenceException if the password has more than 2 characters of the same value in a row
	 */
	private static void checkSequence(String password) throws InvalidSequenceException{
			int count = 1;
			char c;
			for(int i = 1; i < password.length(); i++) {
				c = password.charAt(i);
				if(c == password.charAt(i - 1)) {
					count++;
					if(count >= 3) {
						throw new InvalidSequenceException("The password cannot contain more than two of the same character in sequence");
					}
				} else {
					count = 1;
				}
			}
	}
	/**
	 * 
	 * @param password is the String user entered for their password
	 * @throws WeakPasswordException if the password is in between 6 and 10 characters long
	 */
	private static void isWeak(String password) throws WeakPasswordException{
			if(password.length() >= 6 && password.length() < 10) {
				throw new WeakPasswordException("The password is OK but weak - it contains fewer than 10 characters");
			}
	}
/**
 * 
 * @param passwordString is the first password entered
 * @param passwordAString is the re-typed password
 * @return true if both Strings are the same and false otherwise
 */
	public static boolean comparePasswordsWithReturn(String passwordString, String passwordAString) {
		return passwordString.equals(passwordAString);
	}
/**
 * 
 * @param password is the String user entered for their password
 * @param passwordConfirm is the re-typed password for confirmation
 * @throws UnmatchedException if both passwords don't match
 */
	public static void comparePasswords(String password, String passwordConfirm) throws UnmatchedException{
		if (password == null || passwordConfirm == null)
		{
			throw new UnmatchedException("Passwords do not match");
		}
		if(!password.equals(passwordConfirm))
		{
			throw new UnmatchedException("Passwords do not match");
		}
		
	}

}
