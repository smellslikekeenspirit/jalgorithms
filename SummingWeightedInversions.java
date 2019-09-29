import java.util.Scanner;

class Pair {
    public long sum;
    public long[] arr;

    //a pair object contains a sum of weighted inversions
    //and an array of long numbers
    public Pair(long sum, long[] arr) {
        this.sum = sum;
        this.arr = arr;
    }
}
public class SummingWeightedInversions {

    //merges two arrays such that they are sorted
    public static long[] merge(long[] arr, long[] left, long[] right, long leftLength, long rightLength) {

        int i = 0, j = 0, k = 0;
        //while we still have elements to iterate over
        while (i < leftLength && j < rightLength) {
            if (left[i] <= right[j]) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }
        //while we still have elements to iterate over

        while (i < leftLength) {
            arr[k++] = left[i++];
        }
        //while we still have elements to iterate over

        while (j < rightLength) {
            arr[k++] = right[j++];
        }
        return arr;
    }

    // sums weights of midpoint inversions
    public static long sumMidInversions(long[] left, long[] right) {
        long leftSum = 0;
        long sum = 0;
        int i = 0;
        int j = 0;

        //sum all elements in left
        for (int m = 0; m < left.length; m++){
            leftSum += left[m];
        }

        while (left.length > i && right.length > j){
            if (left[i] < right[j]){
                // subtract elements from sum that are not part of inversions
                leftSum -= left[i];
                i++;

            }else{
                // sum is the sum of left elements that are inversions,
                // minus the right element we are at times the number of inversions
                sum += Math.abs(leftSum- ((left.length-i) * right[j]) );

                j++;
            }
        }
        return sum;
    }

    //sums weighted inversions of a given array of long numbers
    public static Pair sumInversions(long[] numbers){
        if (numbers.length == 1){
            return new Pair(0, numbers);
        }
        int leftNum = Math.floorDiv(numbers.length,2);
        int rightNum = numbers.length - Math.floorDiv(numbers.length,2);
        long[] left = new long[leftNum];
        long[] right = new long[rightNum];

        //populating left array
        for (int i = 0; i < leftNum; i++){
            left[i] = numbers[i];

        }
        //populating right array
        for (int i = 0; i < rightNum; i++){

            right[i]= numbers[leftNum+i];

        }

        Pair leftPair = sumInversions(left);
        Pair rightPair = sumInversions(right);

        long midSum = sumMidInversions(leftPair.arr, rightPair.arr);

        return new Pair(leftPair.sum+rightPair.sum+ midSum,
                merge(numbers, leftPair.arr, rightPair.arr, leftNum, rightNum));
    }

    //returns the sum part of the Pair object
    public static long sum(Pair pair){
        return pair.sum;
    }

    //main method runs the entire program
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n =  Integer.parseInt(scanner.nextLine());
        long[] numbers = new long[n];
        String[] s = scanner.nextLine().split(" ");
        //populates the numbers array
        for (int i = 0; i<n;i++){
            numbers[i] = Integer.parseInt(s[i]);
        }
        System.out.println(sum(sumInversions(numbers)));
    }
}
