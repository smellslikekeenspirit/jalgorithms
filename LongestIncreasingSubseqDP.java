/**
 * Prionti Nasir
 * pdn3628@rit.edu
 */

import java.util.Scanner;
import org.checkerframework.common.value.qual.*;
import org.checkerframework.checker.index.qual.*;

public class LongestIncreasingSubseqDP {

    private int[] subseq;
    private int[] solution;

    /**
     * constructor for the class
     * @param numbers input numbers
     * @param size size of array
     */
    public LongestIncreasingSubseqDP(int[] numbers, @Positive int size ) {
        this.subseq = numbers;
        this.solution = new int[size];
    }

    /**
     * main method which handles memory allotting to carry
     * out a DP program
     * @param args unused
     */
    @SuppressWarnings({"cast.unsafe" , "expression.unparsable.type.invalid", "array.access.unsafe.high"})
    public static void main(String[] args) {
        Scanner scanner = new Scanner( System.in );
        int n = Integer.parseInt(scanner.next());
        if (n > 0) {
            @Positive int index = n;
            int @MinLen(1)[] numbers = new int [index];
            for(int i = 0 ; i < numbers.length ; i++ ) {
                int number = scanner.nextInt();
                numbers[ (@IndexFor("numbers") int) i] = number;
            }
    
            LongestIncreasingSubseqDP lisDP = new LongestIncreasingSubseqDP(numbers, n);
    
            //keeping track of older solutions to "memoize"
            for(int i = 0 ; i < index ; i++ ) {
                lisDP.solution[(@IndexFor("lisDP.solution") int) i] = 1;
                for( int j = 0 ; j < i ; j++ ) {
                    if( (lisDP.subseq[(@IndexFor("lisDP.subseq") int) j] < lisDP.subseq[(@IndexFor("lisDP.subseq") int) i] ) && (lisDP.solution[(@IndexFor("lisDP.subseq") int) i] < lisDP.solution[(@IndexFor("lisDP.subseq") int) j]+1 ) ) {
                        lisDP.solution[ (@IndexFor("lisDP.solution") int) i] = lisDP.solution[(@IndexFor("lisDP.solution") int) j]+1;
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
        else {
            System.out.println("The size of input must be a positive integer.");
        }
    }
}
