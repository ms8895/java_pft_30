package ru.stqa.pft.sandbox;

public class Point {
    public double x;
    public double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double distance(Point p2) {
        return Math.sqrt((p2.x - this.x) * (p2.x - this.x) + (p2.y - this.y) * (p2.y - this.y));
    }

/*public double distance1(p2){
        return*/
}
    /*public double distance() {
        return Math.sqrt((this.p2 - this.p1) * (this.p2 - this.p1) + (this.p2 - this.y1) * (this.y2 - this.y1));

    }*/

