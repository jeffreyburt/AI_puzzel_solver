public class IterativeDeepeningSearch implements Search{
    @Override
    public Solution search(State startState) {
        Solver solver = new Solver();
        while (true) {
            int i = 1;
            TreeSearch search_algorithm = new DepthLimitedSearch(i);
            Solution solution = solver.solve(startState, search_algorithm);
            if(solution != null){
                return solution;
            }
            i ++;
        }
    }
}
