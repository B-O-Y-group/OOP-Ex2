import api.NodeData;

import java.util.Iterator;
import java.util.function.Consumer;

public class NodeIter implements Iterator<NodeData> {
    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public NodeData next() {
        return null;
    }

    @Override
    public void remove() {

    }

    @Override
    public void forEachRemaining(Consumer<? super NodeData> action) {

    }
}
