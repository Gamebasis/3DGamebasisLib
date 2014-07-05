/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gamebasislib.client;

import com.jme3.app.SimpleApplication;
import com.jme3.network.Client;

/**
 *
 * @author Justin
 */
public class ClientLoader {
    
    protected SimpleApplication app = null;
    protected Client client = null;
    
    public ClientLoader (SimpleApplication app, Client client) {
        this.app = app;
        this.client = client;
    }
    
    public void loadClient () {
        //
    }
    
}
