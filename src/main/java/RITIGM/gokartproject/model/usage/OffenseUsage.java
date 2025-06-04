package RITIGM.gokartproject.model.usage;

/**
 * The class return template for the player boost item
 * in gameplay
 * 
 * @author Peter Dang
 */
public class OffenseUsage {
    public static final String TO_STRING_FORMAT = "Offense Usage:\r\n" + //
                                                    "\t\tItem 1 = %d, \r\n" + //
                                                    "\t\tItem 2 = %d, \r\n" + //
                                                    "\t\tItem 3 = %d, \r\n" + //
                                                    "\t\tItem 4 = %d,";

    //Field for items
    private int boostItem1;
    private int boostItem2;
    private int boostItem3;
    private int boostItem4;


    /**
     * Contructor for the item usage
     * @param boostItem1 usage of item 1
     * @param boostItem2 usage of item 2
     * @param boostItem3 usage of item 3
     * @param boostItem4 usage of item 4
     */
    public OffenseUsage(Integer boostItem1, Integer boostItem2, Integer boostItem3, Integer boostItem4){
        this.boostItem1 = boostItem1;
        this.boostItem2 = boostItem2;
        this.boostItem3 = boostItem3;
        this.boostItem4 = boostItem4;
    }

    /**
     * Get the amount of usage for boost item 1
     * @return amount usage of boost item 1
     */
    public int getBoostItem1() {
        return boostItem1;
    }

    /**
     * Set the new boost usage for the item 1
     * @param boostItem1 new boost usage
     */
    public void setBoostItem1(Integer boostItem1) {
        this.boostItem1 = boostItem1;
    }

    /**
     * Get the amount of usage for boost item 2
     * @return amount usage of boost item 2
     */
    public int getBoostItem2() {
        return boostItem2;
    }

    /**
     * Set the new boost usage for the item 2
     * @param boostItem1 new boost usage
     */
    public void setBoostItem2(Integer boostItem2) {
        this.boostItem2 = boostItem2;
    }

    /**
     * Get the amount of usage for boost item 3
     * @return amount usage of boost item 3
     */
    public int getBoostItem3() {
        return boostItem3;
    }

    /**
     * Set the new boost usage for the item 3
     * @param boostItem1 new boost usage
     */
    public void setBoostItem3(Integer boostItem3) {
        this.boostItem3 = boostItem3;
    }

    /**
     * Get the amount of usage for boost item 4
     * @return amount usage of boost item 4
     */
    public int getBoostItem4() {
        return boostItem4;
    }

    /**
     * Set the new boost usage for the item 4
     * @param boostItem1 new boost usage
     */
    public void setBoostItem4(Integer boostItem4) {
        this.boostItem4 = boostItem4;
    }

    @Override
    public String toString() {
        return String.format(TO_STRING_FORMAT, this.boostItem1,this.boostItem2, this.boostItem3, this.boostItem4);
    }
}
