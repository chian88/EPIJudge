package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class ClosestIntSameWeight {
	@EpiTest(testDataFile = "closest_int_same_weight.tsv")
	public static long closestIntSameBitCount(long x) {
		// TODO - you fill in here.
		final int NUM_UNSIGNED_BITS = 63;

		for (int i = 0; i < NUM_UNSIGNED_BITS - 1; i++) {
			if (((( x >>> i ) & i ) != (( x >>> (i + 1)) & 1))) {
				x ^= (1L << i ) | (1L << (i + 1));
				return x;
			}
		}
		throw new IllegalArgumentException("all bits are 0 or 1");
	}

	public static void main(String[] args) {
		System.exit(
				GenericTest
						.runFromAnnotations(args, "ClosestIntSameWeight.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
