package vn.khanhpdt.playgrounds.ctci.bitmanipulation;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * @author khanhpdt
 */
class BitManipulationSolutions {

    /**
     * Problem 5.1: Injects M into the space between ith and jth bits (inclusive) in N
     */
    static int injectBits(int N, int M, int i, int j) {
        // note: ((1 << i) - 1) produces a sequence of bits where the i lower-order bits are 1,
        // and the higher-order bits are 0

        // keep all bits from MSB to (j + 1)
        int initN = N & (~ ((1 << (j + 1)) - 1));
        // keep all bits from i to 0
        int tailN = N & ((1 << (i + 1)) - 1);
        // shift M to the left i bits
        int shiftedM = M << i;
        return initN | shiftedM | tailN;
    }

    /**
     * Problem 5.2: Prints the binary representation of the given double number.
     * @see <a href="http://cs.furman.edu/digitaldomain/more/ch6/dec_frac_to_bin.htm">dec_frac_to_bin</a>
     */
    static String fractionInBinary(double d) {
        if (d == 0.0) {
            return "0";
        }

        final int lengthLimit = 32;

        StringBuilder sb = new StringBuilder("0.");
        while (BigDecimal.ZERO.compareTo(BigDecimal.valueOf(d)) != 0 && sb.length() <= lengthLimit) {
            double doubleD = d * 2;

            long integerPart = (long) Math.floor(doubleD);
            sb.append(integerPart);

            d = doubleD - integerPart;
        }

        if (sb.length() > lengthLimit) {
            return "ERROR";
        }

        return sb.toString();
    }

    // this solution mainly uses array manipulation
    static int getNextLargerNumberSameOneBitCount_2(int n) {
        char[] bits = Integer.toBinaryString(n).toCharArray();

        // find the first bit 0 whose right bit is 1
        int indexOfZeroWhoseRightIsOne = -1;
        int nZeros = 0;
        boolean oneFound = false;
        // go from right to left
        for (int i = bits.length - 1; i >= 0; i--) {
            if (bits[i] == '0' && oneFound) {
                indexOfZeroWhoseRightIsOne = i;
                break;
            }
            if (bits[i] == '1' && !oneFound) {
                oneFound = true;
            }
            if (bits[i] == '0') nZeros++;
        }

        char[] result;
        int flippedZeroIndex;
        if (indexOfZeroWhoseRightIsOne == -1) {
            // no 0 bit whose right bit is 1 is found. we add such 0 bit to the head of the array.
            result = new char[bits.length + 1];
            flippedZeroIndex = 0;
        } else {
            result = new char[bits.length];
            System.arraycopy(bits, 0, result, 0, indexOfZeroWhoseRightIsOne);
            flippedZeroIndex = indexOfZeroWhoseRightIsOne;
        }

        // this flip gives us a larger number than the given number
        result[flippedZeroIndex] = '1';
        result[flippedZeroIndex + 1] = '0';

        // makes the number smallest possible by moving the zeroes to before the ones
        for (int i = flippedZeroIndex + 2, nZero = 0; i < result.length; i++) {
            if (nZero < nZeros) {
                result[i] = '0';
                nZero++;
            } else {
                result[i] = '1';
            }
        }

        return Integer.parseInt("+" + String.valueOf(result), 2);
    }

    // this solution uses bit manipulation
    static int getNextLargerNumberSameOneBitCount(int n) {
        char[] bits = Integer.toBinaryString(n).toCharArray();

        // find the first bit 0 whose right bit is 1
        int indexOfZeroWhoseRightIsOne = -1;
        for (int i = 0; i < bits.length - 1; i++) {
            if (bits[i] == '0' && bits[i + 1] == '1') {
                indexOfZeroWhoseRightIsOne = i;
                break;
            }
        }

        // count the number of ones and zeros before the zero whose right is 1
        int nOnes = 0, nZeros = 0;
        for (int i = indexOfZeroWhoseRightIsOne + 1; i < bits.length; i++) {
            if (bits[i] == '0') {
                nZeros++;
            }
            else {
                nOnes++;
            }
        }

        int result = n;
        // flip the bit 0 to 1 to get a number larger than the given number.
        result |= 1 << ((bits.length - 1) - indexOfZeroWhoseRightIsOne);

        // we want to make it as small as possible while keeping the same numbers of zeros and ones.
        // set the trailing bits to 1. since we already added 1, there are (nOnes - 1) ones left
        result |= (1 << (nOnes - 1)) - 1;
        // set the in-between bits to 0
        result &= ~ (((1 << (nZeros + 1)) - 1) << (nOnes - 1));

        return result;
    }

    // this solution uses bit manipulation
    static int getNextSmallerNumberSameOneBitCount(int n) {
        char[] bits = Integer.toBinaryString(n).toCharArray();

        // find the first bit 1 whose right bit is 0
        int indexOfOneWhoseRightIsZero = -1;
        for (int i = 0; i < bits.length - 1; i++) {
            if (bits[i] == '1' && bits[i + 1] == '0') {
                indexOfOneWhoseRightIsZero = i;
                break;
            }
        }

        if (indexOfOneWhoseRightIsZero == -1) {
            throw new IllegalArgumentException("Smaller number than " + n + " with same one bit count does not exist!");
        }

        // count the number of ones and zeros before the 1 whose right is 0
        int nOnes = 0, nZeros = 0;
        for (int i = indexOfOneWhoseRightIsZero + 1; i < bits.length; i++) {
            if (bits[i] == '0') {
                nZeros++;
            }
            else {
                nOnes++;
            }
        }

        int result = n;
        // flip the bit 1 to 0 to get a number smaller than the given number
        result &= ~(1 << ((bits.length - 1) - indexOfOneWhoseRightIsZero));

        // we want to make it as large as possible while keeping the same numbers of zeros and ones.
        // set the trailing bits to 0. since we already added 0, there are (nZeros - 1) zeros left
        result &= ~((1 << (nZeros - 1)) - 1);
        // set the in-between bits to 1
        result |= ((1 << (nOnes + 1)) - 1) << (nZeros - 1);

        return result;
    }

    /**
     * Problem 5.4: Explain what ((n & (n - 1)) == 0) indicates.
     */
    static boolean problem54(int n) {
        // n & (n - 1) flips the right most bit 1 and all the zeros following that bit.
        // let say n1 is the part in n that is before the bit 1, then n = (n1)1(00...0) and (n - 1) = (n1)0(11...1).
        // if (n & (n - 1) == 0), then (n1 & n1) == 0, meaning that n1 = 0. Thus, n = 1(00...0) and is a power of 2.
        // so this test (n & (n - 1) == 0) can check if n is a power of 2.
	    // and since n & (n - 1) == 0 if n = 0, this test can also check if n is 0.
	    // so (n & (n - 1) == 0) checks if n is 0 or a power of 2.
        return (n & (n - 1)) == 0;
    }

	/**
	 * Problem 5.5
	 * <p>This solution uses an bit manipulation to count number of bits.</p>
	 */
	static int countNumberOfBitsToConvert(int a, int b) {
		int xor = a ^ b;
		// since xor contains 1 bit where the bits in a and b are different, we only need to count the bit 1 in xor.
		int result = 0;
		while (xor != 0) {
			if ((xor & 1) == 1) {
				result++;
			}
			// uses shifting
			xor = xor >>> 1;
			// or we can also flip the processed least significant bits, but this needs a to keep track of the number of
			// the processed bit.
		}
		return result;
	}

	/**
	 * Problem 5.5
	 * <p>This solution uses an additional array to count number of bits.</p>
	 */
	static int countNumberOfBitsToConvert_2(int a, int b) {
		int xor = a ^ b;
		// since xor contains 1 bit where the bits in a and b are different, we only need to count the bit 1 in xor.
		int result = 0;
		for (char bit : Integer.toBinaryString(xor).toCharArray()) {
			if (bit == '1') result++;
		}
		return result;
	}

    /**
     * Problem 5.6
     * <p> This solution uses array manipulation
     */
    static int swapOddAndEvenBits_2(int n) {
        char[] bits = Integer.toBinaryString(n).toCharArray();
        // start from the LSB (the 0th bit) at the end of the bit array
        int i = bits.length - 1;
        for (;i >= 1; i -= 2) {
            char tmp = bits[i];
            bits[i] = bits[i - 1];
            bits[i - 1] = tmp;
        }

        String swappedBits;
        if (i == 0) {
            // the length of the bit array is odd. the first bit must be 1 and was not swapped yet.
            bits[0] = '0';
            swappedBits = "1" + String.valueOf(bits);
        } else {
            swappedBits = String.valueOf(bits);
        }

        // assume that we only handle positive numbers
        return Integer.parseInt("+" + swappedBits, 2);
    }

    /**
     * Problem 5.6
     * <p>This solution uses bit manipulation.
     */
    static int swapOddAndEvenBits(int n) {
        int oddBits = 0xaaaaaaaa & n;
        int evenBits = 0x55555555 & n;
        return (oddBits >>> 1) | (evenBits << 1);
    }

    /**
     * Problem 5.7
     * <p>Use bitmap data structure.</p>
     */
    static int findMissingNumber(int max, int[] binaryNumbers) {
        // also called bitmap data structure
        boolean[] markers = new boolean[max + 1];
        Arrays.fill(markers, false);

        for (int binaryNumber : binaryNumbers) {
            // note: we can only read bit by bit, not the whole integer
            int number = convertBinaryToDecimal(binaryNumber);
            markers[number] = true;
        }

        for (int i = 0; i < markers.length; i++) {
            if (!markers[i]) return i;
        }

        throw new IllegalArgumentException("The given array has no missing number!");
    }

	/**
	 * Problem 5.7
	 * <p>Use sum.</p>
	 */
    static int findMissingNumber_2(int max, int[] binaryNumbers) {
	    // sum from 0 to max except the missing number
        long sum = 0;
        for (int binaryNumber : binaryNumbers) {
            // note: we can only read bit by bit, not the whole integer
            int number = convertBinaryToDecimal(binaryNumber);
	        sum += number;
        }

        // sum from 0 to max
	    long sumAll = (max * (max + 1)) / 2;

	    return (int) (sumAll - sum);
    }

    private static int convertBinaryToDecimal(int binaryNumber) {
        final int nBits = 32;
        int result = 0;
        for (int j = 0; j < nBits; j++) {
            if (isBitOne(binaryNumber, j)) result += Math.pow(2, j);
        }
        return result;
    }

    /**
     * Checks if ith bit is 1.
     */
    private static boolean isBitOne(int n, int i) {
        return (n & (1 << i)) != 0;
    }

    /**
     * Problem 5.8
     */
    static void drawHorizontalLine(byte[] screen, int width, int x1, int x2, int y) {
        final int nBits = 8;
        final int nBytesPerRow = width / nBits;
        final int rowIndex = y * nBytesPerRow;

        for (int w = Math.min(x1, x2); w <= Math.max(x1, x2); w++) {
            int subRowIndex = rowIndex + (w / nBits);
            int colInRow = w % nBits;
            screen[subRowIndex] |= (1 << (nBits - 1 - colInRow));
        }
    }
}
