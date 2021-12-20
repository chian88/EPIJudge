package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
import java.util.*;


public class LongestContainedInterval {
	@EpiTest(testDataFile = "longest_contained_interval.tsv")

	public static int longestContainedRange(List<Integer> A) {
		// TODO - you fill in here.
		Set<Integer> setA = new HashSet<>(A);
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < A.size() ; i++) {
			if (setA.contains(A.get(i))) {
				Set<Integer> subset = new HashSet<>();
				int target = A.get(i);
				subset.add(target);
				setA.remove(target);

				int left = target - 1;

				while (setA.contains(left)) {
					subset.add(left);
					setA.remove(left);
					left -= 1;
				}

				int right = target + 1;
				while (setA.contains(right)) {
					subset.add(right);
					setA.remove(right);
					right += 1;
				}

				max = Math.max(max, subset.size());
			}
		}

		return max;
	}

	public static void main(String[] args) {
		System.exit(
				GenericTest
						.runFromAnnotations(args, "LongestContainedInterval.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
