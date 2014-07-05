/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gamebasislib.gameworld;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.Vector2f;
import com.jme3.network.Client;
import com.jme3.network.Message;
import com.jme3.network.MessageListener;
import com.jme3.network.serializing.Serializer;
import com.jme3.renderer.Camera;
import com.jme3.terrain.Terrain;
import com.jme3.terrain.geomipmap.TerrainLodControl;
import com.jme3.terrain.geomipmap.TerrainQuad;
import com.jme3.terrain.heightmap.HeightMap;
import com.jme3.terrain.heightmap.ImageBasedHeightMap;
import com.jme3.texture.Texture;
import com.jme3.texture.Texture.WrapMode;
import de.gamebasislib.camera.GameCameraManager;
import de.gamebasislib.terrain.GameWorldTerrain;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

/**
 *
 * @author Justin
 */
public class GameWorldManager implements MessageListener<Client> {
    
    protected SimpleApplication app = null;
    protected Client client = null;
    protected GameWorld gameworld = null;
    protected List<GameWorldTerrain> terrains = new ArrayList<GameWorldTerrain>();
    
    protected int lastTerrainID = 0;
    
    public GameWorldManager (SimpleApplication app, Client client) {
        this.app = app;
        this.client = client;
        
        Serializer.registerClass(GameWorldSettingsChangedMessage.class);
        Serializer.registerClass(GameWorldHeightMap.class);
    }
    
    public void loadGameWorldManager () {
        //
    }

    @Override
    public void messageReceived(Client source, Message message) {
        if (message instanceof GameWorldSettingsChangedMessage) {
            GameWorldSettingsChangedMessage gameworldsettingschangedmessage = (GameWorldSettingsChangedMessage) message;
            
            GameWorldSettings gameworldsettings = this.gameworld.getGameWorldSettings();
            gameworldsettings.addGameWorldSettings(gameworldsettingschangedmessage.getHashMap());
        } else if (message instanceof GameWorldHeightMap) {
            GameWorldHeightMap gameworldheightmap = (GameWorldHeightMap) message;
        } else if (message instanceof GameWorldTerrain) {
            GameWorldTerrain gameworldterrain = (GameWorldTerrain) message;
            this.addGameWorldTerrain(gameworldterrain);
        }
    }
    
    public void addGameWorldTerrain (GameWorldTerrain gameworldterrain) {
        Vector2f vector = gameworldterrain.getPosition();
        
        for (GameWorldTerrain terrains : this.terrains) {
            if (terrains.getPosition() == vector) {
                this.terrains.remove(terrains);
            }
        }
        
        //Material laden
        Material mat = new Material(this.app.getAssetManager(), "Common/MatDefs/Terrain/Terrain.j3md");
        
        //Textures laden
        for (Entry<String,Texture> entry : gameworldterrain.getTextures().entrySet()) {
            String name = entry.getKey();
            Texture texture = entry.getValue();
            texture.setWrap(WrapMode.Repeat);
            mat.setTexture(name, texture);
            mat.setFloat(name + "Scale", 32f);
        }
        
        gameworldterrain.setTerrainID(++this.lastTerrainID);
        this.terrains.add(gameworldterrain);
        
        //Load HeightMap
        HeightMap heightMap = new ImageBasedHeightMap(gameworldterrain.getHeightMapImage().getImage(), 1f);
        heightMap.load();
        
        //Create Terrain
        TerrainQuad terrain = new TerrainQuad("terrain", gameworldterrain.getTerrainTilesSize(), (int) gameworldterrain.getTerrainSize().getX(), heightMap.getHeightMap());
        terrain.setMaterial(mat);
        
        //Camera
        List<Camera> cameras = GameCameraManager.getInstance().getCameras();
        TerrainLodControl control = new TerrainLodControl(terrain, cameras);
        terrain.addControl(control);
        this.app.getRootNode().attachChild(terrain);
    }
    
}
