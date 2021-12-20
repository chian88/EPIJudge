package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;

import java.util.List;

public class Knapsack {
	@EpiUserType(ctorParams = {Integer.class, Integer.class})

	public static class Item {
		public Integer weight;
		public Integer value;

		public Item(Integer weight, Integer value) {
			this.weight = weight;
			this.value = value;
		}
	}

	@EpiTest(testDataFile = "knapsack.tsv")

	public static int optimumSubjectToCapacity(List<Item> items, int capacity) {
		// TODO - you fill in here.
		int[][] dp = new int[items.size()][capacity + 1];
		for (int i = 0 ; i < items.size(); i++) {
			for (int weight = 0; weight <= capacity; weight++) {
				Item item = items.get(i);
				if (item.weight <= weight) {
					if (i - 1 >= 0) {
						if (weight - item.weight >= 0) {
							dp[i][weight] = Math.max(dp[i - 1][weight], dp[i - 1][weight - item.weight] + item.value);
						} else {
							dp[i][weight] = Math.max(dp[i - 1][weight], 0 + item.value);
						}

					} else {
						dp[i][weight] = item.value;
					}

				} else {
					if (i - 1 >= 0) {
						dp[i][weight] = dp[i - 1][weight];
					} else {
						dp[i][weight] = 0;
					}

				}
			}
		}


		return dp[items.size() - 1][capacity];
	}

	public static void main(String[] args) {
		System.exit(
				GenericTest
						.runFromAnnotations(args, "Knapsack.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
