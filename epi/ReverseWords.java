package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;
import java.util.*;
public class ReverseWords {

	public static void reverseWords(char[] input) {
		// TODO - you fill in here.
		reverse(input, 0 , input.length - 1);

		int start = 0;
		int end = 0;

		while (end < input.length) {
			if (Character.isWhitespace(input[end])) {
				reverse(input, start, end - 1);
				start = end + 1;
			}
			end++;
		}
		reverse(input, start, end - 1);

		return;
	}

	private static void reverse(char[] input, int start, int end) {


		while (start < end) {
			char temp = input[start];
			input[start] = input[end];
			input[end] = temp;

			start++;
			end--;
		}
	}

	@EpiTest(testDataFile = "reverse_words.tsv")
	public static String reverseWordsWrapper(TimedExecutor executor, String s)
			throws Exception {
		char[] sCopy = s.toCharArray();

		executor.run(() -> reverseWords(sCopy));

		return String.valueOf(sCopy);
	}

	public static void main(String[] args) {
		reverseWords(new char[]{'r', 'a', 'm', ' ', 'c', 'o', 's', 't'});
		System.exit(
				GenericTest
						.runFromAnnotations(args, "ReverseWords.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
