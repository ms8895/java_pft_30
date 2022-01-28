package ru.stqa.pft.sandbox;

public class FirstProgram {

    public static void main(String[] args) {

        hello("user");
        hello("world");

        Square s = new Square(5);
        //System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());

        Rectangle r = new Rectangle(6, 7);
        //System.out.println("Площадь прямоугольника со стороной " + r.a + " и " + r.b + " = " + r.area());

        Point p1 = new Point(2, 4);
        Point p2 = new Point(4, 6);
        System.out.println("Расстояние между двумя точками" + " = " + p1.distance(p2));

    }

    public static void hello(String somebody) {
        System.out.println("Hello " + somebody + "!!!");
    }

}