public class DepthLimitedSearch extends TreeSearch{

    protected int depth;

    public DepthLimitedSearch(int depth) {
        super(new FrontierStack());
        this.depth = depth;
    }

    public boolean pruneThisNode(SearchNode node){
        return check_grandparent(node) || (node.depth >= depth);
    }


}
