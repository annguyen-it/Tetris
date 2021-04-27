import shape.Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Board extends JPanel implements ActionListener {

    private static final int BLOCK_SIZE = 30;
    private static final int WIDTH = 15;
    private static final int HEIGHT = 28;
    private static final int DELAY = 800;

    private final Timer timer;

    private Shape controllingBlock;
    private Shape nextBlock;

    private List<List<Integer>> MATRIX;
    private List<List<Integer>> LANDED;
    private int score;

    public Board() {
        super();

        initMatrix();

        requestFocusInWindow();
        setFocusable(true);
        requestFocus();
        setVisible(true);
        setLayout(null);

        setupButtons();

        addKeyListener(new Adapter(this));

        timer = new Timer(DELAY, this);
    }

    public Shape getControllingBlock() {
        return controllingBlock;
    }

    //#region Logic of Board
    private void initMatrix() {
        MATRIX = new ArrayList<>();
        LANDED = new ArrayList<>();

        for (int i = 0; i < HEIGHT; i++) {
            MATRIX.add(new ArrayList<>());
            LANDED.add(new ArrayList<>());

            for (int j = 0; j < WIDTH; j++) {
                MATRIX.get(i).add(0);
                LANDED.get(i).add(0);
            }
        }
    }

    private void start() {
        createNewBlock();
        changeToNextBlock();
        score = 0;

        timer.start();
    }

    private void changeToNextBlock() {
        controllingBlock = nextBlock;
        createNewBlock();
    }

    private void createNewBlock() {
        nextBlock = Shape.randomBlock();
    }

    public void update() {
        int w = controllingBlock.getWidth();
        int h = controllingBlock.getHeight();
        int x = controllingBlock.getX();
        int y = controllingBlock.getY();
        int[][] blockMatrix = controllingBlock.getBlockMatrix();

        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                MATRIX.get(i).set(j, LANDED.get(i).get(j));
            }
        }

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (blockMatrix[i][j] > 0) {
                    MATRIX.get(y + i).set(x + j, blockMatrix[i][j]);
                }
            }
        }

        repaint();
    }

    private void clear() {
        int clearedRow = 0;

        for (int i = 0; i < LANDED.size(); ) {
            boolean fullRow = true;
            for (int j = 0; j < WIDTH; j++) {
                if (LANDED.get(i).get(j) == 0) {
                    fullRow = false;
                    break;
                }
            }

            if (fullRow) {
                LANDED.remove(i);
                clearedRow++;
            }
            else {
                i++;
            }
        }

        while (LANDED.size() < HEIGHT) {
            List<Integer> a = new ArrayList<>();
            for (int i = 0; i < WIDTH; i++) {
                a.add(0);
            }
            LANDED.add(0, a);
        }

        score += clearedRow*10;
    }

    private void lose() {
        String[] options = { "Có", "Thoát" };

        timer.stop();
        int playerChoice = JOptionPane.showOptionDialog(
                null,
                new JLabel("Bạn đã thua. Bạn có muốn chơi tiếp không?"),
                "Thông tin",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.ERROR_MESSAGE,
                null,
                options,
                null);

        if (playerChoice == JOptionPane.OK_OPTION){
            initMatrix();
            start();
        }
        else {
            System.exit(0);
        }
    }

    //#endregion

    //#region UI
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        paintGame(g);
        paintNextBlockBox(g);
        paintScoreBox(g);
    }

    private void paintGame(Graphics g) {
        paintBlock(g);
        paintCell(g);
    }

    private void paintBlock(Graphics g) {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                int cell = MATRIX.get(i).get(j);
                if (cell != 0) {
                    g.setColor(Shape.COLORS.get(cell));
                    g.fillRect(j*BLOCK_SIZE, (i - 3)*BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
                }
            }
        }
    }

    private void paintCell(Graphics g) {
        g.setColor(Color.BLACK);

        for (int i = 0; i < HEIGHT + 1 - 4; i++) {
            g.drawLine(0, i*BLOCK_SIZE, WIDTH*BLOCK_SIZE, i*BLOCK_SIZE);
        }

        for (int i = 0; i < WIDTH + 1; i++) {
            g.drawLine(i*BLOCK_SIZE, 0, i*BLOCK_SIZE, HEIGHT*BLOCK_SIZE);
        }
    }

    private void paintNextBlockBox(Graphics g) {
        paintNextBlock(g);
        paintNextBlockCell(g);
    }

    private void paintNextBlock(Graphics g) {
        if (nextBlock != null) {
            int[][] nextBlockMatrix = nextBlock.getTemplateMatrix();
            Color color = nextBlock.getColor();
            g.setColor(color);

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    int cell = nextBlockMatrix[i][j];
                    if (cell != 0) {
                        g.fillRect((WIDTH + 2 + j)*BLOCK_SIZE, (2 + i)*BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
                    }
                }
            }
        }
    }

    private void paintNextBlockCell(Graphics g) {
        g.setColor(Color.BLACK);

        for (int i = 0; i < 5; i++) {
            g.drawLine((WIDTH + 2)*BLOCK_SIZE, (2 + i)*BLOCK_SIZE, (WIDTH + 6)*BLOCK_SIZE, (2 + i)*BLOCK_SIZE);
        }

        for (int i = 0; i < 5; i++) {
            g.drawLine((WIDTH + 2 + i)*BLOCK_SIZE, 2*BLOCK_SIZE, (WIDTH + 2 + i)*BLOCK_SIZE, 6*BLOCK_SIZE);
        }
    }

    private void paintScoreBox(Graphics g) {
        g.setColor(Color.BLACK);

        g.setFont(new Font("Serif", Font.PLAIN, 20));
        g.drawString("Điểm", (WIDTH + 3)*BLOCK_SIZE + 5, 8*BLOCK_SIZE);

        g.setFont(new Font("Serif", Font.PLAIN, 25));
        g.drawString(String.valueOf(score), (WIDTH + 3)*BLOCK_SIZE + 5, 9*BLOCK_SIZE);
    }

    private void setupButtons() {
        setupNewGameButton();
        setupHelpButton();
        setupExitButton();
    }

    private void setupNewGameButton() {
        JButton newGame = new JButton("Chơi mới");
        newGame.setPreferredSize(new Dimension(4*BLOCK_SIZE, BLOCK_SIZE));
        newGame.setBounds((WIDTH + 2)*BLOCK_SIZE, 12*BLOCK_SIZE, 4*BLOCK_SIZE, BLOCK_SIZE);

        add(newGame);

        newGame.addActionListener(e -> {
            if (timer.isRunning()) {
                timer.stop();

                int option = JOptionPane.showConfirmDialog(
                        null,
                        new JLabel("Bạn có muốn chơi mới không?"),
                        "Thông tin",
                        JOptionPane.OK_CANCEL_OPTION
                );

                if (option == JOptionPane.OK_OPTION) {
                    initMatrix();
                    start();
                }
                else {
                    timer.start();
                }
            }
            else {
                start();
                newGame.setFocusable(false);
            }
        });
    }

    private void setupHelpButton() {
        JButton help = new JButton("Hướng dẫn");
        help.setPreferredSize(new Dimension(4*BLOCK_SIZE, BLOCK_SIZE));
        help.setBounds((WIDTH + 2)*BLOCK_SIZE, 14*BLOCK_SIZE, 4*BLOCK_SIZE, BLOCK_SIZE);

        add(help);
        help.setFocusable(false);

        help.addActionListener(e -> JOptionPane.showMessageDialog(
                null,
                new JLabel("<html>Cố gắng đưa các viên gạch thành một hàng hoàn chỉnh." +
                           "<p>" +
                           "<p>Các phím di chuyển:\n" +
                           "<p>\uD83E\uDC11: Xoay khối gạch\n" +
                           "<p>\uD83E\uDC10: Di chuyển sang trái\n" +
                           "<p>\uD83E\uDC12: Di chuyển sang phải\n" +
                           "<p>\uD83E\uDC13: Tăng tốc khối gạch\n" +
                           "<p>Space: Làm khối gạch rơi ngay lập tức")
        ));
    }

    private void setupExitButton() {
        JButton exit = new JButton("Thoát");
        exit.setPreferredSize(new Dimension(4*BLOCK_SIZE, BLOCK_SIZE));
        exit.setBounds((WIDTH + 2)*BLOCK_SIZE, 16*BLOCK_SIZE, 4*BLOCK_SIZE, BLOCK_SIZE);

        add(exit);
        exit.setFocusable(false);

        exit.addActionListener(e -> {
            timer.stop();

            int option = JOptionPane.showConfirmDialog(
                    null,
                    new JLabel("Bạn có chắc muốn thoát không?"),
                    "Thông tin",
                    JOptionPane.OK_CANCEL_OPTION
            );

            if (option == JOptionPane.OK_OPTION) {
                initMatrix();
                start();
                System.exit(0);
            }
            else {
                timer.start();
            }
        });
    }

    //#endregion

    //#region Key press
    @Override
    public void actionPerformed(ActionEvent e) {
        performFall();
    }

    public void pauseOrContinue() {
        if (timer.isRunning()) {
            timer.stop();
        }
        else {
            timer.start();
        }
    }

    public boolean isRunning() {
        return timer.isRunning();
    }

    public void performFall() {
        if (!willBottomOverlapped() && !willLanded()) {
            controllingBlock.fall();
        }
        else {
            merge();

            if (willTopOverlapped()) {
                lose();
            }

            changeToNextBlock();
        }

        update();
    }

    public void performFastFall() {
        while (!willBottomOverlapped() && !willLanded()) {
            controllingBlock.fall();
        }

        merge();

        if (willTopOverlapped()) {
            lose();
        }

        changeToNextBlock();
        update();
    }

    public void performMoveLeft() {
        if (!willLeftOverlapped() && !willTouchLeft()) {
            controllingBlock.moveLeft();
        }
    }

    public void performMoveRight() {
        if (!willRightOverlapped() && !willTouchRight()) {
            controllingBlock.moveRight();
        }
    }
    //#endregion

    //#region Logic of Block
    private boolean willLanded() {
        int[][] matrix = controllingBlock.getBlockMatrix();
        int w = controllingBlock.getWidth();
        int h = controllingBlock.getHeight();
        int x = controllingBlock.getX();
        int y = controllingBlock.getY() + 1;

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (matrix[i][j] > 0 && LANDED.get(y + i).get(x + j) > 0) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean willTopOverlapped() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (LANDED.get(i).get(j) != 0) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean willBottomOverlapped() {
        int y = controllingBlock.getY();
        int h = controllingBlock.getHeight();
        return y + h >= HEIGHT;
    }

    private boolean willLeftOverlapped() {
        return controllingBlock.getX() - 1 < 0;
    }

    private boolean willRightOverlapped() {
        return controllingBlock.getX() + controllingBlock.getWidth() + 1 > WIDTH;
    }

    private boolean willTouchLeft() {
        int[][] matrix = controllingBlock.getBlockMatrix();
        int w = controllingBlock.getWidth();
        int h = controllingBlock.getHeight();
        int x = controllingBlock.getX() - 1;
        int y = controllingBlock.getY();

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (matrix[i][j] > 0 && LANDED.get(y + i).get(x + j) > 0) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean willTouchRight() {
        int[][] matrix = controllingBlock.getBlockMatrix();
        int w = controllingBlock.getWidth();
        int h = controllingBlock.getHeight();
        int x = controllingBlock.getX() + 1;
        int y = controllingBlock.getY();

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (matrix[i][j] > 0 && LANDED.get(y + i).get(x + j) > 0) {
                    return true;
                }
            }
        }

        return false;
    }

    private void merge() {
        int[][] matrix = controllingBlock.getBlockMatrix();
        int w = controllingBlock.getWidth();
        int h = controllingBlock.getHeight();
        int x = controllingBlock.getX();
        int y = controllingBlock.getY();

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (matrix[i][j] > 0) {
                    LANDED.get(y + i).set(x + j, matrix[i][j]);
                }
            }
        }

        clear();
    }
    //#endregion

}
