package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
import java.util.*;


public class KthLargestInArray {
	// The numbering starts from one, i.e., if A = [3,1,-1,2] then
	// findKthLargest(1, A) returns 3, findKthLargest(2, A) returns 2,
	// findKthLargest(3, A) returns 1, and findKthLargest(4, A) returns -1.
	@EpiTest(testDataFile = "kth_largest_in_array.tsv")
	public static int findKthLargest(int k, List<Integer> A) {
		// TODO - you fill in here.
		Collections.sort(A);
		return findKthLargestHelper(k, 0, A.size(), A);

	}

	static int findKthLargestHelper(int k, int low, int high, List<Integer> A) {
		Random rand = new Random();
		int pivotIdx = rand.nextInt(high - low) + low;

		Collections.swap(A, low, pivotIdx);

		int left = low + 1;
		int right = high - 1;

		while (left <= right) {
			if (A.get(left) <= A.get(low)) {
				left++;
			} else if (A.get(right) >= A.get(low)) {
				right--;
			} else {
				Collections.swap(A, left, right);
				left++;
				right--;
			}
		}

		Collections.swap(A, low, right);

		if (A.size() - right == k) {
			return A.get(right);
		}
		else if (k > A.size() - right) {
			/// go left
			return findKthLargestHelper(k, low, right, A);
		} else {
			// go right
			return findKthLargestHelper(k, right+1, high, A);
		}
	}

	public static void main(String[] args) {
		//findKthLargest(5, Arrays.asList(3,2,1,5,4,6,6));

		System.exit(
				GenericTest
						.runFromAnnotations(args, "KthLargestInArray.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
