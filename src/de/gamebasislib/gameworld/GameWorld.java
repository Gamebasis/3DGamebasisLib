/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gamebasislib.gameworld;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
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
    
    public GameWorld (SimpleApplication app) {
        this.mat_terrain = new Material(app.getAssetManager(), "Common/MatDefs/Terrain/HeightBasedTerrain.j3md");
    }
    
    public void loadGameWorld (String name) {
        //LastGameWorld setzen
        GameWorld.setLastGameWorld(name);
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
