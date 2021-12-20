package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IsStringInMatrix {
	@EpiTest(testDataFile = "is_string_in_matrix.tsv")
	public static boolean isPatternContainedInGrid(List<List<Integer>> grid,
												   List<Integer> pattern) {
		// TODO - you fill in here.
		for (int row = 0; row < grid.size(); row++) {
			for (int col = 0; col < grid.get(row).size(); col++) {
				boolean result = isPatternContainedInGridHelper(grid, pattern, 0, row, col);
				if (result) return true;
			}
		}

		return false;
	}

	static boolean isPatternContainedInGridHelper(List<List<Integer>> grid,
										List<Integer> pattern, int ptr, int row, int col) {

		if (ptr == pattern.size() - 1 && grid.get(row).get(col) == pattern.get(ptr)) return true;

		if (grid.get(row).get(col) == pattern.get(ptr)) {
			// explore
			// top
			if (row - 1 >= 0) {
				boolean res = isPatternContainedInGridHelper(grid, pattern, ptr + 1, row - 1, col);
				if (res) return true;
			}

			// left
			if (col - 1 >= 0) {
				boolean res = isPatternContainedInGridHelper(grid, pattern, ptr + 1, row, col - 1);
				if (res) return true;
			}

			// down
			if (row + 1 < grid.size()) {
				boolean res =  isPatternContainedInGridHelper(grid, pattern, ptr + 1, row + 1, col);
				if (res) return true;
			}

			// right
			if (col + 1 < grid.get(0).size()) {
				boolean res = isPatternContainedInGridHelper(grid, pattern, ptr + 1, row, col + 1);
				if (res) return true;
			}
		}

		return false;
	}

	public static void main(String[] args) {
		List<List<Integer>> grid = new ArrayList<>();
		grid.add(Arrays.asList(34));

		List<Integer> pattern = Arrays.asList(34);
		isPatternContainedInGrid(grid, pattern);
		System.exit(
				GenericTest
						.runFromAnnotations(args, "IsStringInMatrix.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
