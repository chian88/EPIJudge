package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class SearchShiftedSortedArray {
	@EpiTest(testDataFile = "search_shifted_sorted_array.tsv")

	public static int searchSmallest(List<Integer> A) {
		// TODO - you fill in here.
		int L = 0;
		int U = A.size() - 1;

		while (L < U) {
			int M = L + (U - L) / 2;


			if (A.get(M) > A.get(U)) {
				// smallest is to the right
				L = M + 1;

			} else {
				// smallest to the left
				U = M ;
			}
		}

		return L;

	}

	public static void main(String[] args) {
		System.exit(
				GenericTest
						.runFromAnnotations(args, "SearchShiftedSortedArray.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
