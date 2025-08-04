package RITIGM.gokartproject.view.webService;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.apache.tomcat.util.digester.ArrayStack;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.mailjet.client.ClientOptions;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.resource.Emailv31;

import RITIGM.gokartproject.model.AdminInfo;
import RITIGM.gokartproject.model.PlayerInfo;
import RITIGM.gokartproject.model.PlayerStat;
import RITIGM.gokartproject.model.RaceLog;
import RITIGM.gokartproject.model.RaceReport;
import RITIGM.gokartproject.model.responseReceiver.CreateUID;
import RITIGM.gokartproject.model.responseReceiver.LoginCreds;
import RITIGM.gokartproject.model.responseReceiver.NoUID;
import RITIGM.gokartproject.persistence.webService.interfaces.PlayerStatInterface;
import RITIGM.gokartproject.persistence.webService.interfaces.WebPlayerInfoInterface;
import RITIGM.gokartproject.view.gameService.GameLogService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@CrossOrigin(origins = "https://nickynicegames.com")
@RequestMapping("webservice/playerinfo")
public class WebPlayerInfoService {
    private static final Logger log = Logger.getLogger(GameLogService.class.getName());
    private WebPlayerInfoInterface webPlayerInfoDAO;
    private PlayerStatInterface playerStatDAO;

    public WebPlayerInfoService(WebPlayerInfoInterface webPlayerInfoDAO, PlayerStatInterface playerStatDAO){
        this.webPlayerInfoDAO = webPlayerInfoDAO;
        this.playerStatDAO = playerStatDAO;
    }


    /**
     * Retrives player information by ID
     * @param pid player ID
     * @return the player corresponding to if succesful 
     */
    @GetMapping("/getplayerinfo/{pid}")
    public ResponseEntity<PlayerInfo> getPlayerByID(@PathVariable String pid){
        log.info("GET /webservice/playerinfo/" + pid);
        
        try{
            PlayerInfo playerInfo = this.webPlayerInfoDAO.getPlayerInfo(pid);
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

    /**
     * Retrieves player by username
     * @param username player's username
     * @param password player's password
     * @return the player data corresponding to the username and password if successful
     */
    @GetMapping("/getinfo/{username}/{password}")
    public ResponseEntity<PlayerInfo> getPlayerByUsername(@PathVariable String username, @PathVariable String password){
        log.info("GET /webservice/playerinfo/" + username);
        try {
            PlayerInfo playerInfo = this.webPlayerInfoDAO.getPlayerInfoWithUsername(username, password);
            if(playerInfo != null){
                return new ResponseEntity<PlayerInfo>(playerInfo, HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

        /**
     * Creates a new user in the database
     * @param info object containing email, password, and username
     * @return the new user if they were succesfully created
     */
    @PostMapping("/create")
    public ResponseEntity<PlayerInfo> createUser(@RequestBody NoUID info){
        log.info("POST /webservice/playerinfo/" + info.getUsername());
        try {
            if(webPlayerInfoDAO.verifyEmail(info.getEmail()) || webPlayerInfoDAO.verifyUsername(info.getUsername())){
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
            PlayerInfo new_player = webPlayerInfoDAO.createUser(info.getEmail(), info.getPassword(), info.getUsername());

            if(new_player != null){
                return new ResponseEntity<PlayerInfo>(new_player, HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            System.err.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Overload of user creation that accepts an additional uid parameter
     * @param info object containing email, password, uid, and username
     * @return the new user if they were successfully added
     */
    @PostMapping("/create/uid")
    public ResponseEntity<PlayerInfo> createUser(@RequestBody CreateUID info){
        log.info("POST /webservice/playerinfo/create/uid" + info.getUsername());
        try {

            if(webPlayerInfoDAO.verifyEmail(info.getEmail()) || webPlayerInfoDAO.verifyUsername(info.getUsername())){
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
            PlayerInfo new_player = webPlayerInfoDAO.createUser(info.getEmail(), info.getPassword(),  info.getUid(), info.getUsername());

            if(new_player != null){
                return new ResponseEntity<PlayerInfo>(new_player, HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * retreives pkayer data corresponding to player id
     * @param pid player id
     * @return relevant player data
     */
    @GetMapping("/getdetailinfo/{pid}")
    public ResponseEntity<PlayerStat> getPlayerDetailInfo(@PathVariable String pid) {
        log.info("POST /webservice/playerinfo/getinfo/" + pid);


        try {
            PlayerStat info = this.playerStatDAO.getPlayerStat(pid);
            return (info != null) ? new ResponseEntity<PlayerStat>(info, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            System.err.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * checks if an email is currently in use
     * @param email email to check
     * @return true if email is in use, false if not
     */
    @GetMapping("/verifyemail")
    public ResponseEntity<Boolean> checkEmail(@RequestParam String email){
        try{
            boolean email_status = webPlayerInfoDAO.verifyEmail(email);
            return new ResponseEntity<Boolean>(email_status, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * checks if a username is currently in use
     * @param username username to check
     * @return true if username is in use, false if not
     */
    @GetMapping("/verifyusername")
    public ResponseEntity<Boolean> checkUsername(@RequestParam String username){
        try{
            boolean email_status = webPlayerInfoDAO.verifyUsername(username);
            return new ResponseEntity<Boolean>(email_status, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * retrieves a log of a players most recent races
     * @param pid player id
     * @returna list of a players most recent races, up to 5 races max
     */
    @GetMapping("/getrecentstats/{pid}")
    public ResponseEntity<ArrayList<RaceReport>> getRecentRaces(@PathVariable String pid){
        try{
            ArrayList<RaceReport> recentRaces = webPlayerInfoDAO.getRecentGames(pid);
            return new ResponseEntity<ArrayList<RaceReport>>(recentRaces, HttpStatus.OK);
        } 
        catch( Exception e){
            System.err.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Retrieves player specific data about perfomance on a specific track
     * @param pid player's id
     * @param tid track's id
     * @return returns the top postion (first index in arraylist) player has earned on the track, and the player's fastest
     * time (second postion in the arraylist)
     */
    @GetMapping("/{pid}/{tid}")
    public ResponseEntity<ArrayList<Integer>> getPlayerTrackData(@PathVariable String pid, @PathVariable int tid){
        log.info("GET /webservice/playerinfo/{pid}/{tid}");
        try{
            ArrayList<Integer> stats = webPlayerInfoDAO.getSpecificTrackData(pid, tid);
            if(stats != null){
                return new ResponseEntity<ArrayList<Integer>>(stats, HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/admincheck/{username}/{password}")
    public ResponseEntity<AdminInfo> adminLogin(@PathVariable String username, @PathVariable String password){
        log.info("GET /webservice/admininfo/{username}/{password}");
        try{
            AdminInfo admin = webPlayerInfoDAO.getAdminInfoWithUsername(username, password);
            if(admin != null){
                return new ResponseEntity<AdminInfo>(admin, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/admincheck/{adminId}")
    public ResponseEntity<AdminInfo> getAdminInfo(@PathVariable String adminId){
        log.info("GET /webservice/playerinfo/admincheck/{adminId}");
        try{
            AdminInfo admin = webPlayerInfoDAO.getAdminInfo(adminId);
            if(admin != null){
                return new ResponseEntity<AdminInfo>(admin, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch(Exception e){
            System.err.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
    @PutMapping("/changepfp")
    public ResponseEntity<Void> changePfp(@RequestParam int pfp, @RequestParam String pid){
        log.info("PUT /webservice/playerinfo/changepfp");
        try{
            
            Boolean success = webPlayerInfoDAO.changePfp(pfp, pid);
            if(success){
                return new ResponseEntity<>(HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/passwordreset")
    public ResponseEntity<Void> updatePassword(@RequestParam String email, @RequestParam String password) {
        log.info("PUT /webservice/playerinfo/passwordreset/ Email = " + email);
        try {
            Boolean check = webPlayerInfoDAO.resetPassword(email, password);
            if(check){
                return new ResponseEntity<>(HttpStatus.OK);
            } else{
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }

    @PostMapping("/sendemail")
    public ResponseEntity<Void> sendEmail(@RequestParam String email) {
        log.info("POST /webservice/playerinfo/sendemail/ Email = " + email);
        try{
            MailjetClient client;
            MailjetRequest request;
            MailjetResponse response;
            //client = new MailjetClient(System.getenv("MJ_APIKEY_PUBLIC"), System.getenv("MJ_APIKEY_SECRET"));
            client = new MailjetClient(System.getenv("MJ_APIKEY_PUBLIC"), System.getenv("MJ_APIKEY_PRIVATE"));
            request = new MailjetRequest(Emailv31.resource)
                    .property(Emailv31.MESSAGES, new JSONArray()
                        .put(new JSONObject()
                            .put(Emailv31.Message.FROM, new JSONObject()
                                .put("Email", "pilot@mailjet.com")
                                .put("Name", "Mailjet Pilot"))
                            .put(Emailv31.Message.TO, new JSONArray()
                                .put(new JSONObject()
                                    .put("Email", "passenger1@mailjet.com")
                                    .put("Name", "passenger 1")))
                            .put(Emailv31.Message.SUBJECT, "Your email flight plan!")
                            .put(Emailv31.Message.TEXTPART, "Dear passenger 1, welcome to Mailjet! May the delivery force be with you!")
                            .put(Emailv31.Message.HTMLPART, "<h3>Dear passenger 1, welcome to <a href=\"https://www.mailjet.com/\">Mailjet</a>!</h3><br />May the delivery force be with you!")));
            response = client.post(request);
            System.out.println(response.getStatus());
            System.out.println(response.getData());
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
