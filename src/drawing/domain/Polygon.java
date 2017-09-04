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
    
    @Override
    public Point getAnchor() {
        double x = 4200;
        double y = 4200;
        
        for (Point vertice : vertices) {
            if (vertice.getX() < x) {
                x = vertice.getX();
            }
        }
        
        for (Point vertice : vertices) {
            if (vertice.getY() < y) {
                y = vertice.getY();
            }
        }
        
        return new Point(x, y);
    }

    @Override
    public double getWidth() {
        double min = 4200;
        double max = 0;
        
        for (Point vertice : vertices) {
            if (vertice.getX() < min) {
                min = vertice.getX();
            }
        }
        
        for (Point vertice : vertices) {
            if (vertice.getX() > max) {
                max = vertice.getX();
            }
        }
        
        return (max - min);
    }

    @Override
    public double getHeight() {
        double min = 4200;
        double max = 0;
        
        for (Point vertice : vertices) {
            if (vertice.getY() < min) {
                min = vertice.getY();
            }
        }
        
        for (Point vertice : vertices) {
            if (vertice.getY() > max) {
                max = vertice.getY();
            }
        }
        
        return (max - min);
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

    @Override
    public void editItem() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void revertChange() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return (this.color.toString() + " POLYGON (anchor: " + this.getAnchor().getX() + "," + this.getAnchor().getY() + " | size: " + this.getWidth() + " × " + this.getHeight() + " | weight: " + this.weight + ")");
    }
    
}
