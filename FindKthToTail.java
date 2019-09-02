//这道题边界条件，虚拟节点的考虑是精华，一定要下来总结
//这道题边界条件的考虑很烦
public class FindKthToTail {
    public ListNode FindKthToTail(ListNode head,int k) {
        int i = 0;
        ListNode virtual_node = new ListNode(0);   //这个虚拟节点的使用非常重要.
        virtual_node.next = head;
        if(k==0)
            return null;

        ListNode current = virtual_node;
        //想想为什么要这个节点.
        //这道题回去要多想.
        while(i<k) {
            current = current.next;
            i++;
            if(current == null)
                return null;
        }

        while(current!=null){
            current = current.next;
            virtual_node = virtual_node.next;
        }

        return virtual_node;
    }

}
