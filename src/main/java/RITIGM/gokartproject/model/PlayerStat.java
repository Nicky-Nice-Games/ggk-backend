package RITIGM.gokartproject.model;

import RITIGM.gokartproject.model.usage.BoostUsage;
import RITIGM.gokartproject.model.usage.DefenseUsage;
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
    private int fastestTime;
    private int favoriteChara;
    private int favoriteTrack;
    private OffenseUsage offenseUsage;
    private TrapUsage trapUsage;
    private BoostUsage boostUsage;
    private DefenseUsage defenseUsage;
    private double podium;
    private double firstPlace;
    private int totalRaces;

    public static final String TO_STRING_FORMAT =   "%s\r\n" + //
                                                    "\tCollision With Wall = %d,\r\n" + //
                                                    "\tCollision With Player = %d,\r\n" + //
                                                    "\tFell of the map = %d,\r\n" + //
                                                    "\tFastest Time = %d,\r\n" + //
                                                    "\tFavorite Character = %d,\r\n" + //
                                                    "\tFavorite Track = %d\r\n" + //
                                                    "\tNumber of First Place = %f,\r\n" + //
                                                    "\tNumber of Podium = %f,\r\n";

    /**
     * Create a new user stat class 
     * @param pid player id
     * @param email player email
     * @param pw player password (HASHED ONLY)
     * @param uid user id
     * @param username player username
     * @param collisionWithPlayer the collision with racers
     * @param collisionWithWall the collision with the wall
     * @param felloffmap number of falls
     * @param offenseUsage the offense usage
     * @param trapUsage the trap usage 
     * @param boostUsage the boost usage 
     * @param podium the podium
     * @param firstPlace percentage of first place wins
     * @param totalRaces
     */
    public PlayerStat(String pid, String email, String pw, Integer uid, String username, int pfpLink,
    int collisionWithWall, int collisionWithPlayer, int felloffmap, int fastestTime, 
    int favoriteChara, int favoriteTrack,
    OffenseUsage offenseUsage, TrapUsage trapUsage, BoostUsage boostUsage, DefenseUsage defenseUsage, 
    double podium, double firstPlace, int totalRaces){
        super(pid,email,pw,uid,username, pfpLink);
        this.fastestTime = fastestTime;
        this.favoriteChara = favoriteChara;
        this.favoriteTrack = favoriteTrack;
        this.offenseUsage = offenseUsage;
        this.trapUsage = trapUsage;
        this.boostUsage = boostUsage;
        this.defenseUsage = defenseUsage;
        this.podium = podium;
        this.firstPlace = firstPlace;
        this.collisionWithPlayer = collisionWithPlayer;
        this.collisionWithWall = collisionWithWall;
        this.felloffmap = felloffmap;
        this.totalRaces = totalRaces;
    }

    /**
     * 
     * @return
     */
    public DefenseUsage getDefenseUsage() {
        return defenseUsage;
    }

    public void setDefenseUsage(DefenseUsage defenseUsage) {
        this.defenseUsage = defenseUsage;
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
     * get the podium placement for a race
     * @return podium placement
     */
    public double getPodium(){
        return podium;
    }

    /**
     * get the fastest time a player has
     * @return fastest time
     */
    public int getFastestTime() {
        return fastestTime;
    }

    /**
     * sets the fastest time for a player
     * @param fastestTime fastest time
     */
    public void setFastestTime(int fastestTime) {
        this.fastestTime = fastestTime;
    }

    /**
     * gets favorite charcater of a player
     * @return player's most used charcater
     */
    public int getFavoriteChara() {
        return favoriteChara;
    }

    /**
     * Sets a players favorite charcater
     * @param favoriteChara new favorite character
     */
    public void setFavoriteChara(int favoriteChara) {
        this.favoriteChara = favoriteChara;
    }

    /***
     * gets the percentage of wins that are first place
     * @return win percentage
     */
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

    /**
     * sets the podium postion
     * @param podium podium postions
     */
    public void setPodium(double podium){
        this.podium = podium;
    }

    /**
     * sets first place freqency
     * @param firstPlace new first place frequency percentage
     */
    public void setFirstPlace(double firstPlace){
        this.firstPlace = firstPlace;
    }

    /**
     * gets the number of player on racer collisions
     * @return number of player on racer collisions
     */
    public int getCollisionWithPlayer() {
        return collisionWithPlayer;
    }

    /**
     * sets the number of player on racer collisions
     * @param collisionWithPlayer number of player on racer collisions
     */
    public void setCollisionWithPlayer(int collisionWithPlayer) {
        this.collisionWithPlayer = collisionWithPlayer;
    }

    /**
     * gets the number of player collisions with wall
     * @return collisions with wall
     */
    public int getCollisionWithWall() {
        return collisionWithWall;
    }

    /**
     * sets number of collisions with wall
     * @param collisionWithWall collisions with wall
     */
    public void setCollisionWithWall(int collisionWithWall) {
        this.collisionWithWall = collisionWithWall;
    }

    /**
     * gets the number of times the player has fallen off the map
     * @return falls
     */
    public int getFelloffmap() {
        return felloffmap;
    }

    /**
     * sets the number of times a player has fallen off the map
     * @param felloffmap falls
     */
    public void setFelloffmap(int felloffmap) {
        this.felloffmap = felloffmap;
    }

    
    /**
     * gets the favorite track
     * @return favorite track
     */
    public int getFavoriteTrack() {
        return favoriteTrack;
    }

    /**
     * sets the favorite track
     * @param favoriteTrack favorite track
     */
    public void setFavoriteTrack(int favoriteTrack) {
        this.favoriteTrack = favoriteTrack;
    }

    

    public int getTotalRaces() {
        return totalRaces;
    }

    public void setTotalRaces(int totalRaces) {
        this.totalRaces = totalRaces;
    }

    /**
     * New to string formatting for backend printing
     */
    @Override
    public String toString() {
        return String.format(TO_STRING_FORMAT, super.toString(), this.collisionWithWall, 
        this.collisionWithPlayer, this.felloffmap,this.fastestTime, this.favoriteChara, this.favoriteTrack, this.podium, this.firstPlace) +
        "\n\t" + offenseUsage +
        "\n\t" + trapUsage +  
        "\n\t" + boostUsage;
    }
}
