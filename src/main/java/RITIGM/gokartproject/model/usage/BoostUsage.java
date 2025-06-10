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

    //String format for the boost usage
    public static final String TO_STRING_FORMAT = "Boost Usage: \r\n" + //
                                                    "\t\tSpeed Boost 1 = %d, \r\n" + //
                                                    "\t\tSpeed Boost 2 = %d, \r\n" + //
                                                    "\t\tSpeed Boost 3 = %d,";
    public BoostUsage(int speedBoost1,int speedBoost2, int speedBoost3){
        this.speedBoost1 = speedBoost1;
        this.speedBoost2 = speedBoost2;
        this.speedBoost3 = speedBoost3;
    }


    public int getSpeedBoost1() {
        return speedBoost1;
    }


    public void setSpeedBoost1(int speedBoost1) {
        this.speedBoost1 = speedBoost1;
    }


    public int getSpeedBoost2() {
        return speedBoost2;
    }


    public void setSpeedBoost2(int speedBoost2) {
        this.speedBoost2 = speedBoost2;
    }


    public int getSpeedBoost3() {
        return speedBoost3;
    }


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
