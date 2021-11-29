import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class Tile_puzzle_state implements State {

    /*
    Bitwise state storage conventions:
    The rightmost 4 bits in the long will code for the top left number in our  4x4 grid
    then numbers are stored in the long in the order of a solver puzzle
     */
    public long state;


    public Tile_puzzle_state(long state) {
        this.state = state;
    }


    @Override
    public ArrayList<Action> listActions() {
        ArrayList<Action> action_list = new ArrayList<>();

        int zero_index = find_zero();

        //if 0 is not on the left
        if (zero_index % 4 != 0) {
            action_list.add(new Tile_puzzle_action(zero_index, zero_index - 1));
        }

        if ((zero_index + 1) % 4 != 0) {
            action_list.add(new Tile_puzzle_action(zero_index, zero_index + 1));
        }

        if (zero_index > 3) {
            action_list.add(new Tile_puzzle_action(zero_index, zero_index - 4));
        }

        if (zero_index < 12) {
            action_list.add(new Tile_puzzle_action(zero_index, zero_index + 4));
        }
        return action_list;
    }

    private int find_zero() throws NoSuchElementException {
        for (int i = 0; i < 16; i++) {
            if (get_num(i) == 0) {
                return i;
            }
        }
        throw new NoSuchElementException();
    }


    @Override
    public boolean isGoalState() {
        return state == -81985529216486896L;
    }


    private int get_num(int index_num) {
        long state_copy = state >> (index_num * 4);
        return (int) (state_copy & 15);
    }


    @Override
    public void display() {
        int[][] print_array = new int[4][4];
        int array_index = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                print_array[i][j] = get_num(array_index);
                array_index++;
            }

        }

        //I stole this code from stack overflow
        System.out.println(Arrays.deepToString(print_array).replace("], ", "]\n"));
        //System.out.println(Long.toHexString(state));
        System.out.println();
    }

    @Override
    public State duplicate() {
        return new Tile_puzzle_state(state);
    }

    @Override
    public void performAction(Action action) {

        Tile_puzzle_action casted_action = (Tile_puzzle_action) action;

        //numbers to set and what index to set them
        int set_index1 = casted_action.swap_index1;
        int set_index2 = casted_action.swap_index2;

        int set_num1 = get_num(set_index2);
        int set_num2 = get_num(set_index1);

        state = clear_bits(state, set_index1);
        state = clear_bits(state, set_index2);

        state = set_cleared_bits(state, set_index1, set_num1);
        state = set_cleared_bits(state, set_index2, set_num2);
    }

    @Override
    public int heuristic() {

        //standard heuristic calculation
        int total = 0;
        for (int i = 0; i < 16; i++) {
            int num_at_index = get_num(i);
            if (num_at_index != 0) {
                total += calc_x_distance(i, num_at_index) + calc_y_distance(i, num_at_index);
            }
        }

        //upper left calculation of last move rule
        //commented out to avoid overlap with linear conflict
//        if ((find_num(1) % 4) != 0) {
//            if (find_num(4) > 3) total += 2;
//        }

        //linear conflict rule
        //horizontal loops
        //todo this code probably has a bug in it
        for (int first_index_of_row = 0; first_index_of_row < 16; first_index_of_row += 4) {
            for (int tile_in_row = first_index_of_row; tile_in_row < first_index_of_row + 4; tile_in_row++) {

                int working_tile_num = get_num(tile_in_row);
                if (tiles_in_same_row(working_tile_num, first_index_of_row) && working_tile_num != 0) {
                    for (int j = tile_in_row + 1; j < first_index_of_row + 4; j++) {

                        int compare_tile_num = get_num(j);
                        if (tiles_in_same_row(compare_tile_num, first_index_of_row) && compare_tile_num != 0) {
                            if (compare_tile_num < working_tile_num) {
                                total += 2;
                            }
                        }
                    }
                }
            }
        }

        //vertical loops
        for (int first_index_of_col = 0; first_index_of_col < 4; first_index_of_col++) {
            for (int tile_in_col = first_index_of_col; tile_in_col < 16; tile_in_col += 4) {

                int working_tile_num = get_num(tile_in_col);
                if (tiles_in_same_col(working_tile_num, first_index_of_col) && working_tile_num != 0) {
                    for (int j = tile_in_col + 4; j < 16; j += 4) {

                        int compare_tile_num = get_num(j);
                        if (tiles_in_same_col(compare_tile_num, first_index_of_col) && compare_tile_num != 0) {
                            if (compare_tile_num < working_tile_num) {
                                total += 2;
                            }
                        }
                    }
                }
            }
        }

        return total;
    }

    //
    private boolean tiles_in_same_row(int index1, int index2) {
        return index1 / 4 == index2 / 4;
    }

    private boolean tiles_in_same_col(int index1, int index2) {
        return index1 % 4 == index2 % 4;
    }


    @Override
    public void gen_state(int depth) {
        long grandparent_state;
        long prev_state = 0;
        for (int i = 0; i < depth; i++) {

            grandparent_state = prev_state;
            prev_state = state;

            ArrayList<Action> action_list = listActions();
            int random_index = (int) (Math.random() * action_list.size());
            performAction(action_list.get(random_index));
            if (state == grandparent_state) {
                //System.out.println("repeat");
                depth += 2;
            }
        }
    }

    private int find_num(int number) throws NoSuchElementException {
        for (int i = 0; i < 16; i++) {
            if (get_num(i) == number) return i;
        }
        throw new NoSuchElementException();
    }


    private int calc_y_distance(int index_1, int index_2) {
        return Math.abs((index_1 / 4) - (index_2 / 4));
    }

    private int calc_x_distance(int index_1, int index_2) {
        return Math.abs((index_1 % 4) - (index_2 % 4));
    }


    private long clear_bits(long input, int index_num) {
        return (~(((long) 15) << (4 * index_num))) & input;
    }

    private long set_cleared_bits(long input, int index_num, int number_to_set) {
        return (input | (((long) number_to_set) << (4 * index_num)));
    }

    public void change_bit(int index_num, int num_to_set) {
        state = clear_bits(state, index_num);
        state = set_cleared_bits(state, index_num, num_to_set);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tile_puzzle_state that = (Tile_puzzle_state) o;
        return state == that.state;
    }

}
