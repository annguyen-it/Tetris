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
        if (key == KeyEvent.VK_LEFT){
            board.performMoveLeft();
            board.update();
        }
        else if (key == KeyEvent.VK_RIGHT){
            board.performMoveRight();
            board.update();
        }
        else if (key == KeyEvent.VK_DOWN){
            board.performFall();
        }
        else if (key == KeyEvent.VK_UP){
            board.getControllingBlock().rotate();
            board.update();
        }
    }
}
