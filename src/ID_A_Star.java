public class ID_A_Star implements Search {

    @Override
    public Solution search(State startState) {
        Solver solver = new Solver();
        int i = startState.heuristic();
        Depth_Limited_A_Star search_algorithm = new Depth_Limited_A_Star(i);
        while (true) {
            search_algorithm.setDepth(i);
            Solution solution = solver.solve(startState, search_algorithm);
            if (solution != null) {
                return solution;
            }
            i = search_algorithm.min_depth_above_threshold;
        }
    }

    /*
    run a series of Depth First Searches (DFS)
    evaluate each node with the A* style evaluation (distance from start + heuristic estimated distance to goal)
        during each DFS, prune nodes whose evaluation is greater than a threshold
        initialize the pruning threshold with the evaluation of the start state
        during each DFS, keep track of the smallest node evaluation that is greater than the current threshold, and use that as the threshold for the next DFS
     */
}
