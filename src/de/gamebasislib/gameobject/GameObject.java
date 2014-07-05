/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gamebasislib.gameobject;

/**
 *
 * @author Justin
 */
public class GameObject {
    
    public GameObject () {
        //
    }
    
    /*
     * return true if the update thread dont must execute the simpleUpdate() method
     * of this object
    */
    public boolean isStaticObject () {
        return true;
    }
    
    public void simpleUpdate (float tpf) {
        //
    }
    
    public void loadGameObject () {
        //
    }
    
}
