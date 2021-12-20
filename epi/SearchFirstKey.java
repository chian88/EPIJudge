package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class SearchFirstKey {
	@EpiTest(testDataFile = "search_first_key.tsv")

	public static int searchFirstOfK(List<Integer> A, int k) {
		// TODO - you fill in here.
		int L = 0;
		int U = A.size() - 1;
		int foundIdx = -1;

		while (L <= U) {
			int M = L + (U - L) / 2;

			if (A.get(M) > k) {
				U = M - 1;
			} else if (A.get(M) == k) {
				foundIdx = M;
				U = M - 1;
			} else {
				L = M + 1;
			}

		}

		return foundIdx;
	}

	public static void main(String[] args) {
		System.exit(
				GenericTest
						.runFromAnnotations(args, "SearchFirstKey.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
