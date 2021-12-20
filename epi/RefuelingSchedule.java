package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.List;

public class RefuelingSchedule {
	private static final int MPG = 20;

	// gallons[i] is the amount of gas in city i, and distances[i] is the distance
	// city i to the next city.
	public static int findAmpleCity(List<Integer> gallons,
									List<Integer> distances) {
		// TODO - you fill in here.
		int lowestDistance = 0;
		int cityIdx = 0;
		int cumulativeDistance = 0;


		for (int i = 0; i < gallons.size(); i++) {
			if (cumulativeDistance < lowestDistance) {
				lowestDistance = cumulativeDistance;
				cityIdx = i;
			}

			cumulativeDistance += gallons.get(i) * 20;
			cumulativeDistance -= distances.get(i);
		}

		if (cumulativeDistance < lowestDistance) {
			lowestDistance = cumulativeDistance;
			cityIdx = gallons.size() - 1;
		}


		return cityIdx;
	}

	@EpiTest(testDataFile = "refueling_schedule.tsv")
	public static void findAmpleCityWrapper(TimedExecutor executor,
											List<Integer> gallons,
											List<Integer> distances)
			throws Exception {
		int result = executor.run(() -> findAmpleCity(gallons, distances));
		final int numCities = gallons.size();
		int tank = 0;
		for (int i = 0; i < numCities; ++i) {
			int city = (result + i) % numCities;
			tank += gallons.get(city) * MPG - distances.get(city);
			if (tank < 0) {
				throw new TestFailure(String.format("Out of gas on city %d", city));
			}
		}
	}

	public static void main(String[] args) {
		System.exit(
				GenericTest
						.runFromAnnotations(args, "RefuelingSchedule.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
