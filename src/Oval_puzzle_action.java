public class Oval_puzzle_action implements Action{

    public int swap_index;

    public Oval_puzzle_action(int index){
        swap_index = index;
    }

    @Override
    public void display() {
        System.out.println("Rotating block of numbers starting with index of: " + swap_index);
    }

    @Override
    public int getCost() {
        return 1;
    }
}
