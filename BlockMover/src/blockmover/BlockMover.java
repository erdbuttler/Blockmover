
package blockmover;

import javax.swing.JFrame;

import core.AssemblyVersion;

public class BlockMover extends JFrame {

    public BlockMover() {
        this.setTitle(AssemblyVersion.version);
        setSize(400, 560);

        BlockMoverGameField blgf = new BlockMoverGameField();
        add(blgf);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
