import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.*;
import java.text.ParseException;

class classify {

	// This english dictionary is provided by ubuntu as a internal dictionay file
	// static final String dictionaryFile = "american-english";

	// this password/dictionary check file is provided by Openwall including easy passwords and dictionary 
	// in many other languages
	// http://www.openwall.com/wordlists/
	static final String dictionaryFile = "passwordCheck";
	
	public static final String comp8Str = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*(_|[^\\w])).+$";
    public static final Pattern comp8Pattern = Pattern.compile(comp8Str);

	public static void main(String[] args) {
		// Check num of input
		if (args.length != 1) {
			System.err.println("Wrong number of arguments");
			return;
		}
		// Get input
		String inputStr = args[0];

		// Test checkIsWord
		System.out.println("Dictionary Contains? " + checkIsWord(inputStr));
		System.out.println("Pass Comprehensive8 Test? " + checkComprehensive8(inputStr));
		System.out.println("Pass Comprehensive8 Test? " + checkComprehensive16(inputStr));

	}

	/*
	 *	Check against a stored file that contains common password and dictionaries in different languages
	 *	It may not contain a dictionary word.
	 */
	public static boolean checkIsWord (String str) {
		try {
			File dict = new File(dictionaryFile);
			BufferedReader reader = new BufferedReader(new FileReader(dict));
			String line;
			while ((line = reader.readLine()) != null) {
				if (line.toLowerCase().equals(str.toLowerCase())) {
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
}