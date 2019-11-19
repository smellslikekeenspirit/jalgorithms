import java.util.Scanner;

public class OneWay {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int n = s.nextInt();

        MyNode[] transposed = new MyNode[n+1];
        for (int i = 1; i <= n; i++) {
            transposed[i] = new MyNode(i);
        }

        MyNode[] nodes = new MyNode[n+1];
        for (int i = 1; i <= n; i++) {
            MyNode node = new MyNode(i);
            int edge = s.nextInt();
            while (edge != 0) {
                transposed[edge].addEdge(i);
                node.addEdge(edge);
                edge = s.nextInt();
            }

            nodes[i] = node;
        }

        boolean[] seen = new boolean[n+1];
        Stack stack = new Stack();

        for (int i = 1; i <= n; i++) {
            if (!seen[i]) {
                getOrdering(nodes, i, seen, stack);
            }
        }

        for(int i = 0; i <= n; i++) {
            seen[i] = false;
        }

        MyLink current = stack.pop();
        int[] stronglyConnected = new int[n+1];
        int componentNumber = 0;
        while (current != null) {
            int index = current.target;
            if(!seen[index]) {
                componentNumber++;
                depthFirstSearch(transposed, index, seen, componentNumber, stronglyConnected);
            }
            current = stack.pop();
        }

        boolean[] inBridges = new boolean[componentNumber + 1];
        boolean[] outBridges = new boolean[componentNumber + 1];

        for(int i = 1; i <= n; i++) {
            MyLink link = nodes[i].front;
            while (link != null) {
                if (stronglyConnected[i] != stronglyConnected[link.target]) {
                    outBridges[stronglyConnected[i]] = true;
                    inBridges[stronglyConnected[link.target]] = true;
                }
                link = link.next;
            }
        }

        int missingOut = 0;
        int missingIn = 0;
        boolean willWork = true;
        for(int i = 1; i <= componentNumber; i++) {
            if(!inBridges[i]) {
                if (missingIn == 0) {
                    missingIn = i;
                } else {
                    willWork = false;
                    break;
                }
            }
            if(!outBridges[i]) {
                if (missingOut == 0) {
                    missingOut = i;
                } else {
                    willWork = false;
                    break;
                }
            }
        }

        if (willWork && missingIn != missingOut) {
            System.out.println("YES");
            int vertexIn = 0;
            int vertexOut = 0;
            for (int i = 1; i <= n; i++) {
                if (stronglyConnected[i] == missingIn && vertexIn == 0) {
                    vertexIn = i;
                }
                if (stronglyConnected[i] == missingOut && vertexOut == 0) {
                    vertexOut = i;
                }
            }
            System.out.println(vertexOut + " " + vertexIn);
        } else {
            System.out.println("NO");
        }
    }

    static void depthFirstSearch(MyNode[] nodes, int current, boolean[] seen, int componentNumber, int[] stronglyConnected) {
        seen[current] = true;
        stronglyConnected[current] = componentNumber;

        MyLink link = nodes[current].front;
        while (link != null) {
            if (!seen[link.target])
                depthFirstSearch(nodes, link.target, seen, componentNumber, stronglyConnected);
            link = link.next;
        }
    }

    static void getOrdering(MyNode[] nodes, int current, boolean[] seen, Stack stack) {
        seen[current] = true;

        MyLink link = nodes[current].front;
        while (link != null) {
            if (!seen[link.target])
                getOrdering(nodes, link.target, seen, stack);
            link = link.next;
        }

        stack.push(current);
    }
}

class Stack {
    MyLink front;
    MyLink back;
    int length;

    public Stack() {
        front = null;
        back = null;
        length = 0;
    }

    void push(int target) {
        MyLink link = new MyLink(target);
        if (length == 0) {
            this.front = link;
            this.back = link;
        } else {
            link.next = this.front;
            this.front = link;
        }
        this.length++;
    }

    MyLink pop() {
        if (length > 0) {
            MyLink link = this.front;

            this.front = link.next;
            this.length--;

            return link;
        }
        return null;
    }
}

class MyLink {
    MyLink next;
    int target;

    public MyLink(int target) {
        this.target = target;
    }
}

class MyNode {
    public int id;
    public int index;
    public MyLink front;
    public MyLink last;

    public MyNode(int id) {
        this.id = id;
        this.front = null;
        this.last = null;
        this.index = 1;
    }

    public void addEdge(int edge) {
        MyLink link = new MyLink(edge);
        if (this.last != null) {
            this.last.next = link;
            this.last = link;
        } else {
            this.front = link;
            this.last = link;
        }
        this.index++;
        this.index++;
    }
}