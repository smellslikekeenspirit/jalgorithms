import java.util.Scanner;

public class Prerequisites {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int n = s.nextInt();


        Vertex[] vertices = new Vertex[n+1];
        for (int i = 1; i <= n; i++) {
            Vertex node = new Vertex(i);
            int edge = s.nextInt();
            while (edge != 0) {
                node.addEdge(edge);
                edge = s.nextInt();
            }

            vertices[i] = node;
        }

        boolean[] seen = new boolean[n+1];
        int[] table = new int[n+1];

        for (int i = 1; i <= n; i++) {
            if(!seen[i]) {
                traverse(vertices, i, seen, table);
            }
        }

        // Getting max
        int max = 0;
        for (int i = 1; i <= n; i++) {
            if(table[i] > max) {
                max = table[i];
            }
        }

        System.out.println(max);
    }

    static void traverse(Vertex[] vertices, int index, boolean[] seen, int[] table) {
        Vertex vertex = vertices[index];
        seen[index] = true;
        ALink link = vertex.front;
        int max = 0;
        while(link != null) {
            if (!seen[link.target]) {
                traverse(vertices, link.target, seen, table);
            }
            if (table[link.target] > max)
                max = table[link.target];

            link = link.next;
        }

        table[index] = max + 1;
    }
}

class ALink {
    ALink next;
    int target;

    public ALink(int target) {
        this.target = target;
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

    public void addEdge(int edge) {
        ALink link = new ALink(edge);
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

