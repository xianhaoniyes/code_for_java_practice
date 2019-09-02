import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class PointSET {

    private SET<Point2D> pointset;

    public PointSET(){
        pointset = new SET<>();
    }

    public boolean isEmpty(){
        return pointset.isEmpty();
    }

    public int size(){
        return pointset.size();
    }
    public void insert(Point2D p){
        if(p == null) throw new IllegalArgumentException("IllegalArgumentException");
        pointset.add(p);
    }
    public boolean contains(Point2D p){
        if(p == null) throw new IllegalArgumentException("IllegalArgumentException");
        return pointset.contains(p);
    }
    public void draw(){
        for(Point2D p:pointset){
            p.draw();
        }
    }

    public Iterable<Point2D> range(RectHV rect){
        if(rect == null) throw new IllegalArgumentException("IllegalArgumentException");
        return new Clustering(rect);
    }

    private class Clustering implements Iterable<Point2D>{

        Node first = null;

        private class Node{
            Point2D point;
            Node next = null;
        }

        public Clustering(RectHV rect){
            for(Point2D p:pointset)
                if(rect.contains(p))
                    add(p);
        }

        public void add(Point2D p){
            Node old_first = first;
            first = new Node();
            first.point = p;
            first.next = old_first;
        }

        public Iterator<Point2D> iterator(){
            return new ClusteringIterator();
        }

        private class ClusteringIterator implements Iterator<Point2D>{

            Node current;

            public ClusteringIterator(){current = first;}

            public boolean hasNext() {
                return current != null;
            }

            public Point2D next() {
                if (!this.hasNext()) throw new NoSuchElementException();
                Point2D p = current.point;
                current = current.next;
                return p;
            }
        }
    }

    public Point2D nearest(Point2D p){
        if(p == null) throw new IllegalArgumentException("IllegalArgumentException");
        if(pointset.isEmpty()) return null;

        Point2D nearest_point = null;
        double distance;
        double min_d = Double.POSITIVE_INFINITY;

        for(Point2D point: pointset){
            distance = p.distanceTo(point);
            if(distance<min_d) {
                nearest_point = point;
                min_d = distance;
            }
        }
        return nearest_point;
    }

    public static void main(String[] args){}
}


