package RITIGM.gokartproject.view.gameService;

import RITIGM.gokartproject.model.PlayerInfo;
import RITIGM.gokartproject.persistence.gameService.interfaces.PlayerInfoInterface;

import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("gameserivce/gamelog")
public class PlayerInfoService {
    private static final Logger log = Logger.getLogger(GameLogService.class.getName());
    private PlayerInfoInterface playerInfoDAO;


    public PlayerInfoService(PlayerInfoInterface playerInfoDAO){
        this.playerInfoDAO = playerInfoDAO;
    }

    /**
     * 
     */
    @GetMapping("/{pid}")
    public ResponseEntity<PlayerInfo> getPlayerByID(@PathVariable String pid){
        log.info("GET /playerservice/playerinfo/" + pid);
        try{
            PlayerInfo playerInfo = this.playerInfoDAO.getPlayerInfo(pid);
            if(playerInfo != null){
                return new ResponseEntity<PlayerInfo>(playerInfo, HttpStatus.OK);
            }
            else{
                 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}