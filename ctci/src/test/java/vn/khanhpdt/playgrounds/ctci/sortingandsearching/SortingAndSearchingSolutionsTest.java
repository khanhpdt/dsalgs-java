package vn.khanhpdt.playgrounds.ctci.sortingandsearching;

import org.hamcrest.Matchers;
import org.junit.Test;
import vn.khanhpdt.playgrounds.ctci.sortingandsearching.SortingAndSearchingSolutions;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class SortingAndSearchingSolutionsTest {

	@Test
	public void testMergeSortedArrays() {
		int[] a = new int[20];
		for (int i = 0; i < 13; i++) {
			a[i] = i;
		}

		int[] b = new int[]{3, 4, 7, 21, 25, 34, 100};

		SortingAndSearchingSolutions.mergeSortedArrays(a, b);
		assertTrue(Arrays.equals(a, new int[]{0, 1, 2, 3, 3, 4, 4, 5, 6, 7, 7, 8, 9, 10, 11, 12, 21, 25, 34, 100}));
	}

	@Test
	public void testMergeSortedArrays2() {
		int[] a = new int[20];
		for (int i = 0; i < 13; i++) {
			a[i] = i;
		}

		int[] b = new int[]{3, 4, 7, 21, 25, 34, 100};

		SortingAndSearchingSolutions.mergeSortedArrays_2(a, b);
		assertTrue(Arrays.equals(a, new int[]{0, 1, 2, 3, 3, 4, 4, 5, 6, 7, 7, 8, 9, 10, 11, 12, 21, 25, 34, 100}));
	}

	@Test
	public void testGroupAnagrams() {
		List<String> s = Arrays.asList("1357", "2464", "123", "7531", "873193", "231", "6424", "2664");

		List<List<String>> result = SortingAndSearchingSolutions.clusterAnagrams(s);

		assertThat(result, Matchers.hasItem(Matchers.containsInAnyOrder("1357", "7531")));
		assertThat(result, Matchers.hasItem(Matchers.containsInAnyOrder("2464", "6424")));
		assertThat(result, Matchers.hasItem(Matchers.containsInAnyOrder("123", "231")));
		assertThat(result, Matchers.hasItem(Matchers.containsInAnyOrder("873193")));
		assertThat(result, Matchers.hasItem(Matchers.containsInAnyOrder("2664")));
	}

	@Test
	public void testGroupAnagrams2() {
		List<String> s = Arrays.asList("1357", "2464", "123", "7531", "873193", "231", "6424", "2664");

		List<List<String>> result = SortingAndSearchingSolutions.clusterAnagrams_2(s);

		assertThat(result, Matchers.hasItem(Matchers.containsInAnyOrder("1357", "7531")));
		assertThat(result, Matchers.hasItem(Matchers.containsInAnyOrder("2464", "6424")));
		assertThat(result, Matchers.hasItem(Matchers.containsInAnyOrder("123", "231")));
		assertThat(result, Matchers.hasItem(Matchers.containsInAnyOrder("873193")));
		assertThat(result, Matchers.hasItem(Matchers.containsInAnyOrder("2664")));
	}

	@Test
	public void testFindInRotatedArray() {
		int[] ints = new int[]{15, 16, 20, 25, 1, 3, 4, 5, 7, 10, 14};
		assertThat(SortingAndSearchingSolutions.findInRotatedArray(ints, 5), is(7));
		assertThat(SortingAndSearchingSolutions.findInRotatedArray(ints, 15), is(0));
		assertThat(SortingAndSearchingSolutions.findInRotatedArray(ints, 14), is(10));
	}

	@Test
	public void testFindInInterspersedArray() {
		String[] strings = new String[]{"at", "", "", "", "ball", "", "", "car", "", "", "dad", "", ""};
		assertThat(SortingAndSearchingSolutions.findInInterspersedArray(strings, "at"), is(0));
		assertThat(SortingAndSearchingSolutions.findInInterspersedArray(strings, "ball"), is(4));
		assertThat(SortingAndSearchingSolutions.findInInterspersedArray(strings, "dad"), is(10));
	}

	@Test
	public void testFindInSortedMatrix() throws Exception {
		int[][] matrix = new int[][]{new int[]{1, 10, 18}, new int[]{2, 11, 21}, new int[]{5, 31, 45}};

		assertTrue(Arrays.equals(SortingAndSearchingSolutions.findInSortedMatrix(matrix, 1), new int[]{0, 0}));
		assertTrue(Arrays.equals(SortingAndSearchingSolutions.findInSortedMatrix(matrix, 45), new int[]{2, 2}));
		assertTrue(Arrays.equals(SortingAndSearchingSolutions.findInSortedMatrix(matrix, 31), new int[]{2, 1}));
		assertTrue(Arrays.equals(SortingAndSearchingSolutions.findInSortedMatrix(matrix, 5), new int[]{2, 0}));
	}

	@Test
	public void testCircusTowerMaxSize_1() {
		int[][] people = new int[][]{new int[]{65, 100}, new int[]{70, 150}, new int[]{56, 90}, new int[]{75, 190},
				new int[]{60, 95}, new int[]{68, 110}};

		assertThat(SortingAndSearchingSolutions.circusTowerMaxSize(people), is(6));
	}

	@Test
	public void testCircusTowerMaxSize_2() {
		int[][] people = new int[][]{new int[]{65, 100}, new int[]{70, 120}, new int[]{56, 120}, new int[]{75, 140},
				new int[]{60, 90}, new int[]{68, 80}};

		assertThat(SortingAndSearchingSolutions.circusTowerMaxSize(people), is(4));
	}
}
