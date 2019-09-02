//这道题你花了大量时间， 要细心啊
//你写了俩解法，哪种好，注意了

public class ReverseList {

    public ListNode ReverseList(ListNode head) {

        ListNode former = null;
        ListNode inter;

/*        if(head == null)
            return null;
        inter = head.next;
        //这句话太重要了， 翻转后最后一个节点要指向空.
        head.next = null;

        while(true){
            former=head;
            if(inter == null)
                break;
            head = inter;
            inter = head.next;
            head.next = former;

        }
        return former;*/

        while(head!=null){
         inter = head.next;
         head.next =former;
         former = head;
         head = inter;
        }
        return former;
    }

    public static void main(String[] args){

        ListNode root = new ListNode(1);
        ListNode current =root;
        for(int i = 2 ;i< 5; i++){
            current.next = new ListNode(i);
            current = current.next;
        }

        ReverseList solution = new ReverseList();
        solution.ReverseList(root);

    }
}
