package src;
import java.util.*;
// import java.lang.Math;

public class Tests {

    static int checkRightAngles(List<int[]> pythTripleList) {
        /* 
        Using Law of Cosines to check if a list of sets of side lengths
        are right angles
        */

        int numFailed = 0;

        for (int i=0; i < pythTripleList.size(); i++) {
            int[] sideLengthSet = pythTripleList.get(i);

            int a = sideLengthSet[0];
            int b = sideLengthSet[1];
            int c = sideLengthSet[2];

            // System.out.println(a + ", "  + b + ", " + c);

            if ((a*b*c > 0) && (a*a != b*b + c*c && b*b != a*a + c*c && c*c != a*a + b*b)) {
                System.out.println(a + " " + b + " " + c);
                numFailed++;
            }
        }

        return(numFailed);
    }

    static List<int[]> checkRepeats(List<int[]> pythTripleList) {

        List<int[]> repeatList = new ArrayList<>();

        for (int i=0; i < pythTripleList.size(); i++) {
            int[] originalSet = pythTripleList.get(i);

            for (int j=i+1; j < pythTripleList.size(); j++) {
                int[] testSet = pythTripleList.get(j);

                int count = 0;
                for (int originalSide : originalSet) {
                    for (int testSide : testSet) {
                        if (originalSide == testSide) { 
                            count++; 
                        }
                    }
                }

                if (count == 3) {
                    repeatList.add(testSet);
                }
            }
        }

        return(repeatList);
    }

    static List<int[]> getMissing(List<int[]> list1, List<int[]> list2) {
        /*
        Function that returns all the arrays of list1 that are not in list2
        */
        List<int[]> missingTriples = new ArrayList<>();
        // int numFailed = 0;

        for (int i=0; i < list1.size(); i++) {
            Boolean passed = false;
            int[] comparisonList = list1.get(i);

            for (int j=0; j < list2.size(); j++) {
                int count = 0;
                for (int comparisonSide : comparisonList) {
                    

                    for (int testSide : list2.get(j)) {

                        if (comparisonSide == testSide) { 
                            count++;
                            
                            if (count == 3) {
                                passed = true;
                            }
                        }
                    }
                }
            }

            if (!passed) {
                // numFailed++;
                missingTriples.add(comparisonList);
            }
        }  

        return(missingTriples);
    }

    public static void main(String[] args) {
        List<int[]> list1 = new ArrayList<>();
        // List<int[]> list2 = new ArrayList<>();

        list1.add(new int[] {5, 7, 10});
        list1.add(new int[] {3, 4, 5});
        list1.add(new int[] {5, 8, 9});


        System.out.println("Number failed: " + checkRightAngles(list1));
        // System.out.println();
        // System.out.println("Results: ");
        // for (int[] set : results) {
        //     System.out.println("(" + set[0] + ", " + set[1] + ", " + set[2] + ")");
        // }
        // System.out.println();

        // printList(results, "Results: ");
    }
}
