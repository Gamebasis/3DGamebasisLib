/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gamebasislib.camera;

import com.jme3.app.SimpleApplication;
import com.jme3.math.Vector2f;
import com.jme3.renderer.Camera;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Justin
 */
public class GameCameraManager {
    
    SimpleApplication app = null;
    protected static GameCameraManager instance = null;
    protected Camera camera = null;
    protected List<Camera> cameralist = new ArrayList<Camera>();
    
    public GameCameraManager (SimpleApplication app) {
        this.app = app;
        this.camera = this.app.getCamera();
        this.addCamera(this.camera);
    }
    
    public void setCameraPosition (Vector2f position) {
        //
    }
    
    public List<Camera> getCameras () {
        return this.cameralist;
    }
    
    public void addCamera (Camera camera) {
        this.cameralist.add(camera);
    }
    
    public static GameCameraManager getInstance () {
        return GameCameraManager.instance;
    }
    
    public static void setInstance (GameCameraManager gamecameramanager) {
        GameCameraManager.instance = gamecameramanager;
    }
    
}
