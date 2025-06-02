package RITIGM.gokartproject.model;

import RITIGM.gokartproject.model.usage.BoostUsage;
import RITIGM.gokartproject.model.usage.CollisionStat;
import RITIGM.gokartproject.model.usage.OffenseUsage;
import RITIGM.gokartproject.model.usage.TrapUsage;

public class PlayerStat extends PlayerInfo{
    private OffenseUsage offenseUsage;
    private TrapUsage trapUsage;
    private BoostUsage boostUsage;
    private CollisionStat collisionStat;


    public PlayerStat(Integer pid, String email, String pw, Integer uid, String username, OffenseUsage offenseUsage,
    TrapUsage trapUsage, CollisionStat collisionStat, BoostUsage boostUsage){
        super(pid,email,pw,uid,username);
        this.offenseUsage = offenseUsage;
        this.trapUsage = trapUsage;
        this.collisionStat = collisionStat;
        this.boostUsage = boostUsage;
    }

    public OffenseUsage getOffenseUsage() {
        return offenseUsage;
    }

    public TrapUsage getTrapUsage() {
        return trapUsage;
    }

    public BoostUsage getBoostUsage() {
        return boostUsage;
    }

    public CollisionStat getCollisionStat() {
        return collisionStat;
    }

    public void setOffenseUsage(OffenseUsage offenseUsage) {
        this.offenseUsage = offenseUsage;
    }

    public void setTrapUsage(TrapUsage trapUsage) {
        this.trapUsage = trapUsage;
    }

    public void setBoostUsage(BoostUsage boostUsage) {
        this.boostUsage = boostUsage;
    }

    public void setCollisionStat(CollisionStat collisionStat) {
        this.collisionStat = collisionStat;
    }


    @Override
    public String toString() {
        return super.toString() + "\n\t" + this.offenseUsage
                                + "\n\t" + this.trapUsage
                                + "\n\t" + this.boostUsage
                                + "\n\t" + this.collisionStat;
    }

    
    public static void main(String[] args) {
        BoostUsage boostTest = new BoostUsage(1, 2, 3, 4);
        CollisionStat collisiontest = new CollisionStat(1, 2);
        OffenseUsage offenseTest = new OffenseUsage(1, 2, 3, 4);
        TrapUsage trapTest = new TrapUsage(1, 4, 2, 3);


        PlayerStat test = new PlayerStat(1234, "diego@gmail.com", "HelloWorld", 1234, "DIEGO IS SMART",
         offenseTest, trapTest, collisiontest, boostTest);
        System.out.println(test);
    }
    
}
