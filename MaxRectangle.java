import java.util.Scanner;

class Item {
    private int x;
    private int y;

    //constructor for the item class
    //an item represents an element meant for the stack
    public Item(int x, int y){
        this.x = x;
        this.y = y;
    }

    //gets x value
    public int getX() {
        return x;
    }

    //gets y value
    public int getY() {
        return y;
    }

}

class Stack {
    private int size;
    private Item[] items;
    private Item top;

    //constructor for the stack class
    //initializes the stack
    public Stack(int n){
        items = new Item[n];
        top = null;
        size = 0;
    }

    //pushes an item onto the stack
    public void push(int x, int y){

        items[size] = new Item(x,y);
        top = items[size];
        size++;

    }

    //pops an item off the stack
    public Item pop(){
        Item popped = top;
        if (size == 1){
            items[0] = null;
            top = null;
        }
        else{
            items[size-1] = null;
            top = items[size-2];
        }
        size--;
        return popped;
    }

    //allows to look at the value of the top
    //of the stack
    public Item peek() {
        return top;
    }
}

public class MaxRectangle {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n =  Integer.parseInt(scanner.nextLine());
        Stack lst = new Stack(n);
        String[] s;
        int maxArea = 0;

        for (int i = 0; i<n; i++){
            System.out.println("i " + i);

            //only looking at top left corners of the "buildings"
            if (i%2 == 0){
                scanner.nextLine();
            }
            else{

                s = scanner.nextLine().split(" ");
                int x = Integer.parseInt(s[0]);
                int y = Integer.parseInt(s[1]);
                //we push if it is higher than last point
                if (lst.peek() == null || y>=lst.peek().getY()){
                    lst.push(x, y);

                }

                else{

                    Item popped = null;
                    //we pop until the top point is lower than the point at hand
                    while(lst.peek()!= null && lst.peek().getY()>y ){
                        //pop last element if higher
                        popped = lst.pop();
                        int currArea = (x - popped.getX())* ( popped.getY() );
                        maxArea = Math.max(currArea, maxArea);

                    }
                    if (popped != null){
                        //push node with new y and x from last popped element
                        lst.push(popped.getX(), y);
                    }



                }

            }

        }
        System.out.println(maxArea);
    }

}
