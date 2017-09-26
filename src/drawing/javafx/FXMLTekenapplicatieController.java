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
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import drawing.domain.*;
import java.io.File;
import java.util.ArrayList;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author gebruiker
 */
public class FXMLTekenapplicatieController implements Initializable {
    // <editor-fold desc="FXML Object References">
    @FXML
    private Canvas myCanvas;
    
    @FXML
    private Label lblDrawingName;
    
    @FXML
    private Tab tabDrawing;
        @FXML
        private Tab tabGeneral;
            @FXML
            private TextField txtGeneralTitle;
            @FXML
            private Button btnGeneralRename;
            @FXML
            private Button btnClear;
    
    @FXML
    private Tab tabAdd;
        @FXML
        private Tab tabAddOval;
            @FXML
            private ComboBox<drawing.domain.Color> cmbOvalAddColor;
            @FXML
            private Spinner<Integer> numOvalAddWidth;
            @FXML
            private Spinner<Integer> numOvalAddHeight;
            @FXML
            private Spinner<Integer> numOvalAddWeight;
        @FXML
        private Tab tabAddPolygon;
            @FXML
            private Button btnPolygonAddStart;
            @FXML
            private Label lblPolygonAddCount;
            @FXML
            private Button btnPolygonAddDraw;
            @FXML
            private ComboBox<drawing.domain.Color> cmbPolygonAddColor;
            @FXML
            private Spinner<Integer> numPolygonAddWeight;
        @FXML
        private Tab tabAddText;
            @FXML
            private TextField txtTextAddText;
            @FXML
            private ComboBox<String> cmbTextAddFont;
            @FXML
            private ComboBox<drawing.domain.Color> cmbTextAddColor;
            @FXML
            private Spinner<Integer> numTextAddWidth;
            @FXML
            private Spinner<Integer> numTextAddHeight;
        @FXML
        private Tab tabAddImage;
            @FXML
            private TextField txtImageAddPath;
            @FXML
            private Button btnImageAddBrowse;
            
    @FXML
    private Tab tabEdit;    
        @FXML
        private Tab tabEditOval;
        @FXML
        private Tab tabEditPolygon;
        @FXML
        private Tab tabEditText;
        @FXML
        private Tab tabEditImage;
    // </editor-fold>
    
    // <editor-fold desc="Global variables">
    GraphicsContext gc;
    Drawing dr;
    JavaFXPaintable painter;
    
    boolean collectingPoints;
    ArrayList<Point> polyPoints;
    //</editor-fold>
    
    @FXML
    private void openTabDrawing() {
        if (tabDrawing.isSelected()) {
            if (tabGeneral.isSelected()) {
                System.out.println("general");
            }
        }
    }
    
    @FXML
    private void openTabAdd() {
        if (tabAdd.isSelected()) {
            if (tabAddOval.isSelected()) {
                System.out.println("add oval");
                cmbOvalAddColor.getItems().addAll(drawing.domain.Color.BLACK, drawing.domain.Color.WHITE, drawing.domain.Color.RED, drawing.domain.Color.BLUE, drawing.domain.Color.GREEN);
                cmbOvalAddColor.getSelectionModel().select(0);
            }
            else if (tabAddPolygon.isSelected()) {
                System.out.println("add polygon");
                cmbPolygonAddColor.getItems().addAll(drawing.domain.Color.BLACK, drawing.domain.Color.WHITE, drawing.domain.Color.RED, drawing.domain.Color.BLUE, drawing.domain.Color.GREEN);
                cmbPolygonAddColor.getSelectionModel().select(0);
                polyPoints.clear();
                collectingPoints = false;
                lblPolygonAddCount.setText("Points: 0");
                lblPolygonAddCount.setVisible(false);
            }
            else if (tabAddText.isSelected()) {
                System.out.println("add text");
                cmbTextAddColor.getItems().addAll(drawing.domain.Color.BLACK, drawing.domain.Color.WHITE, drawing.domain.Color.RED, drawing.domain.Color.BLUE, drawing.domain.Color.GREEN);
                cmbTextAddColor.getSelectionModel().select(0);
                cmbTextAddFont.getItems().addAll(javafx.scene.text.Font.getFamilies());
                cmbTextAddFont.getSelectionModel().select("Arial");
            }
            else if (tabAddImage.isSelected()) {
                System.out.println("add image");
            }
        }
    }
    
    @FXML
    private void openTabEdit() {
        if (tabEdit.isSelected()) {
            if (tabEditOval.isSelected()) {
                System.out.println("edit oval");
            }
            else if (tabEditPolygon.isSelected()) {
                System.out.println("edit polygon");
            }
            else if (tabEditText.isSelected()) {
                System.out.println("edit text");
            }
            else if (tabEditImage.isSelected()) {
                System.out.println("edit image");
            }
        }
    }
    
    @FXML
    private void renameDrawing() {
        String newName = txtGeneralTitle.getText();
        dr.setName(newName);
        lblDrawingName.setText(newName);
    }
        
    @FXML
    private void testScript() {
        gc = myCanvas.getGraphicsContext2D();
        
        dr = new Drawing("Mooi tekening");
        painter = new JavaFXPaintable(gc);
        
        Oval ov = new Oval(new Point(40, 80), 60, 20, 3, drawing.domain.Color.BLUE, dr);
        Point[] polyPoints2 = {new Point(150, 75), new Point(350, 200), new Point(255, 40)};
        Polygon po = new Polygon(polyPoints2, 1, drawing.domain.Color.RED, dr);
        PaintedText txt = new PaintedText(new Point(42, 42), 120, 40, "Ik ben een text", "Arial", drawing.domain.Color.GREEN, dr);
        Image img = new Image(new Point(40, 200), 1, 1, new File("C:\\Users\\User\\Pictures\\4 - Icons\\Icon Files\\Raw PNG\\Fontys.png"), drawing.domain.Color.BLUE, dr);
        
        dr.addDrawingItem(ov);
        dr.addDrawingItem(po);
        dr.addDrawingItem(txt);
        dr.addDrawingItem(img);
        
        dr.paintUsing(painter);
    }
    
    @FXML
    private void addItemToDrawing(MouseEvent e) {
        if (tabAdd.isSelected()) {
            if (tabAddOval.isSelected()) {
                Oval ov = new Oval(new Point(e.getX(), e.getY()), (double) numOvalAddWidth.getValue(), (double) numOvalAddHeight.getValue(), (double) numOvalAddWeight.getValue(), cmbOvalAddColor.getValue(), dr);
                dr.addDrawingItem(ov);
                
                draw();
            }
            else if (tabAddPolygon.isSelected()) {
                if (collectingPoints) {
                    Point newPoint = new Point(e.getX(), e.getY());
                    if (!polyPoints.contains(newPoint)) {
                        polyPoints.add(newPoint);
                        lblPolygonAddCount.setText("Points: " + polyPoints.size());
                    }
                }
            }
            else if (tabAddText.isSelected()) {
                PaintedText txt = new PaintedText(new Point(e.getX(), (e.getY() + numTextAddHeight.getValue() - 10)), (double) numTextAddWidth.getValue(), (double) numTextAddHeight.getValue(), txtTextAddText.getText(), cmbTextAddFont.getValue(), cmbTextAddColor.getValue(), dr);
                dr.addDrawingItem(txt);
                
                draw();
            }
            else if (tabAddImage.isSelected()) {
                Image img = new Image(new Point(e.getX(), e.getY()), 1, 1, new File(txtImageAddPath.getText()), drawing.domain.Color.BLUE, dr);
                dr.addDrawingItem(img);
                
                draw();
            }
        }
    }
    
    @FXML
    private void draw() {
        clearCanvas();
        dr.paintUsing(painter);
    }
    
    @FXML
    private void clearCanvas() {
        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
    }
    
    @FXML
    private void startCollectingPoints() {
        polyPoints.clear();
        collectingPoints = true;
        lblPolygonAddCount.setText("Points: 0");
        lblPolygonAddCount.setVisible(true);
    }
    
    @FXML
    private void addPolygonToDrawing() {
        Point[] points = new Point[polyPoints.size()];
        points = polyPoints.toArray(points);
        
        Polygon po = new Polygon(points, (double) numPolygonAddWeight.getValue(), cmbPolygonAddColor.getValue(), dr);
        dr.addDrawingItem(po);
        
        polyPoints.clear();
        collectingPoints = false;
        lblPolygonAddCount.setText("Points: 0");
        lblPolygonAddCount.setVisible(false);
        
        draw();
    }
    
    @FXML
    private void selectImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose an image");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.jpeg", "*.png"));
        
        File image = fileChooser.showOpenDialog(null);
        if (image != null) {
            txtImageAddPath.setText(image.getPath());
        }
        else {
            txtImageAddPath.setText("");
        }
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gc = myCanvas.getGraphicsContext2D();
        dr = new Drawing("Untitled drawing");
        painter = new JavaFXPaintable(gc);
        
        collectingPoints = false;
        polyPoints = new ArrayList<Point>();
    }    
    
}
