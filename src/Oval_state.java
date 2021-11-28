import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Oval_state implements State{

    public ArrayList<Short> state;
    private ArrayList<Action> actions;

    public Oval_state(ArrayList<Short> state, ArrayList<Action> actions){
        this.state = state;
        this.actions = actions;
    }

    public Oval_state(ArrayList<Short> state){
        this.state = state;
        precalc_actions();
    }

    public Oval_state(){
        state = new ArrayList<>();
        for (short i = 0; i < 20; i++) {
            state.add(i);
        }
        precalc_actions();
    }

    @Override
    public ArrayList<Action> listActions() {
        return actions;
    }

    private void precalc_actions(){
        actions = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            actions.add(new Oval_action(i));
        }
    }


    @Override
    public boolean isGoalState() {
        for (int i = 0; i < 20; i++) {
            if(state.get(i) != i) return false;
        }
        return true;
    }

    @Override
    public void display() {
        System.out.println(state);
    }

    @Override
    public State duplicate() {
        return new Oval_state(state, actions);
    }

    @Override
    public void performAction(Action action) {
        int switch_index = ((Oval_action) action).switch_index;

        Collections.swap(state,switch_index,get_adjusted_index(switch_index + 3));
        Collections.swap(state, get_adjusted_index(switch_index + 1), get_adjusted_index(switch_index + 2));
    }

    //links the front and back of the array list, so a call to the index behind the final index will return the first index
    private int get_adjusted_index(int index){
        return index % 20;
    }

    @Override
    public int heuristic() {
        return 0;
    }

    @Override
    public void gen_state(int depth) {
        Random random = new Random();
        for (int i = 0; i < depth; i++) {
            performAction(actions.get(random.nextInt(actions.size())));
        }
        display();
    }
}
