package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IntSquareRoot {
	@EpiTest(testDataFile = "int_square_root.tsv")

	public static int squareRoot(int k) {
		// TODO - you fill in here.

		int low = 0;
		int high = k;

		while (low <= high) {
			int median = (low + high) / 2;

			if (Math.pow(median, 2) > k) {
				high = median - 1;
			} else {
				low = median + 1;
			}
		}
		return low - 1;
	}

	public static void main(String[] args) {
		squareRoot(21);
		System.exit(
				GenericTest
						.runFromAnnotations(args, "IntSquareRoot.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
