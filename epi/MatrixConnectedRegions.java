package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class MatrixConnectedRegions {
	public static void flipColor(int x, int y, List<List<Boolean>> image) {
		// TODO - you fill in here.
		boolean originalColor = image.get(x).get(y);

		Deque<Integer[]> queue = new ArrayDeque<>();
		queue.addLast(new Integer[]{x, y});

		while (!queue.isEmpty()) {
			Integer[] current = queue.pollFirst();
			image.get(current[0]).set(current[1], !originalColor);
			List<Integer[]> nextMoves = generateNextMoves(current[0], current[1], image, originalColor);

			for (Integer[] nextMove : nextMoves) {
				queue.addLast(nextMove);
			}
		}

		return;
	}

	static List<Integer[]> generateNextMoves(int x, int y, List<List<Boolean>> image, boolean originalColor) {
		List<Integer[]> result = new ArrayList<>();
		// top
		if (x - 1 >= 0 && image.get(x - 1).get(y) == originalColor) {
			result.add(new Integer[]{x - 1, y});
		}


		// right
		if (y + 1 < image.get(0).size() && image.get(x).get(y + 1) == originalColor) {
			result.add(new Integer[]{x, y + 1});
		}

		// down

		if (x + 1 < image.size() && image.get(x + 1).get(y) == originalColor) {
			result.add(new Integer[]{x + 1, y});
		}

		//left

		if (y - 1 >= 0 && image.get(x).get(y - 1) == originalColor) {
			result.add(new Integer[]{x, y - 1});
		}

		return result;
	}



	@EpiTest(testDataFile = "painting.tsv")
	public static List<List<Integer>> flipColorWrapper(TimedExecutor executor,
													   int x, int y,
													   List<List<Integer>> image)
			throws Exception {
		List<List<Boolean>> B = new ArrayList<>();
		for (int i = 0; i < image.size(); i++) {
			B.add(new ArrayList<>());
			for (int j = 0; j < image.get(i).size(); j++) {
				B.get(i).add(image.get(i).get(j) == 1);
			}
		}

		executor.run(() -> flipColor(x, y, B));

		image = new ArrayList<>();
		for (int i = 0; i < B.size(); i++) {
			image.add(new ArrayList<>());
			for (int j = 0; j < B.get(i).size(); j++) {
				image.get(i).add(B.get(i).get(j) ? 1 : 0);
			}
		}

		return image;
	}

	public static void main(String[] args) {
		System.exit(
				GenericTest
						.runFromAnnotations(args, "MatrixConnectedRegions.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
