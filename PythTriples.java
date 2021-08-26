import java.util.*;
import java.lang.Math;

public class PythTriples {

    static List<int[]> baseMethod(int stopNum) {
        /* 
            A method of generating Pythagorean triples to use as
            a reference for the new method
        */

        List<int[]> pythTripleList = new ArrayList<>();

        for (int i=1; i < stopNum; i++) {
            for (int j=i; j < stopNum; j++) {

                double hyp = Math.sqrt(i*i + j*j);

                if (hyp == Math.floor(hyp)) {
                    int[] sideLengths = new int[] {i, j, (int) hyp};

                    pythTripleList.add(sideLengths);
                }
            }
        }

        return(pythTripleList);
    }

    static List<int[]> coprimeMethod(int stopNum) {

        List<int[]> pythTripleList = new ArrayList<>();
        // int numPythTriples = 0;

        for (int d=2; d < stopNum; d += 2) {
            for (int k=3; k < Math.sqrt(d); k++) {
                
                if (d % k == 0) {

                    int a = k;
                    int b = d / k;
                    

                    while (b != 0) {

                        if (a > b) {
                            a -= b;
                        } else {
                            b -= a;
                        }
                    }

                    if (a == 1) {
                        int c = d / k;

                        int leg1;
                        int leg2;

                        if (k % 2 == 0) {
                            leg1 = d + k*k / 2;
                            leg2 = d + c*c;
                        } else {
                            leg1 = d + k*k;
                            leg2 = d + c*c / 2;
                        }

                        if (leg1 < stopNum && leg2 < stopNum) {
                            int hyp = (int) Math.sqrt(leg1*leg1 + leg2*leg2);

                            pythTripleList.add(new int[] {leg1, leg2, hyp});
                        }
                    }
                }
            }

        }

        // pythTripleList.addAll(coprimeMultiples(pythTripleList, stopNum));

        return(pythTripleList);
        // return(numPythTriples);
    }

    static List<int[]> coprimeMultiples(List<int[]> coprimeList, int stopNum) {
        List<int[]> multipleList = new ArrayList<>();

        for (int[] set : coprimeList) {
            for (int i=1; i < Math.floor(stopNum / Math.max(set[0], set[1]) + 1); i++) {
                multipleList.add(new int[] {set[0] * i, set[1] * i, set[2] * i});
            }
        }

        return(multipleList);
    }

    static List<int[]> freemanMethod(int stopNum) {

        List<int[]> pythTripleList = new ArrayList<>();

        for (int d=2; d < stopNum; d += 2) {
            for (int i=1; i < d / 2 + 1; i++) {

                if (d / i == Math.floor(d / i)) {

                    for (int j=1; j < stopNum; j++) {

                        int leg1 = d + i;
                        int leg2 = d + j;
                        // double hyp = d + i + j;
                        double hyp = d + i + j;

                        if ((leg1 * leg1 + leg2 * leg2 == hyp * hyp) 
                            && (leg1 < stopNum) && (leg2 < stopNum)) {
                            
                            int[] sideLengths = new int[] {leg1, leg2, (int) hyp};
                            
                            pythTripleList.add(sideLengths);
                        }
                    }
                }
            }
        }

        return(pythTripleList);
    }

    // Test Functions
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
                        if (originalSide == testSide) { count++; }
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

        for (int i=0; i < list1.size(); i++) {
            Boolean passed = false;
            int[] comparisonList = list1.get(i);

            for (int j=0; j < list2.size(); j++) {

                for (int comparisonSide : comparisonList) {
                    int count = 0;

                    for (int testSide : list2.get(j)) {

                        if (comparisonSide == testSide) { 
                            count++;
                            
                            if (count == 3) {
                                passed = true;
                            }
                        }
                    }

                    count = 0;
                }
            }

            if (passed) {
                missingTriples.add(comparisonList);
            }
        }

        return(missingTriples);
    }

    static void printList(List<int[]> list) {

        for (int[] set : list) {
            System.out.println("(" + set[0] + ", " + set[1] + ", " + set[2] + ")");
        }

        System.out.println();
    }

    static void printList(List<int[]> list, String name) {
        System.out.println(name);

        printList(list);
    }

    static void printResults(int stopNum) {

        List<int[]> baseMethodArr = baseMethod(stopNum);
        List<int[]> freemanMethodArr = freemanMethod(stopNum);
        List<int[]> coprimeMethodArr = coprimeMethod(stopNum);

        List<int[]> freemanCoprimeArr = new ArrayList<int[]>(freemanMethodArr);
        freemanCoprimeArr.addAll(coprimeMethodArr);

        List<int[]> missingTriples = getMissing(freemanCoprimeArr, baseMethodArr);
        
        // printList(coprimeMethodArr, "Coprime Array");

        System.out.println("Base method size: " + baseMethodArr.size());
        System.out.println("Freeman method size: " + freemanMethodArr.size());
        System.out.println("Coprime method size: " + coprimeMethodArr.size());
        System.out.println("Freeman + Coprime size: " + freemanCoprimeArr.size());
        System.out.println("Number of missing answers: " + missingTriples.size());
        System.out.println();
        System.out.println("Checking right angles (base): " + checkRightAngles(baseMethodArr));
        System.out.println("Checking right angels (Freeman): " + checkRightAngles(freemanMethodArr));
        System.out.println("Checking right angels (coprime): " + checkRightAngles(coprimeMethodArr));
        System.out.println("Checking right angles (missing): " + checkRightAngles(missingTriples));
        System.out.println("Checking repeats (base): " + checkRepeats(baseMethodArr).size());
        System.out.println("Checking repeats (Freeman): " + checkRepeats(freemanMethodArr).size());
        List<int[]> repeats = checkRepeats(coprimeMethodArr);
        System.out.println("Checking repeats (coprime): " + repeats.size());

        printList(repeats, "Repeats");
    }
    public static void main(String[] args) {
        // printResults(100);
        List<int[]> list1 = new ArrayList<>();
        List<int[]> list2 = new ArrayList<>();

        list1.add(new int[] {1, 2, 3});
        list1.add(new int[] {4, 5, 6});
        list1.add(new int[] {7, 8, 9});

        list2.add(new int[] {2, 3, 1});
        list2.add(new int[] {33, 69, 420});
        list2.add(new int[] {7, 8, 9});

        List<int[]> results = getMissing(list1, list2);

        printList(results, "Results: ");
    }
}
