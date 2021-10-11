import java.util.ArrayList;

public class SearchNode{
    public SearchNode parent_node;
    public int depth;
    public int cost;
    public Action action;
    public State state;
    public ArrayList<SearchNode> children;

    public SearchNode(SearchNode parent_node, Action action){
        this.parent_node = parent_node;
        depth = parent_node.depth + 1;
        cost = parent_node.cost + action.getCost();
        State state_copy = parent_node.state.duplicate();
        state_copy.performAction(action);
        state = state_copy;
        this.action = action;
    }

    public SearchNode(State start_state){
        parent_node = null;
        depth = 0;
        state = start_state;
        cost = 0;
    }

    public int evaluate(){
        return state.heuristic() + cost;
    }

    public int compareTo(Object other_node){
        SearchNode other_nodee = (SearchNode) other_node;
       if(evaluate() < other_nodee.evaluate()){
           return 1;
       }else if(evaluate() == other_nodee.evaluate()){
           return 0;
       }
       return -1;
    }

}
