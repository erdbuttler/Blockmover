/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package blockmover;

import java.awt.Color;

/**
 *
 * @author Niki
 */
public enum ShapeColor {

    NONE(Color.WHITE),
    RED(Color.RED),
    BLUE(Color.BLUE),
    GREEN(Color.GREEN),
    YELLOW(Color.YELLOW),
    GRAY(Color.GRAY);

    private Color color;

    ShapeColor(Color c) {
        this.color = c;
    }

    public Color getColor() {
        return color;
    }
}
