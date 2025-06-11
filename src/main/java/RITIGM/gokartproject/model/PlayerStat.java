package RITIGM.gokartproject.model;

import RITIGM.gokartproject.model.usage.BoostUsage;
import RITIGM.gokartproject.model.usage.OffenseUsage;
import RITIGM.gokartproject.model.usage.TrapUsage;

/**
 * The Player Stat class which include all information with the player
 * 
 * @author Peter Dang
 */
public class PlayerStat extends PlayerInfo{
    private int collisionWithPlayer;
    private int collisionWithWall;
    private int felloffmap;
    private OffenseUsage offenseUsage;
    private TrapUsage trapUsage;
    private BoostUsage boostUsage;
    private double podium;
    private double firstPlace;

    public static final String TO_STRING_FORMAT =   "%d\r\n" + //
                                                    "\tCollision With Wall = %s,\r\n" + //
                                                    "\tCollision With Player = %s,\r\n" + //
                                                    "\tFell of the map = %s,\r\n" + //
                                                    "\tNumber of First Place = %s,\r\n" + //
                                                    "\tNumber of Podium = %s,\r\n";

    /**
     * Create a new user stat class 
     * @param pid player id
     * @param email player email
     * @param pw player password (HASHED ONLY)
     * @param uid user id
     * @param username player username
     * @param offenseUsage the offense usage
     * @param trapUsage the trap usage 
     * @param collisionStat the collision
     * @param boostUsage the boost usage 
     */
    public PlayerStat(String pid, String email, String pw, Integer uid, String username, 
    int collisionWithWall, int collisionWithPlayer, int felloffmap,
    OffenseUsage offenseUsage, TrapUsage trapUsage, BoostUsage boostUsage, double podium, double firstPlace){
        super(pid,email,pw,uid,username);
        this.offenseUsage = offenseUsage;
        this.trapUsage = trapUsage;
        this.boostUsage = boostUsage;
        this.podium = podium;
        this.firstPlace = firstPlace;
        this.collisionWithPlayer = collisionWithPlayer;
        this.collisionWithWall = collisionWithWall;
        this.felloffmap = felloffmap;
    }

    /**
     * Get all offense item usage
     * @return The offense usage (in offense class)
     */
    public OffenseUsage getOffenseUsage() {
        return offenseUsage;
    }

    /**
     * Get all trap item usage
     * @return The trap usage (in trap class)
     */
    public TrapUsage getTrapUsage() {
        return trapUsage;
    }

    /**
     * Get all boost item usage
     * @return The boost usage (in boost class)
     */
    public BoostUsage getBoostUsage() {
        return boostUsage;
    }

    public double getPodium(){
        return podium;
    }

    public double getFirstPlace(){
        return firstPlace;
    }

    /**
     * Set new offense item usage stat
     * @param offenseUsage new offsense Usage
     */
    public void setOffenseUsage(OffenseUsage offenseUsage) {
        this.offenseUsage = offenseUsage;
    }

    /**
     * Set new trap item usage stat
     * @param trapUsage new trap Usage
     */
    public void setTrapUsage(TrapUsage trapUsage) {
        this.trapUsage = trapUsage;
    }

    /**
     * Set new boost item usage stat
     * @param boostUsage new boost Usage
     */
    public void setBoostUsage(BoostUsage boostUsage) {
        this.boostUsage = boostUsage;
    }


    public void setPodium(double podium){
        this.podium = podium;
    }

    public void setFirstPlace(double firstPlace){
        this.firstPlace = firstPlace;
    }

    public int getCollisionWithPlayer() {
        return collisionWithPlayer;
    }

    public void setCollisionWithPlayer(int collisionWithPlayer) {
        this.collisionWithPlayer = collisionWithPlayer;
    }

    public int getCollisionWithWall() {
        return collisionWithWall;
    }

    public void setCollisionWithWall(int collisionWithWall) {
        this.collisionWithWall = collisionWithWall;
    }

    public int getFelloffmap() {
        return felloffmap;
    }

    public void setFelloffmap(int felloffmap) {
        this.felloffmap = felloffmap;
    }

    /**
     * New to string formatting for backend printing
     */
    @Override
    public String toString() {
        return String.format(TO_STRING_FORMAT, super.toString(), this.collisionWithWall, 
        this.collisionWithPlayer, this.felloffmap, this.podium, this.firstPlace) +
        "\n\t" + offenseUsage +
        "\n\t" + trapUsage +  
        "\n\t" + boostUsage;
    }
}
