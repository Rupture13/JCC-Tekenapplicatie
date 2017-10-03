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
public class Point implements Serializable{
    //Fields
    private double x;
    private double y;

    //Getters-setters
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
    
    //Constructors

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
}
