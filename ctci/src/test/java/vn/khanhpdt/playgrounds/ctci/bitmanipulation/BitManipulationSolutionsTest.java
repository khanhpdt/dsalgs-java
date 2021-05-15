package vn.khanhpdt.playgrounds.ctci.bitmanipulation;

import org.junit.Test;

import java.util.Arrays;
import java.util.stream.IntStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static vn.khanhpdt.playgrounds.ctci.bitmanipulation.BitManipulationSolutions.*;

/**
 * @author khanhpdt
 */
public class BitManipulationSolutionsTest {

    @Test
    public void testInjectBits() {
        assertThat(injectBits(0b11_10110_1100, 0b11111, 4, 8), is(0b11_11111_1100));
        assertThat(injectBits(0b100_00000_00, 0b10011, 2, 6), is(0b100_10011_00));
        assertThat(injectBits(0b10000, 0b11111, 0, 4), is(0b11111));
    }

    @Test
    public void testFractionInBinary() throws Exception {
        assertThat(fractionInBinary(0.0625), is("0.0001"));
        assertThat(fractionInBinary(0.625), is("0.101"));
        assertThat(fractionInBinary(0.72), is("ERROR"));
    }

    @Test
    public void testGetNextLargerNumberSameOneBitCount() {
        assertThat(getNextLargerNumberSameOneBitCount(0b101), is(0b110));
        assertThat(getNextLargerNumberSameOneBitCount(0b1110), is(0b10011));
    }

    @Test
    public void testGetNextLargerNumberSameOneBitCount2() {
        assertThat(getNextLargerNumberSameOneBitCount_2(0b101), is(0b110));
        assertThat(getNextLargerNumberSameOneBitCount_2(0b1110), is(0b10011));
    }

    @Test
    public void testGetNextSmallerNumberSameOneBitCount() {
        assertThat(getNextSmallerNumberSameOneBitCount(0b110), is(0b101));
        assertThat(getNextSmallerNumberSameOneBitCount(0b111100001), is(0b111011000));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetNextSmallerNumberSameOneBitCount_notExist() {
        getNextSmallerNumberSameOneBitCount(0b111111);
    }

    @Test
    public void testProblem54() throws Exception {
        assertThat(problem54(0b100000000000), is(true));
        assertThat(problem54(0), is(true));
        assertThat(problem54(0b100000000000_1), is(false));
        assertThat(problem54(0b1_1_000000), is(false));
    }

	@Test
	public void testNumberOfBitsToConvert() throws Exception {
		assertThat(countNumberOfBitsToConvert(0b11111, 0b1110), is(2));
		assertThat(countNumberOfBitsToConvert(0b11_010_111, 0b11_101_111), is(3));
		assertThat(countNumberOfBitsToConvert(0b101010101_11, 0b11), is(5));
	}

    @Test
    public void testNumberOfBitsToConvert_2() throws Exception {
        assertThat(countNumberOfBitsToConvert_2(0b11111, 0b1110), is(2));
        assertThat(countNumberOfBitsToConvert_2(0b11_010_111, 0b11_101_111), is(3));
        assertThat(countNumberOfBitsToConvert_2(0b101010101_11, 0b11), is(5));
    }

    @Test
    public void testSwapOddAndEvenBits() throws Exception {
        assertThat(swapOddAndEvenBits(0b11_10_01_00), is(0b11_01_10_00));
        assertThat(swapOddAndEvenBits(0b1_10_01_10), is(0b10_01_10_01));
    }

    @Test
    public void testSwapOddAndEvenBits2() throws Exception {
        assertThat(swapOddAndEvenBits_2(0b11_10_01_00), is(0b11_01_10_00));
        assertThat(swapOddAndEvenBits_2(0b1_10_01_10), is(0b10_01_10_01));
    }

    @Test
    public void testFindMissingNumber() throws Exception {
        assertThat(findMissingNumber(20, IntStream.range(0, 21).filter(i -> i != 12).toArray()), is(12));
        assertThat(findMissingNumber(100, IntStream.range(0, 101).filter(i -> i != 81).toArray()), is(81));
    }

    @Test
    public void testFindMissingNumber_2() throws Exception {
        assertThat(findMissingNumber_2(20, IntStream.range(0, 21).filter(i -> i != 12).toArray()), is(12));
        assertThat(findMissingNumber_2(100, IntStream.range(0, 101).filter(i -> i != 81).toArray()), is(81));
    }

    @Test
    public void testDrawHorizontalLine_widthEight() {
        // 8x10 screen
        int width = 8;
        byte[] screen = new byte[20];
        Arrays.fill(screen, (byte)0);

        drawHorizontalLine(screen, width, 0, 7, 2);

        assertThat(screen[2], is((byte)0b11111111));
    }

    @Test
    public void testDrawHorizontalLine_widthMultipleEight() {
        // 24x10 screen
        int width = 24;
        byte[] screen = new byte[30];
        Arrays.fill(screen, (byte)0);

        drawHorizontalLine(screen, width, 2, 20, 3);

        assertThat(screen[9], is((byte)0b00111111));
        assertThat(screen[10], is((byte)0b11111111));
        assertThat(screen[11], is((byte)0b11111000));
    }
}