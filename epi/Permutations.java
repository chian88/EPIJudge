package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;
import epi.test_framework.LexicographicalListComparator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Permutations {
	@EpiTest(testDataFile = "permutations.tsv")

	public static List<List<Integer>> permutations(List<Integer> A) {
		// TODO - you fill in here.
		List<List<Integer>> result = new ArrayList<>();
		permutationsHelper(A, result, new ArrayList<>());
		return result;
	}

	static void permutationsHelper(List<Integer> A, List<List<Integer>> result, List<Integer> temp) {

		if (A.size() == 0) {
			result.add(new ArrayList<>(temp));
			return;
		}

		for (int i = 0; i < A.size(); i++) {
			List<Integer> newA = new ArrayList<>(A);
			List<Integer> newTemp = new ArrayList<>(temp);

			newA.remove(i);
			newTemp.add(A.get(i));
			permutationsHelper(newA, result, newTemp);
		}
	}

	@EpiTestComparator
	public static boolean comp(List<List<Integer>> expected,
							   List<List<Integer>> result) {
		if (result == null) {
			return false;
		}
		for (List<Integer> l : expected) {
			Collections.sort(l);
		}
		expected.sort(new LexicographicalListComparator<>());
		for (List<Integer> l : result) {
			Collections.sort(l);
		}
		result.sort(new LexicographicalListComparator<>());
		return expected.equals(result);
	}

	public static void main(String[] args) {
		permutations(Arrays.asList(2,3,5,7));
		System.exit(
				GenericTest
						.runFromAnnotations(args, "Permutations.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
