package epi;
import java.util.*;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.HashMap;

public class RomanToInteger {
	@EpiTest(testDataFile = "roman_to_integer.tsv")

	public static int romanToInteger(String s) {
		// TODO - you fill in here.
	  	Map<String, Integer> info = Map.ofEntries(
	  			Map.entry("I", 1),
				Map.entry("IV", 4),
				Map.entry("V", 5),
				Map.entry("IX", 9),
				Map.entry("X", 10),
				Map.entry("XL", 40),
				Map.entry("L", 50),
				Map.entry("XC", 90),
				Map.entry("C", 100),
				Map.entry("CD", 400),
				Map.entry("D", 500),
				Map.entry("CM", 900),
				Map.entry("M", 1000)
		);

	  	int res = 0;
		for (int i = 0; i < s.length(); i+= 1) {
			int next = (i + 2 <= s.length()) ? i+2 : i+1;
			String sub = s.substring(i, next);
			if (info.containsKey(sub)) {
				res += info.get(sub);
				i++;
			} else {
				res += info.get(s.substring(i, i+1));
			}
		}


		return res;
	}


	public static void main(String[] args) {
//		romanToInteger("IIII");
//		romanToInteger("IV");
		romanToInteger("XXXXXIIIIIIIII");
		romanToInteger("LVIIII");
		romanToInteger("LIX");
		System.exit(
				GenericTest
						.runFromAnnotations(args, "RomanToInteger.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
