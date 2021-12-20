package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BuyAndSellStock {
	@EpiTest(testDataFile = "buy_and_sell_stock.tsv")
	public static double computeMaxProfit(List<Double> prices) {
		// TODO - you fill in here.
		double minSoFar = Double.MAX_VALUE;
		double maxProfit = 0;
		for (Double price : prices) {
			minSoFar = Math.min(price, minSoFar);

			maxProfit =  Math.max(maxProfit, price - minSoFar);
		}


		return maxProfit;
	}

	public static void main(String[] args) {
//		Double[] input = new Double[]{310.0,315.0,275d,295d,260d,270d,290d,230d,255d,250d};
//		computeMaxProfit(Arrays.asList(input));
		System.exit(
				GenericTest
						.runFromAnnotations(args, "BuyAndSellStock.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
