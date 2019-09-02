//链表逆转，用栈，注意java中栈的两种实现方式

import java.util.ArrayList;
import java.util.Stack;

public class reverseList {

    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
            Stack<Integer> stack =new Stack<>();
            ArrayList<Integer> answer = new ArrayList<>();

            while(listNode!=null){
                stack.push(listNode.val);
                listNode = listNode.next;
            }

            while(!stack.isEmpty()){
                answer.add(stack.pop());
            }
            return  answer;
    }
}
