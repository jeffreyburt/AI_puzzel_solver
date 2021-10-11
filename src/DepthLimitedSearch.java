public class DepthLimitedSearch extends TreeSearch{

    private int depth;

    public DepthLimitedSearch(int depth) {
        super(new FrontierStack());
        this.depth = depth;
    }

    public boolean pruneThisNode(SearchNode node){
        return check_grandparent(node) || (node.depth >= depth);
    }

    //todo find a better way to do this
    //todo this check isn't working
    private boolean check_grandparent(SearchNode node){
        if(node.parent_node != null){
            SearchNode grandparent = node.parent_node.parent_node;
            if(grandparent != null){
                return grandparent.state.equals(node.state);
            }
        }
        return false;
    }
}
