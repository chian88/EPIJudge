package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.*;
public class IntAsArrayMultiply {
  @EpiTest(testDataFile = "int_as_array_multiply.tsv")
  public static List<Integer> multiply(List<Integer> num1, List<Integer> num2) {

    final int sign = num1.get(0) < 0 ^ num2.get(0) < 0 ? -1 : 1;
    num1.set(0, Math.abs(num1.get(0)));
    num2.set(0, Math.abs(num2.get(0)));

    List<Integer> result =
            new ArrayList<>(Collections.nCopies(num1.size() + num2.size(), 0));
    for (int i = num1.size() - 1; i >= 0; --i) {
      for (int j = num2.size() - 1; j >= 0; --j) {
        result.set(i + j + 1,
                result.get(i + j + 1) + num1.get(i) * num2.get(j));
        result.set(i + j, result.get(i + j) + result.get(i + j + 1) / 10);
        result.set(i + j + 1, result.get(i + j + 1) % 10);
      }
    }

    // Remove the leading zeroes.
    int firstNotZero = 0;
    while (firstNotZero < result.size() && result.get(firstNotZero) == 0) {
      ++firstNotZero;
    }
    result = result.subList(firstNotZero, result.size());
    if (result.isEmpty()) {
      return List.of(0);
    }
    result.set(0, result.get(0) * sign);
    return result;
  }

//  public static List<Integer> multiply(List<Integer> num1, List<Integer> num2) {
//    // TODO - you fill in here.
//    int offset = 0;
//    List<Integer> total = new ArrayList<>();
//    for (int i = num2.size() - 1; i >= 0 ; i--) {
//      int carry = 0;
//      List<Integer> res = new ArrayList<>();
//
//      for (int count = 0; count < offset; count++) {
//        res.add(0);
//      }
//
//
//      for (int j = num1.size() - 1; j >= 0; j--) {
//        int a = Math.abs(num1.get(j));
//        int b = Math.abs(num2.get(i));
//        int result = (a * b) + carry;
//        res.add(0, result % 10);
//        carry = result / 10;
//      }
//
//      if (carry > 0) res.add(0, carry);
//      offset += 1;
//
//      total = add(total, res);
//    }
//
//    while (total.size() > 1 && total.get(0) == 0) {
//      total.remove(0);
//    }
//
//    if (num1.get(0) < 0 && num2.get(0) >= 0) {
//      total.set(0, total.get(0) * -1);
//    } else if (num1.get(0) >=0 && num2.get(0) < 0) {
//      total.set(0, total.get(0) * -1);
//    }
//    return total;
//  }
//
//  static List<Integer> add(List<Integer> num1, List<Integer> num2) {
//    int ptr1 = num1.size() - 1;
//    int ptr2 = num2.size() - 1;
//
//    List<Integer> result = new ArrayList<>();
//    int carry = 0;
//    while (ptr1 >= 0 && ptr2 >= 0) {
//      int res = num1.get(ptr1) + num2.get(ptr2) + carry;
//      carry = res / 10;
//
//
//      result.add(0, res % 10);
//      ptr1--;
//      ptr2--;
//    }
//
//    while (ptr1 >= 0) {
//      int res = carry + num1.get(ptr1);
//      carry = res / 10;
//      result.add(0, res % 10);
//      ptr1--;
//    }
//
//    while (ptr2 >= 0) {
//      int res = carry + num2.get(ptr2);
//      carry = res / 10;
//      result.add(0, res % 10);
//      ptr2--;
//    }
//
//    if (carry > 0) {
//      result.add(0, carry);
//    }
//
//
//    return result;
//  }

  public static void main(String[] args) {

    List<Integer> num1 = new ArrayList<>(Arrays.asList(new Integer[]{1,9,3}));
    List<Integer> num2 = new ArrayList<>(Arrays.asList(new Integer[]{2,5,6}));
    multiply(num1, num2);
//    System.exit(
//        GenericTest
//            .runFromAnnotations(args, "IntAsArrayMultiply.java",
//                                new Object() {}.getClass().getEnclosingClass())
//            .ordinal());
  }
}
