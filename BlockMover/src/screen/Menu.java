package screen;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import blockmover.BlockMoverGameField;
import core.ScreenManager;

/**
 * Main screen class - every other one should inherit from that.
 * @author niko
 */
public abstract class Menu {
	
	private BlockMoverGameField panelParent;
	
	public Menu(BlockMoverGameField parent) {
		super();
		panelParent = parent;
	}
	abstract public void load();
	abstract public void unload();
	abstract public void draw(Graphics g);
	abstract public void update();
	
	public BlockMoverGameField getPanelParent() {
		return panelParent;
	}

}
