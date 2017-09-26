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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
            private ComboBox<DrawingItem> cmbOvalEdit;
            @FXML
            private Button btnOvalRevert;
            @FXML
            private ComboBox<drawing.domain.Color> cmbOvalEditColor;
            @FXML
            private Spinner<Integer> numOvalEditWidth;
            @FXML
            private Spinner<Integer> numOvalEditHeight;
            @FXML
            private Spinner<Integer> numOvalEditWeight;
            @FXML
            private Button btnOvalChange;
            @FXML
            private Button btnOvalRemove;
        @FXML
        private Tab tabEditPolygon;
            @FXML
            private ComboBox<DrawingItem> cmbPolygonEdit;
            @FXML
            private Button btnPolygonRevert;
            @FXML
            private ComboBox<drawing.domain.Color> cmbPolygonEditColor;
            @FXML
            private Spinner<Integer> numPolygonEditWeight;
            @FXML
            private Button btnPolygonChange;
            @FXML
            private Button btnPolygonRemove;
        @FXML
        private Tab tabEditText;
            @FXML
            private ComboBox<DrawingItem> cmbTextEdit;
            @FXML
            private Button btnTextRevert;
            @FXML
            private TextField txtTextEditText;
            @FXML
            private ComboBox<String> cmbTextEditFont;
            @FXML
            private ComboBox<drawing.domain.Color> cmbTextEditColor;
            @FXML
            private Spinner<Integer> numTextEditWidth;
            @FXML
            private Spinner<Integer> numTextEditHeight;
            @FXML
            private Button btnTextChange;
            @FXML
            private Button btnTextRemove;
        @FXML
        private Tab tabEditImage;
            @FXML
            private ComboBox<DrawingItem> cmbImageEdit;
            @FXML
            private Button btnImageRevert;
            @FXML
            private TextField txtImageEditPath;
            @FXML
            private Button btnImageEditBrowse;
            @FXML
            private Button btnImageChange;
            @FXML
            private Button btnImageRemove;
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
                
            }
        }
    }
    
    @FXML
    private void openTabAdd() {
        if (tabAdd.isSelected()) {
            if (tabAddOval.isSelected()) {
                cmbOvalAddColor.getItems().clear();
                cmbOvalAddColor.getItems().addAll(drawing.domain.Color.BLACK, drawing.domain.Color.WHITE, drawing.domain.Color.RED, drawing.domain.Color.BLUE, drawing.domain.Color.GREEN);
                cmbOvalAddColor.getSelectionModel().select(0);
            }
            else if (tabAddPolygon.isSelected()) {
                cmbPolygonAddColor.getItems().clear();
                cmbPolygonAddColor.getItems().addAll(drawing.domain.Color.BLACK, drawing.domain.Color.WHITE, drawing.domain.Color.RED, drawing.domain.Color.BLUE, drawing.domain.Color.GREEN);
                cmbPolygonAddColor.getSelectionModel().select(0);
                
                polyPoints.clear();
                collectingPoints = false;
                
                lblPolygonAddCount.setText("Points: 0");
                lblPolygonAddCount.setVisible(false);
            }
            else if (tabAddText.isSelected()) {
                cmbTextAddColor.getItems().clear();
                cmbTextAddColor.getItems().addAll(drawing.domain.Color.BLACK, drawing.domain.Color.WHITE, drawing.domain.Color.RED, drawing.domain.Color.BLUE, drawing.domain.Color.GREEN);
                cmbTextAddColor.getSelectionModel().select(0);
                
                cmbTextAddFont.getItems().clear();
                cmbTextAddFont.getItems().addAll(javafx.scene.text.Font.getFamilies());
                cmbTextAddFont.getSelectionModel().select("Arial");
            }
        }
    }
    
    @FXML
    private void openTabEdit() {
        if (tabEdit.isSelected()) {
            if (tabEditOval.isSelected()) {
                cmbOvalEdit.getItems().clear();
                cmbOvalEdit.getItems().addAll(dr.getOvals());
                cmbOvalEdit.getSelectionModel().select(0);
                
                cmbOvalEditColor.getItems().clear();
                cmbOvalEditColor.getItems().addAll(drawing.domain.Color.BLACK, drawing.domain.Color.WHITE, drawing.domain.Color.RED, drawing.domain.Color.BLUE, drawing.domain.Color.GREEN);
                cmbOvalEditColor.getSelectionModel().select(0);
                
                fillOvalInfo();
            }
            else if (tabEditPolygon.isSelected()) {
                cmbPolygonEdit.getItems().clear();
                cmbPolygonEdit.getItems().addAll(dr.getPolygons());
                cmbPolygonEdit.getSelectionModel().select(0);
                
                cmbPolygonEditColor.getItems().clear();
                cmbPolygonEditColor.getItems().addAll(drawing.domain.Color.BLACK, drawing.domain.Color.WHITE, drawing.domain.Color.RED, drawing.domain.Color.BLUE, drawing.domain.Color.GREEN);
                cmbPolygonEditColor.getSelectionModel().select(0);
                
                fillPolygonInfo();
            }
            else if (tabEditText.isSelected()) {
                cmbTextEdit.getItems().clear();
                cmbTextEdit.getItems().addAll(dr.getTexts());
                cmbTextEdit.getSelectionModel().select(0);
                
                cmbTextEditFont.getItems().clear();
                cmbTextEditFont.getItems().addAll(javafx.scene.text.Font.getFamilies());
                cmbTextEditFont.getSelectionModel().select("Arial");
                
                cmbTextEditColor.getItems().clear();
                cmbTextEditColor.getItems().addAll(drawing.domain.Color.BLACK, drawing.domain.Color.WHITE, drawing.domain.Color.RED, drawing.domain.Color.BLUE, drawing.domain.Color.GREEN);
                cmbTextEditColor.getSelectionModel().select(0);
                
                fillTextInfo();
            }
            else if (tabEditImage.isSelected()) {
                cmbImageEdit.getItems().clear();
                cmbImageEdit.getItems().addAll(dr.getImages());
                cmbImageEdit.getSelectionModel().select(0);
                
                fillImageInfo();
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
    private void clearDrawing() {
        dr.removeAllDrawingItems();
        clearCanvas();
        openTabDrawing();
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
    
    @FXML
    private void selectImage2() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose another image");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.jpeg", "*.png"));
        
        File image = fileChooser.showOpenDialog(null);
        if (image != null) {
            txtImageEditPath.setText(image.getPath());
        }
        else {
            txtImageEditPath.setText("");
        }
    }
    
    @FXML
    private void fillOvalInfo() {
        if (cmbOvalEdit.getValue() != null) {
            cmbOvalEditColor.setValue(cmbOvalEdit.getValue().getColor());
            numOvalEditWidth.getValueFactory().setValue((int) cmbOvalEdit.getValue().getWidth());
            numOvalEditHeight.getValueFactory().setValue((int) cmbOvalEdit.getValue().getHeight());
            numOvalEditWeight.getValueFactory().setValue((int) ((Oval) cmbOvalEdit.getValue()).getWeight());
        }
    }
    
    @FXML
    private void revertOval() {
        if (cmbOvalEdit.getValue() != null) {
            cmbOvalEdit.getValue().revertChange();
            fillOvalInfo();
            draw();
        }
    }
    
    @FXML
    private void changeOval() {
        if (cmbOvalEdit.getValue() != null) {
            dr.editDrawingItem(cmbOvalEdit.getValue(), cmbOvalEdit.getValue().getAnchor(), cmbOvalEditColor.getValue(), numOvalEditHeight.getValue(), numOvalEditWidth.getValue(), numOvalEditWeight.getValue());
            fillOvalInfo();
            draw();
        }
    }
    
    @FXML
    private void removeOval() {
        if (cmbOvalEdit.getValue() != null) {
            dr.removeDrawingItem(cmbOvalEdit.getValue());
            openTabEdit();
            draw();
        }
    }
    
    @FXML
    private void fillPolygonInfo() {
        if (cmbPolygonEdit.getValue() != null) {
            cmbPolygonEditColor.setValue(cmbPolygonEdit.getValue().getColor());
            numPolygonEditWeight.getValueFactory().setValue((int) ((Polygon) cmbPolygonEdit.getValue()).getWeight());
        }
    }
    
    @FXML
    private void revertPolygon() {
        if (cmbPolygonEdit.getValue() != null) {
            cmbPolygonEdit.getValue().revertChange();
            fillPolygonInfo();
            draw();
        }
    }
    
    @FXML
    private void changePolygon() {
        if (cmbPolygonEdit.getValue() != null) {
            dr.editDrawingItem(cmbPolygonEdit.getValue(), ((Polygon) cmbPolygonEdit.getValue()).getVertices(), numPolygonEditWeight.getValue(), cmbPolygonEditColor.getValue());
            fillPolygonInfo();
            draw();
        }
    }
    
    @FXML
    private void removePolygon() {
        if (cmbPolygonEdit.getValue() != null) {
            dr.removeDrawingItem(cmbPolygonEdit.getValue());
            openTabEdit();
            draw();
        }
    }
    
    @FXML
    private void fillTextInfo() {
        if (cmbTextEdit.getValue() != null) {
            txtTextEditText.setText(((PaintedText) cmbTextEdit.getValue()).getContent());
            cmbTextEditFont.setValue(((PaintedText) cmbTextEdit.getValue()).getFontName());
            cmbTextEditColor.setValue(cmbTextEdit.getValue().getColor());
            numTextEditWidth.getValueFactory().setValue((int) cmbTextEdit.getValue().getWidth());
            numTextEditHeight.getValueFactory().setValue((int) cmbTextEdit.getValue().getHeight());
        }
    }
    
    @FXML
    private void revertText() {
        if (cmbTextEdit.getValue() != null) {
            cmbTextEdit.getValue().revertChange();
            fillTextInfo();
            draw();
        }
    }
    
    @FXML
    private void changeText() {
        if (cmbTextEdit.getValue() != null) {
            dr.editDrawingItem(cmbTextEdit.getValue(), cmbTextEdit.getValue().getAnchor(), numTextEditWidth.getValue(), numTextEditHeight.getValue(), txtTextEditText.getText(), cmbTextEditFont.getValue(), cmbTextEditColor.getValue());
            fillTextInfo();
            draw();
        }
    }
    
    @FXML
    private void removeText() {
        if (cmbTextEdit.getValue() != null) {
            dr.removeDrawingItem(cmbTextEdit.getValue());
            openTabEdit();
            draw();
        }
    }
    
    @FXML
    private void fillImageInfo() {
        if (cmbImageEdit.getValue() != null) {
            txtImageEditPath.setText(((Image) cmbImageEdit.getValue()).getFile().getPath());
        }
    }
    
    @FXML
    private void revertImage() {
        if (cmbImageEdit.getValue() != null) {
            cmbImageEdit.getValue().revertChange();
            fillImageInfo();
            draw();
        }
    }
    
    @FXML
    private void changeImage() {
        if (cmbImageEdit.getValue() != null) {
            dr.editDrawingItem(cmbImageEdit.getValue(), cmbImageEdit.getValue().getAnchor(), cmbImageEdit.getValue().getWidth(), cmbImageEdit.getValue().getHeight(), new File(txtImageEditPath.getText()), cmbImageEdit.getValue().getColor());
            fillImageInfo();
            draw();
        }
    }
    
    @FXML
    private void removeImage() {
        if (cmbImageEdit.getValue() != null) {
            dr.removeDrawingItem(cmbImageEdit.getValue());
            openTabEdit();
            draw();
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
        polyPoints = new ArrayList<>();
        
        cmbOvalEdit.valueProperty().addListener((ObservableValue<? extends DrawingItem> observable, DrawingItem oldValue, DrawingItem newValue) -> {
            fillOvalInfo();
        });
        cmbPolygonEdit.valueProperty().addListener((ObservableValue<? extends DrawingItem> observable, DrawingItem oldValue, DrawingItem newValue) -> {
            fillPolygonInfo();
        });
        cmbTextEdit.valueProperty().addListener((ObservableValue<? extends DrawingItem> observable, DrawingItem oldValue, DrawingItem newValue) -> {
            fillTextInfo();
        });
        cmbImageEdit.valueProperty().addListener((ObservableValue<? extends DrawingItem> observable, DrawingItem oldValue, DrawingItem newValue) -> {
            fillImageInfo();
        });
        
    }
}
