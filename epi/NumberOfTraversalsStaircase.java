package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Arrays;

public class NumberOfTraversalsStaircase {
	@EpiTest(testDataFile = "number_of_traversals_staircase.tsv")

	public static int numberOfWaysToTop(int top, int maximumStep) {
		// TODO - you fill in here.
	  	int[] steps = new int[top];
	  	for (int i = 0; i < steps.length; i++) {
	  		if ( (i + 1) <= maximumStep) {
				int sum = Arrays.stream(Arrays.copyOfRange( steps,Math.max(i - maximumStep, 0), i )).sum();
				steps[i] = sum + 1;
			} else {
				int sum = Arrays.stream(Arrays.copyOfRange( steps,Math.max(i - maximumStep , 0), i )).sum();
				steps[i] = sum ;
			}
		}

		return steps[steps.length - 1];
	}

	public static void main(String[] args) {
		numberOfWaysToTop(5, 3);

		System.exit(
				GenericTest
						.runFromAnnotations(args, "NumberOfTraversalsStaircase.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
