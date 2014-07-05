/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gamebasis.gamesettings;

import com.jme3.network.AbstractMessage;
import com.jme3.network.serializing.Serializable;
import java.util.HashMap;

/**
 *
 * @author Justin
 */
@Serializable
public class GameSettingsChangedMessage extends AbstractMessage {
    
    protected HashMap<String,String> gamesettings = new HashMap<String,String>();
    
    public GameSettingsChangedMessage () {
        //
    }
    
    public void setHashMap (HashMap<String,String> gamesettings) {
        this.gamesettings.putAll(gamesettings);
    }
    
    public HashMap<String,String> getHashMap () {
        return this.gamesettings;
    }
    
    public void addGameSetting (String gamesetting, String value) {
        this.gamesettings.put(gamesetting, value);
    }
    
}
