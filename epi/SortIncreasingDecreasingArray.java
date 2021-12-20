package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;

public class SortIncreasingDecreasingArray {
	@EpiTest(testDataFile = "sort_increasing_decreasing_array.tsv")

	public static List<Integer> sortKIncreasingDecreasingArray(List<Integer> A) {
		// TODO - you fill in here.
		int k = 1;
		boolean firstTime = true;
		boolean ascending = true;
		List<List<Integer>> sortedArray = new ArrayList<>();

		int startIdx = 0;
		for (int i = 0; i < A.size() - 1; i++) {
			if (A.get(i) < A.get(i+1)) {
				if (!firstTime && !ascending) {
					List<Integer> subList = A.subList(startIdx, i + 1);
					Collections.reverse(subList);
					sortedArray.add(subList);
					startIdx = i+1;
					k++;
				}
				ascending = true;
				firstTime = false;
			} else if (A.get(i) > A.get(i+1)) {
				if (!firstTime && ascending) {
					k++;
					List<Integer> subList = A.subList(startIdx, i + 1);
					sortedArray.add(subList);
					startIdx = i+1;
				}
				ascending = false;
				firstTime = false;
			}
		}
		List<Integer> subList = A.subList(startIdx, A.size());
		if (!ascending) {
			Collections.reverse(subList);
		}

		sortedArray.add(subList);
		return mergeSortedArrays(sortedArray);
	}

	static List<Integer> mergeSortedArrays(List<List<Integer>> sortedArrays) {
		// TODO - you fill in here.
		List<Integer> res = new ArrayList<>();

		PriorityQueue<Structure> heap = new PriorityQueue<>(new Comparator<Structure>() {
			@Override
			public int compare(Structure o1, Structure o2) {
				return Integer.compare(o1.value, o2.value);
			}
		});
		int count = 0;
		for (List<Integer> list : sortedArrays) {
			if (list.size() > 0) {
				heap.add(new Structure(0, list.get(0), count++));
			}
		}

		while (!heap.isEmpty()) {
			Structure smallest = heap.poll();
			res.add(smallest.value);
			int i = smallest.index + 1;
			List<Integer> targetList = sortedArrays.get(smallest.listIdx);

			if (i < targetList.size()) {

				heap.add(new Structure(i, targetList.get(i), smallest.listIdx));
			}

		}

		return res;
	}

	public static void main(String[] args) {
		List<Integer> A = new ArrayList<>(Arrays.asList(new Integer[]{57,131,493, 294, 221,339, 418, 452, 442, 190}));
		sortKIncreasingDecreasingArray(A);
		System.exit(
				GenericTest
						.runFromAnnotations(args, "SortIncreasingDecreasingArray.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
