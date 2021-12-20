package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

public class RunLengthCompression {

	public static String decoding(String s) {

		// TODO - you fill in here.

		StringBuilder res = new StringBuilder();
		int start = 0;
		int end = 1;

		while (end <= s.length()) {
			String sub = s.substring(start, end);
			if (isDigit(sub)) {
				end++;
			} else {
				char letter = sub.charAt(sub.length() - 1);
				int count = Integer.parseInt(sub.substring(0, sub.length() - 1));

				while (count > 0) {
					res.append(letter);
					count--;
				}

				start = end;
				end++;
			}
		}
		return res.toString();
	}

	public static String encoding(String s) {
		// TODO - you fill in here.
		char prev = s.charAt(0);
		int count = 1;
		StringBuilder res = new StringBuilder();

		for(int i = 1; i < s.length(); i++) {
			if (s.charAt(i) != prev) {
				res.append(count + ""+ prev);
				prev = s.charAt(i);
				count = 1;
			} else {
				count++;
			}
		}

		res.append(count + "" + prev);

		return res.toString();

	}

	public static boolean isDigit(String s) {
		for (char c : s.toCharArray()) {
			if (!Character.isDigit(c)) return false;
		}

		return true;
	}

	@EpiTest(testDataFile = "run_length_compression.tsv")
	public static void rleTester(String encoded, String decoded)
			throws TestFailure {
		if (!decoding(encoded).equals(decoded)) {
			throw new TestFailure("Decoding failed");
		}
		if (!encoding(decoded).equals(encoded)) {
			throw new TestFailure("Encoding failed");
		}
	}

	public static void main(String[] args) {
//		encoding("14a1b3c2a");
		System.exit(
				GenericTest
						.runFromAnnotations(args, "RunLengthCompression.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
