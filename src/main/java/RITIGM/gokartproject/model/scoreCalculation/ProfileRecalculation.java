package RITIGM.gokartproject.model.scoreCalculation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ProfileRecalculation {
    public static final int TOTALTRACK = 1;
    public static final double FIRST_MAP_EFFECTIVE = Math.pow(0.9, (1-1));
    public static final double SECOND_MAP_EFFECTIVE = Math.pow(0.9, (2-1));
    public static final double THIRD_MAP_EFFECTIVE = Math.pow(0.9, (3-1));
    public static final double FORTH_MAP_EFFECTIVE = Math.pow(0.9, (4-1));
    public static final double FIFTH_MAP_EFFECTIVE = Math.pow(0.9, (5-1));

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

    public static void main(String[] args) {

        Double[][] check = 
        {
            {19375.0, 18775.0, 18393.0, 14856.0, 12937.0},
            {33392.0, 32024.0, 30283.0, 29464.0, 28774.0},
            {46792.0, 45992.0, 45827.0, 44259.0, 43027.0},
            {67284.0, 65917.0, 63957.0, 62384.0, 61028.0}
        };

        ProfileRecalculation b = new ProfileRecalculation();
        Double a = b.profileCalculation(check);
        System.out.println(a);
        System.out.println(a);
        System.out.println(a);
        System.out.println(a);
        System.out.println(a);

    }
}
