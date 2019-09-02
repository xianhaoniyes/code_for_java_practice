
import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;

public class Point implements Comparable<Point> {

    private final int x;     // x-coordinate of this point
    private final int y;     // y-coordinate of this point

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw() {
        StdDraw.point(x, y);
    }

    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    public double slopeTo(Point that) {
        if(this.x == that.x){
            if (this.y == that .y) return Double.NEGATIVE_INFINITY;
            else return Double.POSITIVE_INFINITY;
        }
        else if (this.y == that.y)
            return +0;
            else return (double)(that.y - this.y) / (that.x - this .x);

    }

    public int compareTo(Point that) {
        if(this.y > that.y) return 1;
            else if (this.y < that.y) return -1;
                else if (this.x > that.x) return 1;
                    else if (this.x <that.x) return -1;
                         else return 0;

    }

    public Comparator<Point> slopeOrder() {
                return new BySlopeOrder();
    }

    private class BySlopeOrder implements Comparator<Point>{

        public int compare(Point v, Point w) {
            if(slopeTo(v) < slopeTo(w)) return -1;
                else if(slopeTo(v) > slopeTo(w)) return 1;
                    else return 0;

        }
    }


    public String toString() {

        return "(" + x + ", " + y + ")";
    }

    public static void main(String[] args) {

    }
}
