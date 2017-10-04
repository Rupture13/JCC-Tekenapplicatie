/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawing.domain;

import java.io.Serializable;

/**
 *
 * @author gebruiker
 */
public abstract class DrawingItem implements Serializable {
    //Fields
    Color color;
    DrawingItem previousState;
    Drawing drawing;

    //Getters-setters
    public Color getColor() {
        return color;
    }
    public void setColor(Color color) {
        this.color = color;
    }
    
    //Constructors
    public DrawingItem(Color color, Drawing drawing) {
        this.color = color;
        this.drawing = drawing;
    }
    
    public DrawingItem(Color color, DrawingItem previousState, Drawing drawing) {
        this.color = color;
        this.previousState = previousState;
        this.drawing = drawing;
    }
    
    //Methods
    public abstract Point getAnchor();
    public abstract double getWidth();
    public abstract double getHeight();
    
    //public abstract void editItem();
    public abstract void revertChange();
    
    public abstract void paintUsing(IPaintable paintable);
    
    public boolean overlaps(DrawingItem item) {
        boolean result = false;
        if (item.insideBoundingBox(this.getAnchor())) {
            result = true;
        }
        else if (item.insideBoundingBox(new Point((this.getAnchor().getX() + this.getWidth()), this.getAnchor().getY()))) {
            result = true;
        }
        else if (item.insideBoundingBox(new Point(this.getAnchor().getX(), (this.getAnchor().getY() + this.getHeight())))) {
            result = true;
        }
        else if (item.insideBoundingBox(new Point((this.getAnchor().getX() + this.getWidth()), (this.getAnchor().getY() + this.getHeight())))) {
            result = true;
        }
        return result;
    }
    
    public boolean insideBoundingBox(Point point) {
        boolean result = false;
        if (point.getX() >= this.getAnchor().getX() 
            && point.getX() <= (this.getAnchor().getX() + this.getWidth())
            && point.getY() >= this.getAnchor().getY()
            && point.getY() <= (this.getAnchor().getY() + this.getHeight())) 
        {
            result = true;
        }
        return result;
    }
    
    @Override
    public abstract String toString();
}