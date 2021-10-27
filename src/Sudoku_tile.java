import java.util.ArrayList;

public class Sudoku_tile {
    protected short num;
    public boolean mutable;
    private ArrayList<Sudoku_set> sets;

    public Sudoku_tile(short num, boolean mutable){
        this.num = num;
        this.mutable = mutable;
        sets = new ArrayList<>();
    }

    public void change_num(short new_num) throws UnsupportedOperationException{
        if(mutable){ 
            num = new_num;
            for (Sudoku_set set :
                    sets) {
                set.recalculate;
            }
        }else{
            throw new UnsupportedOperationException();
        }
    }
    
    public void add_set(Sudoku_set set){
        sets.add(set);
    }

}
