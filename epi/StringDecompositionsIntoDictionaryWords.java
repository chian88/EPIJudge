package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
import java.util.*;

public class StringDecompositionsIntoDictionaryWords {
	@EpiTest(testDataFile = "string_decompositions_into_dictionary_words.tsv")

	public static List<Integer> findAllSubstrings(String s, List<String> words) {
		// TODO - you fill in here.
		Map<String, Integer> wordFreq = new HashMap<>();

		for (String word : words) {
			wordFreq.put(word, wordFreq.getOrDefault(word, 0) + 1);
		}

		final int wordSize = words.get(0).length();
		List<Integer> result = new ArrayList<>();
		for (int i = 0; i + wordSize * words.size() <= s.length(); i++) {
			if (matching(i, wordFreq, s, wordSize, words)) {
				result.add(i);
			}
		}


		return result;
	}

	public static boolean matching(int start, Map<String, Integer> wordFreq, String s, int wordSize, List<String> words) {
		Map<String, Integer> currFreq = new HashMap<>();

		for (int i = 0 ; i < words.size(); i++) {
			String currWord = s.substring(start + i * wordSize, start + (i + 1) * wordSize);

			Integer freq = wordFreq.get(currWord);

			if (freq == null) {
				return false;
			} else {
				currFreq.put(currWord, currFreq.getOrDefault(currWord, 0) + 1);
			}

			if (currFreq.get(currWord) > freq) {
				return false;
			}
		}

		return true;
	}

	public static void main(String[] args) {
		System.exit(GenericTest
				.runFromAnnotations(
						args, "StringDecompositionsIntoDictionaryWords.java",
						new Object() {
						}.getClass().getEnclosingClass())
				.ordinal());
	}
}
