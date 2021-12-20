package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;
import epi.test_framework.LexicographicalListComparator;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;

public class NQueens {
	@EpiTest(testDataFile = "n_queens.tsv")

	public static List<List<Integer>> nQueens(int n) {
		// TODO - you fill in here.
		List<List<Integer>> result = new ArrayList<>();

		nQueensHelper(n ,1, result, new ArrayList<>());
		return result;
	}

	public static void nQueensHelper(int n, int row, List<List<Integer>> result, List<Integer> colPlacement) {
		// TODO - you fill in here.
		if (row > n) {
			result.add(new ArrayList<>(colPlacement));
			colPlacement.remove(colPlacement.size() - 1);
			return;
		}

		for (int col = 0; col < n; col++) {
			colPlacement.add(col);

			if (isValidPlacement(colPlacement)) {
				nQueensHelper(n, row + 1, result, colPlacement);
			} else {
				colPlacement.remove(colPlacement.size() - 1);
			}
		}
		if (!colPlacement.isEmpty()) colPlacement.remove(colPlacement.size() - 1);
		return;
	}

	private static boolean isValidPlacement(List<Integer> colPlacement) {

		for (int row1 = 0; row1 < colPlacement.size() ; row1++) {
			for (int row2 = row1+1; row2 < colPlacement.size(); row2++) {
				int col1 = colPlacement.get(row1);
				int col2 = colPlacement.get(row2);

				if (col1 == col2) return false;
				if (Math.abs(row2 - row1) == Math.abs(col2 - col1)) return false;
			}
		}
		return true;
	}

	@EpiTestComparator
	public static boolean comp(List<List<Integer>> expected,
							   List<List<Integer>> result) {
		if (result == null) {
			return false;
		}
		expected.sort(new LexicographicalListComparator<>());
		result.sort(new LexicographicalListComparator<>());
		return expected.equals(result);
	}

	public static void main(String[] args) {
		nQueens(4);
		System.exit(
				GenericTest
						.runFromAnnotations(args, "NQueens.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
