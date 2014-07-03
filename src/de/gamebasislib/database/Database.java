/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gamebasislib.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Justin
 */
public class Database {
    
    protected String server = "";
    protected int port = 3306;
    protected String username = "";
    protected String password = "";
    protected String database = "";
    protected String jdbc_url = "";
    protected String filePath = "";
    protected String jdbcdriver = "";
    
    protected static Database instance = null;
    protected Connection c = null;
    
    public Database (String server, int port, String username, String password, String database) {
        this.server = server;
        this.port = port;
        this.username = username;
        this.password = password;
        this.database = database;
        this.jdbc_url = "jdbc:mysql://" + this.server + ":" + this.port + "/" + this.database + "?user=" + this.username + "&password=" + this.password + "";
        
        this.jdbcdriver = "com.mysql.jdbc.Driver";
    }
    
    public Database (String filePath) {
        this.filePath = filePath;
        this.jdbc_url = "jdbc:sqlite:" + filePath;
        
        this.jdbcdriver = "org.sqlite.JDBC";
    }
    
    public static Database getInstance () {
        return Database.instance;
    }
    
    public static void setInstance (Database database) {
        Database.instance = database;
    }
    
    public Connection open () {
        try {
            Class.forName(this.jdbcdriver);
            this.c = DriverManager.getConnection(this.jdbc_url);
            return c;
        } catch (SQLException e) {
            Logger.getLogger(Database.class.getName()).log(Level.WARNING, "Database connection failed!");
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found!");
        }
        return this.c;
    }
    
    public boolean isConnected () {
        try {
            return !this.c.isClosed();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public Connection getConnection () {
        return this.c;
    }
    
    public void close () {
        try {
            this.c.close();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int update (String sql) throws SQLException {
        try {
            Statement statement = this.getConnection().createStatement();
            return statement.executeUpdate(sql);
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }
    
    public int update (PreparedStatement preparedstatement) throws SQLException {
        try {
            return preparedstatement.executeUpdate();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }
    
    public PreparedStatement getPreparedStatement (String sql) throws SQLException {
        try {
            return this.getConnection().prepareStatement(sql);
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }
    
    public ResultSet select (String sql) throws SQLException {
        try {
            PreparedStatement preparedstatement = this.getConnection().prepareStatement(sql);
            ResultSet resultset = preparedstatement.executeQuery();
            return resultset;
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }
    
    public ResultSet select (PreparedStatement preparedstatement) throws SQLException {
        try {
            ResultSet resultset = preparedstatement.executeQuery();
            return resultset;
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }
    
    public Statement getStatement () throws SQLException {
        try {
            return this.getConnection().createStatement();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }
    
}
