import java.awt.*;
import java.util.ArrayList;

interface State
{
    // public constructor (maybe with inputs) that starts at a legal initial state

    // returns a list of the legal actions possible from this state
    public ArrayList<Action> listActions();

    // returns true if this state is a Goal State, and false otherwise
    public boolean isGoalState();

    // displays a human-readable form of this state
    public void display();

    // returns a new state that exactly duplicates the current one
    public State duplicate();

    // applies the given action to the current state
    public void performAction(Action action);

    // rolls back the given action from the current state
    // commented out for now, but documented in case we need it later
    // public void undoAction(Action action);

    // returns true if the otherState is the same as this one
    //   it is safe to cast otherState to your state class
    public boolean equals(Object otherState);
}

interface Action
{
    // displays a human-readable form of this action
    public void display();

    // returns the cost of taking this action (often 1)
    public int getCost();
}

interface Frontier
{
    // removes all elements from the Frontier
    public void clear();

    // returns true if the Frontier has no elements
    public boolean isEmpty();

    // adds a new node to the Frontier
    public void insert(SearchNode node);

    // removes and returns the next node in the Frontier
    public SearchNode removeNext();
}

interface Search {

    public Solution search(State startState);
    public String toString();// returns name of search algorithm, like "BFS", "DFS", etc.
}
