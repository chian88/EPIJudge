package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;

import java.util.Arrays;
import java.util.List;

public class SearchForMinMaxInArray {
	@EpiUserType(ctorParams = {Integer.class, Integer.class})

	public static class MinMax {
		public Integer smallest;
		public Integer largest;

		public MinMax(Integer smallest, Integer largest) {
			this.smallest = smallest;
			this.largest = largest;
		}

		private static MinMax minMax(Integer a, Integer b) {
			return Integer.compare(b, a) < 0 ? new MinMax(b, a) : new MinMax(a, b);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (o == null || getClass() != o.getClass()) {
				return false;
			}

			MinMax minMax = (MinMax) o;

			if (!smallest.equals(minMax.smallest)) {
				return false;
			}
			return largest.equals(minMax.largest);
		}

		@Override
		public String toString() {
			return "min: " + smallest + ", max: " + largest;
		}
	}

	@EpiTest(testDataFile = "search_for_min_max_in_array.tsv")

	public static MinMax findMinMax(List<Integer> A) {
		// TODO - you fill in here.

		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;

		for (int i = 0; i < A.size(); i+=2) {
			if (i + 1 >= A.size()) {
				int a = A.get(i);
				max = Math.max(max, a);
				min = Math.min(min, a);
				break;
			}

			int a = A.get(i);
			int b = A.get(i+1);

			if (a > b) {
				max = Math.max(max, a);
				min = Math.min(min, b);
			} else {
				max = Math.max(max, b);
				min = Math.min(min, a);
			}
		}

		return new MinMax(min, max);
	}

	public static void main(String[] args) {

		findMinMax(Arrays.asList(3,2,5,1,2,4));
		System.exit(
				GenericTest
						.runFromAnnotations(args, "SearchForMinMaxInArray.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
