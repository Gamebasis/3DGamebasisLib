/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gamebasislib.ui;

import com.jme3.app.SimpleApplication;
import com.jme3.math.Vector2f;
import java.util.HashMap;
import tonegod.gui.controls.windows.Window;
import tonegod.gui.core.Screen;

/**
 *
 * @author Justin
 */
public class UIWindowManager {
    
    protected Screen screen = null;
    protected SimpleApplication app = null;
    protected HashMap<Integer,Window> windowlist = new HashMap<Integer,Window>();
    protected int windows = 0;
    
    public UIWindowManager (SimpleApplication app, Screen screen) {
        this.app = app;
        this.screen = screen;
    }
    
    public int addWindow (Window window) {
        this.windowlist.put(++this.windows, window);
        this.screen.addElement(window);
        return this.windows;
    }
    
    public Window getWindowByID (int windowID) {
        if (this.windowlist.containsKey(windowID)) {
            return this.windowlist.get(windowID);
        } else {
            return null;
        }
    }
    
    public Screen getScreen () {
        return this.screen;
    }
    
    public Window createWindow (String uid, Vector2f position, Vector2f dimensions) {
        Window window = new Window(this.screen, uid, position, dimensions);
        return window;
    }
    
    public Window createWindow (String uid, Vector2f position) {
        Window window = new Window(this.screen, uid, position);
        return window;
    }
    
}
