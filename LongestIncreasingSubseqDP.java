/**
 * Prionti Nasir
 * pdn3628@rit.edu
 */

import java.util.Scanner;

public class LongestIncreasingSubseqDP {

    private int[] subseq;
    private int[] solution;

    /**
     * constructor for the class
     * @param numbers input numbers
     * @param size size of array
     */
    public LongestIncreasingSubseqDP( int[] numbers, int size ) {
        this.subseq = numbers;
        this.solution = new int[size];
    }

    /**
     * main method which handles memory allotting to carry
     * out a DP program
     * @param args unused
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner( System.in );
        int n = Integer.parseInt(scanner.next());
        int[] numbers = new int[n];
        for( int i = 0 ; i < n ; i++ ) {
            int number = scanner.nextInt();
            numbers[i] = number;
        }

        LongestIncreasingSubseqDP lisDP = new LongestIncreasingSubseqDP(numbers, n);

        //keeping track of older solutions to "memoize"
        for( int i = 0 ; i < n ; i++ ) {
            lisDP.solution[i] = 1;
            for( int j = 0 ; j < i ; j++ ) {
                if( ( lisDP.subseq[j] < lisDP.subseq[i] ) && (lisDP.solution[i] < lisDP.solution[j]+1 ) ) {
                    lisDP.solution[i] = lisDP.solution[j]+1;
                }
            }
        }
        int max = 0;
        for( int localMax : lisDP.solution ) {
            if( localMax > max ) {
                max = localMax;
            }
        }
        System.out.println(max);
    }
}