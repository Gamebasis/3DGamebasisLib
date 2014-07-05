/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gamebasislib.gameobject;

import com.jme3.app.SimpleApplication;

/**
 *
 * @author Justin
 */
public class GameObjectManager {
    
    protected SimpleApplication app = null;
    protected static GameObjectManager instance = null;
    
    public GameObjectManager (SimpleApplication app) {
        this.app = app;
    }
    
    /*
     * is executed by Main.simpleUpdate() method
    */
    public void simpleUpdate (float tpf) {
        //
    }
    
    public static GameObjectManager getInstance () {
        return GameObjectManager.instance;
    }
    
    public static void setInstance (GameObjectManager gameObjectManager) {
        GameObjectManager.instance = gameObjectManager;
    }
    
}
