/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawing.domain;

import java.util.Comparator;
/**
 *
 * @author gebruiker
 */
public class DrawingItemDistanceToOriginComparator implements Comparator<DrawingItem> {

    @Override
    public int compare(DrawingItem o1, DrawingItem o2) {
        //To calculate the distance to the origin of the canvas, Pythagorean theorem is used
        //Therefore, the distance is definded as
        //distance d = Math.sqrt((Math.pow(anchorX,2)) + (Math.pow(anchorY,2)))
        
        double distance1 = Math.sqrt((Math.pow(o1.getAnchor().getX(), 2)) + (Math.pow(o1.getAnchor().getY(), 2)));
        
        double distance2 = Math.sqrt((Math.pow(o2.getAnchor().getX(), 2)) + (Math.pow(o2.getAnchor().getY(), 2)));
        
        return Double.compare(distance1, distance2);
    }
    
}
