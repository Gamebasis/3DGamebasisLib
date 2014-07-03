/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gamebasislib.ui;

import com.jme3.app.SimpleApplication;
import java.util.HashMap;
import tonegod.gui.core.Screen;

/**
 *
 * @author Justin
 */
public class UIManager {
    
    protected Screen screen = null;
    protected SimpleApplication app = null;
    protected String stylemap = "";
    protected HashMap<Integer,UIWindowManager> screenlist = new HashMap<Integer,UIWindowManager>();
    protected int screens = 0;
    protected static UIManager instance = null;
    
    public UIManager (SimpleApplication app) {
        this.app = app;
        this.screen = new Screen(app);
        this.addScreen(screen);
        UIManager.setInstance(this);
    }
    
    public int addScreen (Screen screen) {
        if (this.stylemap.equals("")) {
            this.app.getGuiNode().addControl(screen);
        } else {
            this.app.getGuiNode().addControl(screen);
        }
        
        this.screenlist.put(++this.screens, new UIWindowManager(this.app, screen));
        return this.screens;
    }
    
    public UIWindowManager getWindowManagerByID (int screenID) {
        if (this.screenlist.containsKey(screenID)) {
            return this.screenlist.get(screenID);
        } else {
            return null;
        }
    }
    
    public Screen getScreen () {
        return this.screen;
    }
    
    public static void setInstance (UIManager uimanager) {
        UIManager.instance = uimanager;
    }
    
    public static UIManager getInstance () {
        return UIManager.instance;
    }
    
}
