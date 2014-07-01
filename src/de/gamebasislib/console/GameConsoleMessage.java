/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gamebasislib.console;

import com.jme3.network.AbstractMessage;
import com.jme3.network.serializing.Serializable;

/**
 *
 * @author Justin
 */
@Serializable
public class GameConsoleMessage extends AbstractMessage {
    
    protected String message = "";
    
    public GameConsoleMessage (String message) {
        this.message = message;
    }
    
    public GameConsoleMessage () {
        this.message = "";
    }
    
    public void setMessage (String message) {
        this.message = message;
    }
    
    public String getMessage () {
        return this.message;
    }
    
}
