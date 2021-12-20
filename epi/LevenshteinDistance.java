package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class LevenshteinDistance {
	@EpiTest(testDataFile = "levenshtein_distance.tsv")

	public static int levenshteinDistance(String A, String B) {

		// TODO - you fill in here.

		int[][] dp = new int[A.length() + 1][B.length() + 1];


		for (int row = 0; row <= A.length(); row++) {
			for (int col = 0; col <= B.length(); col++) {
				if (row == 0) {
					dp[row][col] = col;
				} else if (col == 0) {
					dp[row][col] = row;
				} else {
					// dp algorithms.

					if (A.charAt(row - 1) == B.charAt(col - 1)) {
						dp[row][col] = dp[row - 1][col - 1];
					} else {
						int min = Math.min(Math.min(dp[row - 1][col], dp[row][col - 1]), dp[row - 1][col - 1]);

						dp[row][col] = min + 1;
					}
				}
			}
		}

		return dp[A.length()][B.length()];
	}

	public static void main(String[] args) {
		System.exit(
				GenericTest
						.runFromAnnotations(args, "LevenshteinDistance.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
