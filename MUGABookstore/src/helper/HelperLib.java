package helper;

import java.security.SecureRandom;

public class HelperLib {
	
	final private static String lowercaseLetters = "abcdefghijklmnopqrstuvwyxz123456789";
	final private static String uppercaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789";
	
    final private static SecureRandom random = new SecureRandom();

	private HelperLib() {}

	
	//Create a random code consisting of uppercase letters and lowercase letters.
	public static String generateRandomString(int length) {
		String a = "";
		
		for (int i = 0; i < length; i++) {
			int randomCaseSelection = random.nextInt(2);
			int randomLetterSelection = random.nextInt(lowercaseLetters.length());
			
			char letter = (randomCaseSelection == 0) ? lowercaseLetters.charAt(randomLetterSelection) : uppercaseLetters.charAt(randomLetterSelection);
			a = a + letter;
		}
		return a;
	}
	
	//Modify string to replace '<' and '/>' with their respective HTML escape characters to prevent XSS  
	public static String xssPrevent(String content) {
		String filteredContent = content.replace("<", "&lt;");
		filteredContent = filteredContent.replace(">", "&gt;");
		return filteredContent;
	}
}
