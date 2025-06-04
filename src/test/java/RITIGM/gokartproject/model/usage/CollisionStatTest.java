package RITIGM.gokartproject.model.usage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * tests the fucntions of the Collision stat object
 */
public class CollisionStatTest {
    private int expected_wallCollision = 0;
    private int expected_playerCollision = 0;

    private CollisionStat collisionStat = new CollisionStat(expected_wallCollision, expected_playerCollision);

    /**
     * tests getter for player collision
     */
    @Test
    void testGetPlayerCollision() {
        assertEquals(expected_playerCollision, collisionStat.getPlayerCollision());
    }

    /**
     * tests getter for wall collision
     */
    @Test
    void testGetWallCollision() {
        assertEquals(expected_wallCollision, collisionStat.getWallCollision());
    }

    /**
     * tests setter for player collision 
     */
    @Test
    void testSetPlayerCollision() {
        expected_playerCollision = 9;
        collisionStat.setPlayerCollision(expected_playerCollision);
        assertEquals(expected_playerCollision, collisionStat.getPlayerCollision());
    }

    /**
     * tests setter for wall collisions
     */
    @Test
    void testSetWallCollision() {
        expected_wallCollision = 9;
        collisionStat.setWallCollision(expected_wallCollision);
        assertEquals(expected_wallCollision, collisionStat.getWallCollision());
    }

    /**
     * tests to string for object
     */
    @Test
    void testToString() {
        String expectedString  = "Collision stat: \r\n" + //
                                "\t\tWall Collision = "+ expected_wallCollision + ", \r\n" + //
                                "\t\tPlayer Collision = " + expected_playerCollision + ",";
        assertEquals(expectedString, collisionStat.toString());

    }
}
