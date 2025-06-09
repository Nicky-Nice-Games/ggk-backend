package RITIGM.gokartproject.persistence.webService.interfaces;

import RITIGM.gokartproject.model.LeaderboardData;

public interface LeaderboardInterface {
    
    abstract LeaderboardData getTopRace(String pid, int mapID);
    

    
}
