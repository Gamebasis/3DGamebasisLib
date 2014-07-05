/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gamebasislib.database;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Justin
 */
public class DatabaseChecker {
    
    protected List<String> tableslist = new ArrayList<String>();
    
    public DatabaseChecker () {
        //
    }
    
    public void checkDatabase () throws SQLException {
        try {
            Database database = Database.getInstance();
            DatabaseMetaData md = database.getConnection().getMetaData();
            ResultSet rs = md.getTables("myschema",null,null,null);
            while(rs.next()) {
                String table_name = rs.getString("TABLE_NAME");
                this.tableslist.add(table_name);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseChecker.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }
    
}
