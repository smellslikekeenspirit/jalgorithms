import java.util.Scanner;
import org.checkerframework.common.value.qual.*;
import org.checkerframework.checker.index.qual.*;

public class LongestConvexSubseq{

    @SuppressWarnings({"cast.unsafe" , "expression.unparsable.type.invalid", "array.access.unsafe.high"})
    static int lcsDP(int[] numbers, @Positive int n) {

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

                    if (convexSubseqLengths[( @IndexFor("convexSubseqLengths") int) j][( @IndexFor("convexSubseqLengths") int) k] + 1 > convexSubseqLengths[( @IndexFor("convexSubseqLengths") int)i][( @IndexFor("convexSubseqLengths") int)j] && ((numbers[( @IndexFor("numbers") int)i] + numbers[( @IndexFor("numbers") int)k]) >= 2 * (numbers[( @IndexFor("numbers") int)j]))){
                        convexSubseqLengths[( @IndexFor("convexSubseqLengths") int) i][( @IndexFor("convexSubseqLengths") int ) j] = convexSubseqLengths[( @IndexFor("convexSubseqLengths") int) j][( @IndexFor("convexSubseqLengths") int) k] + 1;
                    }


                }
                longest = Math.max(longest, convexSubseqLengths[i][j]);
            }


            i++;
        }
        return longest;

    }

    @SuppressWarnings({"cast.unsafe" , "expression.unparsable.type.invalid", "array.access.unsafe.high"})
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n;
        n = scanner.nextInt();
        if (n > 0) {
            @Positive int size = n;
            int numbers[] = new int[size];
            int inp;
            for (int i = 0; i < size; i++) {
                inp = scanner.nextInt();
                numbers[ (@IndexFor("numbers") int) i] = inp;
            }
            int result;
            result = lcsDP(numbers, size);
    
            System.out.println(result);
        }
        else {
            System.out.println("Please enter a positive integer for size.");
        }   
    }
}
