
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * STUDENT tests for the methods of PasswordChecker
 * @author 
 * Ian Green
 */
public class PasswordCheckerTest_STUDENT {
	
	private ArrayList<String> passwords;
	@Before
	public void setUp() throws Exception {
		passwords = new ArrayList<>();
		passwords.add("abc12");
		passwords.add("Val1dLength!");
		passwords.add("uppercase1");
		passwords.add("UPPERCASE");
		passwords.add("Val1dP@ssword1");
		passwords.add("Val1dP@ssword2");
	}

	@After
	public void tearDown() throws Exception {
		passwords = null;
	}

	/**
	 * Test if the password is less than 6 characters long.
	 * This test should throw a LengthException for second case.
	 */
	@Test
	public void testIsValidPasswordTooShort()
	{
		try { //Invalid Length
			assertTrue(PasswordCheckerUtility.isValidPassword("abc12"));
		} catch(LengthException e)
		{
			assertTrue("Successfully threw LengthException", true);
		}
		catch(Exception e)
		{
			assertTrue("Threw some other exception besides lengthException", false);
			System.out.println("line 42");
		}
		
		try { // Valid Length
			assertTrue(PasswordCheckerUtility.isValidPassword("Val1dLength!"));
		} catch (Exception e) {
			fail("Exception shouldn't be thrown, valid password.");
		}
	}
	
	/**
	 * Test if the password has at least one uppercase alpha character
	 * This test should throw a NoUpperAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoUpperAlpha()
	{
		try { // No upper alpha
			assertTrue(PasswordCheckerUtility.isValidPassword("uppercase1!"));
		} catch (NoUpperAlphaException e) {
			assertTrue("Successfully threw NoUpperAlphaException", true);
		} 
		catch(Exception e) {
			assertTrue("Threw some other exception besides no upper alpha exception", false);
			System.out.println("line 65");
		}
		
		try { // Valid password
			assertTrue(PasswordCheckerUtility.isValidPassword("UpperCase1!"));
		} catch (Exception e) {
			fail("Exception shouldn't be thrown, valid password.");
		}
	}
	
	/**
	 * Test if the password has at least one lowercase alpha character
	 * This test should throw a NoLowerAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoLowerAlpha()
	{
		try { // No lower alpha
			assertTrue(PasswordCheckerUtility.isValidPassword("UPPERCASE1!"));
		} catch (NoLowerAlphaException e) {
			assertTrue("Successfully threw NoLowerAlphaException", true);
		} 
		catch(Exception e) {
			assertTrue("Threw some other exception besides no lower alpha exception", false);
			System.out.println("line 91");
		}
		
		try { // Valid password
			assertTrue(PasswordCheckerUtility.isValidPassword("uPPERcASE1!"));
		} catch (Exception e) {
			fail("Exception shouldn't be thrown, valid password.");
		}
	}
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsWeakPassword()
	{
		try { // Weak
			assertTrue(PasswordCheckerUtility.isValidPassword("we@kPA22"));
		} catch (WeakPasswordException e) {
			assertTrue("Successfully threw WeakPasswordException", true);
		} 
		catch(Exception e) {
			assertTrue("Threw some other exception besides weak password exception", false);
			System.out.println("line 114");
		}
		
		try { // Valid password
			assertTrue(PasswordCheckerUtility.isValidPassword("we@kPA22!w0rd"));
		} catch (Exception e) {
			fail("Exception shouldn't be thrown, valid password.");
		}
	}
	
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsValidPasswordInvalidSequence()
	{
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword("aaaBBBcc2@"));
		} catch (InvalidSequenceException e) {
			assertTrue("Successfully threw InvalidSequenceException", true);
		} catch (Exception e) {
			assertTrue("Threw some other exception besides invalid sequence exception", false);
			System.out.println("Line 136");
		}
	}
	
	/**
	 * Test if the password has at least one digit
	 * One test should throw a NoDigitException
	 */
	@Test
	public void testIsValidPasswordNoDigit()
	{
		try {
            assertTrue(PasswordCheckerUtility.isValidPassword("NoDigit@"));
        } catch (NoDigitException e) {
            assertTrue("Successfully threw NoDigitException", true);
        } catch (Exception e) {
            fail("Threw some other exception besides no digit excpetion");
        }
		try {
	        assertTrue(PasswordCheckerUtility.isValidPassword("HasDigit1!"));
	    } catch (Exception e) {
	         fail("Exception should not be thrown, valid password.");
	    }
	   
	}
	/**
	 * Test correct passwords
	 * This test should not throw an exception
	 */
	@Test
	public void testIsValidPasswordNoSpecialCharacterFail()
	{
		 try {
	         assertTrue(PasswordCheckerUtility.isValidPassword("InvalidPass1"));
	     } catch (NoSpecialCharacterException e) {
	         assertTrue("Successfully threw NoSpecialCharacterException", true);
	     } catch (Exception e) {
	         fail("Threw some otther exception besides no special character");
	     }
		 try {
		     assertTrue(PasswordCheckerUtility.isValidPassword("SpecialChar1@"));
		 } catch (Exception e) {
		     fail("Exception should not be thrown for valid password.");
		 }
	}
	/**
	 * Test correct passwords
	 * This test should not throw an exception
	 */
	@Test
	public void testIsValidPasswordSuccessful()
	{
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword("ValidPass1@"));
		} catch (Exception e) {
			fail("Valid password");
		}
	}
	
	/**
	 * Test the invalidPasswords method
	 * Check the results of the ArrayList of Strings returned by the validPasswords method
	 */
	@Test
	public void testInvalidPasswords() {
		ArrayList<String> invalidPasswords = PasswordCheckerUtility.getInvalidPasswords(passwords);
	    assertEquals(3, invalidPasswords.size());
	}
	
}
