package RITIGM.gokartproject.model.usage;

/**
 * The class return template for the player collision
 * in gameplay
 * 
 * @author Peter Dang
 */
public class CollisionStat {
    private int wallCollision;
    private int playerCollision;
    public static final String TO_STRING_FORMAT = "Collision stat: \r\n" + //
                                                    "\t\tWall Collision = %d, \r\n" + //
                                                    "\t\tPlayer Collision = %d,";
    
    /**
     * Create a new class of collision stat
     * @param wallCollision amount of collision to the wall
     * @param playerCollision amount of collision to other player
     */
    public CollisionStat(Integer wallCollision, Integer playerCollision){
        this.playerCollision = playerCollision;
        this.wallCollision = wallCollision;
    }    

    /**
     * Get the amount of collision to the wall
     * @return amount of collision to the wall
     */
    public int getWallCollision() {
        return wallCollision;
    }

    /**
     * Set new amount of collision to the wall
     * @param wallCollision amount of wall collision
     */
    public void setWallCollision(Integer wallCollision) {
        this.wallCollision = wallCollision;
    }

    /**
     * Get the amount of player collision
     * @return amount of collision to other players
     */
    public int getPlayerCollision() {
        return playerCollision;
    }

    /**
     * Set the new collision to other player
     * @param playerCollision the new amount of collision
     */
    public void setPlayerCollision(Integer playerCollision) {
        this.playerCollision = playerCollision;
    }

    @Override
    public String toString() {
        return String.format(TO_STRING_FORMAT, this.wallCollision, this.playerCollision);
    }

    
}
