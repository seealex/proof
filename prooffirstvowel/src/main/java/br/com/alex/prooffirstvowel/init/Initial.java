package br.com.alex.prooffirstvowel.init;

public class Initial {

	static final char CHAR_NULL = 0;
	
	public static void main(String[]args) {
		//Test on console if you need
		System.out.println(initialCall(args[0]));
	}
	
	public static char initialCall(String proofChars) {
		char c = vowelNotRepeated(new StreamImpl(proofChars));
		return c;
	}
	
	private static char vowelNotRepeated(Stream input) {

		char vowelNotRepeated = CHAR_NULL;
		char previous = CHAR_NULL;
		char previous1 = CHAR_NULL;

		while (input.hasNext()) {

			char next = input.getNext();
			
			if (previous != 0 && isDifferentAndVowel(previous1, previous, next)) {
					vowelNotRepeated = next;
			}
			previous1 = previous; 
			previous = next;
		}

		return vowelNotRepeated;
	}


	private static final boolean isDifferentAndVowel(char previousConsonant, char previous, char next) {
		Character c = Character.toLowerCase(next);
		return Character.toLowerCase(previous) != c && isVowel(c) && isVowel(previousConsonant);
	}

	private static boolean isVowel(Character c) {
		if (c.equals('A') || c.equals('E') || c.equals('I') || c.equals('O') || c.equals('U')
				|| c.equals('a') || c.equals('e') || c.equals('i') || c.equals('o')
				|| c.equals('u')) {
			return true;
		} else {
			return false;
		}

	}

}
