import java.util.*;

public class Fast {

    Point[] points;         //Array of the points
    int size;               //Number of points to be considered

    /**
    Constructor.
    **/
    public Fast(int size) {
        this.size = size;
        this.points = new Point[this.size];
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
    public void output(Point[] points, Out outputFile) {
        String output = "" + points.length + ":(";
        for(int i = 0; i < points.length; i++) {
            output += points[i].getX() + ", " + points[i].getY() + ")";
            if(i != points.length - 1) {
                output += " -> (";
            }
        }
        System.out.println(output);
        outputFile.println(output);
    }

    /**
    Reads in n (x,y) coordinate pairs piped from an input file, finds every colinear set of points and outputs them. This algorithm 
    iterates through the points array, sorts each point by its slope relative to points[i], and then .
    **/
    public static void main(String[] args) {
        int n = StdIn.readInt();
        int position = 0;
     
        Fast f = new Fast(n);
     	while(!StdIn.isEmpty()) {
     		int x = StdIn.readInt();
         	int y = StdIn.readInt();
         	f.add(x,y,position);
         	position++;
        }
     	Out outputFile = new Out("visualPoints.txt");
        for(int i = 0; i < f.points.length; i++) {
            Point[] copy = Arrays.copyOf(f.points, f.points.length);
            Arrays.sort(copy, copy[i].BY_SLOPE_ORDER);
            ArrayList<Point> list = new ArrayList(Arrays.asList(copy));
            int indexP = list.indexOf(f.points[i]);
            Point p = list.remove(indexP);
            list.add(p);
         	copy = list.toArray(new Point[list.size()]);
            int count = 1;
            double currSlope = f.points[i].getSlope(copy[0]);
                        for(int j = 1; j < f.points.length; j++) {
                double newSlope;
                if (j == f.points.length - 1) {
                    newSlope = Double.NEGATIVE_INFINITY;
                }
                else {
                    newSlope = f.points[i].getSlope(copy[j]);
                }
                if (currSlope == newSlope) count++;
                if (count > 2 && currSlope != newSlope) {
                    Point[] outputPoints = Arrays.copyOfRange(copy, j - count, j + 1);
                    outputPoints[outputPoints.length - 1] = copy[copy.length - 1];
                    Arrays.sort(outputPoints);
                    if (f.points[i].compareTo(outputPoints[0]) != 0) {
                        count = 1;
                        continue;
                    }
                    f.output(outputPoints, outputFile);
                    currSlope = newSlope;
                    count = 1;
                } else if (currSlope != newSlope) {
                    currSlope = newSlope;
                    count = 1;
                }
            }
        }
    }
}
