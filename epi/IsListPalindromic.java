package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import javax.swing.tree.TreeNode;

public class IsListPalindromic {
	@EpiTest(testDataFile = "is_list_palindromic.tsv")

	public static boolean isLinkedListAPalindrome(ListNode<Integer> L) {
		// TODO - you fill in here.
		ListNode<Integer> fast = L, slow = L;

		while (fast != null && fast.next != null) {
			fast = fast.next.next;
			slow = slow.next;
		}

		ListNode<Integer> firstHalfIter = L;
		ListNode<Integer> secondHalfIter = reverseLinkedList(slow);

		while (firstHalfIter != null && secondHalfIter != null) {
			if (firstHalfIter.data != secondHalfIter.data) {
				return false;
			}

			firstHalfIter = firstHalfIter.next;
			secondHalfIter = secondHalfIter.next;
		}



		return true;
	}


	private static ListNode reverseLinkedList(ListNode<Integer> L) {
		ListNode<Integer> prev = null;
		ListNode<Integer> current = L;
		ListNode<Integer> next = null;

		while (current != null) {
			next = current.next;
			current.next = prev;
			prev = current;
			current = next;
		}

		L = prev;
		return L;
	}

	public static void main(String[] args) {
		ListNode<Integer> two2 = new ListNode<>(2, null);
		ListNode<Integer> three2 = new ListNode<>(3, two2);
		ListNode<Integer> five = new ListNode<>(5, three2);
		ListNode<Integer> three1 = new ListNode<>(3, five);
		ListNode<Integer> two1 = new ListNode<>(2, three1);
		isLinkedListAPalindrome(two1);

		System.exit(
				GenericTest
						.runFromAnnotations(args, "IsListPalindromic.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
