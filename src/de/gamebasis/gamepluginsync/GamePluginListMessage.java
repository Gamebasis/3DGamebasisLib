/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gamebasis.gamepluginsync;

import com.jme3.network.AbstractMessage;
import com.jme3.network.serializing.Serializable;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Justin
 */
@Serializable
public class GamePluginListMessage extends AbstractMessage {
    
    protected String gamepluginname = "";
    protected byte gamepluginjar[] = null;
    
    public GamePluginListMessage (String gamepluginname) {
        this.gamepluginname = gamepluginname;
    }
    
    public GamePluginListMessage () {
        this.gamepluginname = "";
    }
    
    public void loadGamePlugin () {
        try {            
            BufferedReader reader = new BufferedReader(new FileReader(new File("./ext/app/" + this.gamepluginname + ".jar")));
            InputStream fileIn = new FileInputStream(new File("./ext/app/" + this.gamepluginname + ".jar"));
            
            while (fileIn.available() > 0) {
                fileIn.read(gamepluginjar);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GamePluginListMessage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GamePluginListMessage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void saveGamePlugin (String gamepluginname) {
        File f = new File("./ext/app/" + gamepluginname + ".jar");
        
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(GamePluginListMessage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            FileOutputStream outputstream = new FileOutputStream(f);
            outputstream.write(this.gamepluginjar);
            outputstream.flush();
            outputstream.close();
        } catch (IOException ex) {
            Logger.getLogger(GamePluginListMessage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String getMD5Checksum () {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            try (InputStream is = Files.newInputStream(Paths.get("./ext/app/" + this.gamepluginname + ".jar"))) {
                DigestInputStream dis = new DigestInputStream(is, md);
                /* Read stream to EOF as normal... */
            } catch (IOException ex) {
                Logger.getLogger(GamePluginListMessage.class.getName()).log(Level.SEVERE, null, ex);
            }
            byte[] digest = md.digest();
            
            return digest.toString();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(GamePluginListMessage.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "";
    }
    
    public void sendGamePlugin () {
        //
    }
    
}
