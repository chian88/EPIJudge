package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashMap;
import java.util.List;
import java.util.*;

public class ThreeSum {
	@EpiTest(testDataFile = "three_sum.tsv")
	public static boolean hasThreeSum(List<Integer> A, int t) {
		// TODO - you fill in here.
		Collections.sort(A);

		for (int i = 0; i < A.size(); i++) {
			int remaining = t - A.get(i);

			int p = 0;
			int q = A.size() - 1;

			while (p <= q) {
				int sum = A.get(p) + A.get(q);

				if (sum < remaining) {
					p++;
				} else if (sum > remaining) {
					q--;
				} else {
					return true;
				}
			}
		}


		return false;
	}

//	public static boolean hasThreeSum(List<Integer> A, int t) {
//		// TODO - you fill in here.
//		Set<Integer> memo = new HashSet<>();
//		memo.addAll(A);
//
//		for (int i = 0; i < A.size(); i++) {
//			for (int j = 0; j < A.size(); j++) {
//				int remaining = t - A.get(i) - A.get(j);
//				if (memo.contains(remaining)) {
//					return true;
//				}
//			}
//		}
//
//		return false;
//	}

	public static void main(String[] args) {
		System.exit(
				GenericTest
						.runFromAnnotations(args, "ThreeSum.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
