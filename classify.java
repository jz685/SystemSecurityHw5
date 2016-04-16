import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.*;
import java.text.ParseException;
import java.util.HashSet;
import java.util.*;
import java.util.Scanner;

/*	Readme
 *	This program takes in one string as password and determine if it is a string password or a weak one. We do not allow space in password.
 *	We used the free Openwall list as the dictionary in this program as it is used in the paper "Guess again (and again and again): Measuring password strength by 
 *	simulating password-cracking algorithms" by Kelley et al. We also ignore case when comparing password with words provided in the dictionary. 
 *
 *	A password is considered strong if it meets any one of the following requirements:
 *
 *	1. Comprehensive 8+: The password is at least 8 characters long and must include at least an uppercase letter, a lowercase letter, a symbol, and a digit. 
 *	And the password must not contains any common password sequence and dictionary words predefined in the free Openwall list that is at least 2 characters long.
 *
 *	2. Basic 16+: The password is at least 16 characters long and must include at least four different characters.
 *
 *	The reason why we choose Comprehensive 8 is that it is proven to be strong in the paper "Guess again (and again and again): Measuring password strength by 
 *	simulating password-cracking algorithms" by Kelley et al. The only difference is that we allows password that contains dictionary words of length 2 characters or less. 
 *	We decide to allow password that contains dictionary words of length 2 characters or less is because in the direction we are using, where are words like "1" and "a".
 *	And it is clearly not correct to say that any password contains an "a" or "1" must be a weak password. 
 *
 *	The reason why we choose Basic 16 is that it is proven to be strong in the paper "Guess again (and again and again): Measuring password strength by 
 *	simulating password-cracking algorithms" by Kelley et al. The only difference is that we require at least 4 different characters. It is because that we believe 
 *	passwords like "1111111111111111" is not a strong password. 
 */

class classify {
	// This english dictionary is provided by ubuntu as a internal dictionay file
	// static final String dictionaryFile = "american-english";

	// this password/dictionary check file is provided by Openwall including easy passwords and dictionary 
	// in many other languages
	// http://www.openwall.com/wordlists/
	static final String dictionaryFile = "passwordCheck.txt";
	// We consider "_" as a non-character symbol
	public static final String comp8Str = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*(_|[\\W])).+$";
    public static final Pattern comp8Pattern = Pattern.compile(comp8Str);
    public static final String nonAlpha = "[^a-zA-Z]";
	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		String inputStr = scan.nextLine();

		// Test checkIsWord
		// System.out.println("Dictionary Contains? " + checkIsWord(inputStr));
		// System.out.println("Pass Comprehensive8 Test? " + checkComprehensive8(inputStr));
		// System.out.println("Pass Comprehensive16 Test? " + checkComprehensive16(inputStr));
		// System.out.println("Pass At Least 4 Unique Char Test? " + extraCheckFor16(inputStr));

		if (inputStr.indexOf(' ') >= 0) {
			System.out.println("weak, as we do not allow space in password");
			return;
		}
		if (checkComprehensive8(inputStr) && (!checkIsWord(inputStr))) {
			System.out.println("strong");
		} else if (checkComprehensive16(inputStr) && extraCheckFor16(inputStr)) {
			System.out.println("strong");
		} else {
			System.out.println("weak");
		}
	}

	/*
	 *	Check against a stored file that contains common password and dictionaries in different languages
	 *	It may not contain a dictionary word.
	 */
	public static boolean checkIsWord (String str) {
		String strAlpha = str.replaceAll(nonAlpha, "");
		String strlower = strAlpha.toLowerCase();
		// System.out.println("removed non-alphabetic characters(lowered): " + strlower);
		try {
			File dict = new File(dictionaryFile);
			BufferedReader reader = new BufferedReader(new FileReader(dict));
			String line;
			while ((line = reader.readLine()) != null) {
				// Do not include words that is too short
				if (line.length() <= 2) continue;

				if (strlower.equals(line.toLowerCase())) {
					System.out.println("Contains: " + line);
					return true;
				} 
			}
		} catch (IOException e) {
			System.err.println("Error Reading file" + e.getMessage());
		}
		return false;
	}

	/*
	 *	Password must have at least 8 characters including an uppercase and lowercase letter, a symbol, and a digit. 
	 */
	public static boolean checkComprehensive8 (String str) {
		if (str.length() < 8) return false;
		Matcher matcher = comp8Pattern.matcher(str);
		if (matcher.matches()) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 *	Password must have at least 16 characters
	 */
	public static boolean checkComprehensive16 (String str) {
		if (str.length() < 16) {
			return false;
		} else {
			return true;
		}
	}

	public static boolean extraCheckFor16 (String str) {
		Set<Character> charSet = new HashSet<>();
		int counter = 0;
		for (int i = 0; i < str.length(); i ++) {
			char c = str.charAt(i);
			if (!charSet.contains(c)) {
				charSet.add(c);
				counter ++;
				// if more than 4 char found, return true
				if (counter >= 4) {
					return true;
				}
			}
		}
		return false;
	}
}