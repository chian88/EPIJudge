package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashSet;
import java.util.*;
public class CollatzChecker {
	@EpiTest(testDataFile = "collatz_checker.tsv")

	public static boolean testCollatzConjecture(int n) {
		// TODO - you fill in here.
		Set<Long> verifiedNum = new HashSet<>();

		for (long test = 3; test <= n ; test += 2) {
			Set<Long> prevSequence = new HashSet<>();
			long longTest = test;
			while (longTest >= test) {
				if (prevSequence.contains(longTest)) {
					return false;
				} else {
					prevSequence.add(longTest);

					if (longTest % 2 != 0) {
						if (!verifiedNum.add(longTest)) {
							break;
						}

						long nextLongTest = longTest * 3 + 1;

						if (nextLongTest <= longTest) {
							throw new ArithmeticException("overflow");
						}

						longTest = nextLongTest;
					} else {
						longTest /= 2;
					}

				}
			}

		}

		return true;
	}

	public static void main(String[] args) {
		System.exit(
				GenericTest
						.runFromAnnotations(args, "CollatzChecker.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
