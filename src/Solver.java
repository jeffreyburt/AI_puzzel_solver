public class Solver {

    public static Solution solve(State start_state, Search strat){
        return strat.search(start_state);
    }


}
