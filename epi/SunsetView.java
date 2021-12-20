package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;

public class SunsetView {

	private static class BuildingWithHeight{
		public Integer id;
		public Integer height;

		public BuildingWithHeight(Integer id, Integer height) {
			this.id = id;
			this.height = height;
		}
	}


	public static List<Integer>
	examineBuildingsWithSunset(Iterator<Integer> sequence) {
		int buildingIdx = 0;

		Deque<BuildingWithHeight> candidates = new ArrayDeque<>();

		while (sequence.hasNext()) {
			int currHeight = sequence.next();

			while (!candidates.isEmpty() &&
					currHeight >= candidates.peekFirst().height) {
				candidates.removeFirst();
			}

			candidates.addFirst(new BuildingWithHeight(buildingIdx++, currHeight));
		}

		return candidates.stream().map( c -> c.id).collect(Collectors.toList());
	}


//	public static List<Integer>
//	examineBuildingsWithSunset(Iterator<Integer> sequence) {
//		// TODO - you fill in here.
//		List<Integer> reverseHouses = new ArrayList<>();
//
//		while (sequence.hasNext()) {
//			reverseHouses.add(0, sequence.next());
//		}
//		int runningMax = Integer.MIN_VALUE;
//
//		List<Integer> res = new ArrayList<>();
//
//		for (int i=0 ; i < reverseHouses.size(); i++) {
//			if (reverseHouses.get(i) > runningMax) {
//				res.add(reverseHouses.size() - 1 - i);
//				runningMax = reverseHouses.get(i);
//			}
//		}
//
//
//		return res;
//	}

	@EpiTest(testDataFile = "sunset_view.tsv")
	public static List<Integer>
	examineBuildingsWithSunsetWrapper(List<Integer> sequence) {
		return examineBuildingsWithSunset(sequence.iterator());
	}

	public static void main(String[] args) {
		List<Integer> houses = new ArrayList<>();
		houses.add(12);
		houses.add(7);
		houses.add(3);
		houses.add(10);
		houses.add(2);
		System.out.println(examineBuildingsWithSunset(houses.iterator()));
		System.exit(
				GenericTest
						.runFromAnnotations(args, "SunsetView.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
