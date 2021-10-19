public class DepthLimitedSearch extends TreeSearch{

    private int depth;

    public DepthLimitedSearch(int depth) {
        super(new FrontierStack());
        this.depth = depth;
    }

    public boolean pruneThisNode(SearchNode node){
        return super.check_grandparent(node) || (node.depth >= depth);
    }


}
