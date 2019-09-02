

//二叉树重建 ，这道题经典
public class reConstructBinaryTree {

    private TreeNode root;

    public TreeNode reConstructBinaryTree(int [] pre,int [] in) {

        return root = reConstructBinaryTree(pre,in,0,pre.length-1,0,in.length-1);

    }


// 边界条件， 边界条件,注意边界条件是什么
    private TreeNode reConstructBinaryTree(int [] pre, int[] in, int pre_start, int pre_end,int in_start, int in_end)
    {
        TreeNode current_root = new TreeNode(pre[pre_start]);

        int location = in_start;
        //不能用binary_search ，你这笨蛋
        while(in[location]!= pre[pre_start])
            location++;

        //这里用来划分没有子树的情况,如果没有子树，就不执行， 已经隐含了叶子节点的情况.妙.
        if(in_start!=location)

        current_root.left = reConstructBinaryTree(pre,in,pre_start+1, pre_start+(location-in_start),
                                                  in_start,location-1);
        if(in_end!=location)
        current_root.right = reConstructBinaryTree(pre,in,pre_start+(location-in_start)+1,
                                                    pre_end, location+1, in_end);

        return current_root;
    }


    private void preordertransverse(TreeNode node){
        if(node == null) return;
        System.out.printf("%s ", node.val);
        preordertransverse(node.left);
        preordertransverse(node.right);
    }

    public void preordertransverse(){
        preordertransverse(root);
        System.out.print("\n");
    }

    public void inordertransverse(){
        inordertransverse(root);
        System.out.print("\n");
    }

    private void inordertransverse(TreeNode node){

        if(node == null) return;
        inordertransverse(node.left);
        System.out.printf("%s ", node.val);// 这里对吗, yes, you could code in this way
        inordertransverse(node.right);

    }

    public static void main(String args[]){
        int[] in = new int[]{4,7,2,1,5,3,8,6};
        int[] pre = new int[]{1,2,4,7,3,5,6,8};
        reConstructBinaryTree solution = new reConstructBinaryTree();
        solution.reConstructBinaryTree(pre,in);
        solution.preordertransverse();
        solution.inordertransverse();

    }
}
