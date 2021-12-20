package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class BuyAndSellStockTwice {
	@EpiTest(testDataFile = "buy_and_sell_stock_twice.tsv")
	public static double buyAndSellStockTwice(List<Double> prices) {
		// TODO - you fill in here.

		List<Double> maxProfitFirst = new ArrayList<>();
		Double maxProfit = 0.0;
		Double lowestPrice = Double.MAX_VALUE;

		for (int i = 0; i < prices.size(); i++) {
      		lowestPrice = Math.min(prices.get(i), lowestPrice);

      		maxProfit = Math.max(prices.get(i)-lowestPrice, maxProfit);

      		maxProfitFirst.add(maxProfit);
		}

		List<Double> maxProfitSecond = new ArrayList<>();
		maxProfit = 0.0;
		Double highestPrice = Double.MIN_VALUE;

		for (int i = prices.size() - 1; i >= 0; --i) {
			highestPrice = Math.max(prices.get(i), highestPrice);

			maxProfit = Math.max(highestPrice - prices.get(i), maxProfit);

			maxProfitSecond.add(0, maxProfit);
		}

		Double maxProfitCombined = 0.0;

		for (int i = 0; i < prices.size() - 1; i++) {
			maxProfitCombined = Math.max(maxProfitCombined, maxProfitFirst.get(i) + maxProfitSecond.get(i));
		}



		return maxProfitCombined;
	}

	public static void main(String[] args) {
		Double[] input = {12d, 11d, 13d, 9d, 12d, 8d, 14d, 13d, 15d};
		buyAndSellStockTwice(Arrays.asList(input));



		System.exit(
				GenericTest
						.runFromAnnotations(args, "BuyAndSellStockTwice.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
