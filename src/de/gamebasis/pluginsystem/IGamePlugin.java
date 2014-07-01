/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gamebasis.pluginsystem;

import com.jme3.app.SimpleApplication;
import com.jme3.network.Client;
import com.jme3.network.ClientStateListener.DisconnectInfo;

/**
 *
 * @author Justin
 */
public interface IGamePlugin {
    
    public void startGamePlugin();
    public void simpleInitApp(SimpleApplication app);
    public void setPluginManager(IGamePluginManager manager);
    public void stopGamePlugin();
    public void onConnected(Client client);
    public void onDisconnected(Client client, DisconnectInfo info);
    
}
