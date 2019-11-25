/**
 * Prionti Nasir
 * pdn3628@rit.edu
 */

import java.util.Scanner;

public class LongestIncreasingSubseqRecursive {

    /**
     * function that recursively computes the maximum
     * length of an increasing subsequence from the array
     * @param j index of last element to recurse down from
     * @param A array of ints in the sequence
     * @return maximum w.r.t jth element
     */
    public static int incrSubseqRecursive(int j, int[] A)
    {
        if(j == 0)
            return 1;
        int max = 0;
        for(int i=0; i<j; i++)
        {
            if(A[i]<A[j]){
                int iSubseq = incrSubseqRecursive(i, A);
                if(iSubseq>max){
                    max = iSubseq;
                }
            }
        }
        return max+1;
    }

    /**
     * main method calls recursive function to
     * compute a final max and prints it
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] numbers = new int[n];
        for(int i=0; i<n; i++){
            numbers[i] = scanner.nextInt();
        }
        int[] maxima = new int[n];
        int max = 0;

        for(int i=0; i<n; i++) {
            maxima[i] = incrSubseqRecursive(i, numbers);
            if(maxima[i]>max){
                max = maxima[i];
            }
        }
        System.out.println(max);

    }
}