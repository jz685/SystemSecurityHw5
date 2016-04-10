import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

class classify {

	// This english dictionary is provided by ubuntu as a internal dictionay file
	// static final String dictionaryFile = "american-english";

	// this password/dictionary check file is provided by Openwall including easy passwords and dictionary 
	// in many other languages
	// http://www.openwall.com/wordlists/
	static final String dictionaryFile = "passwordCheck";
	
	public static void main(String[] args) {
		// Check num of input
		if (args.length != 1) {
			System.err.println("Wrong number of arguments");
			return;
		}
		// Get input
		String inputStr = args[0];

		// Test checkIsWord
		System.out.println(checkIsWord(inputStr));

	}

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
}