/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gamebasislib.console;

import de.gamebasislib.event.GameEvent;
import de.gamebasislib.player.IPlayer;

/**
 *
 * @author Justin
 */
public class GameConsoleEvent implements GameEvent {
    
    protected String message = "";
    protected IPlayer player = null;
    
    public GameConsoleEvent (String message, IPlayer player) {
        this.message = message;
        this.player = player;
    }
    
    public String getMessage () {
        return this.message;
    }
    
    public void setMessage (String message) {
        this.message = message;
    }
    
    public IPlayer getPlayer () {
        return this.player;
    }
    
}
