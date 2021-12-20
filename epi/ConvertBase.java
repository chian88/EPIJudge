package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class ConvertBase {
	@EpiTest(testDataFile = "convert_base.tsv")

	public static String convertBase(String numAsString, int b1, int b2) {
		// TODO - you fill in here.
		if (numAsString.equals("0")) return "0";
		long base10 = 0l;
		int multiplier = 0;

		boolean negative = (numAsString.startsWith("-") ? true : false);

		if (negative) {
			numAsString = numAsString.replace("-", "");
		}

		for (int i = numAsString.length() - 1; i >= 0 ; i--) {
			base10 += convertToInt(numAsString.charAt(i) ) * Math.pow(b1,multiplier);
			multiplier++;
		}
		StringBuilder res = new StringBuilder();

		while (base10 > 0) {
			res.append( convert((int) base10 % b2));
			base10 /= b2;
		}

		if (negative) {
			res.append("-");
		}


		return res.reverse().toString();
	}

	private static int convertToInt(char c) {
		if (c == 'A') {
			return 10;
		} else if ( c == 'B') {
			return 11;
		} else if ( c == 'C') {
			return 12;
		} else if ( c == 'D') {
			return 13;
		} else if ( c == 'E') {
			return 14;
		} else if ( c == 'F') {
			return 15;
		} else {
			return c - '0';
		}
	}

	private static char convert(int num) {
		if (num == 10) {
			return 'A';
		} else if (num == 11) {
			return 'B';
		} else if (num == 12) {
			return 'C' ;
		} else if (num == 13) {
			return 'D';
		} else if (num == 14) {
			return 'E';
		} else if (num == 15) {
			return 'F';
		}
		return Character.forDigit(num, 10);
	}

	public static void main(String[] args) {
		System.out.println(convertBase("4B5C", 14, 5));
		System.exit(
				GenericTest
						.runFromAnnotations(args, "ConvertBase.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
