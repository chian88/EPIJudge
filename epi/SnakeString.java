package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class SnakeString {
	@EpiTest(testDataFile = "snake_string.tsv")

	public static String snakeString(String s) {
		// TODO - you fill in here.
	  	String firstRow = "";
	  	String secondRow = "";
	  	String thirdRow = "";

	  	for (int i = 1; i < s.length(); i+= 4) {
	  		firstRow += s.charAt(i);
		}

	  	for (int i = 0; i < s.length(); i+= 2) {
	  		secondRow += s.charAt(i);
		}

	  	for (int i = 3; i < s.length(); i+= 4) {
	  		thirdRow += s.charAt(i);
		}

	  	String res = firstRow + secondRow + thirdRow;
		return res;
	}

	public static void main(String[] args) {
		snakeString("Hello World!");
		System.exit(
				GenericTest
						.runFromAnnotations(args, "SnakeString.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
