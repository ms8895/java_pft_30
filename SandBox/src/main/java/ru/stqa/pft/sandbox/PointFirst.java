package ru.stqa.pft.sandbox;

//Закомментированные участки для примера, как было
// Удалить если все норм!!!
/*
public class Point {

    public static void main(String[] args) {
        */
/*Point p1 = new Point(2, 4);*//*

        Point p1 = new Point(6, 8);
        Point p2 = new Point(2, 4);
        System.out.println(p1.distance());
    }

    public double x1;
    public double y1;
    public double x2;
    public double y2;

    public Point(double x1, double y1) {
        this.x1 = x1;
        this.y1 = y1;
    }

    // Попытка разделить, не правильно, требовала void
    public void p2(double x2, double y2) {
        this.x2 = x2;
        this.y2 = y2;
    }

    //Убран static, параметры в distance(). Добавлен this, ссылается на объект.
    public double distance() {
        return Math.sqrt((this.x2 - this.x1) * (this.x2 - this.x1) + (this.y2 - this.y1) * (this.y2 - this.y1));
    }

    // Функция использовалась в первом коммите. ↑ переделана
    */
/*public static double distance(Point p1, Point p2) {
        return Math.sqrt((p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y));
    }*//*


}
*/

