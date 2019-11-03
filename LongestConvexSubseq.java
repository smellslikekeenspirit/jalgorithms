import java.util.Scanner;

public class LongestConvexSubseq{

   
    static int lcsDP(int[] numbers, int n) {

        if (numbers.length <= 2) {
            return numbers.length;
        }

        int convexSubseqLengths[][] = new int[n][n];
        int longest = 0;
        convexSubseqLengths[0][0] = 1;
        int i = 1;
        while (i < n) {
            for (int j = 0; j < n; j++) {
                convexSubseqLengths[i][j] = 2;
                for (int k = 0; k < j; k++) {

                    if (convexSubseqLengths[j][k] + 1 > convexSubseqLengths[i][j] && ((numbers[i] + numbers[k]) >= 2 * (numbers[j]))){
                        convexSubseqLengths[i][j] = convexSubseqLengths[j][k] + 1;
                    }


                }
                longest = Math.max(longest, convexSubseqLengths[i][j]);
            }


            i++;
        }
        return longest;

    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n;
        n = scanner.nextInt();

        int numbers[] = new int[n];
        int inp;
        for (int i = 0; i < n; i++) {
            inp = scanner.nextInt();
            numbers[i] = inp;
        }
        int result;
        result = lcsDP(numbers, n);

        System.out.println(result);
    }
}