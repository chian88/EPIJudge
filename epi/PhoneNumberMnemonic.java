package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;

public class PhoneNumberMnemonic {
	@EpiTest(testDataFile = "phone_number_mnemonic.tsv")



	public static List<String> phoneMnemonic(String phoneNumber) {
		// TODO - you fill in here.
		String[] numPad = {"0", "1", "ABC", "DEF", "GHI", "JKL", "MNO", "PQRS", "TUV", "WXYZ"};
		List<String> result = new ArrayList<>();

		phoneMnemonicHelper(phoneNumber, 0, "", numPad, result);
		return result;
	}

	static void phoneMnemonicHelper(String phoneNumber, int idx, String temp, String[] numPad, List<String> result) {
		if (idx >= phoneNumber.length()) {
			// add temp to result
			result.add(temp);
			return;
		}

		int phoneNum = Character.getNumericValue(phoneNumber.charAt(idx));

		String possible = numPad[phoneNum];

		for (char c : possible.toCharArray()) {
			phoneMnemonicHelper(phoneNumber, idx + 1, temp + c , numPad, result);
		}

	}

	@EpiTestComparator
	public static boolean comp(List<String> expected, List<String> result) {
		if (result == null) {
			return false;
		}
		Collections.sort(expected);
		Collections.sort(result);
		return expected.equals(result);
	}

	public static void main(String[] args) {
		phoneMnemonic("52");
		System.exit(
				GenericTest
						.runFromAnnotations(args, "PhoneNumberMnemonic.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
