package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
import java.util.*;

public class MinimumWaitingTime {
	@EpiTest(testDataFile = "minimum_waiting_time.tsv")

	public static int minimumTotalWaitingTime(List<Integer> serviceTimes) {
		// TODO - you fill in here.
		Collections.sort(serviceTimes);

		int sum = 0;

		for (int i = 0; i < serviceTimes.size(); i++) {
			sum += (serviceTimes.get(i) * (serviceTimes.size() - 1 - i));
		}

		return sum;
	}

	public static void main(String[] args) {
		System.exit(
				GenericTest
						.runFromAnnotations(args, "MinimumWaitingTime.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
