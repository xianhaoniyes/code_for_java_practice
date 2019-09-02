
//看似困难实则简单 二叉树的遍历你又忘了,
public class TreeDepth {

    public int TreeDepth(TreeNode root) {
        if(root == null)
            return 0;
        int nleft =TreeDepth(root.left);
        int nright = TreeDepth(root.right);

        return (nleft>nright) ? nleft+1: nright+1;
    }
}
