import java.util.Scanner;

public class optimizedRadixSort {

    //calculates the maximum number among the numbers
    //in the array
    public static long max(long[] arr, int n){
        long max = arr[0];

        for(int i = 1; i < n; i++){
            if(arr[i] > max){
                max = arr[i];
            }
        }

        return max;
    }

    //sorts the given array by the last digit
    public static void countsort(long[] arr, int n, long d){
        long[] output = new long[n];
        long[] count = new long[n];

        for(int i = 0; i < n; i++){
            count[(int) (arr[i]/d % n)] += 1;
        }

        for(int i = 1; i <= n-1; i++){
            count[i] += count[i-1];
        }

        for(int i = n-1; i >= 0; i--){
            output[(int) (count[(int) (arr[i]/d % n)]-1)] = arr[i];
            count[(int) (arr[i]/d % n)] -= 1;
        }

        for(int i = 0; i < n; i++){
            arr[i] = output[i];
        }
    }

    //sorts the array using counting sort for all digits
    //so that the resulting array is sorted
    public static long radixsort(long[] arr, int n){
        long max = max(arr, n);

        for(int d = 1; max/d > 0; d *= n){
            countsort(arr, n, d);
        }
        long sum = 0;
        for (int i = 0; i<n; i++){
            if(arr[i]%3 == 0){
                sum += i+1;
            }
        }
        return sum;

    }


    //main method runs the entire program
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n =  Integer.parseInt(scanner.nextLine());
        long upperBound = (long) (Math.pow(n,2)-1);
        long[] numbers = new long[n];
        String[] s = scanner.nextLine().split(" ");
        for (int i = 0; i<n;i++){
            numbers[i] = Long.parseLong(s[i]);
        }
        System.out.println(radixsort(numbers, n));



    }


}
