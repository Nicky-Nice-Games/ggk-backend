package RITIGM.gokartproject.model;

import RITIGM.gokartproject.model.usage.BoostUsage;
import RITIGM.gokartproject.model.usage.CollisionStat;
import RITIGM.gokartproject.model.usage.OffenseUsage;
import RITIGM.gokartproject.model.usage.TrapUsage;

/**
 * The Player Stat class which include all information with the player
 * 
 * @author Peter Dang
 */
public class PlayerStat extends PlayerInfo{
    private OffenseUsage offenseUsage;
    private TrapUsage trapUsage;
    private BoostUsage boostUsage;
    private CollisionStat collisionStat;

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
    public PlayerStat(String pid, String email, String pw, Integer uid, String username, OffenseUsage offenseUsage,
    TrapUsage trapUsage, CollisionStat collisionStat, BoostUsage boostUsage){
        super(pid,email,pw,uid,username);
        this.offenseUsage = offenseUsage;
        this.trapUsage = trapUsage;
        this.collisionStat = collisionStat;
        this.boostUsage = boostUsage;
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

    /**
     * Get all Collision stast
     * @return The Collision stast (in Collision class)
     */
    public CollisionStat getCollisionStat() {
        return collisionStat;
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

    /**
     * Set new collision stat
     * @param collisionStat new collision stat
     */
    public void setCollisionStat(CollisionStat collisionStat) {
        this.collisionStat = collisionStat;
    }

    /**
     * New to string formatting for backend printing
     */
    @Override
    public String toString() {
        return super.toString() + "\n\t" + this.offenseUsage
                                + "\n\t" + this.trapUsage
                                + "\n\t" + this.boostUsage
                                + "\n\t" + this.collisionStat;
    }

}
