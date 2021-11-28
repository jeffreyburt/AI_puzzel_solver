public class Oval_action implements Action{

    //the first index in the group of numbers to rotate
    public int switch_index;

    public Oval_action(int switch_index){
        this.switch_index = switch_index;

    }


    @Override
    public void display() {
        System.out.println("Rotated the set at indices" + switch_index + " and " + (switch_index +4) % 20);
    }

    @Override
    public int getCost() {
        return 1;
    }


}
