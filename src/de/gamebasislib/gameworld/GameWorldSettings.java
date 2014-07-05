/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gamebasislib.gameworld;

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
public class GameWorldSettings {
    
    protected HashMap<String,String> gameworldsettings = new HashMap<String,String>();
    protected Client client = null;
    protected Server server = null;
    protected static GameWorldSettings instance = null;
    
    protected Database gameworld_database = null;
    
    public GameWorldSettings () {
        //
    }
    
    public void loadGameSettings (Database database) {
        this.gameworld_database = database;
        this.gameworldsettings.clear();
        
        try {
            ResultSet rs = this.gameworld_database.select("SELECT * FROM `gameworldsettings`; ");
            while (rs.next()) {
                this.gameworldsettings.put(rs.getString("gameworldsetting"), rs.getString("value"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(GameWorldSettings.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addGameWorldSetting (String gamesetting, String value) {
        this.gameworldsettings.put(gamesetting, value);
        
        try {
            PreparedStatement preparedstatement = Database.getInstance().getPreparedStatement("INSERT INTO `gameworldsettings` (`gamesetting`, `value`) VALUES (?, ?) ON DUPLICATE KEY UPDATE `value` = ?; ");
            preparedstatement.setString(1, gamesetting);
            preparedstatement.setString(2, value);
            preparedstatement.setString(3, value);
            preparedstatement.executeQuery();
            
            GameWorldSettingsChangedMessage gameworldsettingschangedmessage = new GameWorldSettingsChangedMessage();
            gameworldsettingschangedmessage.setHashMap(this.gameworldsettings);
            
            if (this.client != null) {
                this.client.send(gameworldsettingschangedmessage);
            }
            
            if (this.server != null) {
                this.server.broadcast(gameworldsettingschangedmessage);
            }
            
            //Event werfen
            GameWorldSettingsChangedEvent gameworldsettingschangedevent = new GameWorldSettingsChangedEvent();
            gameworldsettingschangedevent.addGameSetting(gamesetting, value);
            GameEventManager.raiseEvent(gameworldsettingschangedevent);
        } catch (SQLException ex) {
            Logger.getLogger(GameWorldSettings.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addGameWorldSettings (HashMap<String,String> gameworldsettings) {
        this.gameworldsettings.putAll(gameworldsettings);
    }
    
    public HashMap<String,String> getHashMap () {
        return this.gameworldsettings;
    }
    
    public String getGameWorldSetting (String gamesetting) {
        if (this.gameworldsettings.containsKey(gamesetting)) {
            return this.gameworldsettings.get(gamesetting);
        } else {
            return "";
        }
    }
    
    public static GameWorldSettings getInstance () {
        return GameWorldSettings.instance;
    }
    
    public static void setInstance (GameWorldSettings gamesettings) {
        GameWorldSettings.instance = gamesettings;
    }
    
}
