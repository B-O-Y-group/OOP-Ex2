import api.NodeData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class NodeIter implements Iterator<NodeData> {

    public Collection<NodeData> list;
    int index = 0;

    public NodeIter(Collection<NodeData> list) {
        this.list =list;
    }

    @Override
    public boolean hasNext() {
        return index < list.size();
    }

    @Override
    public NodeData next() {
//        NodeData next = this.list;
//        this.index++;
        return next();
    }
}
