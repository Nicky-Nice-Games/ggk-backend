package RITIGM.gokartproject.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TopRaceScoreTest {

    private TopRaceScore score;

    @BeforeEach
    void setUp() {
        score = new TopRaceScore(10, 20, 30, 40);
    }

    @Test
    void testGetRaceTime1() {
        assertEquals(10, score.getRaceTime1());
    }

    @Test
    void testGetRaceTime2() {
        assertEquals(20, score.getRaceTime2());
    }

    @Test
    void testGetRaceTime3() {
        assertEquals(30, score.getRaceTime3());
    }

    @Test
    void testGetRaceTime4() {
        assertEquals(40, score.getRaceTime4());
    }

    @Test
    void testSetRaceTime1() {
        score.setRaceTime1(100);
        assertEquals(100, score.getRaceTime1());
    }

    @Test
    void testSetRaceTime2() {
        score.setRaceTime2(200);
        assertEquals(200, score.getRaceTime2());
    }

    @Test
    void testSetRaceTime3() {
        score.setRaceTime3(300);
        assertEquals(300, score.getRaceTime3());
    }

    @Test
    void testSetRaceTime4() {
        score.setRaceTime4(400);
        assertEquals(400, score.getRaceTime4());
    }
}
