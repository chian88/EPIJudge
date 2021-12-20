package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.*;

public class SmallestSubarrayCoveringSet {

	// Represent subarray by starting and ending indices, inclusive.
	private static class Subarray {
		public Integer start;
		public Integer end;

		public Subarray(Integer start, Integer end) {
			this.start = start;
			this.end = end;
		}
	}

	public static Subarray findSmallestSubarrayCoveringSet(List<String> paragraph,
														   Set<String> keywords) {
		// TODO - you fill in here.
		int minLen = Integer.MAX_VALUE;
		Subarray res = new Subarray(-1, -1);
		int minimumLen = keywords.size();

		Map<String, Integer> locationSaver = new HashMap<>();

		for (int i = 0; i < paragraph.size() ; i++) {
			String word = paragraph.get(i);

			if (keywords.contains(word)) {
				locationSaver.put(word, i);

				if (locationSaver.size() == minimumLen) {
					int max = Integer.MIN_VALUE;
					int min = Integer.MAX_VALUE;

					for (String keyword : keywords) {
						max = Math.max(max, locationSaver.get(keyword));
						min = Math.min(min, locationSaver.get(keyword));
					}

					if (max - min < minLen) {
						minLen = max - min;
						res.start = min;
						res.end = max;
					}
				}
			}

		}


		return res;
	}

	@EpiTest(testDataFile = "smallest_subarray_covering_set.tsv")
	public static int findSmallestSubarrayCoveringSetWrapper(
			TimedExecutor executor, List<String> paragraph, Set<String> keywords)
			throws Exception {
		Set<String> copy = new HashSet<>(keywords);

		Subarray result = executor.run(
				() -> findSmallestSubarrayCoveringSet(paragraph, keywords));

		if (result.start < 0 || result.start >= paragraph.size() ||
				result.end < 0 || result.end >= paragraph.size() ||
				result.start > result.end)
			throw new TestFailure("Index out of range");

		for (int i = result.start; i <= result.end; i++) {
			copy.remove(paragraph.get(i));
		}

		if (!copy.isEmpty()) {
			throw new TestFailure("Not all keywords are in the range");
		}
		return result.end - result.start + 1;
	}

	public static void main(String[] args) {
		System.exit(
				GenericTest
						.runFromAnnotations(args, "SmallestSubarrayCoveringSet.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
