package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IsStringPalindromicPunctuation {
	@EpiTest(testDataFile = "is_string_palindromic_punctuation.tsv")

	public static boolean isPalindrome(String s) {
		// TODO - you fill in here.
		int front = 0;
		int back = s.length() - 1;

		while (front < back) {

			char frontChar = s.charAt(front);
			char backChar = s.charAt(back);

			if (!Character.isLetterOrDigit(frontChar)) {
				front++;
				continue;
			}

			if (!Character.isLetterOrDigit(backChar)) {
				back--;
				continue;
			}

			if (Character.toLowerCase(frontChar) != Character.toLowerCase(backChar)) {
				return false;
			}
			front++;
			back--;
		}

		return true;
	}

	public static void main(String[] args) {
		boolean ans = isPalindrome("A man, a plan, a canal, Panama.");
		int x = 1;
		System.exit(
				GenericTest
						.runFromAnnotations(args, "IsStringPalindromicPunctuation.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
