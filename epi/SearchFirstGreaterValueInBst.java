package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class SearchFirstGreaterValueInBst {

	public static BstNode<Integer> findFirstGreaterThanK(BstNode<Integer> tree,
														 Integer k) {
		// TODO - you fill in here.
		return findFirstGreaterThanKHelper(tree, k);
	}

	static BstNode<Integer> findFirstGreaterThanKHelper(BstNode<Integer> tree,
														Integer k) {

		if (tree == null) return null;

		BstNode<Integer> leftRes = findFirstGreaterThanKHelper(tree.left, k);
		if (leftRes != null) return leftRes;

		if (tree.data > k) return tree;

		BstNode<Integer> rightRes = findFirstGreaterThanKHelper(tree.right, k);
		if (rightRes != null) return rightRes;

		return null;
	}

	@EpiTest(testDataFile = "search_first_greater_value_in_bst.tsv")
	public static int findFirstGreaterThanKWrapper(BstNode<Integer> tree,
												   Integer k) {
		BstNode<Integer> result = findFirstGreaterThanK(tree, k);
		return result != null ? result.data : -1;
	}

	public static void main(String[] args) {
		System.exit(
				GenericTest
						.runFromAnnotations(args, "SearchFirstGreaterValueInBst.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
