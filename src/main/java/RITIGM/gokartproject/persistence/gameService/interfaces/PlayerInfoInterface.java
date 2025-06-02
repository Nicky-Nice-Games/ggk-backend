package RITIGM.gokartproject.persistence.gameService.interfaces;

import RITIGM.gokartproject.model.PlayerInfo;

public interface PlayerInfoInterface {
    /**
     * Get playerInfo by playerID
     * @param playerID the player ID
     * @return the player information corresponding to the ID provided. returns null if ID does not correspond
     * an existing player
     */
    PlayerInfo getPlayerInfo(int playerID);
}
