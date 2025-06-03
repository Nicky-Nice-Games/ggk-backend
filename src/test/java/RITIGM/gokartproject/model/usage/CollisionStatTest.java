package RITIGM.gokartproject.model.usage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CollisionStatTest {
    private int expected_wallCollision = 0;
    private int expected_playerCollision = 0;

    private CollisionStat collisionStat = new CollisionStat(expected_wallCollision, expected_playerCollision);

    @Test
    void testGetPlayerCollision() {
        assertEquals(expected_playerCollision, collisionStat.getPlayerCollision());
    }

    @Test
    void testGetWallCollision() {
        assertEquals(expected_wallCollision, collisionStat.getWallCollision());
    }

    @Test
    void testSetPlayerCollision() {
        expected_playerCollision = 9;
        collisionStat.setPlayerCollision(expected_playerCollision);
        assertEquals(expected_playerCollision, collisionStat.getPlayerCollision());
    }

    @Test
    void testSetWallCollision() {
        expected_wallCollision = 9;
        collisionStat.setWallCollision(expected_wallCollision);
        assertEquals(expected_wallCollision, collisionStat.getWallCollision());
    }

    @Test
    void testToString() {
        String expectedString  = "Collision stat: \r\n" + //
                                "\t\tWall Collision = "+ expected_wallCollision + ", \r\n" + //
                                "\t\tPlayer Collision = " + expected_playerCollision + ",";
        assertEquals(expectedString, collisionStat.toString());

    }
}
