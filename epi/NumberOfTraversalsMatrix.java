package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class NumberOfTraversalsMatrix {
	@EpiTest(testDataFile = "number_of_traversals_matrix.tsv")

	public static int numberOfWays(int n, int m) {
		// TODO - you fill in here.
		int[][] dp = new int[n][m];

		for (int row = 0; row < n; row++) {
			for (int col = 0; col < m; col++) {
				if (row == 0) {
					dp[row][col] = 1;
				} else if (col == 0) {
					dp[row][col] = 1;
				} else {
					dp[row][col] = dp[row - 1][col] + dp[row][col - 1];
				}
			}
		}

		return dp[n - 1][m - 1];
	}

	public static void main(String[] args) {
		System.exit(
				GenericTest
						.runFromAnnotations(args, "NumberOfTraversalsMatrix.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
