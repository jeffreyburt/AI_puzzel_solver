import java.util.ArrayList;
import java.util.BitSet;

public class Sudoku_tile {
    protected short num;
    public boolean mutable;
    private final ArrayList<Sudoku_set> sets;
    public ArrayList<Short> possible_nums;
    protected int x;
    protected int y;

    public Sudoku_tile(short num, boolean mutable, int x, int y) {
        this.num = num;
        this.mutable = mutable;
        sets = new ArrayList<>();
        possible_nums = new ArrayList<>();
        this.x = x;
        this.y = y;
    }

    public void change_num(short new_num) throws UnsupportedOperationException {
        if (mutable) {
            short old_num = num;
            num = new_num;
            for (Sudoku_set set :
                    sets) {
                set.change_num(old_num, num);
            }

            for (Sudoku_set set :
                    sets) {
                set.recalculate_tiles();
            }
        } else {
            throw new UnsupportedOperationException();
        }
    }

    public void add_set(Sudoku_set set) {
        sets.add(set);
    }


    public void calculate_available_nums() {
        possible_nums.clear();

        BitSet set0 = sets.get(0).available_nums;
        set0.and(sets.get(1).available_nums);
        set0.and(sets.get(2).available_nums);

        //I stole this code from the java doc
        for (int i = set0.nextSetBit(0); i >= 0; i = set0.nextSetBit(i + 1)) {
            possible_nums.add((short) i);
        }

    }
}
