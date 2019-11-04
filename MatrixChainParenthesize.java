import java.util.Scanner;

public class MatrixChainParenthesize {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();

        // Scan in the intervals
        long numbers[] = new long[n+2];
        numbers[0] = 1;

        for (int i = 1; i < n+2; i++) {
            numbers[i] = s.nextLong();
        }

        long table[][] = new long[n+1][n+1];
        Point origins[][] = new Point[n+1][n+1];

        for (int i = 1; i <= n; i++) {
            for (int left = 1; left <= n - i; left++) {
                int right = left + i;
                boolean unset = true;
                for (int j = left; j <= right - 1; j++) {
                    long calculation = table[left][j] + table[j+1][right] + (numbers[left] * numbers[j+1] * numbers[right+1]);

                    if (unset || table[left][right] > calculation) {
                        unset = false;
                        table[left][right] = calculation;
                        boolean isStart = table[j+1][right] == 0 || table[left][j] == 0;
                        origins[left][right] = new Point(j+1, right, left, j, isStart);
                    }
                }
            }
        }

        System.out.println(table[1][n]);

        System.out.println(getOptimization(origins, 1, n ));
    }

    static String getOptimization(Point[][] origins, int x, int y) {
        String left;
        String right;
        if (origins[x][y].left_x == origins[x][y].left_y) {
            left = "A" + origins[x][y].left_x;
        } else {
            left = getOptimization(origins, origins[x][y].left_x, origins[x][y].left_y);
        }

        if (origins[x][y].right_y == origins[x][y].right_x) {
            right = "A" + origins[x][y].right_x;
        } else {
            right = getOptimization(origins, origins[x][y].right_x, origins[x][y].right_y);
        }


        return "( " + left + " x " + right + " )";
    }
}

class Point {
    public int right_x;
    public int right_y;
    public int left_x;
    public int left_y;
    public boolean isStart;

    public Point(int right_x, int right_y, int left_x, int left_y, boolean isStart) {
        this.right_x = right_x;
        this.right_y = right_y;
        this.left_x = left_x;
        this.left_y = left_y;
        this.isStart = isStart;
    }
}
