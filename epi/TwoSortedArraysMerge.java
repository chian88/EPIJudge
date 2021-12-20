package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.*;

public class TwoSortedArraysMerge {

	public static void mergeTwoSortedArrays(List<Integer> A, int m,
											List<Integer> B, int n) {
		// TODO - you fill in here.
		int a = m - 1;
		int b = n - 1;



		while (a >= 0 && b >= 0) {
			if (A.get(a) > B.get(b)) {
				A.set(a + b + 1, A.get(a));
				a--;
			} else {
				A.set(a + b + 1, B.get(b));
				b--;
			}
		}

		while (a >= 0) {
			A.set(a + b + 1, A.get(a));
			a--;
		}

		while (b >= 0) {
			A.set(a + b + 1, B.get(b));
			b--;
		}

		return;
	}

	@EpiTest(testDataFile = "two_sorted_arrays_merge.tsv")
	public static List<Integer>
	mergeTwoSortedArraysWrapper(List<Integer> A, int m, List<Integer> B, int n) {
		mergeTwoSortedArrays(A, m, B, n);
		return A;
	}

	public static void main(String[] args) {
		List<Integer> A = new ArrayList<>(8);
		Collections.fill(A, null);
		A.addAll(Arrays.asList(3,13,17));
		A.add(null);
		A.add(null);
		A.add(null);
		A.add(null);
		A.add(null);
		mergeTwoSortedArrays(A, 3, Arrays.asList(3,7,11,19), 4);
		System.exit(
				GenericTest
						.runFromAnnotations(args, "TwoSortedArraysMerge.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
