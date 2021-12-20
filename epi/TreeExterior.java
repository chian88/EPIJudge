package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TreeExterior {

	public static List<BinaryTreeNode<Integer>>
	exteriorBinaryTree(BinaryTreeNode<Integer> tree) {
		// TODO - you fill in here.
		List<BinaryTreeNode<Integer>> exterior = new ArrayList<>();

		exterior.add(tree);
		leftBound(tree.left, exterior);
		leaf(tree.left, exterior);
		leaf(tree.right, exterior);
		rightBound(tree.right, exterior);


		return exterior;
	}

	static void rightBound(BinaryTreeNode<Integer> tree,
						  List<BinaryTreeNode<Integer>> exterior) {
		if (tree == null || (tree.left == null && tree.right == null)) {
			return;
		}

		if (tree.right != null) {
			rightBound(tree.right, exterior);
		} else {
			rightBound(tree.left, exterior);
		}

		exterior.add(tree);

	}

	static void leaf (BinaryTreeNode<Integer> tree,
					  List<BinaryTreeNode<Integer>> exterior) {
		if (tree == null) return;

		if (tree.left == null && tree.right == null) {
			exterior.add(tree);
		}

		leaf(tree.left, exterior);
		leaf(tree.right, exterior);
	}

	static void leftBound(BinaryTreeNode<Integer> tree,
						  List<BinaryTreeNode<Integer>> exterior) {
		if (tree == null || (tree.left == null && tree.right == null)) {
			return;
		}

		exterior.add(tree);

		if (tree.left != null) {
			leftBound(tree.left, exterior);
		} else {
			leftBound(tree.right, exterior);
		}
	}

	private static List<Integer> createOutputList(List<BinaryTreeNode<Integer>> L)
			throws TestFailure {
		if (L.contains(null)) {
			throw new TestFailure("Resulting list contains null");
		}
		List<Integer> output = new ArrayList<>();
		for (BinaryTreeNode<Integer> l : L) {
			output.add(l.data);
		}
		return output;
	}

	@EpiTest(testDataFile = "tree_exterior.tsv")
	public static List<Integer>
	exteriorBinaryTreeWrapper(TimedExecutor executor,
							  BinaryTreeNode<Integer> tree) throws Exception {
		List<BinaryTreeNode<Integer>> result =
				executor.run(() -> exteriorBinaryTree(tree));

		return createOutputList(result);
	}

	public static void main(String[] args) {
		System.exit(
				GenericTest
						.runFromAnnotations(args, "TreeExterior.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
