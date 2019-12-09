import java.util.Scanner;

public class CountShortestPaths {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int n = s.nextInt();
        int m = s.nextInt();
        int start = s.nextInt();

        Vertex[] vertices = new Vertex[n+1];
        for (int i = 1; i <= n; i++) {
            vertices[i] = new Vertex(i);
        }

        for (int i = 1; i <= m; i++) {
            int index = s.nextInt();
            int edge = s.nextInt();
            int weight = s.nextInt();

            vertices[index].addEdge(edge, weight);
        }

        boolean[] changed = new boolean[n+1];
        boolean[] inList = new boolean[n+1];
        int[] table = new int[n+1];
        int[] paths = new int[n+1];

        Vertex currentVertex = vertices[start];

        changed[start] = true;
        table[start] = 0;
        paths[start] = 1;
        ALink list = new ALink(start, 0);
        ALink end = list;
        int currentIndex = start;

        for(int i = 0; i < n; i++) {
            ALink current = currentVertex.front;
            while(current != null) {
                if(!changed[current.target] || table[current.target] > table[currentVertex.id] + current.weight) {
                    table[current.target] = table[currentVertex.id] + current.weight;
                    changed[current.target] = true;
                    paths[current.target] = paths[currentIndex];
                    if(!inList[current.target]) {
                        end.next = new ALink(current.target, 0);
                        end = end.next;
                        inList[current.target] = true;
                    }
                } else if(table[current.target] == table[currentVertex.id] + current.weight) {
                    changed[current.target] = true;
                    paths[current.target] += paths[currentIndex];
                    if (!inList[current.target]) {
                        end.next = new ALink(current.target, 0);
                        end = end.next;
                        inList[current.target] = true;
                    }
                }
                current = current.next;
            }

            if (list.target == currentIndex) {
                list = list.next;
                if (list == null)
                    break;
            }
            ALink movable = list.next;
            ALink previous = list;
            ALink minimum = list;

            while(movable != null) {
                if(movable.target == currentIndex) {
                    previous.next = movable.next;
                }
                else if(table[movable.target] < table[minimum.target]) {
                    minimum = movable;
                }
                previous = movable;
                movable = movable.next;
            }
            if(minimum == null) {
                break;
            }
            currentIndex = minimum.target;
            currentVertex = vertices[currentIndex];
        }

        for(int i = 1; i <= n; i++) {
            if(!changed[i]) {
                System.out.println("inf 0");
            } else {
                System.out.println(table[i] + " " + paths[i]);
            }
        }
    }
}



class ALink {
    ALink next;
    int target;
    int weight;

    public ALink(int target, int weight) {
        this.target = target;
        this.weight = weight;
    }
}

class Vertex {
    public int id;
    public ALink front;
    public ALink last;
    public int index;

    public Vertex(int id) {
        this.id = id;
        this.last = null;
        this.index = 0;
        this.front = null;
    }

    public void addEdge(int edge, int weight) {
        ALink link = new ALink(edge, weight);
        if (this.last != null) {
            this.last.next = link;
            this.last = link;
        } else {
            this.front = link;
            this.last = link;
        }

        this.index++;
    }
}
