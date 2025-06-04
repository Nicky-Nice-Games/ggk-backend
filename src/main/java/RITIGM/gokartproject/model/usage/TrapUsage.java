package RITIGM.gokartproject.model.usage;


/**
 * The class return template for the player trap item
 * in gameplay
 * 
 * @author Peter Dang
 */
public class TrapUsage {
    
    public static final String TO_STRING_FORMAT = "Trap Usage: \r\n" + //
                                                    "\t\tItem 1 = %d, \r\n" + //
                                                    "\t\tItem 2 = %d, \r\n" + //
                                                    "\t\tItem 3 = %d, \r\n" + //
                                                    "\t\tItem 4 = %d,";
    //Field for item
    private int trapItem1;
    private int trapItem2;
    private int trapItem3;
    private int trapItem4;

    /**
     * Create the new trap dataset
     * @param trapItem1
     * @param trapItem2
     * @param trapItem3
     * @param trapItem4
     */
    public TrapUsage(Integer trapItem1, Integer trapItem2, Integer trapItem3, Integer trapItem4){
        this.trapItem1 = trapItem1;
        this.trapItem2 = trapItem2;
        this.trapItem3 = trapItem3;
        this.trapItem4 = trapItem4;
    }

    /**
     * get the usage of trap item 1
     * @return the usage of trap item 1
     */
    public int getTrapItem1() {
        return trapItem1;
    }


    /**
     * Set the new usage for trap item 1
     * @param trapItem1 the new usage
     */
    public void setTrapItem1(Integer trapItem1) {
        this.trapItem1 = trapItem1;
    }

    /**
     * get the usage of trap item 2
     * @return the usage of trap item 2
     */
    public int getTrapItem2() {
        return trapItem2;
    }

    /**
     * Set the new usage for trap item 2
     * @param trapItem1 the new usage
     */
    public void setTrapItem2(Integer trapItem2) {
        this.trapItem2 = trapItem2;
    }

    /**
     * get the usage of trap item 3
     * @return the usage of trap item 3
     */
    public int getTrapItem3() {
        return trapItem3;
    }

    /**
     * Set the new usage for trap item 3
     * @param trapItem1 the new usage
     */
    public void setTrapItem3(Integer trapItem3) {
        this.trapItem3 = trapItem3;
    }

    /**
     * get the usage of trap item 4
     * @return the usage of trap item 4
     */
    public int getTrapItem4() {
        return trapItem4;
    }

    /**
     * Set the new usage for trap item 4
     * @param trapItem1 the new usage
     */
    public void setTrapItem4(Integer trapItem4) {
        this.trapItem4 = trapItem4;
    }

    @Override
    public String toString() {
        return String.format(TO_STRING_FORMAT, this.trapItem1, this.trapItem2, this.trapItem3, this.trapItem4);
    }
}
