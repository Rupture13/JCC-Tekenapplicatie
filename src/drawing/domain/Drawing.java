/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawing.domain;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author gebruiker
 */
public class Drawing {
    //Fields
    private String name;
    ArrayList<DrawingItem> items;

    //Getters-setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public ArrayList<DrawingItem> getOvals() {
        ArrayList<DrawingItem> result = new ArrayList<>();
        for (DrawingItem item : items) {
            if (item instanceof Oval) {
                result.add(item);
            }
        }
        return result;
    }
    
    public ArrayList<DrawingItem> getPolygons() {
        ArrayList<DrawingItem> result = new ArrayList<>();
        for (DrawingItem item : items) {
            if (item instanceof Polygon) {
                result.add(item);
            }
        }
        return result;
    }
    
    public ArrayList<DrawingItem> getTexts() {
        ArrayList<DrawingItem> result = new ArrayList<>();
        for (DrawingItem item : items) {
            if (item instanceof PaintedText) {
                result.add(item);
            }
        }
        return result;
    }
    
    public ArrayList<DrawingItem> getImages() {
        ArrayList<DrawingItem> result = new ArrayList<>();
        for (DrawingItem item : items) {
            if (item instanceof Image) {
                result.add(item);
            }
        }
        return result;
    }

    //Constructors
    public Drawing(String name) {
        this.name = name;
        this.items = new ArrayList<DrawingItem>();
    }
    
    //Methods    
    public void addDrawingItem(DrawingItem item) {
        this.items.add(item);
    }
    
    public void editDrawingItem(DrawingItem item, Point anchor, Color color, double height, double width, double weight) {
        if (item instanceof Oval) {
            ((Oval) item).editItem(anchor, color, height, width, weight);
        }
    }
    
    public void editDrawingItem(DrawingItem item, Point[] vertices, double weight, Color color) {
        if (item instanceof Polygon) {
            ((Polygon) item).editItem(vertices, weight, color);
        }
    }
    
    public void editDrawingItem(DrawingItem item, Point anchor, double width, double height, String content, String fontName, Color color) {
        if (item instanceof PaintedText) {
            ((PaintedText) item).editItem(anchor, width, height, content, fontName, color);
        }
    }
    
    public void editDrawingItem(DrawingItem item, Point anchor, double width, double height, File file, Color color) {
        if (item instanceof Image) {
            ((Image) item).editItem(anchor, width, height, file, color);
        }
    }
    
    public void removeDrawingItem(DrawingItem item) {
        this.items.remove(item);
    }
    
    public void removeAllDrawingItems() {
        this.items.clear();
    }
    
    public void sortDrawingItemsByDistanceToOrigin() {
        Collections.sort(items, new DrawingItemDistanceToOriginComparator());
    }
    
    public void paintUsing(IPaintable paintable) {
        for (DrawingItem item : items) {
            item.paintUsing(paintable);
        }
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("DRAWING ['" + this.name + "'] \n");
        sb.append("Items in drawing: \n");
        for (DrawingItem item : items) {
            sb.append(item.toString() + System.lineSeparator());
        }
        
        return sb.toString();
    }
}
