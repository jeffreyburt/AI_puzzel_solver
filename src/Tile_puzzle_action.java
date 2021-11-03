public class Tile_puzzle_action implements Action {

    public int swap_index1;
    public int swap_index2;

    public Tile_puzzle_action(int int_to_swap1, int int_to_swap2) {
        swap_index1 = int_to_swap1;
        swap_index2 = int_to_swap2;
    }

    @Override
    public void display() {
        System.out.println("Moved tile in place " + (swap_index1) + " to place " + (swap_index2));
    }

    @Override
    public int getCost() {
        return 1;
    }


}
