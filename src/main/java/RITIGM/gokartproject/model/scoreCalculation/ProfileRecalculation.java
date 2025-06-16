package RITIGM.gokartproject.model.scoreCalculation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Recalculates score for player profile
 */
public class ProfileRecalculation {
    public static final int TOTALTRACK = 1;
    public static final double FIRST_MAP_EFFECTIVE = Math.pow(0.9, (1-1));
    public static final double SECOND_MAP_EFFECTIVE = Math.pow(0.9, (2-1));
    public static final double THIRD_MAP_EFFECTIVE = Math.pow(0.9, (3-1));
    public static final double FORTH_MAP_EFFECTIVE = Math.pow(0.9, (4-1));
    public static final double FIFTH_MAP_EFFECTIVE = Math.pow(0.9, (5-1));

    /**
     * constructor(?)
     */
    public ProfileRecalculation(){
        // Nothing
    }

    /**
     * Parsing x by 5 matrix
     * @param playerScore the x by 5 of all of the score PLEASE HAVE A 0 FOR ALL OF THE BLANK
     * @return the total score
     */
    public double profileCalculation(Double[][] playerScore){
        double returnScore = 0;

        for(Double[] scoreSet : playerScore){
            returnScore += scoreSet[0] * FIRST_MAP_EFFECTIVE;
            returnScore += scoreSet[1] * SECOND_MAP_EFFECTIVE;
            returnScore += scoreSet[2] * THIRD_MAP_EFFECTIVE;
            returnScore += scoreSet[3] * FORTH_MAP_EFFECTIVE;
            returnScore += scoreSet[4] * FIFTH_MAP_EFFECTIVE;
        }

        return returnScore;
    }
}
