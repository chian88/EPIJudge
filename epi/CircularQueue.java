package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

import java.util.List;
import java.util.*;

public class CircularQueue {
	private static int head = 0, tail = 0, numQueuElements = 0;
	private static final int SCALE_FACTOR = 2;
	private static Integer[] entries;

	public static class Queue {
		public Queue(int capacity) {

			entries = new Integer[capacity];
		}

		public void enqueue(Integer x) {
			// TODO - you fill in here.
			if (numQueuElements == entries.length) {
				Collections.rotate(Arrays.asList(entries), -head);

				head = 0;
				tail = numQueuElements;
				entries = Arrays.copyOf(entries, numQueuElements * SCALE_FACTOR);
			}

			entries[tail] = x;
			tail = (tail + 1) % entries.length;
			++numQueuElements;

			return;
		}

		public Integer dequeue() {
			// TODO - you fill in here.
			--numQueuElements;
			Integer result = entries[head];
			head = (head + 1) % entries.length;
			return result;
		}

		public int size() {
			// TODO - you fill in here.
			return numQueuElements;
		}

		@Override
		public String toString() {
			// TODO - you fill in here.
			return super.toString();
		}
	}

	@EpiUserType(ctorParams = {String.class, int.class})
	public static class QueueOp {
		public String op;
		public int arg;

		public QueueOp(String op, int arg) {
			this.op = op;
			this.arg = arg;
		}

		@Override
		public String toString() {
			return op;
		}
	}

	@EpiTest(testDataFile = "circular_queue.tsv")
	public static void queueTester(List<QueueOp> ops) throws TestFailure {
		Queue q = new Queue(1);
		int opIdx = 0;
		for (QueueOp op : ops) {
			switch (op.op) {
				case "Queue":
					q = new Queue(op.arg);
					break;
				case "enqueue":
					q.enqueue(op.arg);
					break;
				case "dequeue":
					int result = q.dequeue();
					if (result != op.arg) {
						throw new TestFailure()
								.withProperty(TestFailure.PropertyName.STATE, q)
								.withProperty(TestFailure.PropertyName.COMMAND, op)
								.withMismatchInfo(opIdx, op.arg, result);
					}
					break;
				case "size":
					int s = q.size();
					if (s != op.arg) {
						throw new TestFailure()
								.withProperty(TestFailure.PropertyName.STATE, q)
								.withProperty(TestFailure.PropertyName.COMMAND, op)
								.withMismatchInfo(opIdx, op.arg, s);
					}
					break;
			}
			opIdx++;
		}
	}

	public static void main(String[] args) {
		CircularQueue.Queue q = new Queue(3);

		q.enqueue(1);
		q.enqueue(2);
		q.enqueue(3);
		q.dequeue();
		q.dequeue();
		q.dequeue();
		q.dequeue();
		q.dequeue();
		q.enqueue(4);
		q.enqueue(5);
		q.enqueue(6);


		System.exit(
				GenericTest
						.runFromAnnotations(args, "CircularQueue.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
