package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Iterator;
import java.util.List;
import java.util.*;

public class OnlineMedian {
	public static List<Double> onlineMedian(Iterator<Integer> sequence) {
		// TODO - you fill in here.
		PriorityQueue<Integer> SecondHalf = new PriorityQueue<>();
		PriorityQueue<Integer> FirstHalf = new PriorityQueue<>(Collections.reverseOrder());
		List<Double> result = new ArrayList<>();
		while (sequence.hasNext()) {
			int curr = sequence.next();

			SecondHalf.add(curr);
			FirstHalf.add(SecondHalf.remove());

			if (FirstHalf.size() > SecondHalf.size()) {
				SecondHalf.add(FirstHalf.remove());
			}

			if (SecondHalf.size() == FirstHalf.size()) {
				result.add((FirstHalf.peek() + SecondHalf.peek()) / 2.0);
			} else {
				result.add((double) SecondHalf.peek());
			}
		}


		return result;
	}

	@EpiTest(testDataFile = "online_median.tsv")
	public static List<Double> onlineMedianWrapper(List<Integer> sequence) {
		return onlineMedian(sequence.iterator());
	}

	public static void main(String[] args) {
//		onlineMedianWrapper(Arrays.asList(1,0,3,5,2,0,1));
		System.exit(
				GenericTest
						.runFromAnnotations(args, "OnlineMedian.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
