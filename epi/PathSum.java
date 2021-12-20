package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class PathSum {
	@EpiTest(testDataFile = "path_sum.tsv")

	public static boolean hasPathSum(BinaryTreeNode<Integer> tree,
									 int remainingWeight) {
		// TODO - you fill in here.
		if (tree == null) return false;

		boolean left = false;
		boolean right = false;

		if (tree.left == null && tree.right == null) {
			if ( (remainingWeight - tree.data) == 0) {
				return true;
			}
		} else {
			if (tree.left != null) {
				left = hasPathSum(tree.left, remainingWeight - tree.data);
			}
			if (tree.right != null) {
				right = hasPathSum(tree.right, remainingWeight - tree.data);
			}
		}

		return left || right;

	}

	public static void main(String[] args) {
		BinaryTreeNode<Integer> one = new BinaryTreeNode<>(314);
		BinaryTreeNode<Integer> two = new BinaryTreeNode<>(6);
		BinaryTreeNode<Integer> three = new BinaryTreeNode<>(6);
		BinaryTreeNode<Integer> four = new BinaryTreeNode<>(271);
		BinaryTreeNode<Integer> five = new BinaryTreeNode<>(561);
		BinaryTreeNode<Integer> six = new BinaryTreeNode<>(2);
		BinaryTreeNode<Integer> seven = new BinaryTreeNode<>(271);
		BinaryTreeNode<Integer> eight = new BinaryTreeNode<>(28);
		BinaryTreeNode<Integer> nine = new BinaryTreeNode<>(0);
		BinaryTreeNode<Integer> ten = new BinaryTreeNode<>(3);
		BinaryTreeNode<Integer> eleven = new BinaryTreeNode<>(1);

		BinaryTreeNode<Integer> twelve = new BinaryTreeNode<>(28);
		BinaryTreeNode<Integer> thirteen = new BinaryTreeNode<>(17);
		BinaryTreeNode<Integer> fourteen = new BinaryTreeNode<>(401);
		BinaryTreeNode<Integer> fifteen = new BinaryTreeNode<>(257);
		BinaryTreeNode<Integer> sixteen = new BinaryTreeNode<>(641);

		one.left = two;
		one.right = three;
		two.left = four;
		two.right = five;
		three.left = six;
		three.right = seven;
		four.left = eight;
		four.right = nine;
		five.right = ten;
		six.right = eleven;
		seven.right = twelve;
		ten.left = thirteen;
		eleven.left = fourteen;
		eleven.right = fifteen;
		fourteen.right = sixteen;

		hasPathSum(one, 591);

		System.exit(
				GenericTest
						.runFromAnnotations(args, "PathSum.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
