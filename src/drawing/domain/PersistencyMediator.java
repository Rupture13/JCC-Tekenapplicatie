/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawing.domain;

import java.util.Properties;

/**
 *
 * @author Rupture13
 */
public interface PersistencyMediator {
    Drawing load(String nameDrawing);
    boolean save(Drawing drawing);
    boolean init(Properties props);
}
