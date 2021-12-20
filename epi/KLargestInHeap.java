package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.function.BiPredicate;

class HeapEntry {
	int idx;
	int value;

	HeapEntry(int idx, int value) {
		this.idx = idx;
		this.value = value;
	}
}


public class KLargestInHeap {
	@EpiTest(testDataFile = "k_largest_in_heap.tsv")



	public static List<Integer> kLargestInBinaryHeap(List<Integer> A, int k) {
		// TODO - you fill in here.
		PriorityQueue<HeapEntry> maxCandidate = new PriorityQueue<HeapEntry>((a, b) -> {
			return Integer.compare(b.value, a.value);
		});
		List<Integer> result = new ArrayList<>();
		maxCandidate.add(new HeapEntry(0, A.get(0)));
		for (int i = 0 ; i < k; i++) {
			HeapEntry largestSoFar = maxCandidate.poll();
			result.add(largestSoFar.value);

			int leftChild = largestSoFar.idx * 2 + 1;

			int rightChild = largestSoFar.idx * 2 + 2;

			if (leftChild < A.size()) {
				maxCandidate.add(new HeapEntry(leftChild, A.get(leftChild)));
			}

			if (rightChild < A.size()) {
				maxCandidate.add(new HeapEntry(rightChild, A.get(rightChild)));
			}

		}

		return result;
	}

	@EpiTestComparator
	public static boolean comp(List<Integer> expected, List<Integer> result) {
		if (result == null) {
			return false;
		}
		Collections.sort(expected);
		Collections.sort(result);
		return expected.equals(result);
	}

	public static void main(String[] args) {
		System.exit(
				GenericTest
						.runFromAnnotations(args, "KLargestInHeap.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
