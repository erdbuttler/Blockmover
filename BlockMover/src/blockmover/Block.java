package blockmover;

public class Block {
    private static BlockMoverGameField parent;
    private int shape_position_row;
    private int shape_position_column;
    private ShapeColor shapecolor;
    //Gravity
    private long time_start_falling;
    //size in pixel
    private int dist_fallingInPx;

    public Block(BlockMoverGameField parent, int shape_position_row, int shape_position_column) {
        this.parent = parent;
        this.shape_position_row = shape_position_row;
        this.shape_position_column = shape_position_column;
    }

    public int getDist_falling() {
        return dist_fallingInPx;
    }

    public void setDist_falling(int dist_falling) {
        this.dist_fallingInPx = dist_falling;
    }

    public long getTime_start_falling() {
        return time_start_falling;
    }

    public void setTime_start_falling(long time_start_falling) {
        this.time_start_falling = time_start_falling;
    }

    public ShapeColor getShapecolor() {
        return shapecolor;
    }

    public void setShapecolor(ShapeColor shapecolor) {
        this.shapecolor = shapecolor;
    }

    public int getShape_position_column() {
        return shape_position_column;
    }

    public int getShape_position_row() {
        return shape_position_row;
    }

    public void setShape_position_row(int shape_position_row) {
        this.shape_position_row = shape_position_row;
    }

    public void setShape_position_column(int shape_position_column) {
        this.shape_position_column = shape_position_column;
    }
}
