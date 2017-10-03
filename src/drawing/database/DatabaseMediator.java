/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawing.database;

import drawing.domain.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rupture13
 */
public class DatabaseMediator implements PersistencyMediator {
    private Properties props;
    private Connection con;

    public DatabaseMediator() {
        props = new Properties();
        init(props);
        
        try {
            con = DriverManager.getConnection(props.getProperty("url"), props.getProperty("user"), props.getProperty("pass"));
        } catch (SQLException ex) {
            System.out.println("Error:\n" + ex.getMessage());
        }
    }
    
    @Override
    public Drawing load(String nameDrawing) {
        Drawing dr = null;
        PreparedStatement stmt;
        try {
            stmt = con.prepareStatement("SELECT * FROM drawing WHERE title = ?");
            stmt.setString(1, nameDrawing);
            ResultSet results = stmt.executeQuery();

            while (results.next()) {
                ByteArrayInputStream byteIn = new ByteArrayInputStream(results.getBytes("object"));
                ObjectInputStream in = new ObjectInputStream(byteIn);
                dr = (Drawing) in.readObject();
                in.close();
                byteIn.close();
                
                return dr;
            }
        } catch (SQLException | IOException | ClassNotFoundException ex) {
            System.out.println("Error:\n" + ex.getMessage());
        }
        return dr;
    }

    @Override
    public boolean save(Drawing drawing) {
        PreparedStatement stmt;
        try {
            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(byteOut);
            out.writeObject(drawing);
            out.close();
            byteOut.close();
            byte[] byteObject = byteOut.toByteArray();
            
            stmt = con.prepareStatement("INSERT INTO drawing (title, object) VALUES (?, ?)");
            stmt.setString(1, drawing.getName());
            stmt.setBytes(2, byteObject);
            
            int updated = stmt.executeUpdate();

            if (updated > 0) {
                return true;
            }
        } catch (SQLException | IOException ex) {
            System.out.println("Error:\n" + ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean init(Properties props) {
        try {
            FileInputStream in = new FileInputStream("C:\\Users\\User\\Documents\\NetBeansProjects\\Tekenapplicatie\\props.properties");
            props.load(in);
            in.close();
            return true;
        } catch (IOException i) {
            System.out.println("Error:\n" + i.getMessage());
        }
        return false;
    }
    
}
