package RITIGM.gokartproject.model.usage;

public class CollisionStat {
    private Integer wallCollision;
    private Integer playerCollision;

    public static final String TO_STRING_FORMAT = "Collision stat: \r\n" + //
                                                    "\t\tWall Collision = %d, \r\n" + //
                                                    "\t\tPlayer Collision = %d,";
    
    public CollisionStat(Integer wallCollision, Integer playerCollision){
        this.playerCollision = playerCollision;
        this.wallCollision = wallCollision;
    }    

    public Integer getWallCollision() {
        return wallCollision;
    }

    public void setWallCollision(Integer wallCollision) {
        this.wallCollision = wallCollision;
    }

    public Integer getPlayerCollision() {
        return playerCollision;
    }

    public void setPlayerCollision(Integer playerCollision) {
        this.playerCollision = playerCollision;
    }

    @Override
    public String toString() {
        return String.format(TO_STRING_FORMAT, this.wallCollision, this.playerCollision);
    }

    
}
