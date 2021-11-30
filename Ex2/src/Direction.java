import java.util.HashMap;

/**
 * in = <src , weight >
 * out = < dest, weight>
 *
 **/


public class Direction {


    HashMap<Integer, Edge> in, out;


    public Direction() {
        in = new HashMap<>();
        out = new HashMap<>();
    }


}
