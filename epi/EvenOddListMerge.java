package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class EvenOddListMerge {
	@EpiTest(testDataFile = "even_odd_list_merge.tsv")

	public static ListNode<Integer> evenOddMerge(ListNode<Integer> L) {
		// TODO - you fill in here.
		if (L == null) return null;
		ListNode<Integer> evenDummyHead = new ListNode<>(0, null),
						  oddDummyHead = new ListNode<>(0, null);


		ListNode<Integer> evenCurr = evenDummyHead;
		ListNode<Integer> oddCurr = oddDummyHead;
		boolean even = true;
		while (L != null) {
			if (even) {
				evenCurr.next = new ListNode<>(L.data, null);
				evenCurr = evenCurr.next;
			} else {
				oddCurr.next = new ListNode<>(L.data, null);
				oddCurr = oddCurr.next;
			}
			even = !even;
			L = L.next;
		}

		evenCurr.next = oddDummyHead.next;
		return evenDummyHead.next;
	}

	public static void main(String[] args) {
		ListNode<Integer> four = new ListNode<>(4, null);
		ListNode<Integer> three = new ListNode<>(3, four);
		ListNode<Integer> two = new ListNode<>(2, three);
		ListNode<Integer> one = new ListNode<>(1, two);
		ListNode<Integer> zero = new ListNode<>(0, one);
		evenOddMerge(zero);

		System.exit(
				GenericTest
						.runFromAnnotations(args, "EvenOddListMerge.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
