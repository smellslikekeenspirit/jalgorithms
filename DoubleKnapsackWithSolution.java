import java.util.Scanner;

/**
 * @author: Prionti Nasir
 * @author: Owen Sullivan
 * Problem 3
 */
public class DoubleKnapsackWithSolution {

    private static void doubleKnapsack(int n, int W1, int W2, int[] weights, int[] costs){
        int[][][] solution = new int[n+1][W1+1][W2+1];

        for(int j=0; j<=W1; j++){
            for(int k=0; k<=W2; k++){
                for(int i=0; i<=n; i++){
                    if(i != 0){
                        int cost1 = 0;
                        if(j >= weights[i]){
                            cost1 = costs[i] + solution[i-1][j-weights[i]][k];
                        }

                        int cost2 = 0;
                        if(k >= weights[i]){
                            cost2 = costs[i] + solution[i-1][j][k-weights[i]];
                        }
                        int cost3 = solution[i-1][j][k];
                        int maxCost = cost1;
                        if(maxCost < cost2){
                            maxCost = cost2;
                        }
                        if(maxCost < cost3){
                            maxCost = cost3;
                        }
                        solution[i][j][k] = maxCost;
                    }
                }
            }
        }

        int knapsack1[] = new int[n];
        int knapsack2[] = new int[n];
        int weight1 = W1;
        int weight2 = W2;
        int ItemsInKnapsack1 = 0, ItemsInKnapsack2 = 0;
        int max = solution[n][W1][W2];
        System.out.println(max);

        //we find out what items were added to each knapsack
        //if cost difference cost difference between current location and the location in weights corresponding
        // to that item's weight is equal to cost of current item then that item was in the knapsack
        // we remove it from other knapsack's calculations
        int count = n;
        while(count > 0){
            if(max - costs[count] >= 0){
                if(weight1 - weights[count] >= 0
                        && max - costs[count] == solution[count-1][weight1 - weights[count]][weight2] ){
                    knapsack1[ItemsInKnapsack1] = count;
                    ItemsInKnapsack1++;
                    weight1 -= weights[count];
                    max -= costs[count];
                    count--;
                }else if(weight2 - weights[count] >= 0
                        && max - costs[count] == solution[count-1][weight1][weight2 - weights[count]]) {
                    knapsack2[ItemsInKnapsack2] = count;
                    ItemsInKnapsack2++;
                    weight2 -= weights[count];
                    max -= costs[count];
                    count--;
                }else{
                    count--;
                }
            }else{
                count--;
            }
        }

        for(int i=ItemsInKnapsack1-1; i>=0; i--){
            System.out.print(knapsack1[i] + " ");
        }
        System.out.println();

        for(int i=ItemsInKnapsack2-1; i>=0; i--){
            System.out.print(knapsack2[i] + " ");
        }
        System.out.println();

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //number of items
        int n = scanner.nextInt();
        int W1 = scanner.nextInt();
        int W2 = scanner.nextInt();
        //ith element in weights is weight of ith item
        int weights[] = new int[n+1];
        //ith element in costs is cost of ith item
        int costs[] = new int[n+1];

        for(int i=1; i<=n; i++){
            weights[i] = scanner.nextInt();
            costs[i] = scanner.nextInt();
        }
        doubleKnapsack(n, W1, W2, weights, costs);
    }

}