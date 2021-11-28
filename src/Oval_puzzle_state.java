import java.util.ArrayList;

public class Oval_puzzle_state implements State {

    public ArrayList<Short> state;
    public ArrayList<Action> actions;

    public Oval_puzzle_state(ArrayList<Short> state, ArrayList<Action> actions){
        this.state = state;
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

    }



    @Override
    public int heuristic() {
        return 0;
    }

    @Override
    public void gen_state(int depth) {

    }
}
