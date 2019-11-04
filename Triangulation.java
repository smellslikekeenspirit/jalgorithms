import java.util.Scanner;

public class Triangulation{

    private static float computeDistance(float[] first, float[] second) {
        double xsquared = Math.pow(first[0] - second[0], 2);
        double ysquared = Math.pow(first[1] - second[1], 2);
        return (float) Math.sqrt(xsquared + ysquared);
    }


    private static float triangulation(float[][] coordinates, int n) {
        float[][] solution = new float[n][n];
        for (int i = 0; i < n; ++i) {
            int j = 0;
            for (int k = i; k < n; k++) {
                float length = computeDistance(coordinates[j], coordinates[k]);
                if (k < j + 2) {
                    solution[j][k] = 0;
                } else {
                    solution[j][k] = Float.MAX_VALUE;
                    //loops from j+1 to k
                    for (int s = j + 1; s < k; s++) {
                        solution[j][k] = Math.min(
                                solution[j][s] + solution[s][k] + length, solution[j][k]);
                    }
                    //prevents double counting
                    if ( k == n - 1 && j == 0) {
                        solution[j][k] -= length;
                    }
                }
                j++;
            }
        }
        return solution[0][n - 1];
    }



    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n;
        //length of coordinates array
        n = scanner.nextInt();
        float coordinates[][] = new float[n][2];
        for (int i = 0; i < n; i++) {
            coordinates[i][0] = scanner.nextFloat();
            coordinates[i][1] = scanner.nextFloat();
        }
        scanner.close();
        System.out.println(String.format("%7.4f ", triangulation(coordinates, n)));
    }
}