package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BiPredicate;

public class KLargestValuesInBst {
	@EpiTest(testDataFile = "k_largest_values_in_bst.tsv")

	public static List<Integer> findKLargestInBst(BstNode<Integer> tree, int k) {
		// TODO - you fill in here.
		return findKLargestInBstHelper(tree, k, new LinkedList<>());

	}

	public static List<Integer> findKLargestInBstHelper(BstNode<Integer> tree, int k, List<Integer> result) {
		// TODO - you fill in here.

		if (tree == null || result.size() >= k) return result;
		findKLargestInBstHelper(tree.right, k, result);

		if (result.size() < k) {
			result.add(0, tree.data);
		}

		findKLargestInBstHelper(tree.left, k, result);
		return result;
	}

	@EpiTestComparator
	public static boolean comp(List<Integer> expected, List<Integer> result) {
		if (result == null) {
			return false;
		}
		Collections.sort(expected);
		Collections.sort(result);
		return expected.equals(result);
	}

	public static void main(String[] args) {
		System.exit(
				GenericTest
						.runFromAnnotations(args, "KLargestValuesInBst.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
