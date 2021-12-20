package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.*;

public class GroupEqualEntries {
	@EpiUserType(ctorParams = {Integer.class, String.class})

	public static class Person {
		public Integer age;
		public String name;

		public Person(Integer k, String n) {
			age = k;
			name = n;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (o == null || getClass() != o.getClass())
				return false;

			Person person = (Person) o;

			if (!age.equals(person.age))
				return false;
			return name.equals(person.name);
		}

		@Override
		public int hashCode() {
			int result = age.hashCode();
			result = 31 * result + name.hashCode();
			return result;
		}
	}

	public static void groupByAge(List<Person> people) {
		// TODO - you fill in here.
		Map<Integer, Integer> ageCount = new HashMap<>();

		for (Person p : people) {
			ageCount.put(p.age, ageCount.getOrDefault(p.age, 0) + 1);
		}

		int offset = 0;

		Map<Integer, Integer> ageOffset = new HashMap<>();


		Iterator<Map.Entry<Integer, Integer>> iter = ageCount.entrySet().iterator();

		while (iter.hasNext()) {
			Map.Entry<Integer, Integer> entry = iter.next();
			ageOffset.put(entry.getKey(), offset);
			offset += entry.getValue();
		}

		while (!ageOffset.isEmpty()) {
			Map.Entry<Integer, Integer> from = ageOffset.entrySet().iterator().next();

			int ageStuckInOriginal = people.get(from.getValue()).age;
			int	ageShouldGoTo = ageOffset.get(ageStuckInOriginal);

			Collections.swap(people, from.getValue(), ageShouldGoTo);

			int count = ageCount.get(ageStuckInOriginal) - 1;
			ageCount.put(ageStuckInOriginal, count);

			if (count > 0) {
				ageOffset.put(ageStuckInOriginal, ageOffset.get(ageStuckInOriginal) + 1);
			} else {
				ageOffset.remove(ageStuckInOriginal);
			}

		}


		return;
	}

	private static Map<Person, Integer> buildMultiset(List<Person> people) {
		Map<Person, Integer> m = new HashMap<>();
		for (Person p : people) {
			m.put(p, m.getOrDefault(p, 0) + 1);
		}
		return m;
	}

	@EpiTest(testDataFile = "group_equal_entries.tsv")
	public static void groupByAgeWrapper(TimedExecutor executor,
										 List<Person> people) throws Exception {
		if (people.isEmpty()) {
			return;
		}
		Map<Person, Integer> values = buildMultiset(people);

		executor.run(() -> groupByAge(people));

		Map<Person, Integer> newValues = buildMultiset(people);
		if (!values.equals(newValues)) {
			throw new TestFailure("Entry set changed");
		}

		Set<Integer> ages = new HashSet<>();
		int lastAge = people.get(0).age;
		for (Person p : people) {
			if (ages.contains(p.age)) {
				throw new TestFailure("Entries are not grouped by age");
			}
			if (p.age != lastAge) {
				ages.add(lastAge);
				lastAge = p.age;
			}
		}
	}

	public static void main(String[] args) {

		Person a = new Person(12, "A");
		Person b = new Person(14, "B");
		Person c = new Person(15, "C");
		Person d = new Person(12, "D");
		Person e = new Person(14, "E");
		Person f = new Person(14, "F");
		groupByAge(Arrays.asList(a,b,c,d,e,f));
		System.exit(
				GenericTest
						.runFromAnnotations(args, "GroupEqualEntries.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
