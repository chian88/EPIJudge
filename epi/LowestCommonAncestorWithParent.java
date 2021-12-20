package epi;

import epi.test_framework.BinaryTreeUtils;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

public class LowestCommonAncestorWithParent {

	public static BinaryTree<Integer> lca(BinaryTree<Integer> node0,
										  BinaryTree<Integer> node1) {
		// TODO - you fill in here.

		int depth0 = findDepth(node0, 0);
		int depth1 = findDepth(node1, 0);

		if (depth0 > depth1) {
			int diff = depth0 - depth1;

			while (diff > 0) {
				node0 = node0.parent;
				diff--;
			}
		} else {
			int diff = depth1 - depth0;
			while (diff > 0) {
				node1 = node1.parent;
				diff--;
			}
		}

		while(node0 != null && node1 != null) {
			if (node0 == node1) return node0;
			node0 = node0.parent;
			node1 = node1.parent;
		}

		return null;
	}

	static int findDepth(BinaryTree<Integer> node, int depth) {
		if (node == null) return depth;
		return findDepth(node.parent, depth + 1);
	}

	@EpiTest(testDataFile = "lowest_common_ancestor.tsv")
	public static int lcaWrapper(TimedExecutor executor, BinaryTree<Integer> tree,
								 Integer key0, Integer key1) throws Exception {
		BinaryTree<Integer> node0 = BinaryTreeUtils.mustFindNode(tree, key0);
		BinaryTree<Integer> node1 = BinaryTreeUtils.mustFindNode(tree, key1);

		BinaryTree<Integer> result = executor.run(() -> lca(node0, node1));

		if (result == null) {
			throw new TestFailure("Result can not be null");
		}
		return result.data;
	}

	public static void main(String[] args) {
		System.exit(
				GenericTest
						.runFromAnnotations(args, "LowestCommonAncestorWithParent.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
