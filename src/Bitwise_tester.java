public class Bitwise_tester {


    public static void main(String[] args) {
        Tile_puzzle_state test_state = new Tile_puzzle_state(0);
        for (int i = 0; i < 16; i++) {
            test_state.change_bit(i, i);
        }
        test_state.display();
        for (int i = 0; i < 16; i++) {
            test_state.change_bit(i, 15 - i);
        }
        test_state.display();


    }
}
