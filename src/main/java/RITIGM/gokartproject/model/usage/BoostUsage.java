package RITIGM.gokartproject.model.usage;


/**
 * The class return template for the boost usage in gameplay
 * 
 * @author Peter Dang
 */
public class BoostUsage {
    private int boostItem1;
    private int boostItem2;
    private int boostItem3;
    private int boostItem4;

    //String format for the boost usage
    public static final String TO_STRING_FORMAT = "Boost Usage: \r\n" + //
                                                    "\t\tItem 1 = %d, \r\n" + //
                                                    "\t\tItem 2 = %d, \r\n" + //
                                                    "\t\tItem 3 = %d, \r\n" + //
                                                    "\t\tItem 4 = %d,";
    public BoostUsage(Integer boostItem1,Integer boostItem2, Integer boostItem3, Integer boostItem4){
        this.boostItem1 = boostItem1;
        this.boostItem2 = boostItem2;
        this.boostItem3 = boostItem3;
        this.boostItem4 = boostItem4;
    }

    /**
     * Get boost for item 1
     * @return the usage of boost item 1
     */
    public int getBoostItem1() {
        return boostItem1;
    }

    /**
     * Get boost for item 2
     * @return the usage of boost item 2
     */
    public int getBoostItem2() {
        return boostItem2;
    }

    /**
     * Get boost for item 3
     * @return the usage of boost item 3
     */
    public int getBoostItem3() {
        return boostItem3;
    }

    /**
     * Get boost for item 4
     * @return the usage of boost item 4
     */
    public int getBoostItem4() {
        return boostItem4;
    }

    /**
     * Update the new usage for boost item 1
     * @param boostItem1 the new usage
     */
    public void setBoostItem1(Integer boostItem1) {
        this.boostItem1 = boostItem1;
    }

    /**
     * Update the new usage for boost item 2
     * @param boostItem1 the new usage
     */
    public void setBoostItem2(Integer boostItem2) {
        this.boostItem2 = boostItem2;
    }

    /**
     * Update the new usage for boost item 3
     * @param boostItem1 the new usage
     */
    public void setBoostItem3(Integer boostItem3) {
        this.boostItem3 = boostItem3;
    }

    /**
     * Update the new usage for boost item 4
     * @param boostItem1 the new usage
     */
    public void setBoostItem4(Integer boostItem4) {
        this.boostItem4 = boostItem4;
    }

    /**
     * Overring and creating the new format for the boost usage
     */
    @Override
    public String toString() {
        return String.format(TO_STRING_FORMAT, this.boostItem1, this.boostItem2, this.boostItem3, this.boostItem4);
    }
}
