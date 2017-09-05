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
public class Oval extends DrawingItem{
    //Fields
    private Point anchor;
    private double width;
    private double height;
    private double weight;

    //Getters-setters
    @Override
    public Point getAnchor() {
        return anchor;
    }
    public void setAnchor(Point anchor) {
        this.anchor = anchor;
    }

    @Override
    public double getWidth() {
        return width;
    }
    public void setWidth(double width) {
        this.width = width;
    }

    @Override
    public double getHeight() {
        return height;
    }
    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }
    public void setWeight(double weight) {
        this.weight = weight;
    }
    
    //Constructors
    public Oval(Point anchor, double width, double height, double weight, Color color, Drawing drawing) {
        super(color, drawing);
        this.anchor = anchor;
        this.width = width;
        this.height = height;
        this.weight = weight;
        this.previousState = null;
    }

    public Oval(Point anchor, double width, double height, double weight, Color color, DrawingItem previousState, Drawing drawing) {
        super(color, previousState, drawing);
        this.anchor = anchor;
        this.width = width;
        this.height = height;
        this.weight = weight;
    }
    
    public void editItem(Point anchor, Color color, double height, double width, double weight) {
        if (this.anchor != anchor || this.color != color || this.height != height || this.width != width || this.weight != weight) {
            this.previousState = new Oval(this.anchor, this.width, this.height, this.weight, this.color, this.previousState, this.drawing);
            this.anchor = anchor;
            this.color = color;
            this.height = height;
            this.width = width;
            this.weight = weight;
        }
    }
    
    @Override
    public void revertChange() {
        if (this.previousState != null) {
            Oval old = (Oval) this.previousState;
            this.anchor = old.anchor;
            this.color = old.color;
            this.height = old.height;
            this.width = old.width;
            this.weight = old.weight;
            this.previousState = old.previousState;
        }
    }
    
    @Override
    public String toString() {
        return (this.color.toString() + " OVAL (anchor: " + this.anchor.getX() + "," + this.anchor.getY() + " | size: " + this.width + " × " + this.height + " | weight: " + this.weight + ")");
    }
    
}
