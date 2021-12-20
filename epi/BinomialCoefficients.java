package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class BinomialCoefficients {
	@EpiTest(testDataFile = "binomial_coefficients.tsv")

	public static int computeBinomialCoefficient(int n, int k) {
		// TODO - you fill in here.
		int[][] dp = new int[n + 1][k + 1];
		return computeBinomialCoefficientHelper(n , k, dp);

	}

	static int computeBinomialCoefficientHelper(int n, int k, int[][] dp) {
		if (k == 0 || n == k) {
			return 1;
		}

		if (dp[n][k] == 0) {
			int	withoutY = computeBinomialCoefficientHelper(n - 1, k, dp);

			int withY = computeBinomialCoefficientHelper(n - 1, k - 1, dp);

			dp[n][k] = withoutY + withY;
		}

		return dp[n][k];
	}

	public static void main(String[] args) {
		System.exit(
				GenericTest
						.runFromAnnotations(args, "BinomialCoefficients.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
