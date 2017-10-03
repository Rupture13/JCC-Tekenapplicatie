/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawing.serialization;

import drawing.domain.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Properties;

/**
 *
 * @author Rupture13
 */
public class SerializationMediator implements PersistencyMediator {
    private Properties props;

    public SerializationMediator() {
        props = new Properties();
        init(props);
    }

    
    
    @Override
    public Drawing load(String nameDrawing) {
        Drawing dr = null;
        try {
            FileInputStream fileIn = new FileInputStream(System.getProperty("user.dir") + props.getProperty("serializationFile"));
            ObjectInputStream in = new ObjectInputStream(fileIn);
            dr = (Drawing) in.readObject();
            in.close();
            fileIn.close();
            return dr;
        } catch(IOException i) {
            System.out.println("Error:\n" + i.getMessage());
        } catch(ClassNotFoundException c) {
            System.out.println("Error:\n" + c.getMessage());
        }
        return dr;
    }

    @Override
    public boolean save(Drawing drawing) {
        try {
            FileOutputStream fileOut =
            new FileOutputStream(System.getProperty("user.dir") + props.getProperty("serializationFile"));
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(drawing);
            out.close();
            fileOut.close();
            return true;
        }catch(IOException i) {
            System.out.println("Error:\n" + i.getMessage());
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
