package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
import java.util.*;

public class NumberOfScoreCombinations {
	@EpiTest(testDataFile = "number_of_score_combinations.tsv")

	public static int
	numCombinationsForFinalScore(int finalScore,
								 List<Integer> individualPlayScores) {
		// TODO - you fill in here.

		int[][] dp = new int[individualPlayScores.size() + 1][finalScore + 1];
		for (int i = 0; i < dp.length; i++) {
			Arrays.fill(dp[i], -1);
		}

		numCombinationsForFinalScoreHelper(finalScore, individualPlayScores, individualPlayScores.size() - 1, dp);

		return dp[individualPlayScores.size()][finalScore];
	}

	public static int
	numCombinationsForFinalScoreHelper(int finalScore,
								 List<Integer> individualPlayScores, int current, int[][] dp) {
		// TODO - you fill in here.
		if (finalScore == 0) {
			dp[current + 1][finalScore] = 1;
			return 1;
		}

		if (current < 0) {
			return 0;
		}

		if (finalScore < 0) {
			return 0;
		}

		if (dp[current + 1][finalScore] != -1) {
			return dp[current + 1][finalScore];
		}


		int score = individualPlayScores.get(current);

		int withCurrent = numCombinationsForFinalScoreHelper(finalScore - score ,individualPlayScores, current, dp);
		int withoutCurrent = numCombinationsForFinalScoreHelper(finalScore,individualPlayScores, current - 1, dp);

		int result = withCurrent + withoutCurrent;
		dp[current + 1][finalScore] = result;

		return result;

	}


//	public static int
//	numCombinationsForFinalScore(int finalScore,
//								 List<Integer> individualPlayScores) {
//		// TODO - you fill in here.
//
//		int[][] dp = new int[individualPlayScores.size() + 1][finalScore + 1];
//
//		for (int i = 0; i < dp.length; i++) {
//			for (int j = 0; j <  dp[i].length; j++) {
//				if (i == 0) {
//					dp[i][j] = 0;
//					continue;
//				}
//
//				if (j == 0) {
//					dp[i][j] = 1;
//					continue;
//				}
//				int prior = 0;
//				if (j - individualPlayScores.get(i - 1) >= 0) {
//					prior = dp[i][j - individualPlayScores.get(i - 1)];
//				}
//				dp[i][j] =  prior + dp[i - 1][j];
//			}
//		}
//
//		return dp[individualPlayScores.size()][finalScore];
//	}

	public static void main(String[] args) {
		numCombinationsForFinalScore(12, Arrays.asList(2, 3, 7));

		System.exit(
				GenericTest
						.runFromAnnotations(args, "NumberOfScoreCombinations.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
