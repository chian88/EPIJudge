package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;

public class ValidIpAddresses {
	@EpiTest(testDataFile = "valid_ip_addresses.tsv")

	public static List<String> getValidIpAddress(String s) {
		// TODO - you fill in here.
		List<String> res = new ArrayList<>();
		String main;
		String secondStr;
		String thirdStr;
		String fourthStr;


		for (int first = 1; (first <= 3 && first <= s.length()) ; first++) {
			main = s.substring(0, first);
			if (!validIp(main)) break;
			for (int second = first + 1; (second <= first + 3 && second <= s.length() ); second++) {
				secondStr =  s.substring(first, second);
				if (!validIp(secondStr)) break;
				for (int third = second + 1; (third <= second + 3 && third <= s.length() ); third++) {
					thirdStr = s.substring(second, third);
					if (!validIp(thirdStr)) break;
					for(int fourth = third + 1; (fourth <= third + 3 && fourth <= s.length() ) ; fourth ++) {
						fourthStr = s.substring(third, fourth);
						if (!validIp(fourthStr)) break;
						if ( fourth == s.length() ) {
							res.add(main + "." + secondStr + "." + thirdStr + "." + fourthStr);
						}


					}
				}
			}
		}

		return res;
	}

	private static boolean validIp(String ip) {
		if (ip.startsWith("0") && ip.length() > 1) {
			return false;
		}

		return (Integer.parseInt(ip) <= 255 && Integer.parseInt(ip) >= 0);
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
		getValidIpAddress("19216811");
		System.exit(
				GenericTest
						.runFromAnnotations(args, "ValidIpAddresses.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
