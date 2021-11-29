import java.util.ArrayList;
import java.util.BitSet;

public class Sudoku_set {

    private final ArrayList<Sudoku_tile> tiles;
    protected BitSet available_nums;

    public Sudoku_set() {
        tiles = new ArrayList<>();
        available_nums = new BitSet(9);
        available_nums.flip(0, 8);
    }

    public void add_tile(Sudoku_tile tile) {
        tiles.add(tile);
        if (is_num_valid(tile.num)) available_nums.set(tile.num);
    }

    public void change_num(short removed_num, short new_num) {
        if (is_num_valid(removed_num)) available_nums.flip(removed_num);
        available_nums.flip(new_num);
    }

    //checks whether a number should be added to the set
    private boolean is_num_valid(short num) {
        return (num >= 0);
    }


    public void recalculate_tiles() {
        for (Sudoku_tile tile : tiles) {
            tile.calculate_available_nums();
        }
    }

    public boolean is_goal() {
        BitSet numbers = new BitSet(9);
        numbers.flip(0, 8);
        for (Sudoku_tile tile : tiles) {
            try {
                numbers.clear(tile.num);
            } catch (IndexOutOfBoundsException exception) {
                return false;
                //todo is this good use of try/catch??
            }
        }
        return numbers.nextSetBit(0) == -1;
    }
}
