package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashMap;
import java.util.*;

public class IsStringPermutableToPalindrome {
	@EpiTest(testDataFile = "is_string_permutable_to_palindrome.tsv")

	public static boolean canFormPalindrome(String s) {
		// TODO - you fill in here.
		Map<Character, Integer> count = new HashMap<>();

		for (char c : s.toCharArray()) {
			if (count.containsKey(c)) {
				count.put(c, count.get(c) + 1);
			} else {
				count.put(c, 1);
			}
		}

		boolean even = s.length() % 2 == 0;
		int oddCount = 0;

		for (Map.Entry<Character, Integer> entry : count.entrySet()) {
			if (entry.getValue() % 2 == 1) oddCount++;
		}

		if (even && oddCount > 0) {
			return false;
		} else if (!even && oddCount != 1) {
			return false;
		}

		return true;
	}

	public static void main(String[] args) {
		System.exit(
				GenericTest
						.runFromAnnotations(args, "IsStringPermutableToPalindrome.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
