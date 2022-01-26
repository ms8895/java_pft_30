package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;


//Удалить комменты если все норм!!!
public class PointTest {
    @Test
    public void testDistance() {
        Point a = new Point(4, 9, 10, 10);
        //Второй объект и Assert для примера как было
        //Point b = new Point(10, 10);
        //Assert.assertEquals(Point.distance(a, b), 6.082762530298219);
        Assert.assertEquals(a.distance(), 6.082762530298219);
    }

    @Test
    public void testZeroDistance() {
        Point c = new Point(5, 5, 5, 5);
        //Point d = new Point(5, 5);
        Assert.assertEquals(c.distance(), 0);
    }

    @Test
    public void testMinDistance() {
        Point e = new Point(6, 1, 6, 2);
        //Point f = new Point(6, 2);
        Assert.assertEquals(e.distance(), 1.0);
    }
}
