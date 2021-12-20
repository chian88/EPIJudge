package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReplaceAndRemove {

	public static int replaceAndRemove(int size, char[] s) {
		// TODO - you fill in here.
	  	int read = 0;
	  	int write = 0;

	  	while (read < s.length && s[read] != '\0') {
	  		if (s[read] != 'b') {
	  			s[write] = s[read];
	  			read++;
	  			write++;
			} else {
	  			read++;
			}
		}

		int numOfA = 0;

	  	for (int i = 0; i < write; i++) {
	  		if (s[i] == 'a') numOfA++;
		}

	  	read = write - 1;
	  	write = write + numOfA - 1 ;

	  	final int finalSize = write + 1;

	  	while(read >= 0) {
	  		if (s[read] == 'a') {
	  			s[write--] = 'd';
	  			s[write] = 'd';

			} else {
	  			s[write] = s[read];

			}
			write--;
	  		read--;
		}






		return finalSize;
	}

	@EpiTest(testDataFile = "replace_and_remove.tsv")
	public static List<String>
	replaceAndRemoveWrapper(TimedExecutor executor, Integer size, List<String> s)
			throws Exception {
		char[] sCopy = new char[s.size()];
		for (int i = 0; i < size; ++i) {
			if (!s.get(i).isEmpty()) {
				sCopy[i] = s.get(i).charAt(0);
			}
		}

		Integer resSize = executor.run(() -> replaceAndRemove(size, sCopy));

		List<String> result = new ArrayList<>();
		for (int i = 0; i < resSize; ++i) {
			result.add(Character.toString(sCopy[i]));
		}
		return result;
	}

	public static void main(String[] args) {
		replaceAndRemove(11, new char[]{'d', 'b', 'b', 'a', 'b', 'c', 'a', 'c', 'a', 'd', 'a', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0'});
		System.exit(
				GenericTest
						.runFromAnnotations(args, "ReplaceAndRemove.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
