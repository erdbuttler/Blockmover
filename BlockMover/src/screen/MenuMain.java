package screen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import core.Gamestate;
import blockmover.BlockMoverGameField;

public class MenuMain extends Menu {

	private JLabel empty;
	private JLabel newGame;
	private JLabel introduction;
	private JLabel exit;
	
	public MenuMain(BlockMoverGameField parent) {
		super(parent);
	}

	@Override
	public void load() {
		empty = new JLabel("");
		
		newGame = new JLabel();
		newGame.setText("New Game");
		newGame.setFont(new Font("Arial", Font.PLAIN, 25) );
		newGame.setHorizontalAlignment(SwingConstants.CENTER);
		newGame.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				newGame.setFont(new Font("Arial", Font.PLAIN, 25) );
				newGame.setForeground(Color.BLACK);
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				newGame.setFont(new Font("Arial", Font.BOLD, 29));
				newGame.setForeground(Color.BLUE);
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				getPanelParent().getScreenManager().changeDisplayMode(Gamestate.MENU_NEW_GAME);
			}
		});
		
		introduction = new JLabel();
		introduction.setText("Introduction");
		introduction.setFont(new Font("Arial", Font.PLAIN, 25) );
		introduction.setHorizontalAlignment(SwingConstants.CENTER);
		introduction.setForeground(Color.GRAY);
		introduction.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				//introduction.setFont(new Font("Arial", Font.PLAIN, 25) );
				//introduction.setForeground(Color.BLACK);
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				//introduction.setFont(new Font("Arial", Font.BOLD, 29));
				//introduction.setForeground(Color.BLUE);
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//getPanelParent().getScreenManager().changeDisplayMode(Gamestate.MENU_INTRODUCTION);
			}
		});
		
		exit = new JLabel();
		exit.setText("Exit");
		exit.setFont(new Font("Arial", Font.PLAIN, 25) );
		exit.setHorizontalAlignment(SwingConstants.CENTER);
		exit.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				exit.setFont(new Font("Arial", Font.PLAIN, 25) );
				exit.setForeground(Color.BLACK);
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				exit.setFont(new Font("Arial", Font.BOLD, 29));
				exit.setForeground(Color.BLUE);
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);
			}
		});
		
		
		getPanelParent().setLayout(new GridLayout(5, 1));
		getPanelParent().add(empty);
		getPanelParent().add(newGame);
		getPanelParent().add(introduction);
		getPanelParent().add(exit);
		getPanelParent().revalidate();
		
	}

	@Override
	public void unload() {
		getPanelParent().remove(newGame);
		getPanelParent().remove(introduction);
		getPanelParent().remove(exit);
		getPanelParent().remove(empty);
	}

	@Override
	public void draw(Graphics g) {
		
	}

	@Override
	public void update() {
	}

}
