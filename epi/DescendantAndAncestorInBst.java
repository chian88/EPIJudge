package epi;

import epi.test_framework.BinaryTreeUtils;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

public class DescendantAndAncestorInBst {

	public static boolean
	pairIncludesAncestorAndDescendantOfM(BstNode<Integer> possibleAncOrDesc0,
										 BstNode<Integer> possibleAncOrDesc1,
										 BstNode<Integer> middle) {
		// TODO - you fill in here.
		if (middle == possibleAncOrDesc0 || middle == possibleAncOrDesc1) return false;

		return findDescendent(middle, possibleAncOrDesc0) && findDescendent(possibleAncOrDesc1, middle) ||
				findDescendent(middle, possibleAncOrDesc1) && findDescendent(possibleAncOrDesc0, middle);
	}

	public static boolean findDescendent(BstNode<Integer> root, BstNode<Integer> target) {
		if (root == null) return false;
		if (root == target) return true;

		if (target.data < root.data) {
			// go left
			return findDescendent(root.left, target);
		} else {
			// go right
			return findDescendent(root.right, target);
		}

	}

	@EpiTest(testDataFile = "descendant_and_ancestor_in_bst.tsv")
	public static boolean pairIncludesAncestorAndDescendantOfMWrapper(
			TimedExecutor executor, BstNode<Integer> tree, int possibleAncOrDesc0,
			int possibleAncOrDesc1, int middle) throws Exception {
		final BstNode<Integer> candidate0 =
				BinaryTreeUtils.mustFindNode(tree, possibleAncOrDesc0);
		final BstNode<Integer> candidate1 =
				BinaryTreeUtils.mustFindNode(tree, possibleAncOrDesc1);
		final BstNode<Integer> middleNode =
				BinaryTreeUtils.mustFindNode(tree, middle);

		return executor.run(()
				-> pairIncludesAncestorAndDescendantOfM(
				candidate0, candidate1, middleNode));
	}

	public static void main(String[] args) {
		System.exit(
				GenericTest
						.runFromAnnotations(args, "DescendantAndAncestorInBst.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
