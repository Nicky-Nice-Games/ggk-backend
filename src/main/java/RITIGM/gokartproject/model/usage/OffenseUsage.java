package RITIGM.gokartproject.model.usage;

/**
 * The class return template for the player boost item
 * in gameplay
 * 
 * @author Peter Dang
 */
public class OffenseUsage {
    public static final String TO_STRING_FORMAT = "Offense Usage:\r\n" + //
                                                    "\t\tPuck 1 = %d, \r\n" + //
                                                    "\t\tPuck 2 = %d, \r\n" + //
                                                    "\t\tPuck 3 = %d, \r\n" + //
                                                    "\t\tPuck 4 = %d,";

    //Field for items
    private int puck1;
    private int puck2;
    private int puck3;
    private int puck4;

    /**
     * Contructor for the item usage
     * @param puck1 usage of item 1
     * @param puck2 usage of item 2
     * @param puck3 usage of item 3
     * @param puck4 usage of item 4
     */
    public OffenseUsage(int puck1, int puck2, int puck3, int puck4){
        this.puck1 = puck1;
        this.puck2 = puck2;
        this.puck3 = puck3;
        this.puck4 = puck4;
    }

    /**
     * getter for puck 1
     * @return puck 1
     */
    public int getPuck1() {
        return puck1;
    }

    /**
     * setter for puck 1
     * @param puck1 puck 1
     */
    public void setPuck1(int puck1) {
        this.puck1 = puck1;
    }

    /**
     * getter for puck 2
     * @return puck 2
     */
    public int getPuck2() {
        return puck2;
    }

    /**
     * setter for puck 2
     * @param puck2 puck 2
     */
    public void setPuck2(int puck2) {
        this.puck2 = puck2;
    }

    /**
     * getter for puck 3
     * @return puck 3
     */
    public int getPuck3() {
        return puck3;
    }

    /**
     * setter for puck 3
     * @param puck3 puck 3
     */
    public void setPuck3(int puck3) {
        this.puck3 = puck3;
    }

    /**
     * getter for puck 4
     * @return puck 4
     */
    public int getPuck4() {
        return puck4;
    }

    /**
     * setter for puck 4
     * @param puck4 puck 4
     */
    public void setPuck4(int puck4) {
        this.puck4 = puck4;
    }

    

    /**
     * ToString Overide for OffenseUsage Object
     */
    @Override
    public String toString() {
        return String.format(TO_STRING_FORMAT, this.puck1, this.puck2, this.puck3, this.puck4);
    }
}
