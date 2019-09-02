//想一想为什么要用先序遍历, 你对递归的技巧还是不够熟悉，回去再想，递归的每个步骤！！！！！！
public class Mirror {
    public void Mirror(TreeNode root) {
        if (root == null)
            return;

        TreeNode imm = root.left;
        root.left =root.right;
        root.right = imm;

        Mirror(root.left);
        Mirror(root.right);

    }
}
