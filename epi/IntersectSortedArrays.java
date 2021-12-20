package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashSet;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;

public class IntersectSortedArrays {
	@EpiTest(testDataFile = "intersect_sorted_arrays.tsv")

	public static List<Integer> intersectTwoSortedArrays(List<Integer> A,
														 List<Integer> B) {
		// TODO - you fill in here.
		int Aptr = 0;
		int Bptr = 0;

		List<Integer> result = new ArrayList<>();
		while (Aptr < A.size() && Bptr < B.size() ) {
			if (A.get(Aptr) == B.get(Bptr)) {
				if (Aptr - 1 < 0 || A.get(Aptr) != A.get(Aptr - 1)) {
					result.add(A.get(Aptr));
				}

				Aptr++;
				Bptr++;
			} else if (A.get(Aptr) < B.get(Bptr)) {
				Aptr++;
			} else {
				Bptr++;
			}
		}

		return result;
	}

	public static void main(String[] args) {

		intersectTwoSortedArrays(Arrays.asList(-33, -33, -32, -31, -28, -28, -25, -19, -14, -10, -9, -6, -1, 1, 2, 3,
				8, 9, 10, 10, 12, 16, 18, 19, 19, 20, 20, 20, 27, 28, 31, 31, 33),
				Arrays.asList(-73, -73, -71, -70, -70, -69, -65, -64, -64, -62, -62, -61, -60, -59, -58, -58, -57, -56,
						-54, -49, -48, -47, -46, -45, -45, -44, -44, -41, -37, -33, -30, -30, -28, -27, -26, -25, -23,
						-23, -14, -1, -1, 0, 2, 3, 4, 5, 7, 12, 13, 13, 20,
						22, 22, 27, 28, 32, 33, 33, 34, 39, 41, 42, 43, 43, 43, 43, 49, 56, 63, 65, 68, 72, 73));
		System.exit(
				GenericTest
						.runFromAnnotations(args, "IntersectSortedArrays.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
