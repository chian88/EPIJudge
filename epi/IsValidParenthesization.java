package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayDeque;
import java.util.*;

public class IsValidParenthesization {
	@EpiTest(testDataFile = "is_valid_parenthesization.tsv")

	public static boolean isWellFormed(String s) {
		// TODO - you fill in here.

		Deque<Character> stack = new ArrayDeque<>();

		for (char c : s.toCharArray()) {
			if (c == ')') {
				Character last = stack.pollFirst();
				if (last == null || last != '(') return false;
			} else if (c == ']') {
				Character last = stack.pollFirst();
				if (last == null || last != '[') return false;
			} else if (c == '}') {
				Character last = stack.pollFirst();
				if (last == null || last != '{') return false;
			} else {
				stack.addFirst(c);
			}
		}
		return stack.isEmpty() ?  true : false;
	}

	public static void main(String[] args) {
		System.exit(
				GenericTest
						.runFromAnnotations(args, "IsValidParenthesization.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
