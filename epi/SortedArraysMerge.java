package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
import java.util.*;

class Structure {
	int index;
	int value;
	int listIdx;

	Structure (int index, int value, int listIdx) {
		this.index = index;
		this.value = value;
		this.listIdx = listIdx;
	}
}

public class SortedArraysMerge {
	@EpiTest(testDataFile = "sorted_arrays_merge.tsv")

	public static List<Integer> mergeSortedArrays(List<List<Integer>> sortedArrays) {
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
		List<List<Integer>> input = new ArrayList<>();

		input.add(new ArrayList<>(Arrays.asList(new Integer[]{3,5,7})));
		input.add(new ArrayList<>(Arrays.asList(new Integer[]{0,6})));
		input.add(new ArrayList<>(Arrays.asList(new Integer[]{0,6,28})));
		mergeSortedArrays(input);
		System.exit(
				GenericTest
						.runFromAnnotations(args, "SortedArraysMerge.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
