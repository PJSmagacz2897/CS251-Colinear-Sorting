/**
 * Created by Jimmy on 2/19/2017.
 */

import java.util.*;

public class Fast {

    Point[] points;
    int size;

    public Fast(int size) {
        this.size = size;
        this.points = new Point[this.size];
    }

    public void add(int x, int y, int pos) {
        Point p = new Point(x,y);
        points[pos] = p;
    }


    public void output(Point[] points) {
        String output = "" + points.length + ":(";
        for(int i = 0; i < points.length; i++) {
            output += points[i].getX() + ", " + points[i].getY() + ")";
            if(i != points.length - 1) {
                output += " -> (";
            }
        }
        System.out.println(output);
       // outputFile.println(output);
    }

    public static void main(String[] args) {
        int n = 5;
        int position = 0;
        Fast f = new Fast(n);
        f.add(18000, 13000, position++);
        f.add(18000, 23000, position++);
        f.add(18000, 26000, position++);
        f.add(18000, 27000, position++);
        f.add(18000, 30000, position++);

      // Out outputFile = new Out("visualPoints.txt");
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
                    f.output(outputPoints);
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
