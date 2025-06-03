package RITIGM.gokartproject.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import RITIGM.gokartproject.model.usage.BoostUsage;
import RITIGM.gokartproject.model.usage.CollisionStat;
import RITIGM.gokartproject.model.usage.OffenseUsage;
import RITIGM.gokartproject.model.usage.TrapUsage;

public class PlayerStatTest {
    private OffenseUsage expected_offenseUsage = new OffenseUsage(0, 0, 0, 0);
    private TrapUsage expected_trapUsage = new TrapUsage(0, 0, 0, 0);
    private BoostUsage expected_boostUsage = new BoostUsage(0, 0, 0, 0);
    private CollisionStat expected_collisionStat = new CollisionStat(0, 0);

    private PlayerStat playerStat = new PlayerStat("20", "test@email.com", "password", 1234, "testername", 
    expected_offenseUsage, expected_trapUsage,  expected_collisionStat, expected_boostUsage);
    private PlayerInfo playerInfo = new PlayerInfo("20", "test@email.com", "password", 1234, "testername");

    @Test
    void testGetOffenseUsage(){
        assertEquals(expected_offenseUsage, playerStat.getOffenseUsage());
    }

    @Test
    void testGetTrapUsage(){
        assertEquals(expected_trapUsage, playerStat.getTrapUsage());
    }

    @Test
    void testGetBoostUsage(){
        assertEquals(expected_boostUsage, playerStat.getBoostUsage());
    }

    @Test
    void testGetCollisionStat(){
        assertEquals(expected_collisionStat, playerStat.getCollisionStat());
    }

    @Test
    void testSetOffenseUsage(){
        expected_offenseUsage = new OffenseUsage(9,9,9,9);
        playerStat.setOffenseUsage(expected_offenseUsage);
        assertEquals(expected_offenseUsage, playerStat.getOffenseUsage());
    }

    @Test
    void testSetTrapUsage(){
        expected_trapUsage = new TrapUsage(9,9, 9, 9);
        playerStat.setTrapUsage(expected_trapUsage);
        assertEquals(expected_trapUsage, playerStat.getTrapUsage());
    }

    @Test
    void testSetBoostUsage(){
        expected_boostUsage = new BoostUsage(9,9,9,9);
        playerStat.setBoostUsage(expected_boostUsage);
        assertEquals(expected_boostUsage, playerStat.getBoostUsage());        
    }

    @Test
    void testSetCollisionStat(){
        expected_collisionStat = new CollisionStat(9,9);
        playerStat.setCollisionStat(expected_collisionStat);
        assertEquals(expected_collisionStat, playerStat.getCollisionStat());
    }

    @Test
    void testToString() {
        String expected_string = playerInfo.toString() 
                                + "\n\t" + expected_offenseUsage
                                + "\n\t" + expected_trapUsage
                                + "\n\t" + expected_boostUsage
                                + "\n\t" + expected_collisionStat;
        assertEquals(expected_string, playerStat.toString());
    }
}
