package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.*;

public class LargestRectangleUnderSkyline {
	@EpiTest(testDataFile = "largest_rectangle_under_skyline.tsv")

	public static int calculateLargestRectangle(List<Integer> heights) {
		// TODO - you fill in here.
		Deque<Integer> previousHeight = new ArrayDeque<>();
		int maxRectangle = 0;
		for (int i = 0 ; i <= heights.size(); i++) {
			while (!previousHeight.isEmpty() &&
					currentIsLowerOrReachEnd(heights, i, previousHeight.peekFirst())) {
				int height = heights.get(previousHeight.removeFirst());
				int width = i;
				if (!previousHeight.isEmpty()) {
					width = i - previousHeight.peekFirst() - 1;
				}

				maxRectangle = Math.max(maxRectangle, height * width);

			}
			previousHeight.addFirst(i);
		}

		return maxRectangle;
	}

	private static boolean currentIsLowerOrReachEnd(List<Integer> heights, int currentIdx, Integer peekFirst) {
		if (currentIdx >= heights.size()) return true;

		return heights.get(currentIdx) <= heights.get(peekFirst);

	}

	public static void main(String[] args) {

		calculateLargestRectangle(Arrays.asList(1,4,2,5,6,3,2,6,6,5,2,1,3));
		System.exit(
				GenericTest
						.runFromAnnotations(args, "LargestRectangleUnderSkyline.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
