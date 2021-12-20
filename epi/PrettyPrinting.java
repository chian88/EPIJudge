package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
import java.util.*;

public class PrettyPrinting {
	@EpiTest(testDataFile = "pretty_printing.tsv")

	public static int minimumMessiness(List<String> words, int lineLength) {
		// TODO - you fill in here.

		int[] minimumMessiness = new int[words.size()];

		Arrays.fill(minimumMessiness, Integer.MAX_VALUE);

		int numRemainingBlanks = lineLength - words.get(0).length();
		minimumMessiness[0] = numRemainingBlanks * numRemainingBlanks;
		for (int i = 1; i < words.size(); i++) {
			String word = words.get(i);
			numRemainingBlanks = lineLength - word.length();
			minimumMessiness[i] = minimumMessiness[i - 1] + numRemainingBlanks * numRemainingBlanks;
			for (int j = i - 1; j >= 0; j--) {
				String word2 = words.get(j);
				numRemainingBlanks -= (word2.length() + 1);

				if (numRemainingBlanks < 0) {
					break;
				}

				int firstJMessiness = j - 1 < 0 ? 0 : minimumMessiness[j - 1];
				int currentLineMessiness = numRemainingBlanks * numRemainingBlanks;
				minimumMessiness[i] = Math.min(minimumMessiness[i], firstJMessiness + currentLineMessiness);
			}
		}
		return minimumMessiness[words.size() - 1];
	}

	public static void main(String[] args) {
		minimumMessiness(Arrays.asList("a", "b", "c", "d"), 5);
		System.exit(
				GenericTest
						.runFromAnnotations(args, "PrettyPrinting.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
