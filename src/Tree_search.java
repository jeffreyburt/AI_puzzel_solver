import java.util.ArrayList;
import java.util.LinkedList;

abstract class TreeSearch implements Search {

    private Frontier frontier;

    public TreeSearch(Frontier frontier){
        this.frontier = frontier;
    }

    public boolean pruneThisNode(SearchNode node){
        return check_grandparent(node);
    }

    //return true when a node is the same as its grandparent node (aka, returns true when the node should be pruned)
    private boolean check_grandparent(SearchNode node){
        if(node.parent_node != null){
            SearchNode grandparent = node.parent_node.parent_node;
            if(grandparent != null){
                if(grandparent.state.equals(node.state)){
                    return true;
                }
            }
        }
        return false;
    }


    public Solution search(State startState){
        try {
            frontier.insert(new SearchNode(startState));
            while (!frontier.isEmpty()) {
                SearchNode node = frontier.removeNext();
                if (node.state.isGoalState()) {
                    SearchNode solution_node = node;
                    LinkedList<SearchNode> path = new LinkedList<>();
                    while (node.parent_node != null){
                        path.addFirst(node);
                        node = node.parent_node;
                    }
                    return new Solution(startState, solution_node.state, path);
                }else{
                    ArrayList<Action> children = node.state.listActions();
                    for (Action child_action:
                         children) {
                        //todo do I really need to make a new node before I check for duplicates?
                        SearchNode child_node = new SearchNode(node,child_action);
                        if(!check_grandparent(child_node)){
                            frontier.insert(child_node);
                        }
                    }
                }
            }
            System.out.println("ERROR: NO SOLUTION FOUND");
            System.out.println("A STRANGE GAME. THE ONLY WINNING MOVE IS NOT TO PLAY");
            return null;

        }catch (OutOfMemoryError error){
            frontier.clear();
            System.out.println("ERROR: NO MEMORY REMAINING, SOLUTION NOT FOUND");
            System.out.println("FAILED START STATE:");
            startState.display();
            return null;
        }
    }

}