package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TreeFromPreorderInorder {
	@EpiTest(testDataFile = "tree_from_preorder_inorder.tsv")

	public static BinaryTreeNode<Integer>
	binaryTreeFromPreorderInorder(List<Integer> preorder, List<Integer> inorder) {
		// TODO - you fill in here.

		Map<Integer, Integer> nodeToInOrderIdx = IntStream.range(0, inorder.size())
															.boxed()
															.collect(Collectors.toMap( i -> inorder.get(i) , i -> i));

		return binaryTreeFromPreorderInorderHelper(preorder,
				0, preorder.size() ,
				0, inorder.size() ,
				nodeToInOrderIdx);
	}

	public static BinaryTreeNode<Integer>
	binaryTreeFromPreorderInorderHelper(List<Integer> preorder,
										int preOrderStart, int preOrderEnd,
										int inOrderStart, int inOrderEnd,
										Map<Integer, Integer> nodeToInOrderIdx) {

		if (preOrderEnd <= preOrderStart || inOrderEnd <= inOrderStart) return null;

		int rootInorderIdx = nodeToInOrderIdx.get(preorder.get(preOrderStart));
		int leftSubtreeSize = rootInorderIdx - inOrderStart;

		return new BinaryTreeNode<>(
				preorder.get(preOrderStart),
				binaryTreeFromPreorderInorderHelper(preorder,
						preOrderStart + 1, preOrderStart + 1 + leftSubtreeSize,
						inOrderStart, rootInorderIdx, nodeToInOrderIdx)
				, binaryTreeFromPreorderInorderHelper(preorder,
						preOrderStart + 1 + leftSubtreeSize, preOrderEnd,
						rootInorderIdx + 1, inOrderEnd, nodeToInOrderIdx));


	}

	public static void main(String[] args) {
		System.exit(
				GenericTest
						.runFromAnnotations(args, "TreeFromPreorderInorder.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
