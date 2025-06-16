package RITIGM.gokartproject.model.usage;


/**
 * The class return template for the boost usage in gameplay
 * 
 * @author Peter Dang
 */
public class BoostUsage {
    private int speedBoost1;
    private int speedBoost2;
    private int speedBoost3;

    /**
     * to String formatting
     */
    public static final String TO_STRING_FORMAT = "Boost Usage: \r\n" + //
                                                    "\t\tSpeed Boost 1 = %d, \r\n" + //
                                                    "\t\tSpeed Boost 2 = %d, \r\n" + //
                                                    "\t\tSpeed Boost 3 = %d,";
    
    /**
     * Contructor for the boost usage 
     * @param speedBoost1  first boost item
     * @param speedBoost2 second boost item
     * @param speedBoost3 third boost item
     */
    public BoostUsage(int speedBoost1,int speedBoost2, int speedBoost3){
        this.speedBoost1 = speedBoost1;
        this.speedBoost2 = speedBoost2;
        this.speedBoost3 = speedBoost3;
    }


    /**
     * Getter for boost item 1
     * @return boost item 1
     */
    public int getSpeedBoost1() {
        return speedBoost1;
    }

/**
 * Setter for boost item 1
 * @param speedBoost1 boost item 1
 */
    public void setSpeedBoost1(int speedBoost1) {
        this.speedBoost1 = speedBoost1;
    }


    /**
     * Getter for boost item 2
     * @return boost item 2
     */
    public int getSpeedBoost2() {
        return speedBoost2;
    }


    /**
     * Setter for boost item 2
     * @param speedBoost2 boost item 2
     */
    public void setSpeedBoost2(int speedBoost2) {
        this.speedBoost2 = speedBoost2;
    }

    /**
     * getter for boost item 3
     * @return boost item 3
     */
    public int getSpeedBoost3() {
        return speedBoost3;
    }

    /**
     * setter fpor boost item 3
     * @param speedBoost3 boost item 3
     */
    public void setSpeedBoost3(int speedBoost3) {
        this.speedBoost3 = speedBoost3;
    }

    /**
     * Overring and creating the new format for the boost usage
     */
    @Override
    public String toString() {
        return String.format(TO_STRING_FORMAT, this.speedBoost1, this.speedBoost2, this.speedBoost3);
    }
}
