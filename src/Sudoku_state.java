import java.util.ArrayList;

public class Sudoku_state implements State {

    /*
    Sudoku storage parameters
    Sudoku's are stored with numbers 0 through 8
    -1s indicate that a tile is not specified at the beginning of the puzzle and hence is mutable

    However, puzzles are imported with the range 0-9, and thus are reduced by one to fit the desired range as soon a possible
    This reduction happens in the constructor, so in summary

    Puzzle imports: 0-9
    Sudoku_state import: 0-9
    All further operations: -1 through 8

     */

    private final Sudoku_tile[][] tile_array = new Sudoku_tile[9][9];
    private final ArrayList<Sudoku_set> sets = new ArrayList<>();

    public Sudoku_state(short[][] tile_numbers) {

        //turning number array into tile array
        //also makes the set for rows
        for (int i = 0; i < 9; i++) {
            Sudoku_set set = new Sudoku_set();
            sets.add(set);
            for (int j = 0; j < 9; j++) {
                short num = (short) (tile_numbers[i][j] - 1);
                Sudoku_tile tile = new Sudoku_tile(num, num > -1, i, j);
                tile_array[i][j] = tile;
                set.add_tile(tile);
                tile.add_set(set);
            }
        }

        //making sets for columns
        for (int i = 0; i < 9; i++) {
            Sudoku_set set = new Sudoku_set();
            sets.add(set);
            for (int j = 0; j < 9; j++) {
                Sudoku_tile tile = tile_array[j][i];
                set.add_tile(tile);
                tile.add_set(set);
            }
        }

        //making sets for 3x3 blocks of tiles
        //OOPS ALL LOOPS!!!
        for (int x_block = 0; x_block < 9; x_block += 3) {
            for (int y_block = 0; y_block < 9; y_block += 3) {
                Sudoku_set set = new Sudoku_set();
                sets.add(set);
                for (int x = x_block; x < x_block + 3; x++) {
                    for (int y = 0; y < y_block + 3; y++) {

                        Sudoku_tile tile = tile_array[x][y];
                        set.add_tile(tile);
                        tile.add_set(set);
                    }
                }
            }
        }

    }


    //this will probably contain some unnecessary iterating in order to allow it to work with the sorting code
    @Override
    public ArrayList<Action> listActions() {
        ArrayList<Action> actionArrayList = new ArrayList<>();
        for (Sudoku_tile[] tiles : tile_array) {
            for (Sudoku_tile tile : tiles) {
                for (short num : tile.possible_nums) {
                    actionArrayList.add(new Sudoku_action(tile, num));
                }
            }
        }
        return actionArrayList;
    }

    @Override
    public boolean isGoalState() {
        for (Sudoku_set set :
                sets) {
            if (!set.is_goal()) return false;
        }
        return true;
    }

    @Override
    //todo implement display
    public void display() {

    }

    @Override
    //todo implement duplicate
    public State duplicate() {
        return null;
    }

    @Override
    public void performAction(Action action) {
        Sudoku_action casted_action = (Sudoku_action) action;
        casted_action.tile.change_num(casted_action.set_num);
    }

    @Override
    //todo yeah this is going to suck...
    public int heuristic() {
        return 0;
    }

    /*
    so there is one central problem that I have here for the heuristic
     and tha tis precetnting possible move in the future that I'e already traveled down
     this is shaping up to be a really really shitty probel that possible kills things entirely

     Basically there are so many possible move here that there is no way I can truely brute force a solution,
     just not going to happen
     so that forces me to find a solution through some sort of heuristic, basically geussing which path is best
     this is turn out can also be really shitty, because there is no way I can possible effi

     Ok, what if I cuse some sort of A* style search tree, where I can pick whatever branch of the tree gets me closest to the solution
     wait shit
     thats just a greedy search
     lmao why didn't I realize that would work in the first place
     god that was stupid,
     I should still probably talk to andrew about this and get his optinion on how to procede

     hahahaha I was so wrong, I have officioally given uo
     full stop I am done thinking about this

     As far as I can see I have a few conflicting design goals here
     I want to:
     not keep track of past path
     selectively expand nodes based on soe heuristic
     and avoid backtracking nodes

     so what if I allow myself a frontier of nodes, but dont expand the entire frontier at once,
     only whichever node seems most promising
     this would remove our backtracking issues at the cost of a much larger queue

     what about a hash table for avoiding clones
     wait could I just remove clones in the priority queue
     yes, but that would be kinda sucky to impliment
     ok I'm tired, sleepy times

     */

    @Override
    public void gen_state(int depth) {
    }


}
