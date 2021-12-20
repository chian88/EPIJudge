package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.serialization_traits.IntegerTrait;

import java.util.*;

public class MinimumDistance3SortedArrays {

	public static class ArrayData implements Comparable<ArrayData> {
		public int val;
		public int idx;

		public ArrayData(int idx, int val) {
			this.val = val;
			this.idx = idx;
		}

		@Override
		public int compareTo(ArrayData o) {
			int result = Integer.compare(val, o.val);
			if (result == 0) {
				result = Integer.compare(idx, o.idx);
			}
			return result;
		}
	}

	@EpiTest(testDataFile = "minimum_distance_3_sorted_arrays.tsv")

	public static int
	findMinDistanceSortedArrays(List<List<Integer>> sortedArrays) {
		List<Integer> pointers = new ArrayList<>();

		for (int i = 0; i < sortedArrays.size(); i++) {
			pointers.add(0);
		}

		TreeSet<ArrayData> idxValuePair = new TreeSet<>();

		for (int i = 0; i < sortedArrays.size(); i++) {
			idxValuePair.add(new ArrayData(i, sortedArrays.get(i).get(pointers.get(i))));
		}
		int minDist = Integer.MAX_VALUE;
		while (true) {
			int dist = (idxValuePair.last().val - idxValuePair.first().val);

			if (dist < minDist) {
				minDist = dist;
			}

			int minimumIdx = idxValuePair.first().idx;
			pointers.set(minimumIdx, pointers.get(minimumIdx) + 1);

			if (pointers.get(minimumIdx) >= sortedArrays.get(minimumIdx).size()) {
				return minDist;
			}

			idxValuePair.pollFirst();
			idxValuePair.add(new ArrayData(minimumIdx, sortedArrays.get(minimumIdx).get(pointers.get(minimumIdx))));
		}

	}

	public static void main(String[] args) {
		List<List<Integer>> sortedArrays = new ArrayList<>();

		sortedArrays.add(Arrays.asList(5,10,15));
		sortedArrays.add(Arrays.asList(3,6,9,12,15));
		sortedArrays.add(Arrays.asList(8,16,24));

		findMinDistanceSortedArrays(sortedArrays);
		System.exit(
				GenericTest
						.runFromAnnotations(args, "MinimumDistance3SortedArrays.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
