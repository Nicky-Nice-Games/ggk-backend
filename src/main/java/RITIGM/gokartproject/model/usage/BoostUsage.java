package RITIGM.gokartproject.model.usage;

public class BoostUsage {
    private int boostItem1;
    private int boostItem2;
    private int boostItem3;
    private int boostItem4;


    public static final String TO_STRING_FORMAT = "Boost Usage: \r\n" + //
                                                    "\t\tItem 1 = %d,\r\n" + //
                                                    "\t\tItem 2 = %d, \r\n" + //
                                                    "\t\tItem 3 = %d, \r\n" + //
                                                    "\t\tItem 4 = %d,";
    public BoostUsage(Integer boostItem1,Integer boostItem2, Integer boostItem3, Integer boostItem4){
        this.boostItem1 = boostItem1;
        this.boostItem2 = boostItem2;
        this.boostItem3 = boostItem3;
        this.boostItem4 = boostItem4;
    }

    public int getBoostItem1() {
        return boostItem1;
    }

    public int getBoostItem2() {
        return boostItem2;
    }

    public int getBoostItem3() {
        return boostItem3;
    }

    public int getBoostItem4() {
        return boostItem4;
    }

    public void setBoostItem1(Integer boostItem1) {
        this.boostItem1 = boostItem1;
    }

    public void setBoostItem2(Integer boostItem2) {
        this.boostItem2 = boostItem2;
    }

    public void setBoostItem3(Integer boostItem3) {
        this.boostItem3 = boostItem3;
    }

    public void setBoostItem4(Integer boostItem4) {
        this.boostItem4 = boostItem4;
    }

    @Override
    public String toString() {
        return String.format(TO_STRING_FORMAT, this.boostItem1, this.boostItem2, this.boostItem3, this.boostItem4);
    }
}
