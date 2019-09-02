import java.util.ArrayList;
import java.util.LinkedList;

//本质上是广度优先遍历而已
public class PrintFromTopToBottom {
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root){
        LinkedList<TreeNode> linkedList = new LinkedList<>();
        ArrayList<Integer> arr = new ArrayList<>();
        if(root == null)
            return arr;
        linkedList.addLast(root);
        while(!linkedList.isEmpty()){
            TreeNode current = linkedList.poll();
            arr.add(current.val);
            if(current.left!=null)
                linkedList.addLast(current.left);
            if(current.right!=null)
                linkedList.addLast(current.right);
        }
        return arr;
    }
}
