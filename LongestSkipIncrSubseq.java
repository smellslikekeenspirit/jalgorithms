import java.util.Scanner;

public class LongestSkipIncrSubseq {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();

        // Scan in the intervals
        long numbers[] = new long[n+1];
        numbers[0] = 0;
        for (int i = 1; i < n+1; i++) {
            numbers[i] = s.nextLong();
        }

        long table[] = new long[n+1];
        table[0] = 0;

        long count[] = new long[n+1];

        for (int i = 1; i < n+1; i++) {
            for(int j = i; j > 0; j--) {
                if (i - j == 1 && numbers[i] > numbers[j] && table[j] > table[i]) {
                    if (count[j] < 1) {
                        table[i] = table[j];
                        count[i] = 1;
                    }
                }
                else if (numbers[i] > numbers[j] && table[j] >= table[i]) {
                    table[i] = table[j];
                    count[i] = 0;
                }
            }
            // account for itself
            table[i]++;
        }

        long max = 0;
        for (int i = 1; i < n+1; i++) {
            if (table[i] > max) {
                max = table[i];
            }
        }

        System.out.println(max);
    }
}