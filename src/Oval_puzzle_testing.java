public class Oval_puzzle_testing {


    public static void main(String[] args) {

        int solution_depth = 10;

        Solver solver = new Solver();
        State state = gen_start_state(solution_depth);
        Solution solution = solver.solve(state, new IterativeDeepeningSearch());
        //solution.display_final_state();
        solution.display_path();
    }

    private static Oval_puzzle_state gen_start_state(int depth) {
        Oval_puzzle_state base_state = new Oval_puzzle_state();
        base_state.gen_state(depth);
        //base_state.display();

        return base_state;
    }


}
