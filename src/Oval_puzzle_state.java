import java.util.ArrayList;
import java.util.Collections;

public class Oval_puzzle_state implements State {

    public int state[];
    public ArrayList<Action> actions;

    public Oval_puzzle_state(int state[], ArrayList<Action> actions){
        this.state = state.clone();
        this.actions = actions;
    }

    public Oval_puzzle_state(){
        state = new int[20];
        for (int i = 0; i < 20; i++) {
            state[i] = i;
        }
        calc_actions();
    }

    public Oval_puzzle_state(int state[]){
        this.state = state;
        calc_actions();
    }

    private void calc_actions(){
        actions = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            actions.add(new Oval_puzzle_action(i));
        }
    }

    @Override
    public ArrayList<Action> listActions() {
        return actions;
    }

    @Override
    public boolean isGoalState() {
        for (int i = 0; i < 20; i++) {
            if(state[i] !=  i) return false;
        }
        return true;
    }

    @Override
    public void display() {
        for (int num :
                state) {
            System.out.print(num + ", ");
        }
        System.out.println();
    }

    @Override
    public State duplicate() {
        return new Oval_puzzle_state(state, actions);
    }

    @Override
    public void performAction(Action action) {
        int first_index = ((Oval_puzzle_action) action).swap_index;
        swap(first_index, get_corrected_index(first_index + 3));
        swap(get_corrected_index(first_index + 1), get_corrected_index(first_index + 2));
    }

    private void swap(int index1, int index2){
        int temp = state[index1];
        state[index1] = state[index2];
        state[index2] = temp;
    }

    private int get_corrected_index(int index){ return index % 20;}

    @Override
    public int heuristic() {
        int a = 1;

        int total = 0;
        for (int i = 0; i < 16; i++) {
            if(state[i] != i) total ++;
        }
        return total / a;
    }

    @Override
    public void gen_state(int depth) {
        int[] grandparent_state;
        int[] prev_state = null;


        for (int i = 0; i < depth; i++) {
            grandparent_state = prev_state;
            prev_state = state.clone();

            ArrayList<Action> action_list = listActions();
            int random_index = (int) (Math.random() * action_list.size());
            performAction(action_list.get(random_index));
            if (state.equals(grandparent_state)) {
                //System.out.println("repeat " + i);
                depth += 2;
            }
        }
    }

}
