/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawing.domain;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author gebruiker
 */
public class Drawing extends DrawingItem implements Serializable {
    //Fields
    private String name;
    private ArrayList<DrawingItem> items;
    private transient ObservableList<DrawingItem> observableList;
    private Point anchor;

    //Getters-setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        editDrawing();
        this.name = name;
    }
    
    public ArrayList<DrawingItem> getItems() {
        return this.items;
    }
    
    public ObservableList<DrawingItem> itemsToObserve() {   
        if (this.observableList == null) {
            this.observableList = FXCollections.observableList(this.items);
        }
        return FXCollections.unmodifiableObservableList(observableList);
    }
    
    @Override
    public Point getAnchor() {
        return this.anchor;
    }

    @Override
    public double getWidth() {
        double width = 0;
        
        for (DrawingItem item : items) {
            if (item.getWidth() > width) {
                width = item.getWidth();
            }
        }
        
        return width;
    }

    @Override
    public double getHeight() {
        double height = 0;
        
        for (DrawingItem item : items) {
            if (item.getHeight()> height) {
                height = item.getHeight();
            }
        }
        
        return height;
    }
    
    public ArrayList<DrawingItem> getDrawings() {
        ArrayList<DrawingItem> result = new ArrayList<>();
        for (DrawingItem item : items) {
            if (item instanceof Drawing) {
                result.add(item);
            }
        }
        return result;
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
    public Drawing(String name, Point anchor, Color color, Drawing drawing) {
        super(color, drawing);
        this.name = name;
        this.items = new ArrayList<DrawingItem>();
        this.observableList = FXCollections.observableList(items);
        this.anchor = anchor;
    }

    public Drawing(String name, Point anchor, Color color, DrawingItem previousState, Drawing drawing) {
        super(color, previousState, drawing);
        this.name = name;
        this.items = new ArrayList<DrawingItem>();
        this.observableList = FXCollections.observableList(items);
        this.anchor = anchor;
    }

    public Drawing(String name, ArrayList<DrawingItem> items, Point anchor, Color color, Drawing drawing) {
        super(color, drawing);
        this.name = name;
        this.items = items;
        this.observableList = FXCollections.observableList(items);
        this.anchor = anchor;
    }

    public Drawing(String name, ArrayList<DrawingItem> items, Point anchor, Color color, DrawingItem previousState, Drawing drawing) {
        super(color, previousState, drawing);
        this.name = name;
        this.items = items;
        this.observableList = FXCollections.observableList(items);
        this.anchor = anchor;
    }

    //Methods    
    public void addDrawingItem(DrawingItem item) {
        editDrawing();
        //this.items.add(item);
        this.observableList.add(item);
    }
    
    public void editDrawing() {
        ArrayList<DrawingItem> oldItems = new ArrayList<>();
        oldItems.addAll(this.items);
        this.previousState = new Drawing(this.name, oldItems, this.anchor, this.color, this.previousState, this.drawing);
    }
    
    public void editDrawingItem(DrawingItem item, Point anchor, Color color, double height, double width, double weight) {
        if (item instanceof Oval) {
            editDrawing();
            ((Oval) item).editItem(anchor, color, height, width, weight);
        }
    }
    
    public void editDrawingItem(DrawingItem item, Point[] vertices, double weight, Color color) {
        if (item instanceof Polygon) {
            editDrawing();
            ((Polygon) item).editItem(vertices, weight, color);
        }
    }
    
    public void editDrawingItem(DrawingItem item, Point anchor, double width, double height, String content, String fontName, Color color) {
        if (item instanceof PaintedText) {
            editDrawing();
            ((PaintedText) item).editItem(anchor, width, height, content, fontName, color);
        }
    }
    
    public void editDrawingItem(DrawingItem item, Point anchor, double width, double height, File file, Color color) {
        if (item instanceof Image) {
            editDrawing();
            ((Image) item).editItem(anchor, width, height, file, color);
        }
    }
    
    public void removeDrawingItem(DrawingItem item) {
        editDrawing();
        //this.items.remove(item);
        this.observableList.remove(item);
    }
    
    public void removeAllDrawingItems() {
        editDrawing();
        //this.items.clear();
        this.observableList.clear();
    }
    
    public void sortDrawingItemsByDistanceToOrigin() {
        editDrawing();
        //Collections.sort(items, new DrawingItemDistanceToOriginComparator());
        Collections.sort(observableList, new DrawingItemDistanceToOriginComparator());
    }
    
    public void paintUsing(IPaintable paintable) {
        for (DrawingItem item : items) {
            item.paintUsing(paintable);
        }
    }
    
    @Override
    public void revertChange() {
        if (this.previousState != null) {
            Drawing old = (Drawing) this.previousState;
            ArrayList<DrawingItem> oldItems = new ArrayList<>();
            oldItems.addAll(old.items);
            this.anchor = old.anchor;
            this.color = old.color;
            this.drawing = old.drawing;
            this.items = oldItems;
            this.name = old.name;
            this.previousState = old.previousState;
            this.observableList = FXCollections.observableList(this.items);
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
