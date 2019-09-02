
//这道题很好，回去继续看
public class Clone {
    public RandomListNode Clone(RandomListNode pHead)
    {

        if(pHead==null)
            return null;
        RandomListNode root  = pHead;
        RandomListNode imme;
        RandomListNode current_new;

        while(pHead!=null){
            imme = pHead.next;
            current_new = new RandomListNode(pHead.label);
            pHead.next = current_new;
            current_new.next = imme;
            pHead = imme;
        }

        pHead = root;

        while(pHead!=null) {
            if(pHead.random!=null){
                pHead.next.random = pHead.random.next;
                pHead = pHead.next.next;
            }
            else {
                pHead.next.random = null;
                pHead = pHead.next.next;
            }
        }

        pHead = root;
        RandomListNode root2 = pHead.next;
        current_new = root2;

        //这里的边界条件考虑非常繁杂.

        while(true){

            pHead.next = current_new.next;
            pHead = pHead.next;
            if (pHead == null)
                break;
            current_new.next = pHead.next;
            current_new = current_new.next;
        }


        return  root2;
    }

    public static void main(String[] args){
        RandomListNode a = new RandomListNode(1);
        RandomListNode b = new RandomListNode(2);
        RandomListNode c = new RandomListNode(3);
        a.next = b;
        b.next = c;
        Clone solution = new Clone();
        a = solution.Clone(a);
        while(a!= null)
        {System.out.print(a.label);
        a = a.next;}

    }
}
