import javax.swing.*;

public class Game extends JFrame {

    private static final int WIDTH = 510;
    private static final int HEIGHT = 810;

    public Game() {
        super();
        setupUI();

        Board board = new Board();
        add(board);

        setVisible(true);
    }

    private void setupUI(){
        setTitle("Tetris");
        setSize(WIDTH, HEIGHT);
        setResizable(false);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
