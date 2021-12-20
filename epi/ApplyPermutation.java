package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
import java.util.*;

public class ApplyPermutation {
	public static void applyPermutation(List<Integer> perm, List<Integer> A) {
		// TODO - you fill in here.
		int counter = 0;

		while (counter < perm.size() ) {
			int permCounter = perm.get(counter);

			Collections.swap(perm, counter, permCounter);
			Collections.swap(A, counter, permCounter);

			if (counter == perm.get(counter)) {
				counter++;
			}
		}

		return;
	}

	@EpiTest(testDataFile = "apply_permutation.tsv")
	public static List<Integer> applyPermutationWrapper(List<Integer> perm,
														List<Integer> A) {
		applyPermutation(perm, A);
		return A;
	}

	public static void main(String[] args) {
		List<Integer> perm = Arrays.asList(new Integer[]{7, 2, 11, 10, 4, 1, 15, 3, 9, 17, 18, 16, 14, 5, 0, 8, 6, 12, 13});
		List<Integer> A = Arrays.asList(new Integer[]{7, 10, 14, 1, 15, 9, 16, 4, 0, 11, 12, 18, 3, 13, 5, 8, 17, 2, 6});
		applyPermutation(perm, A);
		System.exit(
				GenericTest
						.runFromAnnotations(args, "ApplyPermutation.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
