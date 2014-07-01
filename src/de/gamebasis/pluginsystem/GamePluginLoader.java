/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gamebasis.pluginsystem;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

/**
 *
 * @author Justin
 */
public class GamePluginLoader {
    
    public static List<IGamePlugin> loadPlugins(File plugDir) throws IOException {
 
    File[] plugJars = plugDir.listFiles(new JARFileFilter());
    ClassLoader cl = new URLClassLoader(GamePluginLoader.fileArrayToURLArray(plugJars));
    List<Class<IGamePlugin>> plugClasses = GamePluginLoader.extractClassesFromJARs(plugJars, cl);
    return GamePluginLoader.createPluggableObjects(plugClasses);
  }
 
  private static URL[] fileArrayToURLArray(File[] files) throws MalformedURLException {
 
    URL[] urls = new URL[files.length];
    for (int i = 0; i < files.length; i++) {
      urls[i] = files[i].toURI().toURL();
    }
    return urls;
  }
 
  private static List<Class<IGamePlugin>> extractClassesFromJARs(File[] jars, ClassLoader cl) throws IOException {
 
    List<Class<IGamePlugin>> classes = new ArrayList<Class<IGamePlugin>>();
    for (File jar : jars) {
      classes.addAll(GamePluginLoader.extractClassesFromJAR(jar, cl));
    }
    return classes;
  }
 
  @SuppressWarnings("unchecked")
  private static List<Class<IGamePlugin>> extractClassesFromJAR(File jar, ClassLoader cl) throws IOException {
 
    List<Class<IGamePlugin>> classes = new ArrayList<Class<IGamePlugin>>();
    JarInputStream jaris = new JarInputStream(new FileInputStream(jar));
    JarEntry ent = null;
    while ((ent = jaris.getNextJarEntry()) != null) {
      if (ent.getName().toLowerCase().endsWith(".class")) {
        try {
          Class<?> cls = cl.loadClass(ent.getName().substring(0, ent.getName().length() - 6).replace('/', '.'));
          if (GamePluginLoader.isPluggableClass(cls)) {
            classes.add((Class<IGamePlugin>)cls);
          }
        }
        catch (ClassNotFoundException e) {
          System.err.println("Can't load Class " + ent.getName());
          e.printStackTrace();
        }
      }
    }
    jaris.close();
    return classes;
  }
 
  private static boolean isPluggableClass(Class<?> cls) {
 
    for (Class<?> i : cls.getInterfaces()) {
      if (i.equals(IGamePlugin.class)) {
        return true;
      }
    }
    return false;
  }
 
  private static List<IGamePlugin> createPluggableObjects(List<Class<IGamePlugin>> pluggables) {
 
    List<IGamePlugin> plugs = new ArrayList<IGamePlugin>(pluggables.size());
    for (Class<IGamePlugin> plug : pluggables) {
      try {
        plugs.add(plug.newInstance());
      }
      catch (InstantiationException e) {
        System.err.println("Can't instantiate plugin: " + plug.getName());
        e.printStackTrace();
      }
      catch (IllegalAccessException e) {
        System.err.println("IllegalAccess for plugin: " + plug.getName());
        e.printStackTrace();
      }
    }
    return plugs;
  }
    
}
