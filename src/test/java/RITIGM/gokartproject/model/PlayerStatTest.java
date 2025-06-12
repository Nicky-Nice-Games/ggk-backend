package RITIGM.gokartproject.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import RITIGM.gokartproject.model.usage.BoostUsage;
import RITIGM.gokartproject.model.usage.OffenseUsage;
import RITIGM.gokartproject.model.usage.TrapUsage;

/**
 * Test for the funcitons of PlayerStat object
 */
public class PlayerStatTest {

    private PlayerStat check;
    private OffenseUsage offense;
    private TrapUsage trap;
    private BoostUsage boost;
    public static final String TO_STRING_FORMAT = "\nPlayer Info:\r\n" + //
                                "\tPID = %s,\r\n" + //
                                "\tEmail = %s,\r\n" + //
                                "\tpw = %s,\r\n" + //
                                "\tUID = %d,\r\n" + //
                                "\tUsername = %s,\r\n" + //
                                "\tCollision With Wall = %d,\r\n" + //
                                "\tCollision With Player = %d,\r\n" + //
                                "\tFell of the map = %d,\r\n" + //
                                "\tFastest Time = %d,\r\n" + //
                                "\tFavorite Character = %d,\r\n" + //
                                "\tNumber of First Place = %f,\r\n" + //
                                "\tNumber of Podium = %f,\r\n";

    @BeforeEach
    void init(){
        this.offense = new OffenseUsage(9,10);
        this.trap = new TrapUsage(11, 12);
        this.boost = new BoostUsage(13, 14, 15);
        this.check = new PlayerStat("1", "2", "3", 4, "5", 6, 7, 8,18,19,
         this.offense, this.trap, this.boost, 16.0, 17.0);
    }

    @Test
    void testGetBoostUsage() {
        assertEquals(check.getBoostUsage(), this.boost);
    }

    @Test
    void testGetFirstPlace() {
        assertEquals(check.getFirstPlace(), 17.0);
    }

    @Test
    void testGetOffenseUsage() {
        assertEquals(check.getOffenseUsage(), offense);
    }

    @Test
    void testGetPodium() {
        assertEquals(check.getPodium(), 16.0);
    }

    @Test
    void testGetTrapUsage() {
        assertEquals(check.getTrapUsage(), trap);
    }

    @Test
    void testSetBoostUsage() {
        boost = new BoostUsage(1, 2, 3);
        check.setBoostUsage(boost);
        assertEquals(boost, check.getBoostUsage());
    }

    @Test
    void testSetFirstPlace() {
        check.setFirstPlace(727);
        assertEquals(check.getFirstPlace(), 727);
    }

    @Test
    void testSetOffenseUsage() {
        OffenseUsage a  = (new OffenseUsage(0, 0));
        check.setOffenseUsage(a);
        assertEquals(check.getOffenseUsage(), a);
    }

    @Test
    void testSetPodium() {
        check.setPodium(0);
        assertEquals(check.getPodium(), 0);
    }

    @Test
    void testSetTrapUsage() {
        TrapUsage a = new TrapUsage(0, 0);
        check.setTrapUsage(a);
        assertEquals(check.getTrapUsage(), a);
    }

    @Test
    void testToString() {
        String expected_String  = String.format(TO_STRING_FORMAT, "1", "2", "3", 4, "5", 6, 7, 8,18,19, 16.0, 17.0) +
        "\n\t" + offense+
        "\n\t" + trap +  
        "\n\t" + boost;
        assertEquals(expected_String, check.toString());
    }

    @Test
    void testGetCollisionWithPlayer() {
        assertEquals(7, check.getCollisionWithPlayer());
    }

    @Test
    void testGetCollisionWithWall() {
        assertEquals(6, check.getCollisionWithWall());
    }

    @Test
    void testGetFelloffmap() {
        assertEquals(8, check.getFelloffmap());
    }

    @Test
    void testSetCollisionWithPlayer() {
        this.check.setCollisionWithPlayer(100);
        assertEquals(100, check.getCollisionWithPlayer());
    }

    @Test
    void testSetCollisionWithWall() {
        this.check.setCollisionWithWall(100);
        assertEquals(100, check.getCollisionWithWall());
    }

    @Test
    void testSetFelloffmap() {
        this.check.setFelloffmap(9090);
        assertEquals(9090, check.getFelloffmap());
    }
}
