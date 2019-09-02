import edu.princeton.cs.algs4.BTree;
import edu.princeton.cs.algs4.Stack;
//这个地方的语法一定要注意.

public class Binarytree<T extends Comparable<T>>{

    private treeNode root;

    private class treeNode{
        T value;
        treeNode leftChild = null;
        treeNode rightChild = null;

        private treeNode(T t){
            this.value = t;
        }
    }


    public void insert(T t){

        root = insert(root,t);

    }

    public Binarytree(){

    }

    private treeNode insert (treeNode node, T t){
        if(node == null)
            return new treeNode(t);
        else if (t.compareTo(node.value) <= 0)
                node.leftChild = insert(node.leftChild, t);
        else
            node.rightChild = insert(node.rightChild,t);
        return node;
    }

    public void inordertransverse(){
        inordertransverse(root);
        System.out.print("\n");
    }
    //notice here, what is overload, parameter must be different ,return value can be the same or different.
    //how to do it remember!!!
    // 想象一种惰性，仅有迫不得已时才转向right.
    public void inordertransverse(boolean b){
        treeNode current = root;
        Stack<treeNode> stack = new Stack<>();
        while(current!=null||!stack.isEmpty())
        {
            if(current == null) {
                current = stack.pop();
                System.out.printf("%s ",current.value);
                current = current.rightChild;
            }

            else {
                stack.push(current);
                current = current.leftChild;
            }
        }
        System.out.printf("\n");
    }


    private void inordertransverse(treeNode node){

        if(node == null) return;
        inordertransverse(node.leftChild);
        System.out.printf("%s ", node.value);// 这里对吗, yes, you could code in this way
        inordertransverse(node.rightChild);

    }

    public void preordertransverse(){
        preordertransverse(root);
        System.out.print("\n");

    }

    public void preordertransverse(boolean b){
        treeNode current = root;
        Stack<treeNode> stack = new Stack<>();
        stack.push(current);
        while(!stack.isEmpty())
        {
            current =stack.pop();
            System.out.printf("%s ",current.value);
            if(current.rightChild!=null)
            stack.push(current.rightChild);
            if(current.leftChild!=null)
                stack.push(current.leftChild);

        }
        System.out.printf("\n");

    }

    private void preordertransverse(treeNode node){
        if(node == null) return;
        System.out.printf("%s ", node.value);
        preordertransverse(node.leftChild);
        preordertransverse(node.rightChild);
    }

    public void postordertransverse(){
        postordertransverse(root);
        System.out.print("\n");
    }

    private void postordertransverse(treeNode node){
        if(node == null) return;
        postordertransverse(node.leftChild);
        postordertransverse(node.rightChild);
        System.out.printf("%s ",node.value);
    }

    public void postordertransverse(boolean b){

        treeNode current = root;
        Stack<treeNode> stack = new Stack<>();
        do{
            while(current!= null){
                if(current.rightChild!=null)
                    stack.push(current.rightChild);

                    stack.push(current);
                current = current.leftChild;
            }

            current = stack.pop();
            if(!stack.isEmpty()&&current.rightChild == stack.peek()){ //注意为什么要加！stack.isempty();   为什么不能把这个&&简单地拆开。

                {
                treeNode c =current;
                current =stack.pop();
                stack.push(c);
                }
            }
            else
            {
                System.out.printf("%s ", current.value);
                current = null;
            }
        }
        while(!stack.isEmpty());

        System.out.printf("\n");

    }

    public static void main(String[] args){
        Binarytree<Integer> bt = new Binarytree<>();
        bt.insert(6);
        bt.insert(3);
        bt.insert(8);
        bt.insert(1);
        bt.insert(4);
        bt.insert(5);
        bt.insert(2);
        bt.insert(7);
        bt.insert(9);

       // bt.inordertransverse();
        //bt.inordertransverse(true);
      //  bt.postordertransvers();
      //  bt.postordertransvers(true);
        bt.preordertransverse();
        bt.preordertransverse(true);


    }






}
