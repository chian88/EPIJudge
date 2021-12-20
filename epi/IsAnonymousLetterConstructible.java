package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.*;

public class IsAnonymousLetterConstructible {
	@EpiTest(testDataFile = "is_anonymous_letter_constructible.tsv")
//  public static boolean isLetterConstructibleFromMagazine(String letterText,
//                                                          String magazineText) {
//    Map<Character, Long> charFrequencyForLetter =
//            letterText.chars()
//                    .mapToObj(c -> (char)c)
//                    .collect(Collectors.groupingBy(Function.identity(),
//                            Collectors.counting()));
//
//    // Check if the characters in magazineText can cover characters in
//    // letterText.
//    for (char c : magazineText.toCharArray()) {
//      if (charFrequencyForLetter.containsKey(c)) {
//        charFrequencyForLetter.put(c, charFrequencyForLetter.get(c) - 1);
//        if (charFrequencyForLetter.remove(c, 0L)) {
//          charFrequencyForLetter.remove(c);
//          // All characters for letterText are matched.
//          if (charFrequencyForLetter.isEmpty()) {
//            break;
//          }
//        }
//      }
//    }
//    // Empty charFrequencyForLetter means every char in letterText can be
//    // covered by a character in magazineText.
//    return charFrequencyForLetter.isEmpty();
//  }


	public static boolean isLetterConstructibleFromMagazine(String letterText,
															String magazineText) {
		// TODO - you fill in here.
		Map<Character, Integer> freqMagazine = new HashMap<>();

		for (char c : magazineText.toCharArray()) {
			if (freqMagazine.containsKey(c)) {
				freqMagazine.put(c, freqMagazine.get(c) + 1);
			} else {
				freqMagazine.put(c, 1);
			}
		}

		for (char c : letterText.toCharArray()) {
			if (freqMagazine.containsKey(c)) {
				int count = freqMagazine.get(c) - 1;
				if (count < 0) return false;
				freqMagazine.put(c, count);
			} else {
				return false;
			}
		}

		return true;
	}

	public static void main(String[] args) {
		System.exit(
				GenericTest
						.runFromAnnotations(args, "IsAnonymousLetterConstructible.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
