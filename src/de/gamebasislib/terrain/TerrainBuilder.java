/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gamebasislib.terrain;

import com.jme3.app.SimpleApplication;
import com.jme3.math.Vector2f;
import com.jme3.terrain.heightmap.ImageBasedHeightMap;
import com.jme3.texture.Texture;
import de.gamebasislib.database.Database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Justin
 */
public class TerrainBuilder {
    
    protected HashMap<Integer,ImageBasedHeightMap> heightmaps = new HashMap<Integer,ImageBasedHeightMap>();
    protected HashMap<Integer,Vector2f> terrainstartpositions = new HashMap<Integer,Vector2f>();
    protected int terrains = 0;
    protected SimpleApplication app = null;
    protected HashMap<Integer,Texture> heightmaptextures = new HashMap<Integer,Texture>();
    
    public TerrainBuilder (SimpleApplication app) {
        this.app = app;
    }
    
    public void loadHeightMaps (Database database) {
        try {
            ResultSet rs = database.select("SELECT * FROM `terrain`; ");
            while (rs.next()) {
                Vector2f vector = new Vector2f(rs.getInt("x"), rs.getInt("y"));
                Texture heightMapImage = this.app.getAssetManager().loadTexture(rs.getString("heightmap"));
                ImageBasedHeightMap heightMap = new ImageBasedHeightMap(heightMapImage.getImage());
                
                this.heightmaptextures.put(++this.terrains, heightMapImage);
                this.heightmaps.put(this.terrains, heightMap);
                this.terrainstartpositions.put(this.terrains, vector);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TerrainBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
