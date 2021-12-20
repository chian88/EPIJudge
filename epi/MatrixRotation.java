package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MatrixRotation {


	public static void rotateMatrix(List<List<Integer>> squareMatrix) {
		// TODO - you fill in here.
		final int matrixSize = squareMatrix.size() - 1;
	  	for (int i = 0 ; i < squareMatrix.size() / 2; i++) {
	  		for (int j = i; j < matrixSize - i; j++) {
	  			int temp1 = squareMatrix.get(matrixSize - j).get(i);
	  			int temp2 = squareMatrix.get(matrixSize - i).get(matrixSize - j);
	  			int temp3 = squareMatrix.get(j).get(matrixSize - i);
	  			int temp4 = squareMatrix.get(i).get(j);

	  			squareMatrix.get(i).set(j, temp1);
	  			squareMatrix.get(matrixSize - j).set(i, temp2);
	  			squareMatrix.get(matrixSize - i).set(matrixSize - j, temp3);
	  			squareMatrix.get(j).set(matrixSize - i, temp4);
			}
		}
		return;
	}




















//	public static void rotateMatrix(List<List<Integer>> squareMatrix) {
//		// TODO - you fill in here.
//	  	final int matrixSize = squareMatrix.size() - 1;
//
//	  	for (int i = 0; i < (squareMatrix.size() / 2); ++i) {
//	  		for (int j = i; j < matrixSize - i; j++) {
//	  			int temp1 = squareMatrix.get(matrixSize - j).get(i);
//	  			int temp2 = squareMatrix.get(matrixSize - i).get(matrixSize - j);
//	  			int temp3 = squareMatrix.get(j).get(matrixSize - i);
//	  			int temp4 = squareMatrix.get(i).get(j);
//
//	  			squareMatrix.get(i).set(j, temp1);
//	  			squareMatrix.get(matrixSize - j).set(i, temp2);
//	  			squareMatrix.get(matrixSize - i).set(matrixSize - j, temp3);
//	  			squareMatrix.get(j).set(matrixSize - i, temp4);
//			}
//		}
//
//		return;
//	}

	@EpiTest(testDataFile = "matrix_rotation.tsv")
	public static List<List<Integer>>
	rotateMatrixWrapper(List<List<Integer>> squareMatrix) {
		rotateMatrix(squareMatrix);
		return squareMatrix;
	}

	public static void main(String[] args) {
		List<List<Integer>> squareMatrix = new ArrayList<>();

		squareMatrix.add(Arrays.asList(1,2,3,4));
		squareMatrix.add(Arrays.asList(5,6,7,8));
		squareMatrix.add(Arrays.asList(9,10,11,12));
		squareMatrix.add(Arrays.asList(13,14,15,16));

		rotateMatrix(squareMatrix);
		System.exit(
				GenericTest
						.runFromAnnotations(args, "MatrixRotation.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
