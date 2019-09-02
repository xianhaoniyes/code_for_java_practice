
//这道题你基本照抄的，还是注意看
public class HasSubtree {

    public boolean HasSubtree(TreeNode root1,TreeNode root2){
        boolean answer = false;
        if(root1!= null && root2 != null){
            if(root1.val == root2.val)
                answer = HasSubtree2(root1,root2);
            //想想看， 为什么不可以写成 root1.val!= root2.val; 因为 如果写成这样，
            // 就只考虑了 root1!= root2 的情况， 而没考虑万一其子节点不等的情况.
            if(!answer)
                answer = HasSubtree(root1.right,root2);
            if(!answer)
                answer = HasSubtree(root1.left,root2);

        }
        return answer;
    }

    private boolean HasSubtree2(TreeNode root1, TreeNode root2){
        if(root2 == null)
            return true;
        if(root1 == null)
            return false;
        if(root1.val!= root2.val)
            return false;
        else return HasSubtree2(root1.left,root2.left) && HasSubtree2(root1.right,root2.right);

    }
}
