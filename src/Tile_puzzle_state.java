import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Tile_puzzle_state implements State{

    /*
    Bitwise state storage conventions
    The rightmost 4 bits in the long will code for the top left number in our  4x4 grid
    then numbers are stored in the long in the order of a solver puzzle
     */
    public long state;
    private ArrayList<Tile_puzzle_action> action_list = new ArrayList<>();

    public Tile_puzzle_state(long state){
        this.state = state;
    }

    public Tile_puzzle_state(long state, ArrayList<Tile_puzzle_action> action_list){
        this.state = state;
        this.action_list = action_list;
    }

    @Override
    //todo swap this for an arraylist/regular list?
    public ArrayList<Tile_puzzle_action> listActions() {
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

    //todo cast this to a shorter data type
    private long get_num(int index_num){
        long state_copy = state << (index_num * 4);
        return (state_copy & 15);
    }


    @Override
    //todo turn this into an array or not?
    public void display() {

    }

    @Override
    public State duplicate() {
        return new Tile_puzzle_state(state, action_list);
    }

    @Override
    public void performAction(Action action) {
        action_list.clear();
        //todo implement this
    }


}
