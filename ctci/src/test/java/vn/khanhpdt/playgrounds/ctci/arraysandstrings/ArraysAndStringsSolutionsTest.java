package vn.khanhpdt.playgrounds.ctci.arraysandstrings;

import org.hamcrest.collection.IsArray;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ArraysAndStringsSolutionsTest {

	@Test
	public void testStringUniqueCharacters() throws Exception {
		String uniqueCharactersString = "abcdefgh";

		assertThat(ArraysAndStringsSolutions.hasUniqueCharacters_1(uniqueCharactersString), is(true));
		assertThat(ArraysAndStringsSolutions.hasUniqueCharacters_2(uniqueCharactersString), is(true));
		assertThat(ArraysAndStringsSolutions.hasUniqueCharacters_3(uniqueCharactersString), is(true));
	}

	@Test
	public void testStringNonUniqueCharacters() throws Exception {
		String nonUniqueCharactersString = "abcdefah";

		assertThat(ArraysAndStringsSolutions.hasUniqueCharacters_1(nonUniqueCharactersString), is(false));
		assertThat(ArraysAndStringsSolutions.hasUniqueCharacters_2(nonUniqueCharactersString), is(false));
		assertThat(ArraysAndStringsSolutions.hasUniqueCharacters_3(nonUniqueCharactersString), is(false));
	}

	@Test
	public void testReverseString() throws Exception {
		String s = "reverse this string";

		char[] reversed = ArraysAndStringsSolutions.reverseString(s.toCharArray());

		assertThat(String.valueOf(reversed), is("gnirts siht esrever"));
	}

	@Test
	public void testReverseString_2() throws Exception {
		String s = "reverse this string";

		char[] reversed = ArraysAndStringsSolutions.reverseString_2(s.toCharArray());

		assertThat(String.valueOf(reversed), is("gnirts siht esrever"));
	}

	@Test
	public void testCheckPermutation_1() throws Exception {
		String s1 = "abcdefghi";
		String s2 = "ighfedcab";

		assertThat(ArraysAndStringsSolutions.checkPermutation(s1, s2), is(true));
	}

	@Test
	public void testCheckPermutation_2() throws Exception {
		String s1 = "abcdefghi";
		String s2 = "ighfekcab";

		assertThat(ArraysAndStringsSolutions.checkPermutation(s1, s2), is(false));
	}

	@Test
	public void testCheckPermutation_3() throws Exception {
		String s1 = "abcdefghifda";
		String s2 = "ighfedcabadf";

		assertThat(ArraysAndStringsSolutions.checkPermutation(s1, s2), is(true));
	}

	@Test
	public void testReplaceSpaces() throws Exception {
		String s = "Mr John Smith    ";

		String result = ArraysAndStringsSolutions.replaceSpaces(s);

		assertThat(result, is("Mr%20John%20Smith"));
	}

	@Test
	public void testReplaceSpaces_2() throws Exception {
		String s = "Mr John Smith    ";

		char[] result = ArraysAndStringsSolutions.replaceSpaces_2(s.toCharArray(), 13);

		assertThat(String.valueOf(result), is("Mr%20John%20Smith"));
	}

	@Test
	public void testCheckPalindromePermutation() {
		assertThat(ArraysAndStringsSolutions.checkPalindromePermutation("Tact Coa"), is(true));
		assertThat(ArraysAndStringsSolutions.checkPalindromePermutation("Tact aCoa"), is(false));
	}

	@Test
	public void testIsOneOrZeroEditAway() {
		assertThat(ArraysAndStringsSolutions.isOneOrZeroEditAway("pale", "ple"), is(true));
		assertThat(ArraysAndStringsSolutions.isOneOrZeroEditAway("pales", "pale"), is(true));
		assertThat(ArraysAndStringsSolutions.isOneOrZeroEditAway("pale", "bale"), is(true));
		assertThat(ArraysAndStringsSolutions.isOneOrZeroEditAway("pale", "bake"), is(false));
	}

	@Test
	public void testCompressString_1() throws Exception {
		String s = "aabcccccaaa";

		String compressed = ArraysAndStringsSolutions.compress(s);

		assertThat(compressed, is("a2b1c5a3"));
	}

	@Test
	public void testCompressString_2() throws Exception {
		String s = "aabbcaaa";

		String compressed = ArraysAndStringsSolutions.compress(s);

		assertThat("should be the original because length after the compression is not reduced",
				compressed, is(s));
	}

	@Test
	public void testCompressString_3() throws Exception {
		String s = "aabbccaa";

		String compressed = ArraysAndStringsSolutions.compress(s);

		assertThat("should be the original because length after the compression is not reduced",
				compressed, is(s));
	}

	@Test
	public void testRotateMatrix1_1() throws Exception {
		Integer[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};

		Integer[][] rotated = ArraysAndStringsSolutions.rotateRight_1(matrix);

		assertThat(rotated[0], IsArray.array(is(7), is(4), is(1)));
		assertThat(rotated[1], IsArray.array(is(8), is(5), is(2)));
		assertThat(rotated[2], IsArray.array(is(9), is(6), is(3)));
	}

	@Test
	public void testRotateMatrix1_2() throws Exception {
		Integer[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {10, 11, 12}};

		Integer[][] rotated = ArraysAndStringsSolutions.rotateRight_1(matrix);

		assertThat(rotated[0], IsArray.array(is(10), is(7), is(4), is(1)));
		assertThat(rotated[1], IsArray.array(is(11), is(8), is(5), is(2)));
		assertThat(rotated[2], IsArray.array(is(12), is(9), is(6), is(3)));
	}

	@Test
	public void testRotateMatrix2_1() throws Exception {
		Integer[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};

		ArraysAndStringsSolutions.rotateRight_2(matrix);

		assertThat(matrix[0], IsArray.array(is(7), is(4), is(1)));
		assertThat(matrix[1], IsArray.array(is(8), is(5), is(2)));
		assertThat(matrix[2], IsArray.array(is(9), is(6), is(3)));
	}

	@Test
	public void testRotateMatrix2_2() throws Exception {
		Integer[][] matrix = {{1, 2, 3, 10}, {4, 5, 6, 11}, {7, 8, 9, 12}, {13, 14, 15, 16}};

		ArraysAndStringsSolutions.rotateRight_2(matrix);

		assertThat(matrix[0], IsArray.array(is(13), is(7), is(4), is(1)));
		assertThat(matrix[1], IsArray.array(is(14), is(8), is(5), is(2)));
		assertThat(matrix[2], IsArray.array(is(15), is(9), is(6), is(3)));
		assertThat(matrix[3], IsArray.array(is(16), is(12), is(11), is(10)));
	}

	@Test
	public void testSetZeros_1() {
		Integer[][] matrix = {{1, 0, 3}, {4, 5, 6}, {7, 8, 9}};

		Integer[][] result = ArraysAndStringsSolutions.setZeros(matrix);

		assertThat(result[0], IsArray.array(is(0), is(0), is(0)));
		assertThat(result[1], IsArray.array(is(4), is(0), is(6)));
		assertThat(result[2], IsArray.array(is(7), is(0), is(9)));
	}

	@Test
	public void testSetZeros_2() {
		Integer[][] matrix = {{1, 0, 0, 4}, {0, 6, 7, 8}, {9, 10, 11, 0}};

		Integer[][] result = ArraysAndStringsSolutions.setZeros(matrix);

		assertThat(result[0], IsArray.array(is(0), is(0), is(0), is(0)));
		assertThat(result[1], IsArray.array(is(0), is(0), is(0), is(0)));
		assertThat(result[2], IsArray.array(is(0), is(0), is(0), is(0)));
	}

	@Test
	public void testSetZeros2_1() {
		Integer[][] matrix = {{1, 0, 0, 4}, {0, 6, 7, 8}, {9, 10, 11, 0}};

		Integer[][] result = ArraysAndStringsSolutions.setZeros_2(matrix);

		assertThat(result[0], IsArray.array(is(0), is(0), is(0), is(0)));
		assertThat(result[1], IsArray.array(is(0), is(0), is(0), is(0)));
		assertThat(result[2], IsArray.array(is(0), is(0), is(0), is(0)));
	}

	@Test
	public void testCheckRotation_1() {
		String s1 = "erbottlewat";
		String s2 = "waterbottle";

		boolean isRotation = ArraysAndStringsSolutions.checkRotation(s1, s2);

		assertThat(isRotation, is(true));
	}

	@Test
	public void testCheckRotation_2() {
		String s1 = "waterbottle";
		String s2 = "tlewaterbot";

		boolean isRotation = ArraysAndStringsSolutions.checkRotation(s1, s2);

		assertThat(isRotation, is(true));
	}

	@Test
	public void testCheckRotation_3() {
		String s1 = "waterbottle";
		String s2 = "tleawterbot";

		boolean isRotation = ArraysAndStringsSolutions.checkRotation(s1, s2);

		assertThat(isRotation, is(false));
	}
}