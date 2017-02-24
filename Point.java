import java.util.*;
import java.util.Comparator;

public class Point implements Comparable<Point>{
    public final Comparator<Point> BY_SLOPE_ORDER = new Comparator<Point>() {
        public int compare(Point point1, Point point2) {
            double slopeP1 = getSlope(point1);
            double slopeP2 = getSlope(point2);
      //      if(Double.isNaN(slopeP1) || Double.isNaN(slopeP2)) return -1;
            if(slopeP1 < slopeP2) return -1;
            if(slopeP1 > slopeP2) return 1;
            return 0;
        }
    };

    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static boolean areCollinear(Point p, Point q, Point r) {
        double slopePQ = p.getSlope(q);
        double slopeQR = q.getSlope(r);
        return p.compareTo(q) < 0 && q.compareTo(r) < 0 && slopePQ == slopeQR;
    }

    public static boolean areCollinear(Point p, Point q, Point r, Point s) {
        double slopeRS = r.getSlope(s);
        double slopeQR = q.getSlope(r);
        return Point.areCollinear(p,q,r) && r.compareTo(s) < 0 && slopeQR == slopeRS;
    }

    public int compareTo(Point that) {
        if(this.getX() < that.getX()) {
            return (-1)*that.getX();
        }
        else if(this.getX() > that.getX()) {
            return this.getX();
        }
        else {
            if(this.getY() < that.getY()) {
                return (-1)*that.getY();
            }
            else if(this.getY() > that.getY()) {
                return this.getY();
            }
        }
        return 0;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public double getSlope(Point p) {
        if(p.getX() == this.getX()) {
            return Double.POSITIVE_INFINITY;
        }
        return ((double)(this.getY() - p.getY())) / (this.getX() - p.getX());
    }
}
