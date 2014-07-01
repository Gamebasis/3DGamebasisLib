/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gamebasis.pluginsystem;

import com.jme3.app.SimpleApplication;
import com.jme3.network.Client;
import com.jme3.network.ClientStateListener;

/**
 *
 * @author Justin
 */
public class GamePlugin implements IGamePlugin {

    @Override
    public void startGamePlugin() {
        //
    }

    @Override
    public void simpleInitApp(SimpleApplication app) {
        //
    }

    @Override
    public void setPluginManager(IGamePluginManager manager) {
        //
    }

    @Override
    public void stopGamePlugin() {
        //
    }

    @Override
    public void onConnected(Client client) {
        //
    }

    @Override
    public void onDisconnected(Client client, ClientStateListener.DisconnectInfo info) {
        //
    }
    
}
