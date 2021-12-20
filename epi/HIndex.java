package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class HIndex {
	@EpiTest(testDataFile = "h_index.tsv")
	public static int hIndex(List<Integer> citations) {
		// TODO - you fill in here.
		if (citations.isEmpty()) return 0;
		Collections.sort(citations);
		int h = 1;
		for (int i = citations.size() - 1; i >= 0; i--) {
			h = citations.size() - i;

			if (citations.get(i) < h) {
				return h - 1;
			}
		}

		return h ;
	}

	public static void main(String[] args) {
		//hIndex(Arrays.asList(1,2,9, 9 ,12,13,16,17,17,19,20,20,23,23));
		hIndex(Arrays.asList(1));
		System.exit(
				GenericTest
						.runFromAnnotations(args, "HIndex.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
