package vn.khanhpdt.playgrounds.datastructures.alphabets;

public class LowerCaseLetterAlphabet implements Alphabet<Character> {

	public static final LowerCaseLetterAlphabet INSTANCE = new LowerCaseLetterAlphabet();

	private static final char[] ALPHABET = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
			'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

	private static final char FIRST_LETTER = ALPHABET[0];

	private static final char LAST_LETTER = ALPHABET[ALPHABET.length - 1];

	private LowerCaseLetterAlphabet() {

	}

	@Override
	public int toIndex(Character key) {
		if (key < FIRST_LETTER || key > LAST_LETTER) {
			throw new IllegalArgumentException(key + " not in the set");
		}
		return key - FIRST_LETTER;
	}

	@Override
	public Character fromIndex(int index) {
		if (index < 0 || index > 25) {
			throw new IndexOutOfBoundsException(index + " not in the range");
		}
		return ALPHABET[index];
	}

	@Override
	public int size() {
		return ALPHABET.length;
	}

}
