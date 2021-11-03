import java.util.ArrayList;

public class Sudoku_state implements State {

    private final Sudoku_tile[][] tile_array = new Sudoku_tile[9][9];
    private final ArrayList<Sudoku_set> sets = new ArrayList<>();

    public Sudoku_state(short[][] tile_numbers) {

        //turning number array into tile array
        //also makes the set for rows
        for (int i = 0; i < 9; i++) {
            Sudoku_set set = new Sudoku_set();
            sets.add(set);
            for (int j = 0; j < 9; j++) {
                short num = tile_numbers[i][j];
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
    public void display() {

    }

    @Override
    public State duplicate() {
        return null;
    }

    @Override
    public void performAction(Action action) {
        Sudoku_action casted_action = (Sudoku_action) action;
        casted_action.tile.change_num(casted_action.set_num);
    }

    @Override
    public int heuristic() {
        return 0;
    }

    @Override
    public void gen_state(int depth) {
    }
}
