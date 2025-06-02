package RITIGM.gokartproject.model.usage;

public class TrapUsage {
    
    public static final String TO_STRING_FORMAT = "Trap Usage: \r\n" + //
                                                    "\t\tItem 1 = %d, \r\n" + //
                                                    "\t\tItem 2 = %d, \r\n" + //
                                                    "\t\tItem 3 = %d, \r\n" + //
                                                    "\t\tItem 4 = %d,";
    private Integer trapItem1;
    private Integer trapItem2;
    private Integer trapItem3;
    private Integer trapItem4;

    public TrapUsage(Integer trapItem1, Integer trapItem2, Integer trapItem3, Integer trapItem4){
        this.trapItem1 = trapItem1;
        this.trapItem2 = trapItem2;
        this.trapItem3 = trapItem3;
        this.trapItem4 = trapItem4;
    }

    public Integer getTrapItem1() {
        return trapItem1;
    }

    public void setTrapItem1(Integer trapItem1) {
        this.trapItem1 = trapItem1;
    }

    public Integer getTrapItem2() {
        return trapItem2;
    }

    public void setTrapItem2(Integer trapItem2) {
        this.trapItem2 = trapItem2;
    }

    public Integer getTrapItem3() {
        return trapItem3;
    }

    public void setTrapItem3(Integer trapItem3) {
        this.trapItem3 = trapItem3;
    }

    public Integer getTrapItem4() {
        return trapItem4;
    }

    public void setTrapItem4(Integer trapItem4) {
        this.trapItem4 = trapItem4;
    }

    @Override
    public String toString() {
        return String.format(TO_STRING_FORMAT, this.trapItem1, this.trapItem2, this.trapItem3, this.trapItem4);
    }
}
