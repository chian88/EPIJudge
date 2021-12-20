package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Arrays;
import java.util.List;


public class BstFromPreorder {
	@EpiTest(testDataFile = "bst_from_preorder.tsv")

	public static BstNode<Integer>
	rebuildBSTFromPreorder(List<Integer> preorderSequence) {
		// TODO - you fill in here.
		BstNode<Integer> root = new BstNode<>(preorderSequence.get(0));
		rebuildBSTFromPreorderHelper(preorderSequence, Integer.MAX_VALUE, Integer.MIN_VALUE, root, 1 );
		return root;


	}

	public static int
	rebuildBSTFromPreorderHelper(List<Integer> preorderSequence, int max , int min, BstNode<Integer> root, int turn) {
		// TODO - you fill in here.
		if (turn >= preorderSequence.size()) return turn;
		int next = preorderSequence.get(turn);

		if (next < root.data && next > min) {
			// left child
			root.left = new BstNode<>(next);
			turn = rebuildBSTFromPreorderHelper(preorderSequence, root.data, min, root.left, turn + 1);
		}

		if (turn >= preorderSequence.size()) return turn;
		next = preorderSequence.get(turn);

		if (next > root.data && next < max) {
			root.right = new BstNode<>(next);
			turn = rebuildBSTFromPreorderHelper(preorderSequence, max, root.data, root.right, turn + 1);
		}


		return turn;
	}

	public static void main(String[] args) {
		rebuildBSTFromPreorder(Arrays.asList(7,3,2,5,11,17,13));
		System.exit(
				GenericTest
						.runFromAnnotations(args, "BstFromPreorder.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
