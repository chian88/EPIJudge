package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class TreeInorder {

	private static class NodeAndState {
		public BinaryTreeNode<Integer> node;
		public Boolean leftSubtreeTraversed;

		public NodeAndState(BinaryTreeNode<Integer> node,
							Boolean leftSubtreeTraversed) {
			this.node = node;
			this.leftSubtreeTraversed = leftSubtreeTraversed;
		}
	}

	@EpiTest(testDataFile = "tree_inorder.tsv")
	public static List<Integer> inorderTraversal(BinaryTreeNode<Integer> tree) {
		List<Integer> result = new ArrayList<>();

		Deque<NodeAndState> inProcess = new ArrayDeque<>();
		inProcess.addFirst(new NodeAndState(tree, false));
		while (!inProcess.isEmpty()) {
			NodeAndState nodeAndState = inProcess.removeFirst();
			if (nodeAndState.node != null) {
				if (nodeAndState.leftSubtreeTraversed) {
					result.add(nodeAndState.node.data);
				} else {
					inProcess.addFirst(new NodeAndState(nodeAndState.node.right, false));
					inProcess.addFirst(new NodeAndState(nodeAndState.node, true));
					inProcess.addFirst(new NodeAndState(nodeAndState.node.left, false));
				}
			}
		}


		return result;
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

		inorderTraversal(two);
		System.exit(
				GenericTest
						.runFromAnnotations(args, "TreeInorder.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
