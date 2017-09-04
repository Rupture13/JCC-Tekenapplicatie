/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawing.domain;

/**
 *
 * @author gebruiker
 */
public class Polygon extends DrawingItem{
    //Fields
    private Point[] vertices;
    private double weight;

    //Getters-setters
    public Point[] getVertices() {
        return vertices;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
    
    public Point getAnchor() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public double getWidth() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public double getHeight() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    //Constructors
    public Polygon(Point[] vertices, double weight, Color color, Drawing drawing) {
        super(color, drawing);
        this.vertices = vertices;
        this.weight = weight;
    }

    public Polygon(Point[] vertices, double weight, Color color, DrawingItem previousState, Drawing drawing) {
        super(color, previousState, drawing);
        this.vertices = vertices;
        this.weight = weight;
    }
    
}
