package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TreeFromPreorderWithNull {
	public static BinaryTreeNode<Integer>
	reconstructPreorder(List<Integer> preorder) {
		// TODO - you fill in here.

		BinaryTreeNode<Integer> result = new BinaryTreeNode<>(preorder.get(0));
		if (preorder.get(0) == null) return null;
		reconstructPreorderHelper(preorder, result, 1);

		return result;
	}

	static int reconstructPreorderHelper(List<Integer> preorder, BinaryTreeNode<Integer> tree, int current) {
		// TODO - you fill in here.
		if (tree.data == null) return current;

		tree.left = new BinaryTreeNode<>(preorder.get(current));

		int maxCurrent = reconstructPreorderHelper(preorder, tree.left, current + 1);

		tree.right = new BinaryTreeNode<>(preorder.get(maxCurrent));

		maxCurrent = reconstructPreorderHelper(preorder, tree.right, maxCurrent + 1);

		return maxCurrent;
	}

	@EpiTest(testDataFile = "tree_from_preorder_with_null.tsv")
	public static BinaryTreeNode<Integer>
	reconstructPreorderWrapper(TimedExecutor executor, List<String> strings)
			throws Exception {
		List<Integer> ints = new ArrayList<>();
		for (String s : strings) {
			if (s.equals("null")) {
				ints.add(null);
			} else {
				ints.add(Integer.parseInt(s));
			}
		}

		return executor.run(() -> reconstructPreorder(ints));
	}

	public static void main(String[] args) {
//		reconstructPreorder(new ArrayList<Character>(Arrays.asList('H', 'B', 'F', null, null, 'E', 'A', null, null, null,
//		'C', null, 'D', null, 'G', 'I', null, null,null)));
		System.exit(
				GenericTest
						.runFromAnnotations(args, "TreeFromPreorderWithNull.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
