import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class KdTree_2 {


    private TreeNode root = null;
    private int count = 0;
    private double min_distance = Double.POSITIVE_INFINITY;
    private Point2D nearest_point = null;

    private class TreeNode{
        private Point2D point;
        private TreeNode left;
        private TreeNode right;

        public TreeNode (Point2D p){
            point = p;
            left = right =null;
        }
    }

    public boolean isEmpty(){
        return root == null;
    }

    public int size(){
        return count;
    }

    public void insert(Point2D p){

        root = insert(p,root,true);//  be careful, this part may cause some problems;

    } // this part is really important, take notes

    private TreeNode insert(Point2D p,TreeNode x, boolean t ){
        if(x == null) { count++; return new TreeNode(p);}// can i use boolean in this way?
        if(x.point.equals(p)) return x;

        if(t){
            boolean cmp = p.x() <= x.point.x();
            if(cmp) x.left = insert(p,x.left,!t);    // in most cases it will just simply return x.left ,
                // unless it creates new node ,think about it;
            else x.right = insert(p,x.right,!t);
        }

        else{
            boolean cmp = p.y() <= x.point.y();
            if(cmp) x.left = insert(p,x.left,!t);
            else x.right = insert(p,x.right,!t);
        }
        return x; //think carefully, why here we return x;
    }


    public boolean contains(Point2D p){
        if(root == null)
            return false;
        boolean k;
        k = contains(p,root,true);
        return k;

    }

    private boolean  contains(Point2D p, TreeNode x, boolean t){

        if(x == null) return false;
        if(x.point.equals(p)) return true;

        boolean k;
        if(t){
            boolean cmp = p.x() <= x.point.x();
            if(cmp) k = contains(p,x.left,!t);
            else k = contains(p,x.right,!t);
        }
        else{
            boolean cmp = p.y() <= x.point.y();
            if(cmp) k = contains(p,x.left,!t);
            else k = contains(p,x.right,!t);
        }
        return k;
    }

    public void draw(){
        draw(root);
    }

    private void draw(TreeNode x){
        if(x == null) return;
        draw(x.left);
        x.point.draw();
        draw(x.right);
    }

    public Iterable<Point2D> range(RectHV rect){
        if(rect == null) throw new IllegalArgumentException("IllegalArgumentException");
        return new Cluster(rect);
    }

    private class Cluster implements Iterable<Point2D>{

        private Node first;

        private class Node{
            Point2D point;
            Node next = null;
        }

        public Cluster(RectHV rect){
            first = null; // can i move this to the begining?
            cluster(rect,root, true);

        }

        private void cluster(RectHV rect, TreeNode x, boolean t){
            if(x==null) return;
            if(rect.contains(x.point)) add(x.point);

            if(t){
                if(rect.xmax()<= x.point.x())
                    cluster(rect,x.left,!t);
                else if(rect.xmin()>x.point.x())
                    cluster(rect,x.right,!t);
                else {  cluster(rect,x.left,!t); cluster(rect,x.right,!t);}
            }
            else{
                if(rect.ymax()<= x.point.y())
                    cluster(rect,x.left,!t);
                else if(rect.ymin()>x.point.y())
                    cluster(rect,x.right,!t);
                else {  cluster(rect,x.left,!t); cluster(rect,x.right,!t);}
            }
            return;
        }

        private void add(Point2D p){
            Node old_first = first;
            first = new Node();
            first.point = p;
            first.next = old_first;
        }

        public Iterator<Point2D> iterator() {
            return new ClusterIterator();
        }

        private class ClusterIterator implements Iterator<Point2D>{
            Node current;

            public ClusterIterator(){
                current = first;
            }

            public boolean hasNext() {
                return current!=null;
            }

            public Point2D next() {
                Point2D p = current.point;
                current = current.next;
                return p;
            }
        }
    }

    public Point2D nearest(Point2D p){

        if (p == null) throw new IllegalArgumentException("IllegalArgumentException");
        nearest(p,root,true);
        min_distance = Double.POSITIVE_INFINITY;
        //nearest_point = null;
        return nearest_point;
    }

    private void nearest(Point2D p,TreeNode x, boolean t){
        if(x==null) return;
        double current_distance = p.distanceTo(x.point);
        if(current_distance < min_distance)
        {nearest_point = x.point; min_distance = current_distance;}

        if(t){
            if(p.x()<= x.point.x()){
                     nearest(p,x.left,!t);
                    if(min_distance > (x.point.x()-p.x())) ;
                    nearest(p,x.right,!t);
            }
            else{
                nearest(p,x.right,!t);
                if(min_distance >= (p.x()-x.point.x())) ;
                nearest(p,x.left,!t);
            }
        }

        else{
            if(p.y()<= x.point.y()){
                nearest(p,x.left,!t);
                if(min_distance > (x.point.y()-p.y()))
                    nearest(p,x.right,!t);
            }
            else{
                nearest(p,x.right,!t);
                if(min_distance > (p.y()-x.point.y()))
                    nearest(p,x.left,!t);
            }
        }
        return;
    }


    public static void main(String[] args){
        KdTree_2 kdtree = new KdTree_2();

        Point2D p = new Point2D(0.372 ,0.497);
        kdtree.insert(p);
        p = new Point2D(0.564,0.413);
        kdtree.insert(p);
        p = new Point2D(0.226,0.577);
        kdtree.insert(p);
        p = new Point2D( 0.144,0.179);
        kdtree.insert(p);
        p = new Point2D(0.083,0.51);
        kdtree.insert(p);
        p = new Point2D(0.32,0.708);
        kdtree.insert(p);
        p = new Point2D(0.417,0.362);
        kdtree.insert(p);
        p = new Point2D(0.862,0.825);
        kdtree.insert(p);
        p = new Point2D(0.785,0.725);
        kdtree.insert(p);

        p = new Point2D(0.499,0.208);
        kdtree.insert(p);

        Point2D k = new Point2D(0.53, 0.919);
        p = kdtree.nearest(k);

        StdOut.print(p);
    }
}
