package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class MaxTrappedWater {
	@EpiTest(testDataFile = "max_trapped_water.tsv")

	public static int getMaxTrappedWater(List<Integer> heights) {
		// TODO - you fill in here.
		int start = 0;
		int end = heights.size() - 1;

		int maxVol = Integer.MIN_VALUE;

		while (start < end) {
			int leftLine = heights.get(start);
			int rightLine = heights.get(end);

			int vol = Math.min(leftLine, rightLine) * (end - start);

			maxVol = Math.max(maxVol, vol);

			if (leftLine < rightLine) {
				start++;
			} else {
				end--;
			}
		}

		return maxVol;
	}

	public static void main(String[] args) {
		System.exit(
				GenericTest
						.runFromAnnotations(args, "MaxTrappedWater.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
