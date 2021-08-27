package src;
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

        List<int[]> missingTriples = Tests.getMissing(baseMethodArr, freemanCoprimeArr);
        List<int[]> testing = Tests.getMissing(missingTriples, freemanCoprimeArr);

        printList(testing, "Missing Triples:");

        System.out.println("Base method size: " + baseMethodArr.size());
        System.out.println("Freeman method size: " + freemanMethodArr.size());
        System.out.println("Coprime method size: " + coprimeMethodArr.size());
        System.out.println("Freeman + Coprime size: " + freemanCoprimeArr.size());
        System.out.println("Number of missing answers: " + missingTriples.size());
        
        System.out.println();
        
        System.out.println("Checking right angles (base): " + Tests.checkRightAngles(baseMethodArr));
        System.out.println("Checking right angles (Freeman): " + Tests.checkRightAngles(freemanMethodArr));
        System.out.println("Checking right angles (coprime): " + Tests.checkRightAngles(coprimeMethodArr));
        System.out.println("Checking right angles (missing): " + Tests.checkRightAngles(missingTriples));
        System.out.println("Checking repeats (base): " + Tests.checkRepeats(baseMethodArr).size());
        System.out.println("Checking repeats (Freeman): " + Tests.checkRepeats(freemanMethodArr).size());

        // List<int[]> repeats = Tests.checkRepeats(coprimeMethodArr);
        System.out.println("Checking repeats (coprime): " + Tests.checkRepeats(coprimeMethodArr).size());

        // printList(repeats, "Repeats");
    }
    public static void main(String[] args) {
        printResults(1000);
    }
}
