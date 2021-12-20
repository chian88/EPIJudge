package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class SortedListsMerge {
	@EpiTest(testDataFile = "sorted_lists_merge.tsv")
	//@include
	public static ListNode<Integer> mergeTwoSortedLists(ListNode<Integer> L1,
														ListNode<Integer> L2) {

		ListNode<Integer> dummyHead = new ListNode<>(0, null);
		ListNode<Integer> current = dummyHead;

		while (L1 != null && L2 != null) {
			if (L1.data <= L2.data) {
				current.next = L1;
				L1 = L1.next;
			} else {
				current.next = L2;
				L2 = L2.next;
			}

			current = current.next;
		}

		if (L1 != null) {
			current.next = L1;
		} else {
			current.next = L2;
		}

		return dummyHead.next;

//		// TODO - you fill in here.
//		ListNode<Integer> head = null;
//		ListNode<Integer> current = null;
//	  	while (L1 != null && L2 != null) {
//	  		Integer insertData = null;
//	  		if (L1.data < L2.data) {
//	  			insertData = L1.data;
//				L1 = L1.next;
//			} else {
//	  			insertData = L2.data;
//				L2 = L2.next;
//			}
//
//	  		if (head == null) {
//	  			head = new ListNode(insertData, null);
//	  			current = head;
//			} else {
//				current.next = new ListNode(insertData, null);
//				current = current.next;
//			}
//		}
//
//	  	while (L1 != null) {
//	  		if (head == null) {
//				head = new ListNode(L1.data, null);
//				current = head;
//			} else {
//				current.next = new ListNode(L1.data, null);
//				current = current.next;
//			}
//
//	  		L1 = L1.next;
//
//		}
//
//		while (L2 != null) {
//			if (head == null) {
//				head = new ListNode(L2.data, null);
//				current = head;
//			} else {
//				current.next = new ListNode(L2.data, null);
//				current = current.next;
//			}
//
//			L2 = L2.next;
//
//		}
//
//		return head;
	}

	public static void main(String[] args) {
		ListNode list2 = new ListNode(2, null);
		ListNode listx = new ListNode(3, null);
		ListNode list5 = new ListNode(5, null);
		ListNode list7 = new ListNode(7, null);
		list2.next = listx;
		listx.next = list5;
		list5.next = list7;


		ListNode list3 = new ListNode(3, null);
		ListNode list11 = new ListNode(11, null);
		list3.next = list11;

		mergeTwoSortedLists(list2, list3);
		System.exit(
				GenericTest
						.runFromAnnotations(args, "SortedListsMerge.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
