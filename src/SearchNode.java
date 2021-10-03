import java.util.ArrayList;

public class SearchNode{
    public SearchNode parent_node;
    public int depth;
    public State state;
    public ArrayList<SearchNode> children;

    public SearchNode(SearchNode parent_node, Action action){
        this.parent_node = parent_node;
        depth = parent_node.depth + 1;
        State state_copy = parent_node.state.duplicate();
        state_copy.performAction(action);
        state = state_copy;
    }

    public SearchNode(State start_state){
        parent_node = null;
        depth = 0;
        state = start_state;
    }

    public int evaluate(){
        return 0;
        //todo implement this
    }

    public int compareTo(Object other_node){
       return 0;
       //todo implement this
    }

}
