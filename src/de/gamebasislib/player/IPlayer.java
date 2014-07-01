/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gamebasislib.player;

import com.jme3.math.Vector3f;

/**
 *
 * @author Justin
 */
public interface IPlayer {
    
    public void setPlayerPos (Vector3f pos);
    public void init ();
    public PlayerPos getPlayerPos();
    public int getPlayerID ();
    public void setPlayerID (int playerID);
    
}
