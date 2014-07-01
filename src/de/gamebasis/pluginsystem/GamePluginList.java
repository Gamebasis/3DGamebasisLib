/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gamebasis.pluginsystem;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Justin
 */
public class GamePluginList {
    
    protected List<String> gamepluginlist = new ArrayList<String>();
    
    public GamePluginList () {
        //
    }
    
    public void addGamePlugin (String name) {
        this.gamepluginlist.add(name);
    }
    
    public void removeGamePlugin (String name) {
        this.gamepluginlist.remove(name);
    }
    
    public void saveGamePluginList (String plugindir) {
        File f = new File(plugindir + "/pluginlist.lst");
        
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(GamePluginList.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            FileWriter writer = new FileWriter(f);
            
            for (String gameplugin : this.gamepluginlist) {
                writer.write(gameplugin + System.lineSeparator());
            }
            
            writer.flush();
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(GamePluginList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
