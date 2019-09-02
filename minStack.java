import java.util.Stack;

//这道题真的非常有意思，经常看
public class minStack {

    private int min =Integer.MAX_VALUE;

    private Stack<Integer> stack = new Stack<>();
    private Stack<Integer> support_stack = new Stack<>();

    public void push(int node) {

        if(node<=min){
            support_stack.push(node);
            min = node;
        }

        stack.push(node);
    }

    public void pop() {
        if(min == stack.pop()) {
            support_stack.pop();
            min = support_stack.peek();
        }
    }

    public int top() {
        return stack.peek();

    }

    public int min() {
        return min;
    }
}
