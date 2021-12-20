package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NextPermutation {
	@EpiTest(testDataFile = "next_permutation.tsv")
	public static List<Integer> nextPermutation(List<Integer> perm) {
		// TODO - you fill in here.
		int targetIdx = -1;
		for (int i = perm.size() - 1; i > 0; i--) {
			if (perm.get(i - 1) < perm.get(i)) {
				targetIdx = i - 1;
				break;
			}
		}
		if (targetIdx == -1) return new ArrayList<>();

		int swapIdx = -1;
		int min = Integer.MAX_VALUE;
		for (int i = targetIdx + 1; i < perm.size() ; i++) {
			if ( perm.get(i) > perm.get(targetIdx) && perm.get(i) <= min) {
				min = perm.get(i);
				swapIdx = i;
			}
		}

		if (swapIdx == -1) return new ArrayList<>();

		Collections.swap(perm, targetIdx, swapIdx);

		List<Integer> subList = perm.subList(targetIdx + 1, perm.size());
		Collections.reverse(subList);

		List<Integer> res = Stream.concat(perm.subList(0, targetIdx + 1).stream(), subList.stream())
				.collect(Collectors.toList());

		return res;
	}

	public static void main(String[] args) {
		//nextPermutation(Arrays.asList(1,5,8,4,7,6,5,3,1));
		System.exit(
				GenericTest
						.runFromAnnotations(args, "NextPermutation.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
