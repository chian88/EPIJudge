package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
public class IntAsArrayIncrement {
  @EpiTest(testDataFile = "int_as_array_increment.tsv")
  public static List<Integer> plusOne(List<Integer> A) {
    // TODO - you fill in here.
    int carry = 0;
    boolean firstTime = true;
    for (int i = A.size() - 1; i >= 0; i--) {
      int res = A.get(i);
      if (firstTime) {
        res += 1;
      }

      res += carry;

      A.set(i, res % 10);

      if (res >= 10) {
        carry = 1;
      } else {
        carry = 0;
      }

      firstTime = false;
    }

    if (carry == 1) {
      A.add(0, 1);
    }

    return A;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntAsArrayIncrement.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
