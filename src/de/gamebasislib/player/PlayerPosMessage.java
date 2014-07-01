/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gamebasislib.player;

import com.jme3.network.AbstractMessage;
import com.jme3.network.serializing.Serializable;

/**
 *
 * @author Justin
 */
@Serializable
public class PlayerPosMessage extends AbstractMessage {
    
    protected PlayerPos playerpos = null;
    protected boolean overwridePlayerPos = false;
    protected int playerID = -1;
    protected IPlayer player = null;
    
    public PlayerPosMessage (PlayerPos playerpos, int playerID, IPlayer player) {
        this.playerpos = playerpos;
        this.playerID = playerID;
        this.player = player;
    }
    
    public PlayerPosMessage () {
        this.playerpos = null;
    }
    
    public void overwridePlayerPos (boolean overwritePlayerPos) {
        this.overwridePlayerPos = overwritePlayerPos;
    }
    
    public boolean overwritePlayerPos () {
        return this.overwridePlayerPos;
    }
    
    public void setPlayerPos (PlayerPos playerpos) {
        this.playerpos = playerpos;
    }
    
    public PlayerPos getPlayerPos () {
        return this.playerpos;
    }
    
}
