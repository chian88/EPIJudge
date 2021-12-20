package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;

public class IntervalAdd {
	@EpiUserType(ctorParams = {int.class, int.class})

	public static class Interval {
		public int left, right;

		public Interval(int l, int r) {
			this.left = l;
			this.right = r;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (o == null || getClass() != o.getClass()) {
				return false;
			}

			Interval interval = (Interval) o;

			if (left != interval.left) {
				return false;
			}
			return right == interval.right;
		}

		@Override
		public String toString() {
			return "[" + left + ", " + right + "]";
		}
	}

	@EpiTest(testDataFile = "interval_add.tsv")

	public static List<Interval> addInterval(List<Interval> disjointIntervals,
											 Interval newInterval) {
		// TODO - you fill in here.
		boolean inserted = false;
		for (int i = 0; i < disjointIntervals.size(); i++) {
			Interval current = disjointIntervals.get(i);

			if (current.left > newInterval.left) {
				disjointIntervals.add(i, newInterval);
				inserted = true;
				break;
			}
		}

		if (!inserted) {
			disjointIntervals.add(newInterval);
		}

		int rightFarthest = disjointIntervals.get(0).right;
		for (int i = 0; i < disjointIntervals.size() -1 ; i++) {
			Interval current = disjointIntervals.get(i);
			Interval next = disjointIntervals.get(i + 1);

			if (rightFarthest >= next.left) {
				// merge
				rightFarthest = Math.max(rightFarthest, next.right);
				disjointIntervals.remove(i + 1);
				i --;
			} else {
				current.right = rightFarthest;
				rightFarthest = next.right;

			}
		}

		disjointIntervals.get(disjointIntervals.size() - 1).right = rightFarthest;

		return disjointIntervals;
	}

	public static void main(String[] args) {
		List<Interval> disjointInterval = new ArrayList<>();
		disjointInterval.add(new Interval(5, 5));
		disjointInterval.add(new Interval(7, 10));
		disjointInterval.add(new Interval(15, 16));
		disjointInterval.add(new Interval(23, 27));
		disjointInterval.add(new Interval(35, 38));
		disjointInterval.add(new Interval(44, 48));
		disjointInterval.add(new Interval(49, 53));

		Interval newInterval = new Interval(47, 63);
		addInterval(disjointInterval, newInterval);
		System.exit(
				GenericTest
						.runFromAnnotations(args, "IntervalAdd.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
