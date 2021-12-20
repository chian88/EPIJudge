package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class ReverseSublist {
	@EpiTest(testDataFile = "reverse_sublist.tsv")

	public static ListNode<Integer> reverseSublist(ListNode<Integer> L, int start,
												   int finish) {
		// TODO - you fill in here.

		ListNode<Integer> dummyHead = new ListNode(0, L);
		ListNode<Integer> sublistHead = dummyHead;
		int k = 1;
		while (k++ < start) {
			sublistHead = sublistHead.next;
		}

		ListNode<Integer> sublistIter = sublistHead.next;

		while (start ++ < finish) {
			ListNode<Integer> temp = sublistIter.next;
			sublistIter.next = temp.next;
			temp.next = sublistHead.next;
			sublistHead.next = temp;
		}

		return dummyHead.next;

	}

	public static void main(String[] args) {
		ListNode list1 = new ListNode(5, null);
		ListNode list2 = new ListNode(4, list1);
		ListNode list3 = new ListNode(3, list2);
		ListNode list4 = new ListNode(2, list3);
		ListNode list5 = new ListNode(1, list4);

		reverseSublist(list5, 3, 5);
		System.exit(
				GenericTest
						.runFromAnnotations(args, "ReverseSublist.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
