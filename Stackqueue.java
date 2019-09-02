import java.util.Stack;
//用两个栈来模拟队列操作, you need to think about it.
public class Stackqueue {
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack1.push(node);

    }

    public int pop() {
        if(!stack2.isEmpty())
            return stack2.pop();
        else {
                while(!stack1.isEmpty())
                stack2.push(stack1.pop());
                return  stack2.pop();
        }
    }
}
