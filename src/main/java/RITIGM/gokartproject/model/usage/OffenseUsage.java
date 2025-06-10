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
                                                    "\t\tPuck 2 = %d, \r\n";

    //Field for items
    private int puck1;
    private int puck2;

    /**
     * Contructor for the item usage
     * @param puck1 usage of item 1
     * @param puck2 usage of item 2
     */
    public OffenseUsage(int puck1, int puck2){
        this.puck1 = puck1;
        this.puck2 = puck2;
    }

    public int getPuck1() {
        return puck1;
    }

    public void setPuck1(int puck1) {
        this.puck1 = puck1;
    }

    public int getPuck2() {
        return puck2;
    }

    public void setPuck2(int puck2) {
        this.puck2 = puck2;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        OffenseUsage other = (OffenseUsage) obj;
        if (puck1 != other.puck1)
            return false;
        if (puck2 != other.puck2)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return String.format(TO_STRING_FORMAT, this.puck1, this.puck2);
    }
}
