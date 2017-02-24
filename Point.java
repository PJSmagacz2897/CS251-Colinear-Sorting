import java.util.*;
import java.util.Comparator;

public class Point implements Comparable<Point>{
    
    private final int x;                //x coordinate of the point
    private final int y;                //y coordinate of the point

    /**
    Constructor.
    **/
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    /**
    Implements the compare function from the Comparator class to compare two points by their slope from a third point
    **/
    public final Comparator<Point> BY_SLOPE_ORDER = new Comparator<Point>() {
        public int compare(Point point1, Point point2) {
            double slopeP1 = getSlope(point1);
            double slopeP2 = getSlope(point2);
            if(slopeP1 < slopeP2) return -1;
            if(slopeP1 > slopeP2) return 1;
            return 0;
        }
    };

    /**
    Returns true if three points are collinear, false otherwise.
    **/
    public static boolean areCollinear(Point p, Point q, Point r) {
        double slopePQ = p.getSlope(q);
        double slopeQR = q.getSlope(r);
        return p.compareTo(q) < 0 && q.compareTo(r) < 0 && slopePQ == slopeQR;
    }

    /**
    Returns true if four points are collinear, false otherwise.
    **/
    public static boolean areCollinear(Point p, Point q, Point r, Point s) {
        double slopeRS = r.getSlope(s);
        double slopeQR = q.getSlope(r);
        return Point.areCollinear(p,q,r) && r.compareTo(s) < 0 && slopeQR == slopeRS;
    }

    /**
    Returns 0 if two points are lexicographically equal. Else, it returns the value of that, poistive if it is smaller and negative 
    if it is larger. 
    **/
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

    /**
    Returns x
    **/
    public int getX() {
        return this.x;
    }

    /**
    Returns y
    **/
    public int getY() {
        return this.y;
    }

    /**
    Returns the slope between two points
    **/
    public double getSlope(Point p) {
        if(p.getX() == this.getX()) {
            return Double.POSITIVE_INFINITY;
        }
        return ((double)(this.getY() - p.getY())) / (this.getX() - p.getX());
    }
}
