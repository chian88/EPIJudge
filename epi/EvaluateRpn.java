package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.*;

public class EvaluateRpn {
	@EpiTest(testDataFile = "evaluate_rpn.tsv")

	public static int eval(String expression) {
		// TODO - you fill in here.
		Deque<Integer> stack = new ArrayDeque<>();

		String[] operations = expression.split(",");

		for (String operation : operations) {
			if (operation.equals("+")) {
				int second = stack.removeFirst();
				int first = stack.removeFirst();
				stack.addFirst(first + second);
			} else if (operation.equals("-")) {
				int second = stack.removeFirst();
				int first = stack.removeFirst();
				stack.addFirst(first - second);
			} else if (operation.equals("*")) {
				int second = stack.removeFirst();
				int first = stack.removeFirst();
				stack.addFirst(first * second);
			} else if (operation.equals("/")) {
				int second = stack.removeFirst();
				int first = stack.removeFirst();
				stack.addFirst(first / second);
			} else {
				stack.addFirst(Integer.parseInt(operation));
			}

		}

		return stack.removeFirst();
	}

	public static void main(String[] args) {
		System.exit(
				GenericTest
						.runFromAnnotations(args, "EvaluateRpn.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
