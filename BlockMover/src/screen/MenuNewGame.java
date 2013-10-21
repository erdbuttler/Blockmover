package screen;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import core.Gamestate;
import blockmover.Block;
import blockmover.BlockMoverGameField;
import blockmover.ShapeColor;

public class MenuNewGame extends Menu {

	private int distForNewRow = 0;
	private int rows = 16;
	private int columns = 8;
	private ShapeColor[][] field = new ShapeColor[rows][columns];
	private Random rd = new Random();
	private ShapeColor[] newRow = new ShapeColor[columns];
	private int blocksize = 28;
	private List<Block> gravityblocks = new ArrayList<Block>();
	private boolean[][] fieldIsBlocked = new boolean[rows][columns];
	private int GamerPositionRow = 3;
	private int GamerPositionColumn = 4;
	private long time = 0;
	private long gravityTimeForOneBlockInMillis = 100;
	private long timeForNewRowInMillis = 5000;
	private long startTimeOfNewRow = System.currentTimeMillis();
	
	private int score = 0;
	
	private JLabel game_titel;
	private JLabel score_titel;
	private JLabel score_number;
	private JLabel level_titel;
	private JLabel level_number;

	public MenuNewGame(BlockMoverGameField parent) {
		super(parent);
	}

	@Override
	public void load() {
		loadJLabel();
		int rd_num = 0;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				if (i > rows / 2) {
					field[i][j] = ShapeColor.NONE;
					continue;
				}
				rd_num = rd.nextInt(ShapeColor.values().length);
				field[i][j] = ShapeColor.values()[rd_num];
			}
		}
		createNewRow();

		// sort the field - no empty elements
		int point_top;
		int point_bottom;
		for (int j = 0; j < columns; j++) {
			point_top = rows - 1;
			point_bottom = 0;
			while (point_top > point_bottom) {
				while (field[point_bottom][j] != ShapeColor.NONE) {
					point_bottom++;
				}
				while (field[point_top][j] == ShapeColor.NONE) {
					point_top--;
				}
				if (point_top > point_bottom) {
					field[point_bottom][j] = field[point_top][j];
					field[point_top][j] = ShapeColor.NONE;
				}
			}
		}
		
	}

	private void loadJLabel() {
		game_titel = new JLabel("BlockMover");
		game_titel.setFont(new Font("Arial", Font.PLAIN, 30));
		game_titel.setBounds(10, 5, 200, 40);
		
		score_titel = new JLabel("Score:");
		score_titel.setFont(new Font("Arial", Font.PLAIN, 20));
		score_titel.setBounds(columns * blocksize + 5, 
							  getPanelParent().getHeight() - (rows+1) * blocksize, 
							  getPanelParent().getWidth() - (columns * blocksize +5), 
							  25);
		
		score_number = new JLabel("0");
		score_number.setFont(new Font("Arial", Font.PLAIN, 20));
		score_number.setHorizontalAlignment(SwingConstants.RIGHT);
		score_number.setBounds(columns * blocksize + 5, 
				  			   getPanelParent().getHeight() - rows * blocksize, 
				  			   getPanelParent().getWidth() - (columns * blocksize + 10), 
				  			   25);
		
		//TODO: implement
		level_titel = new JLabel("Level:");
		level_number = new JLabel("1");
		
		getPanelParent().setLayout(null);
		getPanelParent().add(game_titel);
		getPanelParent().add(score_titel);
		getPanelParent().add(score_number);
		getPanelParent().revalidate();
	}

	@Override
	public void unload() {
		getPanelParent().remove(game_titel);
		getPanelParent().remove(score_titel);
		getPanelParent().remove(score_number);
		getPanelParent().remove(level_titel);
		getPanelParent().remove(level_number);
	}

	@Override
	public void draw(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		
		int boxheight = getPanelParent().getHeight() - (blocksize * (rows+1) );
		g2D.drawLine(blocksize * columns + 1, boxheight, blocksize * columns +1, getPanelParent().getHeight());
		g2D.drawLine(blocksize * columns + 2, boxheight, blocksize * columns +2, getPanelParent().getHeight());
		g2D.drawLine(blocksize * columns + 3, boxheight, blocksize * columns +3, getPanelParent().getHeight());
		
		g2D.drawLine(0, boxheight, blocksize * columns + 1, boxheight);
		g2D.drawLine(0, boxheight+1, blocksize * columns + 1, boxheight+1);
		//g2D.translate(getPanelParent().getWidth(), getPanelParent().getHeight() - distForNewRow);
		g2D.translate(blocksize * columns, getPanelParent().getHeight() - distForNewRow);
		g2D.transform(AffineTransform.getRotateInstance(Math.PI));
		// draw the field
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				if (field[i][j] == ShapeColor.NONE) {
					continue;
				}
				g.setColor(Color.BLACK);
				g.drawRect(blocksize * j, blocksize * i, blocksize, blocksize);
				g.setColor(field[i][j].getColor());
				g.fillRect(blocksize * j, blocksize * i, blocksize - 1,
						blocksize - 1);
			}
		}
		for (Block b : gravityblocks) {
			g.setColor(Color.BLACK);
			g.drawRect(
					blocksize * b.getShape_position_column(),
					blocksize * b.getShape_position_row() - b.getDist_falling(),
					blocksize - 1, blocksize - 1);
			g.setColor(b.getShapecolor().getColor());
			if (b.getShapecolor() == ShapeColor.NONE) {
				System.out.println("Shapecolor of falling block is none!");
				System.exit(2);
			}
			g.fillRect(
					blocksize * b.getShape_position_column(),
					blocksize * b.getShape_position_row() - b.getDist_falling(),
					blocksize - 1, blocksize - 1);
		}
		// draw the new Row
		g.setColor(Color.BLACK);
		g.drawLine(0, 0, blocksize * rows, 0);
		g.drawLine(0, 1, blocksize * rows, 1);
		g.drawLine(0, 2, blocksize * rows, 2);
		for (int i = 0; i < columns; i++) {
			g.setColor(Color.BLACK);
			g.drawRect(blocksize * i, 0 - blocksize, blocksize, blocksize);
			g.setColor(new Color((int) (newRow[i].getColor().getRGB())));
			g.fillRect(blocksize * i, 0 - blocksize, blocksize - 1,
					blocksize - 1);
		}

		// draw the player area
		g2D.setStroke(new BasicStroke(3));
		g2D.setColor(Color.BLACK);
		g2D.drawRect(blocksize * GamerPositionColumn, blocksize
				* GamerPositionRow, blocksize, blocksize);
		g2D.drawRect(blocksize * (GamerPositionColumn + 1), blocksize
				* GamerPositionRow, blocksize, blocksize);

	}

	@Override
	public void update() {
		time = System.currentTimeMillis();
		gravity();
		checkCombo();
		updateNewRow();
	}

	private void createNewRow() {
		int rd_num;
		for (int i = 0; i < columns; i++) {
			rd_num = rd.nextInt(ShapeColor.values().length - 1);
			rd_num++;
			newRow[i] = ShapeColor.values()[rd_num];
			if(newRow[i] == field[0][i] && newRow[i] == field[1][i] ||
					(i > 1 && newRow[i] == newRow[i-1] && newRow[i] == newRow[i-2]) ) {
				i--;
			}
		}
	}
	
	private void gravity() {
		Block block = null;
		// Check if something is falling, when the block underneath is missing
		// (ShapeColor.NONE)
		for (int j = 0; j < columns; j++) {
			for (int i = 0; i < rows - 1; i++) {
				if (field[i][j] == ShapeColor.NONE
						&& field[i + 1][j] != ShapeColor.NONE) {
					block = new Block(getPanelParent(), i + 1, j);
					block.setTime_start_falling(time);
					block.setShapecolor(field[i + 1][j]);
					gravityblocks.add(block);
					// Mark blocked fields
					fieldIsBlocked[i + 1][j] = true;
					fieldIsBlocked[i][j] = true;
					field[i + 1][j] = ShapeColor.NONE;
				}
			}
		}

		List<Block> block_del = new ArrayList<Block>();
		for (Block b : gravityblocks) {
			long time_dist = time - b.getTime_start_falling();
			double dist_falling = (double) time_dist
					/ (double) gravityTimeForOneBlockInMillis;
			if (dist_falling >= 1.0) {
				if (b.getShape_position_row() > 0) {
					fieldIsBlocked[b.getShape_position_row()][b
							.getShape_position_column()] = false;
					b.setShape_position_row(b.getShape_position_row() - 1);
					fieldIsBlocked[b.getShape_position_row()][b
							.getShape_position_column()] = true;
				}

				// check if they are 'landed'
				if (b.getShape_position_row() == 0
						|| field[b.getShape_position_row() - 1][b
								.getShape_position_column()] != ShapeColor.NONE) {
					field[b.getShape_position_row()][b
							.getShape_position_column()] = b.getShapecolor();
					// Unmark blocked fields
					fieldIsBlocked[b.getShape_position_row()][b
							.getShape_position_column()] = false;
					fieldIsBlocked[b.getShape_position_row() + 1][b
							.getShape_position_column()] = false;
					block_del.add(b);
				} else {
					b.setTime_start_falling(b.getTime_start_falling()
							+ gravityTimeForOneBlockInMillis);
					// Mark the field
					fieldIsBlocked[b.getShape_position_row()][b
							.getShape_position_column()] = true;
					fieldIsBlocked[b.getShape_position_row() - 1][b
							.getShape_position_column()] = true;
					dist_falling -= 1.0;
				}
			}
			b.setDist_falling((int) ((double) blocksize * dist_falling));
		}
		for (Block b : block_del) {
			gravityblocks.remove(b);
		}
	}

	private void checkCombo() {
		int combo = 0;
		int checked_field[][] = new int[rows][columns];
		combo += checkComboRows(checked_field);
		combo += checkComboColumns(checked_field);

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				if (checked_field[i][j] >= 1) {
					field[i][j] = ShapeColor.NONE;
				}
			}
		}

		if (combo > 0) {
			//timeForNewRowInMillis -= combo * 15;
			score_number.setText("" + (new Integer(score_number.getText()) + combo * 10) );
			System.out.println("combo: " + combo + "   score: " + score_number.getText());
			combo = 0;
		}
	}
	
	private int checkComboRows(int checked_field[][]) {
		int combo = 0;
		ShapeColor refShape;
		// ShapeColor[rows][columns];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				refShape = field[i][j];
				if (refShape.equals(ShapeColor.NONE)) {
					continue;
				}

				int k = 1;
				while (j + k < columns && field[i][j + k].equals(refShape)) {
					k++;
				}
				// due to while, k might be one too much.
				k--;
				if (k >= 2) {
					// System.out.println("combo(row)! " + k);
					combo++;
					while (k >= 0) {
						checked_field[i][j + k] = 1;
						k--;
					}
				}
			}
		}
		return combo;
	}

	private int checkComboColumns(int checked_field[][]) {
		int combo = 0;
		// ShapeColor[rows][columns];
		ShapeColor refShape;
		for (int i = 0; i < columns; i++) {
			for (int j = 0; j < rows; j++) {
				refShape = field[j][i];
				if (refShape.equals(ShapeColor.NONE)) {
					continue;
				}

				int k = 1;
				while (j + k < rows && field[j + k][i].equals(refShape)) {
					k++;
				}
				k--;
				// 3 means, at least 2 more with same shape.
				if (k >= 2) {
					// System.out.println("combo column! " + k);
					combo++;
					while (k >= 0) {
						checked_field[j + k][i] = 1;
						k--;
					}
				}
			}
		}
		return combo;
	}
	
	/**
	 * Calculates the distance, how far the row can be seen. If the row is ready
	 * to be on the gamefield, a now row will be made.
	 * 
	 * @return true, if a new row is made.
	 */
	private boolean updateNewRow() {
		// Update of the new Row comming
		distForNewRow = (int) (((double) (time - startTimeOfNewRow) / timeForNewRowInMillis) * blocksize);
		if (distForNewRow >= blocksize) {
			for (int i = 0; i < columns; i++) {
				if (!field[rows - 1][i].equals(ShapeColor.NONE)) {
					getPanelParent().getScreenManager().changeDisplayMode(Gamestate.GAME_OVER);
					return true;
				}
			}

			startTimeOfNewRow = time;
			distForNewRow = 0;
			//move the fields
			for (int i = rows - 1; i > 0; i--) {
				field[i] = field[i - 1];
			}
			field[0] = newRow;
			newRow = new ShapeColor[columns];
			createNewRow();
			GamerPositionRow++;
			for (Block b : gravityblocks) {
				// moving the falling block means, the blocked areas have to
				// move too
				fieldIsBlocked[b.getShape_position_row() - 1][b
						.getShape_position_column()] = false;
				fieldIsBlocked[b.getShape_position_row() + 1][b
						.getShape_position_column()] = true;
				b.setShape_position_row(b.getShape_position_row() + 1);
			}
			return true;
		}
		return false;
	}
	
	/**
	 * For Testing purpose only. The block that is blocked now get a line from
	 * edge to edge.
	 * 
	 * @param g2D
	 */
	@SuppressWarnings("unused")
	private void showLockedElements(Graphics2D g2D) {
		for (int i = 0; i < fieldIsBlocked.length; i++) {
			for (int j = 0; j < fieldIsBlocked[0].length; j++) {
				if (fieldIsBlocked[i][j]) {
					g2D.drawLine(blocksize * j, blocksize * i, blocksize
							* (j + 1), blocksize * (i + 1));
				}
			}
		}
	}
	
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (GamerPositionColumn > 0) {
				GamerPositionColumn--;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			if (GamerPositionColumn < (columns - 2)) {
				GamerPositionColumn++;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			if (GamerPositionRow < (rows - 2)) {
				GamerPositionRow++;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			if (GamerPositionRow > 0) {
				GamerPositionRow--;
			}
		}

		if (e.getKeyChar() == KeyEvent.VK_SPACE) {
			switchShapes();
		}
	}

	private void switchShapes() {
		if (fieldIsBlocked[GamerPositionRow][GamerPositionColumn]
				|| fieldIsBlocked[GamerPositionRow][GamerPositionColumn + 1]) {
			System.out.println("can't swap, its blocked!");
			return;
		}
		ShapeColor swap = field[GamerPositionRow][GamerPositionColumn];
		field[GamerPositionRow][GamerPositionColumn] = field[GamerPositionRow][GamerPositionColumn + 1];
		field[GamerPositionRow][GamerPositionColumn + 1] = swap;
	}
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

}
