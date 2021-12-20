package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpiralOrdering {
	@EpiTest(testDataFile = "spiral_ordering.tsv")

	public static List<Integer>
	matrixInSpiralOrder(List<List<Integer>> squareMatrix) {
		// TODO - you fill in here.
	  	List<Integer> result = new ArrayList<>();

	  	for (int offset = 0; offset < Math.ceil(0.5 * squareMatrix.size() ) ; offset++) {
			matrixInSpiralOrderHelper(squareMatrix, offset, result);
		}
		return result;
	}

	private static void matrixInSpiralOrderHelper(List<List<Integer>> squareMatrix, int offset, List<Integer> result) {

		if (offset == squareMatrix.size() - offset - 1) {
			result.add(squareMatrix.get(offset).get(offset));
			return ;
		}

		for (int i = offset ; i < squareMatrix.size() - offset - 1; i++) {
			result.add(squareMatrix.get(offset).get(i));
		}

		for (int j = offset ; j < squareMatrix.size() - offset - 1; j++) {
			result.add(squareMatrix.get(j).get(squareMatrix.size() - offset - 1));
		}

		for (int i = squareMatrix.size() - offset - 1; i > offset; i--) {
			result.add(squareMatrix.get(squareMatrix.size() - 1 - offset).get(i)) ;
		}

		for (int j = squareMatrix.size() - offset - 1; j > offset; j--) {
			result.add(squareMatrix.get(j).get(offset));
		}
	}


	public static void main(String[] args) {
		List<List<Integer>> squareMatrix = new ArrayList<>();
		squareMatrix.add(Arrays.asList(1,2,3,4));
		squareMatrix.add(Arrays.asList(5,6,7,8));
		squareMatrix.add(Arrays.asList(9,10,11,12));
		squareMatrix.add(Arrays.asList(13,14,15,16));

		matrixInSpiralOrder(squareMatrix);

		System.exit(
				GenericTest
						.runFromAnnotations(args, "SpiralOrdering.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
