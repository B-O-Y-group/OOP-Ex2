//import org.junit.Test;

import api.EdgeData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

class EdgeTest {

    Edge [] arr;
    Edge e1,e2,e3;


    @BeforeEach
     void beforeEach() {

        e1 = new Edge(0,1,1);
        e2 = new Edge(1,2,4);
        e3 = new Edge(2,0,5);
        arr = new Edge[]{e1, e2, e3};


    }

    @Test
    void getSrc() {
        for (int i = 0; i < arr.length ; i++) {
            assertEquals(arr[i].getSrc() , i);
        }
    }


    @Test
    void getDest() {
     assertEquals(e1.getDest(), 1);
     assertEquals(e2.getDest(), 2);
     assertEquals(e3.getDest(),0);
    }

    @Test
    void getWeight() {
        Assertions.assertEquals(e1.getWeight(), 1);
        Assertions.assertEquals(e2.getWeight(), 4);
        Assertions.assertEquals(e3.getWeight(),5);
    }

    @Test
    void getInfo() {
        assertEquals("src: " + e1.src + " dest: " + e1.dest + " weight: " + e1.weight,e1.getInfo() );
        assertEquals("src: " + e2.src + " dest: " + e2.dest + " weight: " + e2.weight,e2.getInfo() );
        assertEquals("src: " + e3.src + " dest: " + e3.dest + " weight: " + e3.weight,e3.getInfo() );
    }

    @Test
    void setInfo() {
        e1.setInfo("");
        assertEquals("",e1.getInfo());
    }

    @Test
    void getTag() {
    assertEquals(0,e1.getTag());
    assertEquals(0,e2.getTag());
    assertEquals(0,e3.getTag());
    }

    @Test
    void setTag() {
        e1.setTag(1);
        e2.setTag(2);
        e3.setTag(3);
        assertEquals(1,e1.getTag());
        assertEquals(2,e2.getTag());
        assertEquals(3,e3.getTag());

    }
}