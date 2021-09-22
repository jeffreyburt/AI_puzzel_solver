public class Bitwise_tester {


    public static void main(String args[]){
        Tile_puzzle_state test_state = new Tile_puzzle_state(0);
        test_state.display();
        test_state.state = test_state.set_cleared_bits(test_state.state, 0, 1);
        test_state.display();
        test_state.state = test_state.set_cleared_bits(test_state.state, 1, 5);
        test_state.display();
        test_state.state = test_state.set_cleared_bits(test_state.state, 15, 15);
        test_state.display();
        test_state.state = test_state.set_cleared_bits(test_state.state, 4, 4);
        System.out.println(test_state.state);
    }
}
