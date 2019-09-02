import java.util.Arrays;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;

public class FastCollinearPoints {

    private int count  = 0;
    private Node first = null;
    private Node last = null;
    private LineSegment[] lineSegments;

    private class Node{
        LineSegment lineSegment;
        Node next;
    }



    public FastCollinearPoints(Point[] points){

        if(points == null) throw new IllegalArgumentException("IllegalArgumentException");
        int N = points.length;
        for(int i = 0; i < N; i++)
            if(points[i]==null)
                throw new  IllegalArgumentException("IllegalArgumentException");
        for(int i = 0; i < N-1; i++)
            for(int j = i+1; j< N;j++)
                if(points[i].compareTo(points[j]) == 0 )
                    throw  new IllegalArgumentException("IllegalArgumentException");

        Point[] points_m = new Point[N];
        for(int i = 0;i < N;i++)
            points_m[i] = points[i];


        for(int i = 0; i < N - 3; i++){
            Arrays.sort(points_m,i,N,points[i].slopeOrder());  //this part is really magic.
            int j=i+1;
            while(j<N){
                int k;
                boolean dupicalte = false;
                for( k=j+1;k<N && points_m[i].slopeTo(points_m[j])==points_m[i].slopeTo(points_m[k]);k++);
                k = k-1;
                if(k-j > 1) {
                    for (int q = 0; q < i ; q++ )
                        if(points_m[q].slopeTo(points_m[i])==points_m[i].slopeTo(points_m[j]))
                        {dupicalte = true;break;}

                    if(!dupicalte){
                    Arrays.sort(points_m,j,k+1);
                    if(first == null)
                        first = last =new Node();
                    else { Node old_last = last; last = new Node(); old_last.next = last; }

                    if(points_m[i].compareTo(points_m[j])<0)  last.lineSegment = new LineSegment(points_m[i],points_m[k]);
                        else if(points_m[i].compareTo(points_m[k])>0) last.lineSegment = new LineSegment(points_m[j],points_m[i]);
                            else last.lineSegment = new LineSegment(points_m[j],points_m[k]);
                    count++;}

                }
                j = k+1;
            }
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
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }

}
