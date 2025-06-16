package RITIGM.gokartproject.model.scoreCalculation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProfileRecalculationTest {
    public ProfileRecalculation classChecker;
    @BeforeEach
    void init(){
        classChecker = new ProfileRecalculation();
    }

    @Test
    void testProfileCalculation() {
        assertEquals(Math.pow(0.9, (1-1)), ProfileRecalculation.FIRST_MAP_EFFECTIVE);
        assertEquals(Math.pow(0.9, (2-1)), ProfileRecalculation.SECOND_MAP_EFFECTIVE);
        assertEquals(Math.pow(0.9, (3-1)), ProfileRecalculation.THIRD_MAP_EFFECTIVE);
        assertEquals(Math.pow(0.9, (4-1)), ProfileRecalculation.FORTH_MAP_EFFECTIVE);
        assertEquals(Math.pow(0.9, (5-1)), ProfileRecalculation.FIFTH_MAP_EFFECTIVE);

        Double[][] data = {{1.0,2.0,3.0,4.0,5.0}};
        double value = 0.0;
        value += 1 * ProfileRecalculation.FIRST_MAP_EFFECTIVE;
        value += 2 * ProfileRecalculation.SECOND_MAP_EFFECTIVE;
        value += 3 * ProfileRecalculation.THIRD_MAP_EFFECTIVE;
        value += 4 * ProfileRecalculation.FORTH_MAP_EFFECTIVE;
        value += 5 * ProfileRecalculation.FIFTH_MAP_EFFECTIVE;
        assertEquals(value, classChecker.profileCalculation(data));
    }
}
