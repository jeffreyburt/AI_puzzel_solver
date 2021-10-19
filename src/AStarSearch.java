public class AStarSearch extends TreeSearch{

    private int depth;

    public AStarSearch(int depth) {
        super(new FrontierPriorityQueue());
        this.depth = depth;
    }
    public boolean pruneThisNode(SearchNode node){
        return super.check_grandparent(node) || (node.depth >= depth);
    }

}
