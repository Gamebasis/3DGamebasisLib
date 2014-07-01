/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gamebasis.pluginsystem;

import com.jme3.app.SimpleApplication;
import com.jme3.network.Client;
import com.jme3.network.ClientStateListener.DisconnectInfo;
import de.gamebasis.pluginsystem.GamePluginLoader;
import de.gamebasis.pluginsystem.IGamePlugin;
import de.gamebasis.pluginsystem.IGamePluginManager;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.java.games.util.plugins.PluginLoader;

/**
 *
 * @author Justin
 */
public class GamePluginManager implements IGamePluginManager {
    
    protected static List<IGamePlugin> pluginlist = new ArrayList<IGamePlugin>();

    public void addAssets(String path) {
        //
    }

    public void showVisualMessage(String message) {
        //
    }
    
    public static void simpleInitApp (SimpleApplication app) {
        for (IGamePlugin p : GamePluginManager.pluginlist) {
            p.simpleInitApp(app);
        }
    }
    
    public static void loadGamePlugins (String plugindir) {
        try {
            GamePluginManager.pluginlist = GamePluginLoader.loadPlugins(new File(plugindir));
        } catch (IOException ex) {
            Logger.getLogger(GamePluginManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        GamePluginList gamepluginlist = new GamePluginList();
        
        GamePluginManager manager = new GamePluginManager();
        for (IGamePlugin p : GamePluginManager.pluginlist) {
            p.setPluginManager(manager);
            gamepluginlist.addGamePlugin(p.getClass().getName());
        }
        for (IGamePlugin p : GamePluginManager.pluginlist) {
          p.startGamePlugin();
        }
        
        gamepluginlist.saveGamePluginList(plugindir);
    }
    
    public static void onConnected (Client client) {
        for (IGamePlugin p : GamePluginManager.pluginlist) {
            p.onConnected(client);
        }
    }
    
    public static void onDisconnected (Client client, DisconnectInfo info) {
        for (IGamePlugin p : GamePluginManager.pluginlist) {
            p.onDisconnected(client, info);
        }
    }
    
}
