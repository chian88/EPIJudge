package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.serialization_traits.IntegerTrait;

import java.util.ArrayList;
import java.util.List;

public class PascalTriangle {
	@EpiTest(testDataFile = "pascal_triangle.tsv")

	public static List<List<Integer>> generatePascalTriangle(int numRows) {
		// TODO - you fill in here.
	  	List<List<Integer>> pascalTriangle = new ArrayList<>();

	  	for (int i = 0; i < numRows; i++) {
	  		List<Integer> currentRow = new ArrayList<>();

	  		for (int j = 0 ; j <= i ; j++ ) {
	  			if (j == 0 || j == i) {
	  				currentRow.add(1);
				} else {
	  				currentRow.add(pascalTriangle.get(i - 1).get(j) + pascalTriangle.get(i-1).get(j - 1));
				}
			}

	  		pascalTriangle.add(currentRow);
		}

		return pascalTriangle;
	}

	public static void main(String[] args) {

		generatePascalTriangle(5);
		System.exit(
				GenericTest
						.runFromAnnotations(args, "PascalTriangle.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
