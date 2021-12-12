import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

class Point3DTest {

    @Test
    void distance() {
        Point3D a = new Point3D(7, 4, 3);
        Point3D b = new Point3D(17, 6, 2);
        double ans = 10.246950765959598;
        assertEquals(a.distance(b), ans);
    }

    @Test
    void testToString() {
        Point3D a = new Point3D(7, 4, 3);
        String ans = "Point3D{x=7.0, y=4.0, z=3.0}";
        assertEquals(a.toString(), ans);
    }
}


