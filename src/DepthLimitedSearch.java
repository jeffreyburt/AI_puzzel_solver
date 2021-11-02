public class DepthLimitedSearch extends Tree_search {

    protected int depth;

    public DepthLimitedSearch(int depth) {
        super(new FrontierStack());
        this.depth = depth;
    }

    public boolean pruneThisNode(SearchNode node){
        return check_grandparent(node) || (node.depth >= depth);
    }


}
