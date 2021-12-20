package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class RemoveDuplicatesFromSortedList {
	@EpiTest(testDataFile = "remove_duplicates_from_sorted_list.tsv")

	public static ListNode<Integer> removeDuplicates(ListNode<Integer> L) {
		// TODO - you fill in here.
		ListNode<Integer> dummyHead = new ListNode<>(0, L);

		ListNode<Integer> iter = dummyHead.next;

		while (iter != null && iter.next != null) {
			if (iter.data == iter.next.data) {
				iter.next = iter.next.next;
				continue;
			}

			iter = iter.next;
		}
		return dummyHead.next;
	}

	public static void main(String[] args) {
//		ListNode<Integer> four = new ListNode<>(4, null);
//		ListNode<Integer> three = new ListNode<>(3, four);
//		ListNode<Integer> two2 = new ListNode<>(2, three);
//		ListNode<Integer> two1 = new ListNode<>(2, two2);
//		ListNode<Integer> one = new ListNode<>(1, two1);
//		removeDuplicates(one);
		System.exit(
				GenericTest
						.runFromAnnotations(args, "RemoveDuplicatesFromSortedList.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
