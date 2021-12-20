package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
import java.util.*;

public class KthNodeInTree {
	public static class BinaryTreeNode<T> extends TreeLike<T, BinaryTreeNode<T>> {
		public T data;
		public BinaryTreeNode<T> left, right;
		public int size;

		public BinaryTreeNode(T data, BinaryTreeNode<T> left,
							  BinaryTreeNode<T> right, int size) {
			this.data = data;
			this.left = left;
			this.right = right;
			this.size = size;
		}

		@Override
		public T getData() {
			return data;
		}

		@Override
		public BinaryTreeNode<T> getLeft() {
			return left;
		}

		@Override
		public BinaryTreeNode<T> getRight() {
			return right;
		}
	}

	public static BinaryTreeNode<Integer>
	findKthNodeBinaryTree(BinaryTreeNode<Integer> tree, int k) {
		// TODO - you fill in here.
		return findKthNodeBinaryTreeHelper(tree, k);
	}

	static BinaryTreeNode<Integer> findKthNodeBinaryTreeHelper(BinaryTreeNode<Integer> node, int k) {
		int L = 0;
		if (node.left == null) {
			L += 1;
		} else {
			L += node.left.size + 1;
		}

		if (k == L) {
			return node;
		} else if (k > L) {
			return findKthNodeBinaryTreeHelper(node.right, k - L);
		} else {
			return findKthNodeBinaryTreeHelper(node.left, k);
		}
	}

	public static BinaryTreeNode<Integer>
	convertToTreeWithSize(BinaryTree<Integer> original) {
		if (original == null)
			return null;
		BinaryTreeNode<Integer> left = convertToTreeWithSize(original.left);
		BinaryTreeNode<Integer> right = convertToTreeWithSize(original.right);
		int lSize = left == null ? 0 : left.size;
		int rSize = right == null ? 0 : right.size;
		return new BinaryTreeNode<>(original.data, left, right, 1 + lSize + rSize);
	}

	@EpiTest(testDataFile = "kth_node_in_tree.tsv")
	public static int findKthNodeBinaryTreeWrapper(TimedExecutor executor,
												   BinaryTree<Integer> tree,
												   int k) throws Exception {
		BinaryTreeNode<Integer> converted = convertToTreeWithSize(tree);

		BinaryTreeNode<Integer> result =
				executor.run(() -> findKthNodeBinaryTree(converted, k));

		if (result == null) {
			throw new TestFailure("Result can't be null");
		}
		return result.data;
	}

	public static void main(String[] args) {

		System.exit(
				GenericTest
						.runFromAnnotations(args, "KthNodeInTree.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
