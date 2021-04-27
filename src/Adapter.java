import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Adapter extends KeyAdapter {

    Board board;

    public Adapter(Board board) {
        this.board = board;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        switch (key) {
            case KeyEvent.VK_LEFT:
                if (board.isRunning()) {
                    board.performMoveLeft();
                    board.update();
                }
                break;

            case KeyEvent.VK_RIGHT:
                if (board.isRunning()) {
                    board.performMoveRight();
                    board.update();
                }
                break;

            case KeyEvent.VK_UP:
                if (board.isRunning()) {
                    board.getControllingBlock().rotate();
                    board.update();
                }
                break;

            case KeyEvent.VK_DOWN:
                if (board.isRunning()) {
                    board.performFall();
                }
                break;

            case KeyEvent.VK_SPACE:
                board.performFastFall();
                break;
        }
    }
}
