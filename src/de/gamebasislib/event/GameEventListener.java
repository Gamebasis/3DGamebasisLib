/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gamebasislib.event;

/**
 *
 * @author Justin
 */
public interface GameEventListener {
    
    public void execute (Class<? extends GameEvent> name, GameEvent gameevent);
    
}
