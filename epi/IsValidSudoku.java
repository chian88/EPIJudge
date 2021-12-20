package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IsValidSudoku {
	@EpiTest(testDataFile = "is_valid_sudoku.tsv")

	// Check if a partially filled matrix has any conflicts.
	public static boolean isValidSudoku(List<List<Integer>> partialAssignment) {
		// TODO - you fill in here.
		for (int row = 0; row < partialAssignment.size(); row++) {
			for(int col = 0; col < partialAssignment.get(0).size(); col++) {
				if (!validRow(partialAssignment, row, col)) return false;
				if (!validCol(partialAssignment, row, col)) return false;
				if (!validBlock(partialAssignment, row, col)) return false;
			}
		}
		return true;
	}

	static boolean validBlock(List<List<Integer>> partialAssignment, int row, int col) {
		int[] count = new int[9];
		int startRow = -1;
		int endRow = -1;
		int startCol = -1;
		int endCol = -1;

		if (row <= 2 && col <= 2) {
			startRow = 0;
			startCol = 0;
			endRow = 2;
			endCol = 2;

		} else if (row <= 2 && col <= 5) {
			startRow = 0;
			startCol = 3;
			endRow = 2;
			endCol = 5;
		} else if (row <= 2 && col <= 8) {
			startRow = 0;
			startCol = 6;
			endRow = 2;
			endCol = 8;
		} else if (row <= 5 && col <= 2) {
			startRow = 3;
			startCol = 0;
			endRow = 5;
			endCol = 2;
		} else if (row <= 5 && col <= 5) {
			startRow = 3;
			startCol = 3;
			endRow = 5;
			endCol = 5;
		} else if (row <= 5 && col <= 8) {
			startRow = 3;
			startCol = 6;
			endRow = 5;
			endCol = 8;

		} else if (row <= 8 && col <= 2) {
			startRow = 6;
			startCol = 0;
			endRow = 8;
			endCol = 2;

		} else if (row <= 8 && col <= 5) {
			startRow = 6;
			startCol = 3;
			endRow = 8;
			endCol = 5;

		} else if (row <= 8 && col <= 8) {
			startRow = 6;
			startCol = 6;
			endRow = 8;
			endCol = 8;
		}

		for (int row2 = startRow; row2 <= endRow; row2++) {
			for (int col2 = startCol; col2 <= endCol; col2++) {
				int num = partialAssignment.get(row2).get(col2);
				if (num != 0) {
					count[num -1]++;
					if (count[num - 1] > 1) return false;
				}

			}
		}

		return true;
	}

	static boolean validCol (List<List<Integer>> partialAssignment, int row, int col) {
		int[] count = new int[9];
		for (int i = 0; i < 9; i++) {
			int num = partialAssignment.get(i).get(col);
			if (num != 0) {
				count[num - 1]++;
				if (count[num - 1] > 1) return false;
			}

		}
		return true;
	}

	static boolean validRow (List<List<Integer>> partialAssignment, int row, int col) {
		int[] count = new int[9];
		for (int i = 0; i < 9; i++) {
			int num = partialAssignment.get(row).get(i);
			if (num != 0) {
				count[num - 1]++;
				if (count[num - 1] > 1) return false;
			}

		}
		return true;
	}

	public static void main(String[] args) {
		List<List<Integer>> sudoku = new ArrayList<>();
		sudoku.add(new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 8, 4)));
		sudoku.add(new ArrayList<>(Arrays.asList(0, 0, 3, 9, 7, 0, 5, 0, 0)));
		sudoku.add(new ArrayList<>(Arrays.asList(6, 0, 2, 0, 0, 0, 7, 0, 0)));
		sudoku.add(new ArrayList<>(Arrays.asList(0, 6, 0, 4, 0, 0, 9, 7, 0)));
		sudoku.add(new ArrayList<>(Arrays.asList(8, 0, 0, 0, 3, 1, 6, 4, 2)));
		sudoku.add(new ArrayList<>(Arrays.asList(0, 0, 0, 5, 0, 0, 3, 0, 8)));
		sudoku.add(new ArrayList<>(Arrays.asList(0, 0, 0, 2, 4, 0, 0, 0, 1)));
		sudoku.add(new ArrayList<>(Arrays.asList(0, 1, 5, 0, 8, 0, 0, 3, 0)));
		sudoku.add(new ArrayList<>(Arrays.asList(4, 7, 0, 0, 5, 0, 0, 6, 0)));
		isValidSudoku(sudoku);
		System.exit(
				GenericTest
						.runFromAnnotations(args, "IsValidSudoku.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
