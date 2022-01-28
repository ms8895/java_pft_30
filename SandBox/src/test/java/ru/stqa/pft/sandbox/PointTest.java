package ru.stqa.pft.sandbox;


import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTest {
    @Test
    public void testDistance() {
        Point a = new Point(4, 9);
        Point b = new Point(10, 10);
        Assert.assertEquals(a.distance(b), 6.082762530298219);
    }

    @Test
    public void testZeroDistance() {
        Point c = new Point(5, 5);
        Point d = new Point(5, 5);
        Assert.assertEquals(c.distance(d), 0);
    }

    @Test
    public void testMinDistance() {
        Point e = new Point(6, 1);
        Point f = new Point(6, 2);
        Assert.assertEquals(e.distance(f), 1.0);
    }
}
