public class Solver {

    public Solution solve(State start_state, Search strat){
        return strat.search(start_state);
    }


}
