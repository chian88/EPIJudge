package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;

public class EnumerateBalancedParentheses {
	@EpiTest(testDataFile = "enumerate_balanced_parentheses.tsv")

	public static List<String> generateBalancedParentheses(int numPairs) {
		// TODO - you fill in here.
		List<String> result = new ArrayList<>();
		directedGeneratedBalancedParenthesis(numPairs, numPairs, "", result);

		return null;
	}

	static void directedGeneratedBalancedParenthesis(int numLeftParenNeeded, int numRightParenNeeded,
													 String validPrefix, List<String> result) {
		if (numRightParenNeeded == 0) {
			result.add(validPrefix);
			return;
		}

		if (numLeftParenNeeded > 0) {
			directedGeneratedBalancedParenthesis(numLeftParenNeeded - 1,
													numRightParenNeeded,
													validPrefix + "(", result);
		}

		if (numLeftParenNeeded < numRightParenNeeded) {
			directedGeneratedBalancedParenthesis(numLeftParenNeeded,
					numRightParenNeeded - 1,
					validPrefix + ")", result);
		}
	}

	@EpiTestComparator
	public static boolean comp(List<String> expected, List<String> result) {
		if (result == null) {
			return false;
		}
		Collections.sort(expected);
		Collections.sort(result);
		return expected.equals(result);
	}

	public static void main(String[] args) {
		generateBalancedParentheses(2);
		System.exit(
				GenericTest
						.runFromAnnotations(args, "EnumerateBalancedParentheses.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
