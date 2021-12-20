package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class SumRootToLeaf {
	@EpiTest(testDataFile = "sum_root_to_leaf.tsv")

	public static int sumRootToLeaf(BinaryTreeNode<Integer> tree) {
		// TODO - you fill in here.
		int sum = sumRootToLeafHelper(tree, 0, 0);

		return sum;
	}

	static int sumRootToLeafHelper(BinaryTreeNode<Integer> tree, int runningSum, int sum) {



		runningSum = runningSum * 2 + tree.data;
		int runningSumLeft = 0;
		int runningSumRight = 0;
		if (tree.left == null && tree.right == null) {
			sum = runningSum;
		} else {

			if (tree.left != null) {
				runningSumLeft = sumRootToLeafHelper(tree.left, runningSum, sum);
			}

			if (tree.right != null) {
				runningSumRight = sumRootToLeafHelper(tree.right, runningSum, sum);
			}


		}

		return sum + runningSumRight + runningSumLeft;
	}

	public static void main(String[] args) {
		BinaryTreeNode<Integer> one = new BinaryTreeNode<>(1);
		BinaryTreeNode<Integer> two = new BinaryTreeNode<>(0);
		BinaryTreeNode<Integer> three = new BinaryTreeNode<>(1);
		BinaryTreeNode<Integer> four = new BinaryTreeNode<>(0);
		BinaryTreeNode<Integer> five = new BinaryTreeNode<>(1);
		BinaryTreeNode<Integer> six = new BinaryTreeNode<>(0);
		BinaryTreeNode<Integer> seven = new BinaryTreeNode<>(0);
		BinaryTreeNode<Integer> eight = new BinaryTreeNode<>(0);
		BinaryTreeNode<Integer> nine = new BinaryTreeNode<>(1);
		BinaryTreeNode<Integer> ten = new BinaryTreeNode<>(1);
		BinaryTreeNode<Integer> eleven = new BinaryTreeNode<>(0);

		BinaryTreeNode<Integer> twelve = new BinaryTreeNode<>(0);
		BinaryTreeNode<Integer> thirteen = new BinaryTreeNode<>(0);
		BinaryTreeNode<Integer> fourteen = new BinaryTreeNode<>(1);
		BinaryTreeNode<Integer> fifteen = new BinaryTreeNode<>(0);
		BinaryTreeNode<Integer> sixteen = new BinaryTreeNode<>(1);

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
		sumRootToLeaf(one);

		System.exit(
				GenericTest
						.runFromAnnotations(args, "SumRootToLeaf.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
