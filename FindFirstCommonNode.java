//这道题比较简单，但还是要看.
public class FindFirstCommonNode {
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {

        if(pHead1==null||pHead2==null)
            return null;
        int len_1 = 0;
        int len_2 = 0;

        ListNode current1 = pHead1;
        ListNode current2 = pHead2;
        while (pHead1 != null) {
            len_1++;
            pHead1 = pHead1.next;
        }

        while (pHead2 != null) {
            len_2++;
            pHead2 = pHead2.next;
        }

        if (len_1 >= len_2)
            for (int i = 0; i < len_1 - len_2; i++)
                current1 = current1.next;
        else for (int i = 0; i < len_2 - len_1; i++)
            current2 = current2.next;

        while(current1!=null){
            if (current1==current2)
                return current1;
            else{current1 = current1.next;
                current2 = current2.next;}
        }
        return null;
    }

}
