package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IsTreeBalanced {

	@EpiTest(testDataFile = "is_tree_balanced.tsv")

	public static boolean isBalanced(BinaryTreeNode<Integer> tree) {
		// TODO - you fill in here.
		if (tree == null) return true;

	  	int heightLeft = findHeightFromNode(tree.left, 0);
	  	int heightRight = findHeightFromNode(tree.right, 0);

		if (Math.abs(heightLeft - heightRight) > 1) return false;

		if (!isBalanced(tree.left) || !isBalanced(tree.right)) return false;

		return true;
	}

	static int findHeightFromNode(BinaryTreeNode<Integer> node, int depth) {

		if (node == null) return depth;

		int heightLeft = findHeightFromNode(node.left, depth + 1);
		int heightRight = findHeightFromNode(node.right, depth + 1);

		return heightLeft > heightRight ? heightLeft : heightRight;

	}

	public static void main(String[] args) {
		BinaryTreeNode<Integer> one = new BinaryTreeNode<>(-4);
		BinaryTreeNode<Integer> two = new BinaryTreeNode<>(-7);
		BinaryTreeNode<Integer> three = new BinaryTreeNode<>(5);
		BinaryTreeNode<Integer> four = new BinaryTreeNode<>(-5);
		BinaryTreeNode<Integer> five = new BinaryTreeNode<>(-4);
		BinaryTreeNode<Integer> six = new BinaryTreeNode<>(-2);
		BinaryTreeNode<Integer> seven = new BinaryTreeNode<>(6);

		one.left = two;
		one.right = three;
		two.left = four;
		two.right = five;
		three.left = six;
		three.right = seven;

		System.out.println(isBalanced(one));
		System.exit(
				GenericTest
						.runFromAnnotations(args, "IsTreeBalanced.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
