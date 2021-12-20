package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Set;
import java.util.*;

class StringWithDistance {
	public String candidateString;
	public Integer distance;

	public StringWithDistance(String candidateString, Integer distance) {
		this.candidateString = candidateString;
		this.distance = distance;
	}
}

public class StringTransformability {
	@EpiTest(testDataFile = "string_transformability.tsv")


	public static int transformString(Set<String> D, String s, String t) {
		// TODO - you fill in here.
		Set<String> visited = new HashSet<>(D);
		Queue<StringWithDistance> q = new ArrayDeque<>();
		visited.remove(s);
		q.add(new StringWithDistance(s, 0));

		StringWithDistance f;
		while ((f = q.poll()) != null) {
			if (f.candidateString.equals(t)) {
				return f.distance;
			}

			String str = f.candidateString;
			for (int i = 0; i < str.length(); i++) {
				String strStart = i == 0 ? "" : str.substring(0, i);
				String strEnd = i + 1 < str.length() ? str.substring(i + 1) : "";
				for (char c = 'a'; c <= 'z' ; ++c) {
					String modStr = strStart + c + strEnd;
					if (visited.contains(modStr)) {
						visited.remove(modStr);
						q.add(new StringWithDistance(modStr, f.distance + 1));
					}
				}
			}
		}


		return -1;
	}




	public static void main(String[] args) {
		//transformString(Set.of("cat", "dog", "dot", "cot", "bat", "dag"), "cat", "dog");
		System.exit(
				GenericTest
						.runFromAnnotations(args, "StringTransformability.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
