public class Oval_puzzle_testing {


    public static void main(String[] args) {

        int soution_depth = 1;

        Tree_search search_algorithm = new BreadthFirstSearch();

        Solver solver = new Solver();
        State state = gen_start_state(soution_depth);
        Solution solution = solver.solve(state, search_algorithm);
        //solution.display_final_state();
        solution.display_path();
    }

    private static Oval_puzzle_state gen_start_state(int depth) {
        Oval_puzzle_state base_state = new Oval_puzzle_state();
        base_state.gen_state(depth);
        base_state.display();

        return base_state;
    }


}
