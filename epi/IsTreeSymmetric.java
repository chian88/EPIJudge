package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IsTreeSymmetric {
	@EpiTest(testDataFile = "is_tree_symmetric.tsv")

	public static boolean isSymmetric(BinaryTreeNode<Integer> tree) {
		// TODO - you fill in here.
		if (tree == null) return true;
		if ( !isSymmetricHelper(tree.left, tree.right)) return false;

		return true;
	}


	static boolean isSymmetricHelper(BinaryTreeNode<Integer> leftTree, BinaryTreeNode<Integer> rightTree) {
		if (leftTree == null && rightTree == null) return true;
			if (leftTree == null) return false;
			if (rightTree == null) return false;
			if (leftTree.data != rightTree.data) return false;


			return isSymmetricHelper(leftTree.left, rightTree.right) && isSymmetricHelper(leftTree.right, rightTree.left);
	}

	public static void main(String[] args) {
		BinaryTreeNode<Integer> one = new BinaryTreeNode<>(314);
		BinaryTreeNode<Integer> two = new BinaryTreeNode<>(6);
		BinaryTreeNode<Integer> three = new BinaryTreeNode<>(6);
		BinaryTreeNode<Integer> four = new BinaryTreeNode<>(7);
		BinaryTreeNode<Integer> five = new BinaryTreeNode<>(2);
		BinaryTreeNode<Integer> six = new BinaryTreeNode<>(2);
		BinaryTreeNode<Integer> seven = new BinaryTreeNode<>(7);
		BinaryTreeNode<Integer> eight = new BinaryTreeNode<>(1);
		BinaryTreeNode<Integer> nine = new BinaryTreeNode<>(3);
		BinaryTreeNode<Integer> ten = new BinaryTreeNode<>(3);
		BinaryTreeNode<Integer> eleven = new BinaryTreeNode<>(1);

		one.left = two;
		one.right = three;
		two.left = four;
		two.right = five;
		three.left = six;
		three.right = seven;

		four.left = eight;
		five.right = nine;
		six.left = ten;
		seven.right = eleven;

		isSymmetric(one);
		System.exit(
				GenericTest
						.runFromAnnotations(args, "IsTreeSymmetric.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
