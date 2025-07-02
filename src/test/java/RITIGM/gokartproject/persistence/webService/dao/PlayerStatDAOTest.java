package RITIGM.gokartproject.persistence.webService.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import RITIGM.gokartproject.ReflectUtils;
import RITIGM.gokartproject.model.PlayerStat;
import RITIGM.gokartproject.model.usage.BoostUsage;
import RITIGM.gokartproject.model.usage.DefenseUsage;
import RITIGM.gokartproject.model.usage.OffenseUsage;
import RITIGM.gokartproject.model.usage.TrapUsage;

public class PlayerStatDAOTest {
    private Connection mockConn;
    private PlayerStatDAO playerStatDAO;
    private PlayerStat check;
    private OffenseUsage offense;
    private TrapUsage trap;
    private BoostUsage boost;
    private DefenseUsage defense;

    @BeforeEach
    void init(){
        this.mockConn = mock(Connection.class);
        this.playerStatDAO = new PlayerStatDAO();

        ReflectUtils.setField(this.playerStatDAO, "conn", mockConn);

        this.offense = new OffenseUsage(9,10,11,12);
        this.trap = new TrapUsage(11, 12,13,14);
        this.boost = new BoostUsage(13, 14, 15,16);
        this.defense = new DefenseUsage(0, 0, 0, 0);
        this.check = new PlayerStat("1", "2", "3", 4, "5",6, 6, 7, 8,18,19, 19,
         this.offense, this.trap, this.boost, defense, 16.0, 17.0, 0);
    }


    @Test
    void testGetPlayerStat() throws SQLException{

        // Init mock test for the main class
        PreparedStatement mainStmt = mock(PreparedStatement.class);
        String mainQuery = 
        """
            SELECT
                p.pid, p.Email, p.Password, p.uid, p.username, p.profile,
                SUM(r.collisionwithplayers) AS totalplayercollision,
                SUM(r.collisionwithwalls) AS totalwallcollision,
                SUM(r.fellofmap) AS totalfellofmap,
                SUM(r.speedboost1) AS totalspeedboost1,
                SUM(r.speedboost2) AS totalspeedboost2,
                SUM(r.speedboost3) AS totalspeedboost3,
                SUM(r.speedboost4) AS totalspeedboost4,
                SUM(r.puck1) AS totalpuck1,
                SUM(r.puck2) AS totalpuck2,
                SUM(r.puck2) AS totalpuck3,
                SUM(r.puck2) AS totalpuck4,
                SUM(r.oilspill1) AS totaloilspill1,
                SUM(r.brickwall) AS totalbrickwall,
                SUM(r.confuseritchie) AS totalconfuseritchie,
                SUM(r.fakepowerupblock) AS totalfakepowerupblock,
                SUM(r.defense1) AS totaldefense1,
                SUM(r.defense2) AS totaldefense2,
                SUM(r.defense3) AS totaldefense3,
                SUM(r.defense4) AS totaldefense4

            FROM
                gokart.racelog r
            JOIN
                gokart.players p ON r.pid = p.pid
            WHERE
                p.pid = ?
            GROUP BY
                p.pid, p.Email, p.Password, p.uid, p.username;
        """;

        ResultSet mainSet = mock(ResultSet.class);

        when(this.mockConn.prepareStatement(mainQuery)).
            thenReturn(mainStmt);

        when(mainSet.next()).thenReturn(true).thenReturn(false);

        when(mainStmt.executeQuery()).
            thenReturn(mainSet);

        when(mainSet.getString("pid")).thenReturn("1");
        when(mainSet.getString("Email")).thenReturn("2");
        when(mainSet.getString("Password")).thenReturn("3");
        when(mainSet.getInt("uid")).thenReturn(4);
        when(mainSet.getString("username")).thenReturn("5");
        when(mainSet.getInt("profile")).thenReturn(6);
        when(mainSet.getInt("totalwallcollision")).thenReturn(6);
        when(mainSet.getInt("totalplayercollision")).thenReturn(7);
        when(mainSet.getInt("totalfellofmap")).thenReturn(8);
        when(mainSet.getInt("totalpuck1")).thenReturn(9);
        when(mainSet.getInt("totalpuck2")).thenReturn(10);
        when(mainSet.getInt("totalpuck3")).thenReturn(11);
        when(mainSet.getInt("totalpuck4")).thenReturn(12);
        when(mainSet.getInt("totaloilspill1")).thenReturn(11);
        when(mainSet.getInt("totalbrickwall")).thenReturn(12);
        when(mainSet.getInt("totalconfuseritchie")).thenReturn(13);
        when(mainSet.getInt("totalfakepowerupblock")).thenReturn(14);
        when(mainSet.getInt("totalspeedboost1")).thenReturn(13);
        when(mainSet.getInt("totalspeedboost2")).thenReturn(14);
        when(mainSet.getInt("totalspeedboost3")).thenReturn(15);
        when(mainSet.getInt("totalspeedboost4")).thenReturn(16);
        when(mainSet.getInt("totaldefense1")).thenReturn(0);
        when(mainSet.getInt("totaldefense2")).thenReturn(0);
        when(mainSet.getInt("totaldefense3")).thenReturn(0);
        when(mainSet.getInt("totaldefense4")).thenReturn(0);

        String queryfirst = 
        """
        SELECT
            COALESCE(COUNT(r.racepos), 0) AS totalpodium
        FROM
            gokart.players p
        LEFT JOIN
            gokart.racelog r ON p.pid = r.pid AND r.racepos = 1
        WHERE
            p.pid = ?;        
        """;

        PreparedStatement mockFirst = mock(PreparedStatement.class);
        ResultSet resultFirst = mock(ResultSet.class);

        when(this.mockConn.prepareStatement(queryfirst)).
            thenReturn(mockFirst);

        when(mockFirst.executeQuery()).thenReturn(resultFirst);

        when(resultFirst.next()).thenReturn(true).thenReturn(false);

        when(resultFirst.getDouble("totalpodium")).thenReturn(17.0);

        String queryPodium = 
        """
        SELECT
            COALESCE(COUNT(r.racepos), 0) AS totalpodium
        FROM
            gokart.players p
        LEFT JOIN
            gokart.racelog r ON p.pid = r.pid AND r.racepos >= 1 AND r.racepos <= 3
        WHERE
            p.pid = ?;        
        """;

        PreparedStatement mockPodium = mock(PreparedStatement.class);
        ResultSet resultPodium = mock(ResultSet.class);

        when(this.mockConn.prepareStatement(queryPodium)).
            thenReturn(mockPodium);

        when(mockPodium.executeQuery()).thenReturn(resultPodium);

        when(resultPodium.next()).thenReturn(true).thenReturn(false);

        when(resultPodium.getDouble("totalpodium")).thenReturn(16.0);

        String fastestLap = 
        """
            SELECT MIN(racelog.racetime) AS fastest
            FROM racelog
            WHERE racelog.pid = ?;
        
        """;

        PreparedStatement fastestStmt = mock(PreparedStatement.class);
        ResultSet resultFast = mock(ResultSet.class);

        when(this.mockConn.prepareStatement(fastestLap)).
        thenReturn(fastestStmt);

        when(fastestStmt.executeQuery()).thenReturn(resultFast);

        when(resultFast.next()).thenReturn(true).thenReturn(false);

        when(resultFast.getInt("fastest")).thenReturn(18);

        String favoriteCharacter = 
        """
            SELECT characterused,
                COUNT(racelog.characterused) as favchara

            FROM racelog
            WHERE racelog.pid = ?
            GROUP BY characterused
            ORDER BY favchara DESC
            LIMIT 1;        
        """;

        PreparedStatement favStmt = mock(PreparedStatement.class);
        ResultSet favSet = mock(ResultSet.class);

        when(this.mockConn.prepareStatement(favoriteCharacter)).
            thenReturn(favStmt);

        when(favStmt.executeQuery()).thenReturn(favSet);

        when(favSet.next()).thenReturn(true).thenReturn(false);

        when(favSet.getInt("favchara")).thenReturn(19);


         String totalRaceQuery = "SELECT COUNT(pid) as totalrace\n" + //
                        "FROM racelog\n" + //
                        "WHERE pid = ?";
        PreparedStatement totalStmt = mock(PreparedStatement.class);
        ResultSet totalSet = mock(ResultSet.class);

        when(this.mockConn.prepareStatement(totalRaceQuery)).
            thenReturn(totalStmt);

        when(totalStmt.executeQuery()).thenReturn(totalSet);

        when(totalSet.next()).thenReturn(true).thenReturn(false);

        when(totalSet.getInt("totalrace")).thenReturn(0);

        String favRaceQuery = "SELECT MAX(racePermap.mapcount) as countfavmap, mapraced\n" + //
                        "FROM \n" + //
                        "    (\n" + //
                        "      SELECT COUNT(racelog.mapraced) as mapcount, mapraced\n" + //
                        "      FROM racelog\n" + //
                        "      WHERE pid = ?\n" + //
                        "      GROUP BY  mapraced\n" + //
                        "     ) as racePermap;";

        PreparedStatement raceStmt = mock(PreparedStatement.class);
        ResultSet raceSet = mock(ResultSet.class);

        when(this.mockConn.prepareStatement(favRaceQuery)).
            thenReturn(raceStmt);

        when(raceStmt.executeQuery()).thenReturn(totalSet);

        when(raceSet.next()).thenReturn(true).thenReturn(false);

        when(raceSet.getInt("countfavmap")).thenReturn(19);

        PlayerStat testCase = this.playerStatDAO.getPlayerStat("123");

        assertEquals(this.check.getPid(), testCase.getPid());
        assertEquals(this.check.getEmail(), testCase.getEmail());
        assertEquals(this.check.getPw(), testCase.getPw());
        assertEquals(this.check.getUid(), testCase.getUid());
        assertEquals(this.check.getUsername(), testCase.getUsername());
        assertEquals(this.check.getCollisionWithWall(), testCase.getCollisionWithWall());
        assertEquals(this.check.getCollisionWithPlayer(), testCase.getCollisionWithPlayer());
        assertEquals(this.check.getFelloffmap(), testCase.getFelloffmap());
        assertEquals(this.check.getTotalRaces(), testCase.getTotalRaces());
        assertEquals(this.check.getFavoriteTrack(), testCase.getFavoriteTrack());
    }
}
