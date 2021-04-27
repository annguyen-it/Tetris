import javax.swing.*;

public class Game extends JFrame {

    public static final int WIDTH = 700;
    public static final int HEIGHT = 790;

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
