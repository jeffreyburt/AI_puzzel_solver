import java.util.List;

public class Solution {

    private State  start_state;
    private State finish_state;
    private List<SearchNode> path;

    public Solution(State start_state, State finish_state, List<SearchNode> path){
        this.start_state = start_state;
        this.finish_state = finish_state;
        this.path = path;
    }

    public void display_final_state(){
        finish_state.display();
    }

    public void display_path(){
        for (SearchNode node:
             path) {
            State state = node.state;
            state.display();
        }
    }

    public int get_path_length(){
        return path.size() - 1;
    }




}
