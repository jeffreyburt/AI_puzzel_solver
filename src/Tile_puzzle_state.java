import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class Tile_puzzle_state implements State{

    /*
    Bitwise state storage conventions:
    The rightmost 4 bits in the long will code for the top left number in our  4x4 grid
    then numbers are stored in the long in the order of a solver puzzle
     */
    public long state;



    public Tile_puzzle_state(long state){
        this.state = state;
    }




    @Override
    public ArrayList<Action> listActions() {
        ArrayList<Action> action_list = new ArrayList<>();

        if(!action_list.isEmpty()){
            return action_list;
        }
        int zero_index = find_zero();

        //if 0 is not on the left
        if(zero_index % 4 != 0){
            action_list.add(new Tile_puzzle_action(zero_index, zero_index - 1));
        }

        if((zero_index + 1) % 4 != 0){
            action_list.add(new Tile_puzzle_action(zero_index, zero_index + 1));
        }

        if(!(zero_index < 4)){
            action_list.add(new Tile_puzzle_action(zero_index, zero_index - 4));
        }

        if(!(zero_index > 11)){
            action_list.add(new Tile_puzzle_action(zero_index, zero_index + 4));
        }
        return action_list;
    }

    private int find_zero() throws NoSuchElementException {
        for (int i = 0; i < 16; i++) {
            if(get_num(i) == 0){
                return i;
            }
        }
        throw new NoSuchElementException();
    }


    @Override
    public boolean isGoalState() {
        for (int i = 0; i < 16; i++) {
            if(get_num(i) != i){
                return false;
            }
        }
        return true;
    }

    
    private int get_num(int index_num){
        long state_copy = state >> (index_num * 4);
        return (int) (state_copy & 15);
    }


    @Override
    public void display() {
        int print_array[][] = new int[4][4];
        int array_index = 0;
        for (int i = 0; i <4; i++) {
            for (int j = 0; j < 4; j++) {
                print_array[i][j] = get_num(array_index);
                array_index ++;
            }

        }
        //I stole this code from stack overflow
        System.out.println(Arrays.deepToString(print_array).replace("], ", "]\n"));
        System.out.println(Long.toHexString(state));
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
        int total = 0;
        for (int i = 0; i < 16; i++) {
            int num_at_index = get_num(i);
            total += calc_x_distance(i, num_at_index) + calc_y_distance(i, num_at_index);
        }
        return total;
    }

    @Override
    public void gen_state(int depth) {
        long prev_state = state;
        for (int i = 0; i < depth; i++) {
            ArrayList<Action> action_list = listActions();
            int random_index = (int) (Math.random() * action_list.size());
            performAction(action_list.get(random_index));
            if(state == prev_state){
                System.out.println("repeat");
                depth ++;
            }
        }
    }


    private int calc_y_distance(int index_1, int index_2){
        return Math.abs((index_1/4)-(index_2/4));
    }

    private int calc_x_distance(int index_1, int index_2){
        return Math.abs((index_1 % 4)-(index_2 % 4));
    }


    //todo change this to private when testing is done
    private long clear_bits(long input, int index_num){
        return (~ ( ((long) 15) << (4 * index_num))) & input;
    }

    private long set_cleared_bits(long input, int index_num, int number_to_set){
        return (input | (( (long) number_to_set) << (4 * index_num)));
    }

    public void change_bit(int index_num, int num_to_set){
        state = clear_bits(state, index_num);
        state = set_cleared_bits(state, index_num, num_to_set);
    }


}
