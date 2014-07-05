/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gamebasislib.gameworldlighting;

import com.jme3.app.SimpleApplication;
import com.jme3.light.DirectionalLight;
import com.jme3.light.Light;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.post.FilterPostProcessor;
import com.jme3.post.ssao.SSAOFilter;
import de.gamebasislib.gameworld.GameWorld;
import de.gamebasislib.player.IPlayer;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Justin
 */
public class GameWorldLighting {
    
    protected SimpleApplication app = null;
    protected IPlayer player = null;
    protected static GameWorldLighting instance = null;
    protected List<Light> gamelights = new ArrayList<Light>();
    protected DirectionalLight sun = new DirectionalLight();
    protected GameWorld gameworld = null;
    
    public GameWorldLighting (SimpleApplication app, GameWorld gameworld, IPlayer player) {
        this.app = app;
        this.gameworld = gameworld;
        this.player = player;
        
        sun.setColor(ColorRGBA.White);
        sun.setDirection(new Vector3f(-.5f,-.5f,-.5f).normalizeLocal());
        this.app.getRootNode().addLight(sun);
        
        //Ambient Occlusion
        FilterPostProcessor fpp = new FilterPostProcessor(this.app.getAssetManager());
        SSAOFilter ssaoFilter = new SSAOFilter(12.94f, 43.92f, 0.33f, 0.61f);
        fpp.addFilter(ssaoFilter);
        this.app.getViewPort().addProcessor(fpp);
    }
    
    public boolean isInRadius (float x, float y, float radius) {
        Vector2f vector = new Vector2f(x, y);
        // return true if the distance is less than equal to the radius
        return vector.length() <= radius;
    }
    
    /*
     * Is executed by the Main.simpleUpdate() Method 
     */
    public void simpleUpdate (float tpf) {
        //Alle GameLightings, die sich in einem bestimmten Radius des Players befinden
        //müssen in die GameWorld gerendert werden, die anderen können entfernt werden
    }
    
    public void setPlayer (IPlayer player) {
        this.player = player;
    }
    
    public static GameWorldLighting getInstance () {
        return GameWorldLighting.instance;
    }
    
    public static void setInstance (GameWorldLighting gameworldlighting) {
        GameWorldLighting.instance = gameworldlighting;
    }
    
}
