import java.util.LinkedList;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode answer = new ListNode(0);
        int remainder = 0;
        int i = 0;
        ListNode last = answer;
        ListNode old_last;


        while(l1!=null||l2!=null){
            if (l1 ==null)  {i = l2.val +remainder; remainder = i/10; i = i%10;
                            old_last =last; last = new ListNode(i); last.next = null; old_last.next = last; l2 = l2.next;}
            else if(l2 == null) {i = l1.val+remainder; remainder =i/10; i =i%10;
                            old_last =last; last = new ListNode(i); last.next = null; old_last.next = last; l1 = l1.next;}
            else { i = l1.val + l2.val+remainder; remainder = i/10; i =i%10;
                      old_last =last; last = new ListNode(i); last.next = null; old_last.next = last; l1=l1.next;l2=l2.next;}
        }

        if(remainder!=0){
            old_last =last;
            last = new ListNode(remainder);
            last.next = null;
            old_last.next = last;
        }

        return answer.next;

    }

      public class ListNode {
          int val;
          ListNode next;
          ListNode(int x) { val = x; }
      }



}
