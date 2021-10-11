public class Main {


    public static void main(String args[]){

        int soution_depth = 1;

        TreeSearch search_algorithm = new DepthLimitedSearch(10);

        Solver solver = new Solver();
        State state = new Tile_puzzle_state(gen_start_state(soution_depth));
        Solution solution = solver.solve(state,search_algorithm);
        //solution.display_final_state();
        solution.display_path();
    }

    private static long gen_start_state(int depth){
        Tile_puzzle_state base_state = new Tile_puzzle_state(0);
        for (int i = 0; i < 16; i++) {
            base_state.change_bit(i,i);
        }
        base_state.gen_state(depth);
        base_state.display();

        return base_state.state;
    }


}
