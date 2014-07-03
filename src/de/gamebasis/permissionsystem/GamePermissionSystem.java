/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gamebasis.permissionsystem;

import com.jme3.network.Client;
import com.jme3.network.Message;
import com.jme3.network.MessageListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

/**
 *
 * @author Justin
 */
public class GamePermissionSystem implements MessageListener<Client> {
    
    protected HashMap<String,Boolean> gamepermissions = new HashMap<String,Boolean>();
    protected static GamePermissionSystem instance = null;
    
    public GamePermissionSystem () {
        GamePermissionSystem.setInstance(this);
    }
    
    public boolean isAuthorized (String permissionname) {
        if (this.gamepermissions.containsKey(permissionname)) {
            return this.gamepermissions.get(permissionname);
        } else {
            return false;
        }
    }

    @Override
    public void messageReceived(Client source, Message message) {
        if (message instanceof GamePermissionChangedMessage) {
            GamePermissionChangedMessage gamepermissionchangedmessage = (GamePermissionChangedMessage) message;
            this.gamepermissions.putAll(gamepermissionchangedmessage.getHashMap());
        }
    }
    
    public List<String> getGamePermissionList () {
        List<String> res = new ArrayList<String>();
        
        for (Entry<String,Boolean> entry : this.gamepermissions.entrySet()) {
            if (entry.getValue()) {
                res.add(entry.getKey());
            }
        }
        
        return res;
    }
    
    public static GamePermissionSystem getInstance () {
        return GamePermissionSystem.instance;
    }
    
    public static void setInstance (GamePermissionSystem gamepermissionsystem) {
        GamePermissionSystem.instance = gamepermissionsystem;
    }
    
}
