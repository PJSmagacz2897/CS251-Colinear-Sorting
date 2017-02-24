import java.util.*;

public class Brute {

    public int size;            //Number of points to be considered
    public Point[] points;      //Array of the points

    /**
    Constructor.
    **/
    public Brute(int size) {
        this.size = size;
        this.points = new Point[size];
    }

    /**
    Creates a point with the given x and y coordinates and stores it in the pos index of points.
    **/
    public void add(int x, int y, int pos) {
        Point p = new Point(x,y);
        points[pos] = p;
    }

    /**
    Formats the output string and outputs it to standard output and the file "visualPoints.txt".
    **/
    public void output(Point p, Point q, Point r, Point s, Out outputFile) {
     	String output = "4:(" + p.getX() + ", " + p.getY() + ") -> (" + q.getX() + ", " + q.getY() + ") -> (" + r.getX() + ", " + r.getY() + ") -> (" + s.getX() + ", " + s.getY() + ")";
     	System.out.println(output);
     	outputFile.println(output);
    }

    /**
    Reads in n (x,y) coordinate pairs piped from an input file, finds every colinear set of 4 points and outputs them. This algorithm is
    meant to be an inefficent, brute force method of solving this problem.
    **/
    public static void main(String[] args) {
        int n = StdIn.readInt();
        int position = 0;
        Brute b = new Brute(n);
     	while(!StdIn.isEmpty()) {
     		int x = StdIn.readInt();
         	int y = StdIn.readInt();
         	b.add(x,y,position);
         	position++;
        }
        Out outputFile = new Out("visualPoints.txt");
        Arrays.sort(b.points);
        if (b.size > 3) {
            for (int i = 0; i < b.size; i++) {
                if (b.points[i] != null) {
                    for (int j = 1; j < b.size; j++) {
                        if (b.points[j] != null) {
                            if (i != j) {
                                for (int k = 2; k < b.size; k++) {
                                    if (i != k && k != j &&b.points[k] != null && Point.areCollinear(b.points[i], b.points[j], b.points[k])) {
                                        for (int l = 3; l < b.size; l++) {
                                            if (i != l && j != l && k != l && b.points[l] != null && Point.areCollinear(b.points[i], b.points[j], b.points[k], b.points[l])) {
                                                b.output(b.points[i], b.points[j], b.points[k], b.points[l]);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                b.points[i] = null;
            }
        }
    }
}
