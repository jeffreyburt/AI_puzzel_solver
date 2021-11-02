import java.util.LinkedList;
import java.util.PriorityQueue;

public class Frontier_Priority_Queue implements Frontier{

    private PriorityQueue<SearchNode> frontier_queue = new PriorityQueue<>();

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
        frontier_queue.add(node);
    }

    @Override
    public SearchNode removeNext() {
        return frontier_queue.poll();
    }
}
