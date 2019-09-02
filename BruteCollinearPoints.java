
import java.util.Arrays;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;

public class BruteCollinearPoints {

    private int count  = 0;
    private Node first = null;
    private Node last = null;
    private LineSegment[] lineSegments;

    private class Node{
        LineSegment lineSegment;
        Node next = null;
    }

    public BruteCollinearPoints(Point[] points){

        if(points == null) throw new IllegalArgumentException("IllegalArgumentException");
        int N = points.length;
        for(int i = 0; i < N; i++)
            if(points[i]==null)
                throw new  IllegalArgumentException("IllegalArgumentException");
        for(int i = 0; i < N-1; i++)
            for(int j = i+1; j< N;j++)
                if(points[i].compareTo(points[j]) == 0 )
                    throw  new IllegalArgumentException("IllegalArgumentException");


        for (int i = 0; i<N-3;i++)
            for(int j = i+1; j < N-2; j++)
                for (int k = j+1; k<N-1; k++)
                    if (points[i].slopeTo(points[j]) == points[i].slopeTo(points[k]))
                        for(int l = k+1; l < N; l++)
                            if (points[i].slopeTo(points[k])== points[i].slopeTo(points[l])) {
                                Point[] a ={points[i],points[j],points[k],points[l]};
                                Arrays.sort(a);
                                if(first == null)
                                    first = last =new Node();
                                else { Node old_last = last; last = new Node(); old_last.next = last; }
                                last.lineSegment = new LineSegment(a[0],a[3]);
                                count++;
                            }

        lineSegments = new LineSegment[count];
        Node current = first;
        for(int i = 0;i < count; i++){
            lineSegments[i]=current.lineSegment;
            current = current.next;
        }

    }

    public int numberOfSegments(){
        return count;
    }

    public LineSegment[] segments(){

         return lineSegments;
    }

    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }

    }


