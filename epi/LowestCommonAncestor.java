package epi;

import epi.test_framework.BinaryTreeUtils;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;



public class LowestCommonAncestor {

	static class Structure {
		boolean foundNode0;
		boolean foundNode1;

		Structure(boolean foundNode0, boolean foundNode1) {
			this.foundNode0 = foundNode0;
			this.foundNode1 = foundNode1;
		}
	}

	public static BinaryTreeNode<Integer> lca(BinaryTreeNode<Integer> tree,
											  BinaryTreeNode<Integer> node0,
											  BinaryTreeNode<Integer> node1) {
		// TODO - you fill in here.

		if (tree == node0 ) return node0;
		if (tree == node1) return node1;

		Structure leftSubtree = foundBothSameSubtree(tree.left, node0, node1, new Structure(false, false));
		Structure rightSubtree = foundBothSameSubtree(tree.right, node0, node1, new Structure(false, false));

		if (leftSubtree.foundNode0 && leftSubtree.foundNode1) {
			return lca(tree.left, node0, node1);
		} else if (rightSubtree.foundNode0 && rightSubtree.foundNode1) {
			return lca(tree.right, node0, node1);
		} else {
			return tree;
		}
	}

	static Structure foundBothSameSubtree(BinaryTreeNode<Integer> tree,
									   BinaryTreeNode<Integer> node0,
										BinaryTreeNode<Integer> node1,
										Structure found) {

		if (tree == null) return found;
		if (tree == node0) found.foundNode0 = true;
		if (tree == node1) found.foundNode1 = true;

		Structure leftSubtree = foundBothSameSubtree(tree.left, node0, node1, found);
		Structure rightSubtree = foundBothSameSubtree(tree.right, node0, node1, found);


		return new Structure(leftSubtree.foundNode0 || rightSubtree.foundNode0, leftSubtree.foundNode1 || rightSubtree.foundNode1);
	}

	@EpiTest(testDataFile = "lowest_common_ancestor.tsv")
	public static int lcaWrapper(TimedExecutor executor,
								 BinaryTreeNode<Integer> tree, Integer key0,
								 Integer key1) throws Exception {
		BinaryTreeNode<Integer> node0 = BinaryTreeUtils.mustFindNode(tree, key0);
		BinaryTreeNode<Integer> node1 = BinaryTreeUtils.mustFindNode(tree, key1);

		BinaryTreeNode<Integer> result =
				executor.run(() -> lca(tree, node0, node1));

		if (result == null) {
			throw new TestFailure("Result can not be null");
		}
		return result.data;
	}

	public static void main(String[] args) {
		BinaryTreeNode<Integer> one = new BinaryTreeNode<>(314);
		BinaryTreeNode<Integer> two = new BinaryTreeNode<>(6);
		BinaryTreeNode<Integer> three = new BinaryTreeNode<>(6);
		BinaryTreeNode<Integer> four = new BinaryTreeNode<>(271);
		BinaryTreeNode<Integer> five = new BinaryTreeNode<>(561);
		BinaryTreeNode<Integer> six = new BinaryTreeNode<>(2);
		BinaryTreeNode<Integer> seven = new BinaryTreeNode<>(271);
		BinaryTreeNode<Integer> eight = new BinaryTreeNode<>(28);
		BinaryTreeNode<Integer> nine = new BinaryTreeNode<>(0);
		BinaryTreeNode<Integer> ten = new BinaryTreeNode<>(3);
		BinaryTreeNode<Integer> eleven = new BinaryTreeNode<>(1);

		BinaryTreeNode<Integer> twelve = new BinaryTreeNode<>(28);
		BinaryTreeNode<Integer> thirteen = new BinaryTreeNode<>(17);
		BinaryTreeNode<Integer> fourteen = new BinaryTreeNode<>(401);
		BinaryTreeNode<Integer> fifteen = new BinaryTreeNode<>(257);
		BinaryTreeNode<Integer> sixteen = new BinaryTreeNode<>(641);

		one.left = two;
		one.right = three;
		two.left = four;
		two.right = five;
		three.left = six;
		three.right = seven;
		four.left = eight;
		four.right = nine;
		five.right = ten;
		six.right = eleven;
		seven.right = twelve;
		ten.left = thirteen;
		eleven.left = fourteen;
		eleven.right = fifteen;
		fourteen.right = sixteen;

		lca(one, fifteen, sixteen);


		System.exit(
				GenericTest
						.runFromAnnotations(args, "LowestCommonAncestor.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
