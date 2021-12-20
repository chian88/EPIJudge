package epi;

import epi.test_framework.BinaryTreeUtils;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

public class SuccessorInTree {

	public static BinaryTree<Integer> findSuccessor(BinaryTree<Integer> node) {
		// TODO - you fill in here.
		if (node.right != null) {

			node = node.right;

			while (node.left != null) {
				node = node.left;
			}

			return node;
		} else {
			BinaryTree<Integer> prev = null;
			while (node != null) {
				prev = node;
				node = node.parent;

				if ( node == null || (node.left != null && node.left == prev)) {
					return node;
				}
			}
		}

		return null;
	}

	@EpiTest(testDataFile = "successor_in_tree.tsv")
	public static int findSuccessorWrapper(TimedExecutor executor,
										   BinaryTree<Integer> tree, int nodeIdx)
			throws Exception {
		BinaryTree<Integer> n = BinaryTreeUtils.mustFindNode(tree, nodeIdx);

		BinaryTree<Integer> result = executor.run(() -> findSuccessor(n));

		return result == null ? -1 : result.data;
	}

	public static void main(String[] args) {
		System.exit(
				GenericTest
						.runFromAnnotations(args, "SuccessorInTree.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
