import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
        assertEquals(e1.getWeight(), 1);
        assertEquals(e2.getWeight(), 4);
        assertEquals(e3.getWeight(),5);
    }

    @Test
    void getInfo() {
    }

    @Test
    void setInfo() {
    }

    @Test
    void getTag() {
    }

    @Test
    void setTag() {
    }
}