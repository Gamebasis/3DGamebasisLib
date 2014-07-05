/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gamebasislib.gameworld;

import com.jme3.app.SimpleApplication;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.terrain.heightmap.ImageBasedHeightMap;
import de.gamebasislib.database.Database;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Justin
 */
public class GameWorld {
    
    protected int size_x = 0;
    protected int size_y = 0;
    
    protected Material mat_terrain = null;
    protected String filePath = "";
    protected Database gameworld_database = null;
    protected ImageBasedHeightMap map = null;
    
    protected Material material = null;
    protected GameWorldSettings gameworldsettings = null;
    protected DirectionalLight sun = new DirectionalLight();
    
    public GameWorld (SimpleApplication app) {
        this.mat_terrain = new Material(app.getAssetManager(), "Common/MatDefs/Terrain/HeightBasedTerrain.j3md");
        this.gameworldsettings = new GameWorldSettings();
        
        this.sun.setColor(ColorRGBA.White);
        this.sun.setDirection(new Vector3f(-.5f,-.5f,-.5f).normalizeLocal());
    }
    
    public void loadGameWorld (String filePath) {
        //LastGameWorld setzen
        GameWorld.setLastGameWorld(filePath);
        
        this.filePath = filePath;
        this.gameworld_database = new Database(this.filePath + "/gameworld.db");
        this.gameworld_database.open();
        
        //GameWorldSettings laden
        this.gameworldsettings.loadGameSettings(this.gameworld_database);
    }
    
    public void setDatabase (Database database) {
        this.gameworld_database = database;
    }
    
    public Database getDatabase () {
        return this.gameworld_database;
    }
    
    public GameWorldSettings getGameWorldSettings () {
        return this.gameworldsettings;
    }
    
    public void setGameWorldSettings (GameWorldSettings gameworldsettings) {
        this.gameworldsettings = gameworldsettings;
    }
    
    public static String getLastGameWorld () {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File("./ext/gameworld/lastGameWorld.cfg")));
            
            return reader.readLine();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GameWorld.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GameWorld.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "";
    }
    
    public static void setLastGameWorld (String name) {
        File f = new File("./ext/gameworld/" + name);
        
        if (f.exists()) {
            try {
                FileWriter writer = new FileWriter(new File("./ext/gameworld/lastGameWorld.cfg"));
                writer.write(name);
                writer.flush();
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(GameWorld.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
