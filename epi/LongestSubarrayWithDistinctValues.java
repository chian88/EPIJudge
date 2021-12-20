package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.*;

public class LongestSubarrayWithDistinctValues {
	@EpiTest(testDataFile = "longest_subarray_with_distinct_values.tsv")

	public static int longestSubarrayWithDistinctEntries(List<Integer> A) {
		// TODO - you fill in here.
		Map<Integer, Integer> loc = new HashMap<>();
		int max = Integer.MIN_VALUE;
		int backStop = 0;
		for (int i = 0; i < A.size(); i++) {
			if (loc.containsKey(A.get(i))) {
				if (loc.get(A.get(i)) + 1 >= backStop) {
					backStop = loc.get(A.get(i)) + 1;
				}

				loc.put(A.get(i), i);
			} else {
				loc.put(A.get(i), i);
			}
			int dist = i - backStop + 1;
			if (dist > max) {
				max = dist;
			}
		}

		return max == Integer.MIN_VALUE ? 0 : max;
	}

	public static void main(String[] args) {
		longestSubarrayWithDistinctEntries(Arrays.asList(1,2,1,3,4,5,3,7,8,3));
		System.exit(
				GenericTest
						.runFromAnnotations(args, "LongestSubarrayWithDistinctValues.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
