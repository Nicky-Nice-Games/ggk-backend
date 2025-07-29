package RITIGM.gokartproject.model;

public class TopRaceScore {
    private int raceTime1;
    private int raceTime2;
    private int raceTime3;
    private int raceTime4;

    public TopRaceScore(int raceTime1, int raceTime2,int raceTime3,int raceTime4){
        this.raceTime1 = raceTime1;
        this.raceTime2 = raceTime2;
        this.raceTime3 = raceTime3;
        this.raceTime4 = raceTime4;
    }

    public int getRaceTime1() {
        return raceTime1;
    }
    public void setRaceTime1(int raceTime1) {
        this.raceTime1 = raceTime1;
    }
    public int getRaceTime2() {
        return raceTime2;
    }
    public void setRaceTime2(int raceTime2) {
        this.raceTime2 = raceTime2;
    }
    public int getRaceTime3() {
        return raceTime3;
    }
    public void setRaceTime3(int raceTime3) {
        this.raceTime3 = raceTime3;
    }
    public int getRaceTime4() {
        return raceTime4;
    }
    public void setRaceTime4(int raceTime4) {
        this.raceTime4 = raceTime4;
    }


    
}
