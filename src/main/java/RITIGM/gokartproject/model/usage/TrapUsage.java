package RITIGM.gokartproject.model.usage;


/**
 * The class return template for the player trap item
 * in gameplay
 * 
 * @author Peter Dang
 */
public class TrapUsage {
    
    public static final String TO_STRING_FORMAT = "Trap Usage: \r\n" + //
                                                    "\t\tOil Spill = %d, \r\n" + //
                                                    "\t\tBrick Wall = %d, \r\n" + //
                                                    "\t\tConfuse Ritchie = %d, \r\n" +//
                                                    "\t\tFake Power-Up Brick = %d,";
    //Field for item
    private int oilSpill1;
    private int brickwall;
    private int confuseritchie;
    private int fakepowerupbrick;

    /**
     * Create the new trap dataset
     * @param oilSpill1 oilspill item 1
     * @param brickwall brickwall item 2
     * @param confuseritchie confuse ritchie item
     * @param fakepowerupbrick fake powerup brick item
     */
    public TrapUsage(int oilSpill1, int brickwall, int confuseritchie, int fakepowerupbrick){
        this.oilSpill1 = oilSpill1;
        this.brickwall = brickwall;
        this.confuseritchie = confuseritchie;
        this.fakepowerupbrick = fakepowerupbrick;

    }

    /**
     * getter for oil spill item 1
     * @return oil spill item 1
     */
    public int getOilSpill1() {
        return oilSpill1;
    }

    /**
     * setter for oil spill item 1
     * @param oilSpill1 oil spill item 1
     */
    public void setOilSpill1(int oilSpill1) {
        this.oilSpill1 = oilSpill1;
    }

    /**
     * getter for oil spill item 2
     * @return oil spill item 2
     */
    public int getBrickwall() {
        return brickwall;
    }

    /**
     * setter for oil spill item 2
     * @param brickwall oil spill item 2
     */
    public void setBrickwall(int brickwall) {
        this.brickwall = brickwall;
    }

    /**
     * getter for oil spill item 1
     * @return oil spill item 1
     */
    public int getConfuseritchie() {
        return confuseritchie;
    }

    /**
     * setter for oil spill item 1
     * @param oilSpill1 oil spill item 1
     */
    public void setConfuseritchie(int confuseritchie) {
        this.confuseritchie = confuseritchie;
    }

    /**
     * getter for oil spill item 2
     * @return oil spill item 2
     */
    public int getFakepowerupbrick() {
        return fakepowerupbrick;
    }

    /**
     * setter for oil spill item 2
     * @param brickwall oil spill item 2
     */
    public void setFakepowerupbrick(int fakepowerupbrick) {
        this.fakepowerupbrick = fakepowerupbrick;
    }

    /**
     * ToString for object TrapUsage
     */
    @Override
    public String toString() {
        return String.format(TO_STRING_FORMAT, this.oilSpill1, this.brickwall, this.confuseritchie, this.fakepowerupbrick);
    }
}
