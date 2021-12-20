package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class SortList {
	@EpiTest(testDataFile = "sort_list.tsv")

	public static ListNode<Integer> stableSortList(ListNode<Integer> L) {
		// TODO - you fill in here.

		ListNode<Integer> dummyHead = new ListNode<>(0, L);

		ListNode<Integer> iter = L;

		while (iter != null && iter.next != null) {
			if (iter.data > iter.next.data){
				ListNode<Integer> target = iter.next , pre = dummyHead;

				while (pre.next.data < target.data) {
					pre = pre.next;
				}

				ListNode<Integer> temp = pre.next;
				pre.next = target;
				iter.next = target.next;
				target.next = temp;
			} else {
				iter = iter.next;
			}
		}
		return dummyHead.next;
	}

	public static void main(String[] args) {
		System.exit(
				GenericTest
						.runFromAnnotations(args, "SortList.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
