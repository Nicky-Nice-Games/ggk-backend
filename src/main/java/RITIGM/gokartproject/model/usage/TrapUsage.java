package RITIGM.gokartproject.model.usage;


/**
 * The class return template for the player trap item
 * in gameplay
 * 
 * @author Peter Dang
 */
public class TrapUsage {
    
    public static final String TO_STRING_FORMAT = "Trap Usage: \r\n" + //
                                                    "\t\tOil Spill 1 = %d, \r\n" + //
                                                    "\t\tOil Spill 2 = %d, \r\n";
    //Field for item
    private int oilSpill1;
    private int oilSpill2;

    /**
     * Create the new trap dataset
     * @param oilSpill1
     * @param oilSpill1
     */
    public TrapUsage(int oilSpill1, int oilSpill2){
        this.oilSpill1 = oilSpill1;
        this.oilSpill2 = oilSpill2;

    }

    public int getOilSpill1() {
        return oilSpill1;
    }

    public void setOilSpill1(int oilSpill1) {
        this.oilSpill1 = oilSpill1;
    }

    public int getOilSpill2() {
        return oilSpill2;
    }

    public void setOilSpill2(int oilSpill2) {
        this.oilSpill2 = oilSpill2;
    }

    @Override
    public String toString() {
        return String.format(TO_STRING_FORMAT, this.oilSpill1, this.oilSpill2);
    }
}
