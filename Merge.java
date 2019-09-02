public class Merge {
    public ListNode Merge (ListNode list1,ListNode list2) {
        ListNode merge = new ListNode(0);
        ListNode current = merge;
        while(list1!=null&&list2!=null){
            if(list1.val<=list2.val){

                  current.next = new ListNode(list1.val);
                  current = current.next;
                  list1 = list1.next;
            }
            else{

                current.next = new ListNode(list2.val);
                current = current.next;
                list2 = list2.next;
            }
        }

        while(list1!=null){
            current.next = new ListNode(list1.val);
            current = current.next;
            list1 = list1.next;
        }

        while(list2!=null){

            current.next = new ListNode(list2.val);
            current = current.next;
            list2 = list2.next;
        }

        return merge.next;

    }

    public static void main(String[] args){
        Merge solution = new Merge();
        ListNode root = new ListNode(1);
        ListNode current =root;
        for(int i = 2 ;i< 5; i++){
            current.next = new ListNode(i);
            current = current.next;
        }


    }
}
