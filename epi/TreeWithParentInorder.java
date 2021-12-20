package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;

public class TreeWithParentInorder {
	@EpiTest(testDataFile = "tree_with_parent_inorder.tsv")

	public static List<Integer> inorderTraversal(BinaryTree<Integer> tree) {
		// TODO - you fill in here.
		List<Integer> result = new ArrayList<>();
		BinaryTree<Integer> prev = tree;

		while (tree != null) {
			if (tree.left == prev) {
				result.add(tree.data);
				if (tree.right != null) {
					prev = tree;
					tree = tree.right;
				} else {
					prev = tree;
					tree = tree.parent;
				}
			} else if (tree.right == prev) {
				prev = tree;
				tree = tree.parent;
			} else if (tree.left != null) {
				prev = tree;
				tree = tree.left;
			} else if (tree.right != null) {

				result.add(tree.data);
				prev = tree;
				tree = tree.right;
			} else {
				result.add(tree.data);
				prev = tree;
				tree = tree.parent;
			}
		}
		return result;
	}

	public static void main(String[] args) {
		System.exit(
				GenericTest
						.runFromAnnotations(args, "TreeWithParentInorder.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
