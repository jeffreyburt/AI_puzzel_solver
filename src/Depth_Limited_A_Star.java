public class Depth_Limited_A_Star extends DepthFirstSearch{

    private int depth;

    protected int min_depth_above_threshold = Integer.MAX_VALUE;

    public Depth_Limited_A_Star(int depth){
        super();
        this.depth = depth;
    }

    public void setDepth(int depth){
        this.depth = depth;
        min_depth_above_threshold = Integer.MAX_VALUE;
    }

    @Override
    public boolean pruneThisNode(SearchNode node) {
        int heuristic = node.evaluate();
        if(heuristic > depth && heuristic < min_depth_above_threshold){
            min_depth_above_threshold = heuristic;
        }
        return check_grandparent(node) || (heuristic > depth);
    }


}
