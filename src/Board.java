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
    private static final int HEIGHT = 25;
    private static final int DELAY = 800;

    private Shape controllingBlock;

    private List<List<Integer>> MATRIX;
    private List<List<Integer>> LANDED;

    public Board() {
        super();
        initMatrix();
        createNewBlock();
        requestFocusInWindow();
        setFocusable(true);
        requestFocus();
        setVisible(true);

        addKeyListener(new Adapter(this));

        Timer timer = new Timer(DELAY, this);
        timer.start();
    }

    public Shape getControllingBlock() {
        return controllingBlock;
    }

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

    private void createNewBlock() {
        controllingBlock = Shape.randomBlock();
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

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        paintCell(g);
        paintBlock(g);
    }

    private void paintCell(Graphics g) {
        for (int i = 0; i < HEIGHT + 1; i++) {
            g.drawLine(0, i*BLOCK_SIZE, WIDTH*BLOCK_SIZE, i*BLOCK_SIZE);
        }

        for (int i = 0; i < WIDTH + 1; i++) {
            g.drawLine(i*BLOCK_SIZE, 0, i*BLOCK_SIZE, HEIGHT*BLOCK_SIZE);
        }
    }

    private void paintBlock(Graphics g) {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                int cell = MATRIX.get(i).get(j);
                if (cell != 0) {
                    g.setColor(Shape.COLORS.get(cell));
                    g.fillRect(j*BLOCK_SIZE, i*BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        performFall();
    }

    public void performFall() {
        if (!willBottomOverlapped() && !willLanded()) {
            controllingBlock.fall();
        }
        else {
            merge();
            controllingBlock = Shape.randomBlock();
        }

        update();
    }

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

    private void clear() {
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
}
