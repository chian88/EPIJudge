package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.*;

public class SearchMaze {
	@EpiUserType(ctorParams = {int.class, int.class})

	public static class Coordinate {
		public int x, y;

		public Coordinate(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}

			if (o == null || getClass() != o.getClass()) {
				return false;
			}

			Coordinate that = (Coordinate) o;
			if (x != that.x || y != that.y) {
				return false;
			}
			return true;
		}
	}

	public enum Color {WHITE, BLACK}

	public static List<Coordinate> searchMaze(List<List<Color>> maze,
											  Coordinate s, Coordinate e) {
		// TODO - you fill in here.
		List<Coordinate> path = new ArrayList<>();
		boolean[][] visited = new boolean[maze.size()][maze.get(0).size()];
		searchMazeDFS(maze, s, e, path, visited);
		return path;
	}

	static boolean searchMazeDFS(List<List<Color>> maze, Coordinate s,
								 Coordinate e, List<Coordinate> path, boolean[][] visited) {
		if (s.equals(e)) {
			path.add(s);
			return true;
		}
		if (visited[s.x][s.y]) return false;

		path.add(s);

		visited[s.x][s.y] = true;
		List<Coordinate> nextMoves = generateNextMove(maze, s);

		for (Coordinate nextMove : nextMoves) {
			boolean found = searchMazeDFS(maze, nextMove, e, path, visited);
			if (found) return true;
		}
		path.remove(path.size() - 1);
		return false;
	}

	static List<Coordinate> generateNextMove(List<List<Color>> maze, Coordinate s) {
		List<Coordinate> result = new ArrayList<>();

		// left
		if (s.y - 1 >= 0 && maze.get(s.x).get(s.y - 1) == Color.WHITE) {
			result.add(new Coordinate(s.x , s.y - 1));
		}


		// top
		if (s.x - 1 >= 0 && maze.get(s.x - 1).get(s.y) == Color.WHITE) {
			result.add(new Coordinate(s.x - 1 , s.y ));
		}



		// right
		if (s.y + 1 < maze.get(0).size() && maze.get(s.x).get(s.y + 1) == Color.WHITE) {
			result.add(new Coordinate(s.x , s.y + 1 ));
		}

		// down
		if (s.x + 1 < maze.size() && maze.get(s.x + 1).get(s.y) == Color.WHITE) {
			result.add(new Coordinate(s.x + 1, s.y));}


		return result;
	}

	public static boolean pathElementIsFeasible(List<List<Integer>> maze,
												Coordinate prev, Coordinate cur) {
		if (!(0 <= cur.x && cur.x < maze.size() && 0 <= cur.y &&
				cur.y < maze.get(cur.x).size() && maze.get(cur.x).get(cur.y) == 0)) {
			return false;
		}
		return cur.x == prev.x + 1 && cur.y == prev.y ||
				cur.x == prev.x - 1 && cur.y == prev.y ||
				cur.x == prev.x && cur.y == prev.y + 1 ||
				cur.x == prev.x && cur.y == prev.y - 1;
	}

	@EpiTest(testDataFile = "search_maze.tsv")
	public static boolean searchMazeWrapper(List<List<Integer>> maze,
											Coordinate s, Coordinate e)
			throws TestFailure {
		List<List<Color>> colored = new ArrayList<>();
		for (List<Integer> col : maze) {
			List<Color> tmp = new ArrayList<>();
			for (Integer i : col) {
				tmp.add(i == 0 ? Color.WHITE : Color.BLACK);
			}
			colored.add(tmp);
		}
		List<Coordinate> path = searchMaze(colored, s, e);
		if (path.isEmpty()) {
			return s.equals(e);
		}

		if (!path.get(0).equals(s) || !path.get(path.size() - 1).equals(e)) {
			throw new TestFailure("Path doesn't lay between start and end points");
		}

		for (int i = 1; i < path.size(); i++) {
			if (!pathElementIsFeasible(maze, path.get(i - 1), path.get(i))) {
				throw new TestFailure("Path contains invalid segments");
			}
		}

		return true;
	}

	public static void main(String[] args) {
		System.exit(
				GenericTest
						.runFromAnnotations(args, "SearchMaze.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
