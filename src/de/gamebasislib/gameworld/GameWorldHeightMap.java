/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gamebasislib.gameworld;

import com.jme3.network.AbstractMessage;
import com.jme3.network.serializing.Serializable;
import com.jme3.terrain.heightmap.HeightMap;
import java.util.Vector;

/**
 *
 * @author Justin
 */
@Serializable
public class GameWorldHeightMap extends AbstractMessage {
    
    protected HeightMap map = null;
    
    public GameWorldHeightMap () {
        //
    }
    
    public GameWorldHeightMap (HeightMap map) {
        this.map = map;
    }
    
    public HeightMap getMap () {
        return this.map;
    }
    
}
