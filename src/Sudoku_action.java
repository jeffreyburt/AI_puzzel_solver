public class Sudoku_action implements Action {

    public Sudoku_tile tile;
    public short set_num;

    public Sudoku_action(Sudoku_tile tile, short set_num){
        this.tile = tile;
        this.set_num = set_num;
    }

    @Override
    public void display() {
        System.out.println("Set tile at: (" + tile.x + 1 + "," + tile.y + 1 + ") to equal " + set_num);
    }

    @Override
    public int getCost() {
        return 1;
    }
}
