import java.util.ArrayList;

interface State {
    // public constructor (maybe with inputs) that starts at a legal initial state

    // returns a list of the legal actions possible from this state
    ArrayList<Action> listActions();

    // returns true if this state is a Goal State, and false otherwise
    boolean isGoalState();

    // displays a human-readable form of this state
    void display();

    // returns a new state that exactly duplicates the current one
    State duplicate();

    // applies the given action to the current state
    void performAction(Action action);

    // rolls back the given action from the current state
    // commented out for now, but documented in case we need it later
    // public void undoAction(Action action);

    // returns true if the otherState is the same as this one
    //   it is safe to cast otherState to your state class
    boolean equals(Object otherState);

    int heuristic();

    void gen_state(int depth);
}

interface Action {
    // displays a human-readable form of this action
    void display();

    // returns the cost of taking this action (often 1)
    int getCost();
}

interface Frontier {
    // removes all elements from the Frontier
    void clear();

    // returns true if the Frontier has no elements
    boolean isEmpty();

    // adds a new node to the Frontier
    void insert(SearchNode node);

    // removes and returns the next node in the Frontier
    SearchNode removeNext();
}

interface Search {

    Solution search(State startState);

    String toString();// returns name of search algorithm, like "BFS", "DFS", etc.
}
