import java.util.Scanner;

public class ColorfulMaze {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int m = s.nextInt();

        int start = s.nextInt();
        int finish = s.nextInt();

        Node[] nodes = new Node[n + 1];
        for (int i = 1; i <= n; i++) {
            nodes[i] = new Node(i);
        }
        String st = s.nextLine();
        for (int i = 1; i <= m; i++) {
            String numbers[] = s.nextLine().split(" ");

            Edge edge = new Edge(i, Integer.parseInt(numbers[2]), Integer.parseInt(numbers[0]), Integer.parseInt(numbers[1]));
            nodes[edge.vertex1].addEdge(edge);
            nodes[edge.vertex2].addEdge(edge);
        }

        Queue queue = new Queue();


        LinkedList list = nodes[start].front;
        while(list != null) {
            Edge current = list.edge;
            if (current.color == 1) {
                if (current.vertex2 == start) {
                    queue.enqueue(current.vertex1, 2, 1);
                } else {
                    queue.enqueue(current.vertex2, 2, 1);
                }
            }
            list = list.next;
        }

        boolean foundFinish = false;

        Link currentLink = queue.dequeue();
        while(currentLink != null) {
            LinkedList currentList = nodes[currentLink.target].front;
            while(currentList != null) {
                Edge current = currentList.edge;
                // MAke sure its a valid color to go on
                if (current.color == currentLink.length) {
                    int nextVertex;
                    if (current.vertex2 == currentLink.target) {
                        nextVertex = current.vertex1;
                    } else {
                        nextVertex = current.vertex2;
                    }
                    // See if it is the end
                    if (nextVertex == finish && current.color == 3) {
                        System.out.println(currentLink.depth + 1);
                        currentLink = null;
                        foundFinish = true;
                        break;
                    }

                    queue.enqueue(nextVertex, (currentLink.length % 3) + 1, currentLink.depth + 1);
                }

                currentList = currentList.next;
            }

            if (currentLink == null) break;

            currentLink = queue.dequeue();
        }

        if (!foundFinish) System.out.println("-1");
    }
}

class Edge {
    public int id;
    public int color;
    public int vertex1;
    public int vertex2;

    public Edge(int id, int color, int vertex1, int vertex2) {
        this.id = id;
        this.color = color;
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
    }
}

class Queue {
    Link front;
    Link back;
    int length;

    public Queue() {
        front = null;
        back = null;
        length = 0;
    }

    void enqueue(int target, int nextColor, int depth) {
        Link link = new Link(target, nextColor, depth);
        if (length == 0) {
            this.front = link;
            this.back = link;
        } else {
            this.back.next = link;
            this.back = link;
        }
        this.length++;
    }

    Link dequeue() {
        if (length > 0) {
            Link link = this.front;
            this.front = link.next;
            this.length--;

            return link;
        }
        return null;
    }
}

class Link {
    Link next;
    int target;
    int length;
    int depth;

    public Link(int target, int length, int depth) {
        this.target = target;
        this.length = length;
        this.depth = depth;
    }
}

class LinkedList {
    LinkedList next;
    Edge edge;

    public LinkedList(Edge edge) {
        this.edge = edge;
    }
}

class Node {
    public int id;
    public LinkedList front;
    public LinkedList last;
    public int index;

    public Node(int id) {
        this.id = id;
        this.front = null;
        this.last = null;
        this.index = 1;
    }

    public void addEdge(Edge edge) {
        LinkedList link = new LinkedList(edge);
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

