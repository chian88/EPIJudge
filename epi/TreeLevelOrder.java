package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Deque;
import java.util.*;

import java.util.Queue;
import java.util.stream.Collectors;

public class TreeLevelOrder {
	@EpiTest(testDataFile = "tree_level_order.tsv")

	public static List<List<Integer>>
	binaryTreeDepthOrder(BinaryTreeNode<Integer> tree) {
		// TODO - you fill in here.

		if (tree == null) return new ArrayList<>();
		Queue<Queue<BinaryTreeNode>> frontier = new ArrayDeque<>();
		Queue<BinaryTreeNode> row = new ArrayDeque<>();
		row.offer(tree);
		frontier.offer(row);

		List<List<Integer>> res = new ArrayList<>();
		List<Integer> resRow = new ArrayList<>();
		resRow.add(tree.data);
		res.add(resRow);

		while (!frontier.isEmpty()) {
			Queue<BinaryTreeNode> currRow = frontier.poll();
			Queue<BinaryTreeNode> newRow = new ArrayDeque<>();

			resRow = new ArrayList<>();

			while (!currRow.isEmpty()) {
				BinaryTreeNode node = currRow.poll();
				if (node.left != null) {
					newRow.offer(node.left);
					resRow.add((Integer) node.left.data);
				}

				if (node.right != null) {
					newRow.offer(node.right);
					resRow.add((Integer) node.right.data);
				}


			}

			if (!newRow.isEmpty()) {
				frontier.offer(newRow);
			}

			if (!resRow.isEmpty()) {
				res.add(resRow);
			}



		}

		return res;
	}

	public static void main(String[] args) {

		BinaryTreeNode twoOneSeven = new BinaryTreeNode(217);
		BinaryTreeNode fivesixone = new BinaryTreeNode(561);
		BinaryTreeNode two = new BinaryTreeNode(2);
		BinaryTreeNode twosevenone = new BinaryTreeNode(271);
		BinaryTreeNode six = new BinaryTreeNode(6);
		BinaryTreeNode six2 = new BinaryTreeNode(6);
		BinaryTreeNode threeonefour = new BinaryTreeNode(314);

		six.left = twoOneSeven;
		six.right = fivesixone;
		six2.left = two;
		six2.right = twosevenone;
		threeonefour.left = six;
		threeonefour.right = six2;
		binaryTreeDepthOrder(threeonefour);
		System.exit(
				GenericTest
						.runFromAnnotations(args, "TreeLevelOrder.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
