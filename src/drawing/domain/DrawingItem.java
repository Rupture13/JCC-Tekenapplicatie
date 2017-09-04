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
public abstract class DrawingItem {
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
    
    public abstract void editItem();
    public abstract void revertChange();
    
    @Override
    public abstract String toString();
}
