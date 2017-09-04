/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawing.domain;

import java.util.ArrayList;

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

    //Constructors
    public Drawing(String name) {
        this.name = name;
        this.items = new ArrayList<DrawingItem>();
    }
}
