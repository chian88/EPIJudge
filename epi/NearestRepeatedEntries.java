package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
import java.util.*;

public class NearestRepeatedEntries {
	@EpiTest(testDataFile = "nearest_repeated_entries.tsv")

	public static int findNearestRepetition(List<String> paragraph) {
		// TODO - you fill in here.

		Map<String, Integer> distanceSoFar = new HashMap<>();
		int minDist = Integer.MAX_VALUE;
		for (int i = 0; i < paragraph.size(); i++) {
			String str = paragraph.get(i);
			if (distanceSoFar.containsKey(str)) {
				int dist = i - distanceSoFar.get(str);
				minDist = Math.min(dist, minDist);
			}
			distanceSoFar.put(str, i);
		}
		return minDist == Integer.MAX_VALUE ? -1 : minDist;
	}

	public static void main(String[] args) {
		System.exit(
				GenericTest
						.runFromAnnotations(args, "NearestRepeatedEntries.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
