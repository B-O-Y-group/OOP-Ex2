import api.GeoLocation;
import api.NodeData;
import jdk.swing.interop.SwingInterOpUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VertexTest {


    Vertex[] v;
    Point3D zero = new Point3D(0, 0, 0);
    Point3D one = new Point3D(1, 1, 0);
    Point3D two = new Point3D(2, 2, 0);
    Vertex a, b, c;



    @BeforeEach
     void beforeEach() {
        a = new Vertex(0, zero);
        a.setWeight(5);
        b = new Vertex(1, one);
        b.setWeight(6);
        c = new Vertex(2, two);
        c.setWeight(7);

        v = new Vertex[]{a, b, c};
    }




    @Test
    void getKey() {
        for (int i = 0; i < v.length; i++) {
            assertEquals(v[i].getKey(), i);
        }
    }


    @Test
    void getLocation() {
        assertEquals(a.getLocation(), zero);
        assertEquals(b.getLocation(), one);
        assertEquals(c.getLocation(), two);

    }

    @Test
    void setLocation() {
        GeoLocation test = new Point3D(3, 3, 0);
        Vertex V = new Vertex(3, new Point3D(5, 5, 0));
        V.setLocation(test);
        assertEquals(test, V.getLocation());

    }


    @Test
    void getTag() {
        int white = 0;

        for (int i = 0; i < v.length; i++) {
            assertEquals(v[i].getTag(), 0);
        }


    }


    @Test
    void getWeight() {
        assertEquals(5,a.getWeight());
        assertEquals(6,b.getWeight());
        assertEquals(7,c.getWeight());
    }

    @Test
    void setWeight() {
        Vertex test = new Vertex(5,new Point3D(1,2,3));
        test.setWeight(100);
        assertEquals(100,test.getWeight());
    }

    @Test
    void setTag() {
        int grey = 1;
        for (int i = 0; i < v.length; i++) {
            v[i].setTag(1);
            assertEquals(v[i].getTag(), grey);
        }


        int black = 2;
        for (int i = 0; i < v.length; i++) {
            v[i].setTag(2);
            assertEquals(v[i].getTag(), black);
        }
    }
}