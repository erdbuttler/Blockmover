/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blockmover;


import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;


import screen.MenuNewGame;
import core.Gamestate;
import core.ScreenManager;

/**
 * 
 * @author Niki
 */
public class BlockMoverGameField extends JPanel implements KeyListener,
		ActionListener {

	private static final long serialVersionUID = 1L;
	private Timer timer;
	private ScreenManager screenMng = null;
	

	public BlockMoverGameField() {
		setFocusable(true);
		addKeyListener(this);
		
		screenMng = new ScreenManager(this);
		screenMng.changeDisplayMode(Gamestate.MENU_MAIN);

		timer = new Timer(40, this);
		timer.start();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		screenMng.draw(g);
	}

	private void update() {
		screenMng.update();
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(screenMng.getCurrentDisplayMode() instanceof MenuNewGame) {
			( (MenuNewGame) screenMng.getCurrentDisplayMode() ).keyPressed(e);
			return;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		update();
		this.repaint();
	}
	
	public ScreenManager getScreenManager() {
		return screenMng;
	}


}
