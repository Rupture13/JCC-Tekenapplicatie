/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tekenapplicatie;
import drawing.domain.*;
/**
 *
 * @author gebruiker
 */
public class Tekenapplicatie {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TestScript1();
    }
    
    public static void TestScript1() {
        Drawing dr = new Drawing("Masterpiece");
        
        Oval ov = new Oval(new Point(2, 3), 12, 768, 3, Color.BLUE, dr);
        Point[] polyPoints = {new Point(1, 2), new Point(3, 4), new Point(2, 3)};
        Polygon po = new Polygon(polyPoints, 0, Color.BLUE, dr);
        PaintedText txt = new PaintedText(new Point(42, 42), 36, 6, "Ik ben een text", "Arial", Color.BLACK, dr);
        
        dr.AddDrawingItem(ov);
        dr.AddDrawingItem(po);
        dr.AddDrawingItem(txt);
        
        System.out.println(dr.toString());
        
        dr.RemoveDrawingItem(po);
        
        System.out.println(dr.toString());
        
        System.out.println("And now for something completely different!");
        
        Oval ov2 = new Oval(new Point(42, 13), 1920, 1080, 1, Color.RED, dr);
        System.out.println(ov2.toString());
        
        ov2.editItem(24, 48, ov2.getColor(), ov2.getHeight(), ov2.getWidth(), ov2.getWeight());
        System.out.println(ov2.toString());
        
        ov2.editItem(12, 36, ov2.getColor(), 9, ov2.getWidth(), 2);
        System.out.println(ov2.toString());
        
        ov2.revertChange();
        System.out.println(ov2.toString());
        
        ov2.revertChange();
        System.out.println(ov2.toString());
    }
    
    public static void TestScript2() {
        
    }
    
}
