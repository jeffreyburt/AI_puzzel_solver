import java.util.LinkedList;

public class FrontierQueue implements Frontier {


    private LinkedList<SearchNode> frontier_queue = new LinkedList<>();

    @Override
    public void clear() {
        frontier_queue.clear();
    }

    @Override
    public boolean isEmpty() {
        return frontier_queue.isEmpty();
    }

    @Override
    public void insert(SearchNode node) {
        frontier_queue.addLast(node);
    }

    @Override
    public SearchNode removeNext() {
        return frontier_queue.pop();
    }
}
