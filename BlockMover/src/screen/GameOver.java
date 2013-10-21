package screen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import core.Gamestate;
import blockmover.BlockMoverGameField;

public class GameOver extends Menu {

	private JLabel gameover;
	private JLabel newgame;
	private JLabel menu;
	private JPanel south;
	
	public GameOver(BlockMoverGameField parent) {
		super(parent);
	}

	@Override
	public void load() {
		gameover = new JLabel();
		gameover.setText("GAME OVER");
		gameover.setFont(new Font("Arial", Font.PLAIN, 35) );
		gameover.setHorizontalAlignment(SwingConstants.CENTER);

		newgame = new JLabel();
		newgame.setText("New game!");
		newgame.setFont(new Font("Arial", Font.PLAIN, 25) );
		newgame.setHorizontalAlignment(SwingConstants.CENTER);
		newgame.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				newgame.setForeground(Color.BLACK);
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				newgame.setForeground(Color.BLUE);
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				getPanelParent().getScreenManager().changeDisplayMode(Gamestate.MENU_NEW_GAME);
			}
		});
		
		menu = new JLabel();
		menu.setText("Menu");
		menu.setFont(new Font("Arial", Font.PLAIN, 25) );
		menu.setHorizontalAlignment(SwingConstants.CENTER);
		menu.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				menu.setForeground(Color.BLACK);
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				menu.setForeground(Color.BLUE);
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				getPanelParent().getScreenManager().changeDisplayMode(Gamestate.MENU_MAIN);
			}
			
		});
		
		south = new JPanel();
		south.setLayout(new GridLayout(5,1));
		south.add(newgame);
		south.add(menu);
		
		//TODO: Think about more free position
		getPanelParent().setLayout(new GridLayout(2,1));
		getPanelParent().add(gameover);
		getPanelParent().add(south);
		gameover.revalidate();
	}

	@Override
	public void unload() {
		getPanelParent().remove(gameover);
		getPanelParent().remove(newgame);
		getPanelParent().remove(menu);
		getPanelParent().remove(south);		//thats a lot ...
		
	}

	@Override
	public void draw(Graphics g) {
		
	}

	@Override
	public void update() {
	}

}
