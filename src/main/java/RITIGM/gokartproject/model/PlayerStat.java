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
    private int favoriteChara;
    private int favoriteTrack;
    private OffenseUsage offenseUsage;
    private TrapUsage trapUsage;
    private BoostUsage boostUsage;
    private DefenseUsage defenseUsage;
    private double podium;
    private double firstPlace;
    private int totalRaces;
    private int raceTime1;
    private int raceTime2;
    private int raceTime3;
    private int raceTime4;

    public static final String TO_STRING_FORMAT =   "%s\r\n" + //
                                                    "\tCollision With Wall = %d,\r\n" + //
                                                    "\tCollision With Player = %d,\r\n" + //
                                                    "\tFell of the map = %d,\r\n" + //
                                                    "\tFavorite Character = %d,\r\n" + //
                                                    "\tFavorite Track = %d,\r\n" + //
                                                    "\tTotal Races = %d,\r\n" + //
                                                    "\tNumber of First Place = %f,\r\n" + //
                                                    "\tNumber of Podium = %f,\r\n" +//
                                                    "\tFastest Time Map 1 = %d,\r\n" + //
                                                    "\tFastest Time Map 2 = %d,\r\n" + //
                                                    "\tFastest Time Map 3 = %d,\r\n" + //
                                                    "\tFastest Time Map 4 = %d,\r\n";

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
    int collisionWithWall, int collisionWithPlayer, int felloffmap,
    int favoriteChara, int favoriteTrack,
    OffenseUsage offenseUsage, TrapUsage trapUsage, BoostUsage boostUsage, DefenseUsage defenseUsage, 
    double podium, double firstPlace, int totalRaces, int raceTime1, int raceTime2,int raceTime3,int raceTime4){
        super(pid,email,pw,uid,username, pfpLink);
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
        this.raceTime1 = raceTime1;
        this.raceTime2 = raceTime2;
        this.raceTime3 = raceTime3;
        this.raceTime4 = raceTime4;
    }

    public int getRaceTime1() {
        return raceTime1;
    }

    public void setRaceTime1(int raceTime1) {
        this.raceTime1 = raceTime1;
    }

    public int getRaceTime2() {
        return raceTime2;
    }

    public void setRaceTime2(int raceTime2) {
        this.raceTime2 = raceTime2;
    }

    public int getRaceTime3() {
        return raceTime3;
    }

    public void setRaceTime3(int raceTime3) {
        this.raceTime3 = raceTime3;
    }

    public int getRaceTime4() {
        return raceTime4;
    }

    public void setRaceTime4(int raceTime4) {
        this.raceTime4 = raceTime4;
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
        this.collisionWithPlayer, this.felloffmap, this.favoriteChara, this.favoriteTrack,this.totalRaces, this.podium, this.firstPlace,
        this.raceTime1, this.raceTime2, this.raceTime3, this.raceTime4) +
        "\n\t" + offenseUsage +
        "\n\t" + trapUsage +  
        "\n\t" + boostUsage;
    }
}
