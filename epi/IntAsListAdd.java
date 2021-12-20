package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IntAsListAdd {
	@EpiTest(testDataFile = "int_as_list_add.tsv")

	public static ListNode<Integer> addTwoNumbers(ListNode<Integer> L1,
												  ListNode<Integer> L2) {
		// TODO - you fill in here.
		ListNode<Integer> dummyHead = new ListNode<>(0, null);
		ListNode<Integer> res = dummyHead;
		int carry = 0;
		while (L1 != null && L2 != null) {
			int digit1 = L1.data;
			int digit2 = L2.data;

			int sum = digit1 + digit2 + carry;

			carry = (sum  > 9 ? 1 : 0);

			res.next = new ListNode<Integer>(sum % 10, null);
			res = res.next;

			L1 = L1.next;
			L2 = L2.next;
		}

		while (L1 != null) {
			int sum = L1.data + carry;
			carry = (sum  > 9 ? 1 : 0);

			res.next = new ListNode<Integer>(sum % 10, null);
			res = res.next;

			L1 = L1.next;
		}

		while (L2 != null) {
			int sum = L2.data + carry;
			carry = (sum  > 9 ? 1 : 0);

			res.next = new ListNode<Integer>(sum % 10, null);
			res = res.next;

			L2 = L2.next;
		}

		if (carry > 0)  res.next = new ListNode<Integer>(1, null);
		return dummyHead.next;
	}

	public static void main(String[] args) {
		ListNode<Integer> nine = new ListNode<>(9, null);
		ListNode<Integer> zero = new ListNode<>(0, nine);
		ListNode<Integer> seven = new ListNode<>(7, zero);

		ListNode<Integer> four = new ListNode<>(4, null);
		ListNode<Integer> one = new ListNode<>(1, four);
		ListNode<Integer> three = new ListNode<>(3, one);

		addTwoNumbers(seven, three);

		System.exit(
				GenericTest
						.runFromAnnotations(args, "IntAsListAdd.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
