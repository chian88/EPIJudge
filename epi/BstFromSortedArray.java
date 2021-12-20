package epi;

import epi.test_framework.BinaryTreeUtils;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TestUtils;
import epi.test_framework.TimedExecutor;

import java.util.Arrays;
import java.util.List;

public class BstFromSortedArray {

	public static BstNode<Integer>
	buildMinHeightBSTFromSortedArray(List<Integer> A) {
		// TODO - you fill in here.

		return buildMinHeightBSTFromSortedArrayHelper(A, 0, A.size());
	}

	public static BstNode<Integer>
	buildMinHeightBSTFromSortedArrayHelper(List<Integer> A, int lo, int hi) {
		if (lo >= hi) return null;
		int mid = lo + ((hi - lo) / 2);
		// TODO - you fill in here.
		return new BstNode<>(A.get(mid),
				buildMinHeightBSTFromSortedArrayHelper(A, lo, mid),
				buildMinHeightBSTFromSortedArrayHelper(A, mid + 1, hi));
	}

	@EpiTest(testDataFile = "bst_from_sorted_array.tsv")
	public static int
	buildMinHeightBSTFromSortedArrayWrapper(TimedExecutor executor,
											List<Integer> A) throws Exception {
		BstNode<Integer> result =
				executor.run(() -> buildMinHeightBSTFromSortedArray(A));

		List<Integer> inorder = BinaryTreeUtils.generateInorder(result);

		TestUtils.assertAllValuesPresent(A, inorder);
		BinaryTreeUtils.assertTreeIsBst(result);
		return BinaryTreeUtils.binaryTreeHeight(result);
	}

	public static void main(String[] args) {
		//buildMinHeightBSTFromSortedArray(Arrays.asList(1,2,3,4,5,6,7));
		//buildMinHeightBSTFromSortedArray(Arrays.asList(1,2,3,4,5,6,7,8));
		System.exit(
				GenericTest
						.runFromAnnotations(args, "BstFromSortedArray.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
