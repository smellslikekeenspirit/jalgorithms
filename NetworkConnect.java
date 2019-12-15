/**
 * @author: Prionti Nasir
 */

import java.util.*;

public class NetworkConnect {
    static int n;
    static int[][] Graph;


    public static int bottleneck(int[] path, int[][] weightedGraph, int t){
        int min = Integer.MAX_VALUE;
        while(path[t] > 0){
            int u = path[t];
            if (weightedGraph[u][t]<min){
                min = weightedGraph[u][t];
            }
            t = u;
        }
        return min;
    }

    public static void augment(int[] path, int[][] weightedGraph, int source, int sink){
        int bottleneck = bottleneck(path, weightedGraph, sink);
        while(sink != source){
            int u = path[sink];
            weightedGraph[u][sink] -= bottleneck;
            weightedGraph[sink][u] += bottleneck;
            sink = u;
        }
    }

    public static int[] EdmondsKarp(int source, int sink, int[][] weightedGraph){
        int[] path = BFS(source, weightedGraph, false);
        while(path[sink] != 0){
            augment(path, weightedGraph, source, sink);
            path = BFS(source, weightedGraph, false);
        }
        return connect(weightedGraph, path, sink);
    }

    public static int[] minPath(int[] path){
        int[] minPath = new int[n+1];
        int j = 0;
        for(int i = 1; i <= n; i++){
            if(path[i] != 0){
                minPath[j] = i;
                j++;
            }
        }
        return minPath;
    }

    public static int[] connect(int[][] weightedGraph, int[] path, int sink){
        int v = -1;
        int u = 1;
        int[] edge = new int[2];
        int[] transposePath = BFS(sink, weightedGraph, true);
        int[] transpose = minPath(transposePath);
        while (v == -1){

            while(path[u]==0 && u < n)
                u++;

            int i = 0;
            while(transpose[i] != 0){
                if(weightedGraph[u][transpose[i]] + weightedGraph[transpose[i]][u] == 0){
                    v = transpose[i];
                    break;
                }
                i++;
            }

            if (v==-1){
                u++;
            }
            if(n<u){
                break;
            }
        }
        edge[0] = u;
        edge[1] = v;
        return edge;
    }


    public static int[] BFS(int source, int[][] weightedGraph, boolean reverse){
        int head = 0;
        int tail = 1;
        int[] path = new int[n+1];
        int[] q = new int[n+1];
        q[0] = source;
        path[source] = -1;
        int a;
        while(head != tail){
            a = q[head];
            head++;
            int i = 1;
            while(i <= Graph[a][0]){
                int b = Math.abs(Graph[a][i]);
                int bandwidth;
                if (reverse) {
                    bandwidth = weightedGraph[b][a];
                }else{
                    bandwidth =  weightedGraph[a][b];
                }
                if( bandwidth > 0 && path[b] == 0){
                    path[b] = a;
                    q[tail] = b;
                    tail++;
                }
                i++;
            }
        }
        return path;
    }

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        //number of computers including server and user
        n = scanner.nextInt();
        //number of connections
        int m = scanner.nextInt();
        //graph
        Graph = new int[n+1][m+1];
         //weighted graph
        int[][] weightedGraph = new int[n+1][n+1];
        int s = scanner.nextInt();
        int t = scanner.nextInt();
        int u, v, w;
        for(int i = 0; i < m; i++){
            u = scanner.nextInt();
            v = scanner.nextInt();
            w = scanner.nextInt();
            Graph[u][0] ++;
            int helper = Graph[u][0];
            Graph[u][helper] = v;
            weightedGraph[u][v] = w;
            Graph[v][0] ++;
            helper = Graph[v][0];
            Graph[v][helper] = u * -1;
        }
        int[] edge = EdmondsKarp(s, t, weightedGraph);
        System.out.println(edge[0] + " " + edge[1]);
    }
}