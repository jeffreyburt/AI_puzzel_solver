public class IterativeDeepeningSearch implements Search{
    @Override
    public Solution search(State startState) {
        Solver solver = new Solver();
        int i = 1;
        while (true) {
            TreeSearch search_algorithm = new DepthLimitedSearch(i);
            Solution solution = solver.solve(startState, search_algorithm);
            if(solution != null){
                return solution;
            }
            i += 1;
        }
    }
}
