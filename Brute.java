/**
 * Created by Jimmy on 2/17/2017.
 */

import java.util.*;

public class Brute {

    public int size;
    public Point[] points;

    public Brute(int size) {
        this.size = size;
        this.points = new Point[size];
    }

    public void add(int x, int y, int pos) {
        Point p = new Point(x,y);
        points[pos] = p;
    }

    public void output(Point p, Point q, Point r, Point s) {
        System.out.print("4:(");
        System.out.print(p.getX() + ", " + p.getY() + ") -> (");
        System.out.print(q.getX() + ", " + q.getY() + ") -> (");
        System.out.print(r.getX() + ", " + r.getY() + ") -> (");
        System.out.print(s.getX() + ", " + s.getY() + ")\n");
    }

    public static void main(String[] args) {
        int n = 6;
        int position = 0;
        Brute b = new Brute(n);
        b.add(19000, 10000, position++);
        b.add(18000, 10000, position++);
        b.add(32000, 10000, position++);
        b.add(21000, 10000, position++);
        b.add(1234, 5678, position++);
        b.add(14000, 10000, position);
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
