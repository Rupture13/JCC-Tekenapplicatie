/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawing.javafx;

import drawing.domain.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
/**
 *
 * @author User
 */
public class JavaFXPaintable implements IPaintable {

    public GraphicsContext gc;

    public JavaFXPaintable(GraphicsContext gc) {
        this.gc = gc;
    }
    
    
    
    @Override
    public void paint(Oval oval) {
        gc.setFill(Color.valueOf(oval.getColor().toString()));
        gc.setLineWidth(oval.getWeight());
        gc.fillOval(oval.getAnchor().getX(), oval.getAnchor().getY(), oval.getWidth(), oval.getHeight());
        gc.strokeOval(oval.getAnchor().getX(), oval.getAnchor().getY(), oval.getWidth(), oval.getHeight());
    }

    @Override
    public void paint(Polygon polygon) {
        gc.setFill(Color.valueOf(polygon.getColor().toString()));
        gc.setLineWidth(polygon.getWeight());
        gc.fillPolygon(polygon.getXPositions(), polygon.getYPositions(), polygon.getVertices().length);
        gc.strokePolygon(polygon.getXPositions(), polygon.getYPositions(), polygon.getVertices().length);
    }

    @Override
    public void paint(PaintedText text) {
        gc.setFill(Color.valueOf(text.getColor().toString()));
        gc.setFont(new Font(text.getFontName(), text.getHeight()));
        gc.fillText(text.getContent(), text.getAnchor().getX(), text.getAnchor().getY(), text.getWidth());
    }

    @Override
    public void paint(Image image) {
//        gc.drawImage(new javafx.scene.image.Image("file:///"+image.getFile().getPath()), image.getAnchor().getX(), image.getAnchor().getY());
        gc.drawImage(new javafx.scene.image.Image(image.getFile().toURI().toString()), image.getAnchor().getX(), image.getAnchor().getY());
    }
    
}
