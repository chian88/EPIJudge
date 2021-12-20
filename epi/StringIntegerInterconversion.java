package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;


public class StringIntegerInterconversion {

	public static String intToString(int x) {
		// TODO - you fill in here.
		if (x == 0) return "0";

	  	StringBuilder res = new StringBuilder();
		boolean negative = x < 0 ? true : false;

		x = Math.abs(x);
	  	while (x != 0) {
			int lastDigit = x % 10;
			res.append(lastDigit);
			x = x / 10;
		}

	  	if (negative) res.append("-");



		return res.reverse().toString();
	}

	public static int stringToInt(String s) {
		// TODO - you fill in here.
		System.out.println(s);
		if (s.length() == 0) return 0;

		int multiplier = 1;
		int result = 0;
		boolean negative = false;

		if (s.startsWith("-")) {
			negative = true;
			int originalLen = s.length();
			s = s.replace("-", "");
			int afterLen = s.length();

			if (afterLen  < originalLen - 1) return Integer.MAX_VALUE;
		}

		s = s.replace("+", "");

		for (int i = s.length() - 1; i >= 0; i--) {
			int digit = s.charAt(i) - 48;
			result += digit * multiplier;
			multiplier *= 10;
		}

		if (negative) result *= -1;

		return result;
	}

	@EpiTest(testDataFile = "string_integer_interconversion.tsv")
	public static void wrapper(int x, String s) throws TestFailure {
		if (Integer.parseInt(intToString(x)) != x) {
			throw new TestFailure("Int to string conversion failed");
		}
		if (stringToInt(s) != x) {
			throw new TestFailure("String to int conversion failed");
		}
	}

	public static void main(String[] args) {
		System.out.println(stringToInt("-2-1-4-7-4-8-3-6-4-8-"));
		System.exit(
				GenericTest
						.runFromAnnotations(args, "StringIntegerInterconversion.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
