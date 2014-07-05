/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gamebasislib.terrain;

import com.jme3.math.Vector2f;
import com.jme3.network.AbstractMessage;
import com.jme3.network.serializing.Serializable;
import com.jme3.terrain.heightmap.ImageBasedHeightMap;
import com.jme3.texture.Texture;
import java.util.HashMap;

/**
 *
 * @author Justin
 */
@Serializable
public class GameWorldTerrain extends AbstractMessage {
    
    protected int terrainID = 0;
    protected Vector2f position = null;
    protected Texture heightMapImage = null;
    protected ImageBasedHeightMap heightMap = null;
    protected HashMap<String,Texture> textures = new HashMap<String,Texture>();
    protected int terraintiles = 65;
    
    protected int size_x = 513;
    protected int size_y = 513;
    
    public GameWorldTerrain () {
        //
    }
    
    public void setTerrainID (int terrainID) {
        this.terrainID = terrainID;
    }
    
    public int getTerrainID () {
        return this.terrainID;
    }
    
    public void setHeightMapImage (Texture texture) {
        this.heightMapImage = texture;
    }
    
    public Texture getHeightMapImage () {
        return this.heightMapImage;
    }
    
    public ImageBasedHeightMap createHeightMap () {
        this.heightMap = new ImageBasedHeightMap(this.heightMapImage.getImage());
        return this.heightMap;
    }
    
    public Vector2f getPosition () {
        return this.position;
    }
    
    public void setPosition (Vector2f vector) {
        this.position = vector;
    }
    
    public HashMap<String,Texture> getTextures () {
        return this.textures;
    }
    
    public void addTexture (String name, Texture texture) {
        this.textures.put(name, texture);
    }
    
    public int getTerrainTilesSize () {
        return this.terraintiles;
    }
    
    public Vector2f getTerrainSize () {
        return new Vector2f(this.size_x, this.size_y);
    }
    
}
