package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SmallestNonconstructibleValue {
	@EpiTest(testDataFile = "smallest_nonconstructible_value.tsv")

	public static int smallestNonconstructibleValue(List<Integer> A) {
		// TODO - you fill in here.
		int change = 0;
		Collections.sort(A);
		for (int i = 0; i < A.size(); i++) {
			int coin = A.get(i);

			if (coin > change + 1) {
				return change + 1;
			}

			change += coin;
		}

		return change + 1;
	}

	public static void main(String[] args) {
		System.exit(
				GenericTest
						.runFromAnnotations(args, "SmallestNonconstructibleValue.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
