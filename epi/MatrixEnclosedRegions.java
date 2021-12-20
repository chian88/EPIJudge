package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
import java.util.*;

public class MatrixEnclosedRegions {

	public static void fillSurroundedRegions(List<List<Character>> board) {
		// TODO - you fill in here.
		boolean[][] visited = new boolean[board.size()][board.get(0).size()];
		for (int i = 1; i < board.size() - 1; i ++) {
			for (int j = 1; j < board.get(i).size() - 1; j++) {
				if (board.get(i).get(j) == 'W' && !visited[i][j]) {
					fillSurroundedRegionsBFS(board, i, j, visited);
				}
			}
		}

		return;
	}

	static void fillSurroundedRegionsBFS(List<List<Character>> board, int i, int j, boolean[][] visited) {
		Deque<Integer[]> queue = new ArrayDeque<>();
		List<Integer[]> forLater = new ArrayList<>();
		queue.addLast(new Integer[]{i, j});
		visited[i][j] = true;
		boolean foundEdge = false;
		while (!queue.isEmpty()) {
			Integer[] current = queue.pollFirst();

			forLater.add(current);
			if ( (current[0] == 0 || current[0] == board.size() - 1 ) ||
					(current[1] == 0 || current[1] == board.get(current[0]).size() - 1)) {
				foundEdge = true;
			}

			List<Integer[]> nextMoves = generateNextMoves(current[0], current[1], board, visited);

			for (Integer[] nextMove : nextMoves) {
				visited[nextMove[0]][nextMove[1]] = true;
				queue.addLast(nextMove);
			}

		}

		if (!foundEdge) {
			for (Integer[] coor : forLater) {
				board.get(coor[0]).set(coor[1], 'B');
			}
		}
	}

	static List<Integer[]> generateNextMoves(int i , int j, List<List<Character>> board, boolean[][] visited) {
		List<Integer[]> result = new ArrayList<>();
		if (i - 1 >= 0 && board.get(i - 1).get(j) == 'W' && !visited[i - 1][j]) {
			result.add(new Integer[]{i - 1, j });
		}

		if (j + 1 < board.get(i).size() && board.get(i).get(j + 1) == 'W' && !visited[i][j + 1]) {
			result.add(new Integer[]{ i, j + 1});
		}

		if (i + 1 < board.size() && board.get(i + 1).get(j) == 'W' && !visited[i + 1][j]) {
			result.add(new Integer[]{ i + 1, j});
		}

		if (j - 1 >= 0 && board.get(i).get(j - 1) == 'W' && !visited[i][j - 1]) {
			result.add(new Integer[]{ i, j - 1});
		}

		return result;
	}

	@EpiTest(testDataFile = "matrix_enclosed_regions.tsv")
	public static List<List<Character>>
	fillSurroundedRegionsWrapper(List<List<Character>> board) {
		fillSurroundedRegions(board);
		return board;
	}

	public static void main(String[] args) {
		System.exit(
				GenericTest
						.runFromAnnotations(args, "MatrixEnclosedRegions.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
