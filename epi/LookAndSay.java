package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.*;

public class LookAndSay {
	@EpiTest(testDataFile = "look_and_say.tsv")

	public static String lookAndSay(int n) {
		// TODO - you fill in here.
		String res = "1";
		Map<Integer, Integer> count = new HashMap<>();

	  	while (n > 1) {
	  		res = readOffNum(res);
			count.put(n, n);
	  		n--;
		}

		return res;
	}

	private static String readOffNum(String str) {
		int start = 0;
		int end = 0;
		int count = 0;
		StringBuilder res = new StringBuilder();
		while (end < str.length()) {
			if (str.charAt(start) == str.charAt(end)) {
				count++;
			} else {
				res.append(count);
				res.append(str.charAt(start));
				start = end;
				count=1;

			}
			end++;
		}
		res.append(count);
		res.append(str.charAt(start));
		return res.toString();
	}

	public static void main(String[] args) {
		lookAndSay(5);
		System.exit(
				GenericTest
						.runFromAnnotations(args, "LookAndSay.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
