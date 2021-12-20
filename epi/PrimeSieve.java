package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PrimeSieve {
	@EpiTest(testDataFile = "prime_sieve.tsv")
	// Given n, return all primes up to and including n.
	public static List<Integer> generatePrimes(int n) {
		// TODO - you fill in here.
		boolean[] check = new boolean[n];
		Arrays.fill(check, true);
		check[0] = false;
		List<Integer> res = new ArrayList<>();

		for (int i = 1; i < n; i++) {
      		if(check[i]) {
      			int prime = i+1;
      			if (isPrime(prime)) {
					res.add(prime);
				}
      			int multiple = prime;
      			while (multiple <= n) {
      				check[multiple - 1] = false;
					multiple += prime;
				}
			}
		}
		return res;
	}



	static boolean isPrime(int n) {
		for (int i = 2; i < Math.sqrt(n); i++) {
			if (n % i == 0) return false;
		}
		return true;
	}

	public static void main(String[] args) {
		generatePrimes(2);
		System.exit(
				GenericTest
						.runFromAnnotations(args, "PrimeSieve.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
