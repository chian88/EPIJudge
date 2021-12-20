package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class SubstringMatch {
	@EpiTest(testDataFile = "substring_match.tsv")

	// Returns the index of the first character of the substring if found, -1
	// otherwise.
	public static int rabinKarp(String t, String s) {
		// TODO - you fill in here.

		if (s.length() > t.length()) return -1;

		final int BASE = 10;

		int sHash = 0, tHash = 0, powers = 1;


		for (int i = 0 ; i < s.length(); i++) {
			if ( i > 0) powers = powers * BASE;

			sHash = sHash * BASE + s.charAt(i);
			tHash = tHash * BASE + t.charAt(i);
		}

		for (int i = s.length(); i < t.length(); i++) {
			if (sHash == tHash && t.substring(i - s.length(), i).equals(s)) {
				return i - s.length();
			}

			tHash -= powers * t.charAt( i - s.length() ) ;
			tHash = tHash * BASE + t.charAt(i);
		}

		if (sHash == tHash && t.substring(t.length() - s.length()).equals(s)) {
			return t.length() - s.length();
		}

		return -1;
	}

	public static void main(String[] args) {
		rabinKarp("GACGCCA", "CGC");

		System.exit(
				GenericTest
						.runFromAnnotations(args, "SubstringMatch.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
