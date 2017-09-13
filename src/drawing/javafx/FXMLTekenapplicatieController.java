/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawing.javafx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import drawing.javafx.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import drawing.domain.*;
import java.io.File;

/**
 * FXML Controller class
 *
 * @author gebruiker
 */
public class FXMLTekenapplicatieController implements Initializable {

    @FXML
    private Canvas myCanvas;
    
    @FXML
    private void exit() {
        System.exit(0);
    }
    
    @FXML
    private void hello() {
        
        GraphicsContext gc = myCanvas.getGraphicsContext2D();
        Drawing dr = new Drawing("Mooi tekening");
        JavaFXPaintable painter = new JavaFXPaintable(gc);
        Oval ov = new Oval(new Point(40, 80), 60, 20, 3, drawing.domain.Color.BLUE, dr);
        Point[] polyPoints = {new Point(150, 75), new Point(350, 200), new Point(255, 40)};
        Polygon po = new Polygon(polyPoints, 1, drawing.domain.Color.RED, dr);
        PaintedText txt = new PaintedText(new Point(42, 42), 120, 40, "Ik ben een text", "Arial", drawing.domain.Color.GREEN, dr);
        Image img = new Image(new Point(40, 200), 1, 1, new File("C:\\Users\\User\\Pictures\\4 - Icons\\Icon Files\\Raw PNG\\Fontys.png"), drawing.domain.Color.BLUE, dr);
        
        dr.addDrawingItem(ov);
        dr.addDrawingItem(po);
        dr.addDrawingItem(txt);
        dr.addDrawingItem(img);
        
        ov.paintUsing(painter);
        po.paintUsing(painter);
        txt.paintUsing(painter);
        System.out.println(img.getFile().getPath());
        img.paintUsing(painter);
        
        
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
