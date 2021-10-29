import java.util.ArrayList;

public class Sudoku_state implements State{

    //todo need efficient way to do this
    @Override
    public ArrayList<Action> listActions() {
        return null;
    }

    @Override
    public boolean isGoalState() {
        return false;
    }

    @Override
    public void display() {

    }

    @Override
    public State duplicate() {
        return null;
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
