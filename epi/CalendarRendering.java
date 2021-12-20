package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CalendarRendering {
	@EpiUserType(ctorParams = {int.class, int.class})

	public static class Event {
		public int start, finish;

		public Event(int start, int finish) {
			this.start = start;
			this.finish = finish;
		}
	}

	private static class Endpoint {
		public int time;
		public boolean isStart;

		Endpoint(int time, boolean isStart) {
			this.time = time;
			this.isStart = isStart;
		}
	}

	@EpiTest(testDataFile = "calendar_rendering.tsv")

	public static int findMaxSimultaneousEvents(List<Event> A) {
		// TODO - you fill in here.
		List<Endpoint> endpoints = new ArrayList<>();

		for (int i = 0; i < A.size(); i++) {
			endpoints.add(new Endpoint(A.get(i).start, true));
			endpoints.add(new Endpoint(A.get(i).finish, false));
		}

		endpoints.sort( (a, b) -> {
			if (a.time != b.time) {
				return Integer.compare(a.time, b.time);
			}

			return a.isStart && !b.isStart ? -1 : !a.isStart && b.isStart ? 1 : 0;
		});

		int session = 0;
		int maxSession = 0;

		for (Endpoint endpoint : endpoints) {
			if (endpoint.isStart) {
				session += 1;
			} else {
				session -= 1;
			}

			if (session > maxSession) {
				maxSession = session;
			}
		}

		return maxSession;
	}

	public static void main(String[] args) {
		System.exit(
				GenericTest
						.runFromAnnotations(args, "CalendarRendering.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
