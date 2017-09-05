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
public class PaintedText extends DrawingItem{
    //Fields
    private Point anchor;
    private double width;
    private double height;
    private String content;
    private String fontName;

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

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public String getFontName() {
        return fontName;
    }
    public void setFontName(String fontName) {
        this.fontName = fontName;
    }
    
    //Constructors

    public PaintedText(Point anchor, double width, double height, String content, String fontName, Color color, Drawing drawing) {
        super(color, drawing);
        this.anchor = anchor;
        this.width = width;
        this.height = height;
        this.content = content;
        this.fontName = fontName;
    }

    public PaintedText(Point anchor, double width, double height, String content, String fontName, Color color, DrawingItem previousState, Drawing drawing) {
        super(color, previousState, drawing);
        this.anchor = anchor;
        this.width = width;
        this.height = height;
        this.content = content;
        this.fontName = fontName;
    }

    public void editItem() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void revertChange() {
        PaintedText old = (PaintedText) this.previousState;
        this.anchor = old.anchor;
        this.color = old.color;
        this.height = old.height;
        this.width = old.width;
        this.content = old.content;
        this.fontName = old.fontName;
        this.previousState = old.previousState;
    }

    @Override
    public String toString() {
        return (this.color.toString() + " TEXT ['" + this.content + "'] (anchor: " + this.anchor.getX() + "," + this.anchor.getY() + " | size: " + this.width + " × " + this.height + " | font: " + this.fontName + ")");
    }
}
