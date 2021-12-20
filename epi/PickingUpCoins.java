package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Arrays;
import java.util.List;

public class PickingUpCoins {
	@EpiTest(testDataFile = "picking_up_coins.tsv")

	public static int pickUpCoins(List<Integer> coins) {
		// TODO - you fill in here.
		return computeMaxumumRevenueForRange(coins, 0, coins.size() - 1, new int[coins.size()][coins.size()]);
	}

	private static int computeMaxumumRevenueForRange(
			List<Integer> coins, int a, int b, int[][] maximumRevenueForRange) {

		if (a > b) {
			return 0;
		}

		if (maximumRevenueForRange[a][b] == 0) {
			int maximumRevenueA = coins.get(a) + Math.min(
					computeMaxumumRevenueForRange(coins, a + 2, b, maximumRevenueForRange) ,
					computeMaxumumRevenueForRange(coins, a + 1, b - 1, maximumRevenueForRange));
			int maximumRevenueB = coins.get(b) + Math.min(
					computeMaxumumRevenueForRange(coins, a + 1, b - 1, maximumRevenueForRange),
					computeMaxumumRevenueForRange(coins, a, b - 2, maximumRevenueForRange));

			maximumRevenueForRange[a][b] = Math.max(maximumRevenueA, maximumRevenueB);
		}

		return maximumRevenueForRange[a][b];
	}


	public static void main(String[] args) {
		pickUpCoins(Arrays.asList(5, 25,10,1));
		System.exit(
				GenericTest
						.runFromAnnotations(args, "PickingUpCoins.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
