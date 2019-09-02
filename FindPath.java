import java.util.ArrayList;
import java.util.LinkedList;

//对于这种找路径，树比图要简单多了.
//什么是递归，递归的本质是什么，你真的以为递归的时候是很多子集一起在执行吗。错！！！ 的确有很多子集但子集是一个一个执行的
public class FindPath {

    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {

        ArrayList<Integer> path = new ArrayList<>();
        ArrayList<ArrayList<Integer>> all_paths = new ArrayList<>();
        if(root==null) return all_paths;
        FindPath(root,path,target,0,all_paths);
        return all_paths;

    }

    private void FindPath(TreeNode current,ArrayList<Integer> path, int target,int current_sum,
                          ArrayList<ArrayList<Integer>> all){
        current_sum = current_sum+current.val;
        path.add(current.val);
        if(current.left!=null) {
            FindPath(current.left,path,target,current_sum,all);
        }
        if(current.right!=null){
            FindPath(current.right,path,target,current_sum,all);
        }

        if(current.right == null&&current.left ==null) {
            if(current_sum == target)
            {
                ArrayList<Integer>  new_path = new ArrayList<>();
                for(Integer i : path)
                    new_path.add(i);
                all.add(new_path);
            }
        }
        path.remove(path.size()-1);
    }

}
