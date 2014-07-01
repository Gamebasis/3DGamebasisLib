/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gamebasislib.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Justin
 */
public class GameEventManager {
    
    protected static HashMap<Class<? extends GameEvent>,List<GameEventListener>> gameevents = new HashMap<Class<? extends GameEvent>,List<GameEventListener>>();
    
    public static void addGameEventListener (Class<GameEvent> name, GameEventListener gameeventlistener) {
        if (GameEventManager.gameevents.containsKey(name)) {
            List<GameEventListener> list = GameEventManager.gameevents.get(name);
            list.add(gameeventlistener);
            GameEventManager.gameevents.put(name, list);
        } else {
            List<GameEventListener> list = new ArrayList<GameEventListener>();
            list.add(gameeventlistener);
            GameEventManager.gameevents.put(name, list);
        }
    }
    
    public static void raiseEvent (GameEvent gameevent) {
        if (GameEventManager.gameevents.containsKey(gameevent.getClass())) {
            List<GameEventListener> list = GameEventManager.gameevents.get(gameevent.getClass());
            
            for (GameEventListener listener : list) {
                listener.execute(gameevent.getClass(), gameevent);
            }
        }
    }
    
}
