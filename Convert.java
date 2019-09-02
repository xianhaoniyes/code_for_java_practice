
//目前看来是你思索时间最长的题,递归的思路，递归的思路，哪一个是基本运算单位，哪一个是最小元
public class Convert {

    public TreeNode Convert(TreeNode pRootOfTree) {
        if(pRootOfTree == null)
            return null;
        TreeNode start = pRootOfTree;
        while(start.left!=null)
            start = start.left;

        left_traverse(pRootOfTree); // right_traverse 也行
        return start;

    }

    private TreeNode left_traverse(TreeNode node){

        if(node.left!=null){
            node.left = left_traverse(node.left);
            node.left.right = node;
        }
        if(node.right!=null){
            node.right = right_traverse(node.right);
            node.right.left = node;
        }
        else return node;

        return node.right;

    }

    private TreeNode right_traverse(TreeNode node){
        if(node.right!=null){
            node.right =right_traverse(node.right);
            node.right.left = node;
        }

        if(node.left!=null){
            node.left = left_traverse(node.left);
            node.left.right = node;
        }
        else return node;

        return node.left;

    }

    public static void main(String[] args) {
        TreeNode b = new TreeNode(2);
        TreeNode a = new TreeNode(1);
        TreeNode c = new TreeNode(3);
        b.left = a;
        b.right = c;
        Convert solution = new Convert();
        solution.Convert(b);
        System.out.print(a.right.val);
    }

}
