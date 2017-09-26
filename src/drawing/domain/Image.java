/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawing.domain;

import java.io.File;

/**
 *
 * @author gebruiker
 */
public class Image extends DrawingItem{
    //Fields
    private Point anchor;
    private double width;
    private double height;
    private File file;

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

    public File getFile() {
        return file;
    }
    public void setFile(File file) {
        this.file = file;
    }
    
    //Constructors
    public Image(Point anchor, double width, double height, File file, Color color, Drawing drawing) {
        super(color, drawing);
        this.anchor = anchor;
        this.width = width;
        this.height = height;
        this.file = file;
        this.previousState = null;
    }

    public Image(Point anchor, double width, double height, File file, Color color, DrawingItem previousState, Drawing drawing) {
        super(color, previousState, drawing);
        this.anchor = anchor;
        this.width = width;
        this.height = height;
        this.file = file;
    }

    public void editItem(Point anchor, double width, double height, File file, Color color) {
        if (this.anchor != anchor || this.width != width || this.height != height || (this.file.equals(file) == false) || this.color != color) {
            this.previousState = new Image(this.anchor, this.width, this.height, this.file, this.color, this.drawing);
            this.anchor = anchor;
            this.width = width;
            this.height = height;
            this.file = file;
            this.color = color;
        }
    }

    @Override
    public void revertChange() {
        if (this.previousState != null) {
            Image old = (Image) this.previousState;
            this.anchor = old.anchor;
            this.color = old.color;
            this.height = old.height;
            this.width = old.width;
            this.file = old.file;
            this.previousState = old.previousState;
        }
    }

    @Override
    public String toString() {
        return ("IMAGE [" + this.file.getPath() + "] (anchor: " + this.anchor.getX() + "," + this.anchor.getY() + " | size: " + this.width + " × " + this.height + ")");
    }

    @Override
    public void paintUsing(IPaintable paintable) {
        paintable.paint(this);
    }
}
