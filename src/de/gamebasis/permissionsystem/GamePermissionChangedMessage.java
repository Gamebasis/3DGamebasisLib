/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gamebasis.permissionsystem;

import com.jme3.network.AbstractMessage;
import com.jme3.network.serializing.Serializable;
import java.util.HashMap;

/**
 *
 * @author Justin
 */
@Serializable
public class GamePermissionChangedMessage extends AbstractMessage {
    
    protected HashMap<String,Boolean> gamepermissions = new HashMap<String,Boolean>();
    
    public GamePermissionChangedMessage () {
        //
    }
    
    public void addPermission (String permissionname, boolean isAuthorized) {
        this.gamepermissions.put(permissionname, isAuthorized);
    }
    
    public HashMap<String,Boolean> getHashMap () {
        return this.gamepermissions;
    }
    
    public void addPermissions (HashMap<String,Boolean> gamepermissions) {
        this.gamepermissions.putAll(gamepermissions);
    }
    
}
