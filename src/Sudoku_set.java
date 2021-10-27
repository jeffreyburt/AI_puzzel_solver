import java.util.ArrayList;

public class Sudoku_set {

    private ArrayList<Sudoku_tile> tiles;
    private ArrayList<Short> available_nums;

    public Sudoku_set(){
        tiles = new ArrayList<>();
        available_nums = new ArrayList<>();

        for (short i = 0; i < 9; i++) {
            available_nums.add(i);
        }
    }

    public void add_tile(Sudoku_tile tile){
        tiles.add(tile);
        available_nums.remove(tile.num);
    }

    public void change_num(short removed_num, short new_num){
        available_nums.remove(new_num);
        available_nums.add(new_num);
    }


}
