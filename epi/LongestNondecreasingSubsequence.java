package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LongestNondecreasingSubsequence {
	@EpiTest(testDataFile = "longest_nondecreasing_subsequence.tsv")

	public static int longestNondecreasingSubsequenceLength(List<Integer> A) {
		// TODO - you fill in here.

		int[] len = new int[A.size()];


		for (int i = 0; i < len.length; i++) {
			int curr = A.get(i);
			int maxSub = 0;
			for (int j = 0; j < i; j++) {
				int prev = A.get(j);

				if (prev <= curr) {
					maxSub = Math.max(maxSub, len[j]);
				}
			}

			len[i] = maxSub + 1;
		}
		int max = Integer.MIN_VALUE;

		for (int i = 0; i < len.length; i++) {
			if (len[i] > max) {
				max = len[i];
			}
		}

		return max == Integer.MIN_VALUE ? 0 : max;
	}

	public static void main(String[] args) {
		longestNondecreasingSubsequenceLength(Arrays.asList(0,8,4,12,2,10,6,14,1,9));
		System.exit(
				GenericTest
						.runFromAnnotations(args, "LongestNondecreasingSubsequence.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
