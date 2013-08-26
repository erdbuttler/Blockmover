
package blockmover;

import javax.swing.JFrame;

public class BlockMover extends JFrame {

    public BlockMover() {
        this.setTitle("BlockerMover Alpha 0.0.2");
        setSize(260, 560);

        BlockMoverGameField blgf = new BlockMoverGameField();
        add(blgf);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

}
