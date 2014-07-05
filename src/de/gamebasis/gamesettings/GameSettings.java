/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gamebasis.gamesettings;

import com.jme3.network.Client;
import com.jme3.network.Server;
import de.gamebasislib.database.Database;
import de.gamebasislib.event.GameEventManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Justin
 */
public class GameSettings {
    
    protected HashMap<String,String> gamesettings = new HashMap<String,String>();
    protected Client client = null;
    protected Server server = null;
    protected static GameSettings instance = null;
    
    public GameSettings () {
        //
    }
    
    public void loadGameSettings () {
        this.gamesettings.clear();
        
        Database database = Database.getInstance();
        
        try {
            ResultSet rs = database.select("SELECT * FROM `gamesettings`; ");
            while (rs.next()) {
                this.gamesettings.put(rs.getString("gamesetting"), rs.getString("value"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(GameSettings.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addGameSetting (String gamesetting, String value) {
        this.gamesettings.put(gamesetting, value);
        
        try {
            PreparedStatement preparedstatement = Database.getInstance().getPreparedStatement("INSERT INTO `gamesettings` (`gamesetting`, `value`) VALUES (?, ?) ON DUPLICATE KEY UPDATE `value` = ?; ");
            preparedstatement.setString(1, gamesetting);
            preparedstatement.setString(2, value);
            preparedstatement.setString(3, value);
            preparedstatement.executeQuery();
            
            GameSettingsChangedMessage gamesettingschangedmessage = new GameSettingsChangedMessage();
            gamesettingschangedmessage.setHashMap(this.gamesettings);
            
            if (this.client != null) {
                this.client.send(gamesettingschangedmessage);
            }
            
            if (this.server != null) {
                this.server.broadcast(gamesettingschangedmessage);
            }
            
            //Event werfen
            GameSettingsChangedEvent gamesettingschangedevent = new GameSettingsChangedEvent();
            gamesettingschangedevent.addGameSetting(gamesetting, value);
            GameEventManager.raiseEvent(gamesettingschangedevent);
        } catch (SQLException ex) {
            Logger.getLogger(GameSettings.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String getGameSetting (String gamesetting) {
        if (this.gamesettings.containsKey(gamesetting)) {
            return this.gamesettings.get(gamesetting);
        } else {
            return "";
        }
    }
    
    public static GameSettings getInstance () {
        return GameSettings.instance;
    }
    
    public static void setInstance (GameSettings gamesettings) {
        GameSettings.instance = gamesettings;
    }
    
}
