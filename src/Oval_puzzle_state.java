import java.util.ArrayList;
import java.util.Collections;

public class Oval_puzzle_state implements State {

    public ArrayList<Short> state;
    public ArrayList<Action> actions;

    public Oval_puzzle_state(ArrayList<Short> state, ArrayList<Action> actions){
        this.state = new ArrayList<>(state);
        this.actions = actions;
    }

    public Oval_puzzle_state(){
        state = new ArrayList<>();
        for (short i = 0; i < 20; i++) {
            state.add(i);
        }
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
            if(state.get(i) != (short) i) return false;
        }
        return true;
    }

    @Override
    public void display() {
        System.out.println(state);
    }

    @Override
    public State duplicate() {
        return new Oval_puzzle_state(state, actions);
    }

    @Override
    public void performAction(Action action) {
        int first_index = ((Oval_puzzle_action) action).swap_index;
        Collections.swap(state, first_index, get_corrected_index(first_index + 3));
        Collections.swap(state, get_corrected_index(first_index + 1), get_corrected_index(first_index + 2));
    }

    private int get_corrected_index(int index){ return index % 20;}

    @Override
    public int heuristic() {
        return 0;
    }

    @Override
    public void gen_state(int depth) {
        ArrayList<Short> grandparent_state;
        ArrayList<Short> prev_state = null;


        for (int i = 0; i < depth; i++) {
            grandparent_state = prev_state;
            prev_state = new ArrayList<>(state);

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
