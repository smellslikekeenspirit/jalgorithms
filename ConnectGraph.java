import java.util.Scanner;

class Node {
    public int item;
    public Node next;

    public Node(int item){
        this.item = item;
        this.next = null;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getNext() {
        return next;
    }

    public int getItem() {
        return item;
    }
}

class LinkedList {
    private int size;
    private Node head;

    public LinkedList() {
        this.size = 0;
        this.head = null;
    }

    //inserting at the beginning of the list
    // since order of neighbors does not matter
    // but speed is crucial

    public void insert(int item) {
        Node node = new Node(item);
        node.setNext(head);
        head = node;
        this.size++;
    }

    public Node getHead() {
        return head;
    }
}


public class ConnectGraph
{
    private int V;
    private LinkedList[] adj;

    public ConnectGraph(int v)
    {
        V = v;
        adj = new LinkedList[v+1];
        for (int i=0; i<v+1; ++i)
            adj[i] = new LinkedList();
    }

    public void addEdge(int v, int w)
    {
        adj[v].insert(w);
        adj[w].insert(v);
    }

    public void DFSHelper(int v, boolean visited[])
    {
        visited[v] = true;
        Node lst = adj[v].getHead();
        while(lst != null){
            if (!visited[lst.getItem()])
                DFSHelper(lst.getItem(), visited);
            lst = lst.next;

        }
    }

    public boolean[] DFS(int v)
    {
        boolean visited[] = new boolean[V+1];

        DFSHelper(v, visited);
        return visited;
    }




    public static void main(String args[])
    {
        int k = 0;
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ConnectGraph graph = new ConnectGraph(n);
        for (int i = 0; i<m; i++){
            graph.addEdge(scanner.nextInt(), scanner.nextInt());
        }
        boolean[] visited = graph.DFS(1);
        for (int i = 1; i<=n; i++){
            if (!visited[i]){
                graph.addEdge(1, i);
                k++;
                visited = graph.DFS(i);
            }
        }

        System.out.println(k);
    }
}
