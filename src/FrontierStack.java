import java.util.Stack;

public class FrontierStack implements Frontier {

    private Stack<SearchNode> frontier_stack = new Stack<>();


    @Override
    public void clear() {
        frontier_stack.clear();
    }

    @Override
    public boolean isEmpty() {
        return frontier_stack.isEmpty();
    }

    @Override
    public void insert(SearchNode node) {
        frontier_stack.add(node);
    }

    @Override
    public SearchNode removeNext() {
        return frontier_stack.pop();
    }
}
