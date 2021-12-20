package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class ListCyclicRightShift {
	@EpiTest(testDataFile = "list_cyclic_right_shift.tsv")

	public static ListNode<Integer> cyclicallyRightShiftList(ListNode<Integer> L,
															 int k) {
		// TODO - you fill in here.
		if (L == null) return null;
		int n = 1;

		ListNode<Integer> tail = L;
		while (tail.next != null) {
			n++;
			tail = tail.next;
		}
		k = k % n;

		int stepsToHead = n - k;

		ListNode<Integer> head = L;
		while (stepsToHead > 0) {
			tail.next = head;
			tail = tail.next;
			head = head.next;
			stepsToHead--;
		}

		tail.next = null;
		return head;
	}

	// textbook ans;

//	public static ListNode<Integer> cyclicallyRightShiftList(ListNode<Integer> L,
//															 int k) {
//		// TODO - you fill in here.
//	  	if (L == null) return null;
//		int n = 1;
//
//		ListNode<Integer> tail = L;
//		while (tail.next != null) {
//			n++;
//			tail = tail.next;
//		}
//		k = k % n;
//		int stepsToNewHead = (n - k);
//
//	  	tail.next = L;
//
//		ListNode<Integer> newTail = tail;
//
//	  	while (stepsToNewHead > 0) {
//	  		stepsToNewHead--;
//	  		newTail = newTail.next;
//		}
//
//	  	ListNode<Integer> newHead = newTail.next;
//	  	newTail.next = null;
//
//
//
//		return newHead;
//	}

	public static void main(String[] args) {
		System.exit(
				GenericTest
						.runFromAnnotations(args, "ListCyclicRightShift.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
