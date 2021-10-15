import java.util.ArrayList;
import java.util.Objects;

public class SillyPuzzle implements State {

    private static boolean is_id_search = true;

    public static void main(String[] args) {
        SillyPuzzle startState = new SillyPuzzle(33);
        if(!is_id_search) {
            Search searcher = new DepthLimitedSearch(10);
            Solution solution = searcher.search(startState);
            solution.display_path();  // or whatever means you want to display the solution
        }else{
            IterativeDeepeningSearch iterativeDeepeningSearch = new IterativeDeepeningSearch();
            Solution id_solution = iterativeDeepeningSearch.search(startState);
            id_solution.display_path();
        }
    }

    private int n;

    public SillyPuzzle(int n) {
        this.n = n;
    }

    public SillyPuzzle(SillyPuzzle oldPuzzle) {
        this.n = oldPuzzle.n;
    }

    @Override
    public ArrayList<Action> listActions() {
        ArrayList actions = new ArrayList<>();
        actions.add(new SillyPuzzleAction(-1));
        actions.add(new SillyPuzzleAction(+1));
        return actions;
    }

    @Override
    public boolean isGoalState() {
        return (n == 42);
    }

    @Override
    public void display() {
        System.out.println("the current number is " + n);
    }

    @Override
    public void performAction(Action action) {
        n = n + ((SillyPuzzleAction)action).delta;
    }

    @Override
    public State duplicate() {
        return new SillyPuzzle(this);
    }

    @Override
    public int heuristic() {
        return Math.abs(42 - n);
    }

    @Override
    public void gen_state(int depth) {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SillyPuzzle that = (SillyPuzzle) o;
        return n == that.n;
    }

}
           