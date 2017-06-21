/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.model;

import java.awt.Color;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author L
 */
public class BancoDados extends BDConfig {

    private static Connection conn;
    
    private BancoDados(){
        
    }
    
    public static Connection createConnection() {
        if (conn != null){
            return conn;
        }
        
        try {
            Class.forName(BDConfig.DRIVER);
            conn = DriverManager.getConnection(
                        BDConfig.URL,
                        BDConfig.USR, 
                        BDConfig.PWD);           
            return conn;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(BancoDados.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
        
    }
    
    
    
}
