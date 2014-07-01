/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gamebasislib.player;

import com.jme3.math.Vector3f;
import java.util.Vector;

/**
 *
 * @author Justin
 */
public class PlayerPos {
    
    protected Vector3f playerpos = new Vector3f(0, 0, 0);
    
    public PlayerPos (float x, float y, float z) {
        this.playerpos = new Vector3f(x, y, z);
    }
    
    public void move (float x, float y, float z) {
        this.playerpos.add(new Vector3f(x, y, z));
    }
    
    public void setVector3f (Vector3f playerpos) {
        this.playerpos = playerpos;
    }
    
    public Vector3f getPlayerPos () {
        return this.playerpos;
    }
    
}
