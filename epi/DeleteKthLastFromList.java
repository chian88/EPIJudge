package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class DeleteKthLastFromList {
	@EpiTest(testDataFile = "delete_kth_last_from_list.tsv")

	// Assumes L has at least k nodes, deletes the k-th last node in L.
	public static ListNode<Integer> removeKthLast(ListNode<Integer> L, int k) {
		// TODO - you fill in here.
	  	ListNode<Integer> dummyHead = new ListNode<>(0, L);

	  	ListNode<Integer> ahead = dummyHead.next;

	  	while (k > 0) {
	  		ahead = ahead.next;
	  		k--;
		}

	  	ListNode<Integer> behind = dummyHead;

	  	while (ahead != null) {
	  		behind = behind.next;
	  		ahead = ahead.next;
		}

	  	behind.next = behind.next.next;

		return dummyHead.next;
	}

	public static void main(String[] args) {
		System.exit(
				GenericTest
						.runFromAnnotations(args, "DeleteKthLastFromList.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
