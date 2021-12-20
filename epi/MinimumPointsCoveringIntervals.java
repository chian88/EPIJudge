package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;

import java.util.List;
import java.util.*;

public class MinimumPointsCoveringIntervals {
	@EpiUserType(ctorParams = {int.class, int.class})

	public static class Interval {
		public int left, right;

		public Interval(int l, int r) {
			this.left = l;
			this.right = r;
		}
	}

	@EpiTest(testDataFile = "minimum_points_covering_intervals.tsv")

	public static Integer findMinimumVisits(List<Interval> intervals) {
		// TODO - you fill in here.
		Collections.sort(intervals, (a, b) -> {
			return Integer.compare(a.right, b.right);
		});
		int latestTime = -1;
		int result = 0;
		for (int i = 0; i < intervals.size(); i++) {
			Interval time = intervals.get(i);

			if (time.left > latestTime) {
				result += 1;
				latestTime = time.right;
			}
		}

		return result;
	}

	public static void main(String[] args) {
		System.exit(
				GenericTest
						.runFromAnnotations(args, "MinimumPointsCoveringIntervals.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
