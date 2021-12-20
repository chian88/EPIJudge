package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Iterator;
import java.util.List;

public class MajorityElement {

	public static String majoritySearch(Iterator<String> stream) {
		// TODO - you fill in here.
		int count = 0;
		String majority = null;

		while (stream.hasNext()) {
			String current = stream.next();
			if (majority == null) {
				majority = current;
				count = 1;
			} else if (majority.equals(current)) {
				count += 1;
			} else {
				count -= 1;

				if (count == 0) {
					majority = null;
				}
			}
		}


		return majority;
	}

	@EpiTest(testDataFile = "majority_element.tsv")
	public static String majoritySearchWrapper(List<String> stream) {
		return majoritySearch(stream.iterator());
	}

	public static void main(String[] args) {
		System.exit(
				GenericTest
						.runFromAnnotations(args, "MajorityElement.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
