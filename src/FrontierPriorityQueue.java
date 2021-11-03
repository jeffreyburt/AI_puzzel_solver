import java.util.PriorityQueue;

public class FrontierPriorityQueue implements Frontier {

    private final PriorityQueue<SearchNode> priorityQueue = new PriorityQueue<>();

    @Override
    public void clear() {
        priorityQueue.clear();
    }

    @Override
    public boolean isEmpty() {
        return priorityQueue.isEmpty();
    }

    @Override
    public void insert(SearchNode node) {
        priorityQueue.add(node);
    }

    @Override
    public SearchNode removeNext() {
        return priorityQueue.poll();
    }
}
