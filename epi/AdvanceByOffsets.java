package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;

public class AdvanceByOffsets {
    @EpiTest(testDataFile = "advance_by_offsets.tsv")
    public static boolean canReachEnd(List<Integer> maxAdvanceSteps) {
        int furthest = 0, lastIndex = maxAdvanceSteps.size() - 1;

        for (int i = 0; i <= furthest && furthest < lastIndex; ++i) {
            furthest = Math.max(furthest, i + maxAdvanceSteps.get(i));
        }

        return furthest >= lastIndex;
    }



    public static void main(String[] args) {
        List<Integer> input = new ArrayList<>(Arrays.asList(new Integer[]{3,3,1,0,2,0,1}));

        canReachEnd(input);
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "AdvanceByOffsets.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
