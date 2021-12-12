import java.util.ArrayList;

public class Oval_puzzle_testing {


    public static void main(String[] args) {

        int solution_depth = 10;

        Solver solver = new Solver();
        int array[] = new int[]{1,4,17,18,6,13,20,10,11,12,16,8,7,5,9,14,3,19,15,2};
        State state = new Oval_puzzle_state(array);
        //State state = new Oval_puzzle_state();
        state.gen_state(solution_depth);
        System.out.println("Started testing");
        Solution solution = solver.solve(state, new ID_A_Star());
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
