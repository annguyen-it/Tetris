package shape;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.List;

public abstract class Shape {

    public List<int[][]> states;
    public List<int[][]> template;

    private final Color color;

    public static final List<Color> COLORS = new ArrayList<>(
            Arrays.asList(
                    null,
                    new Color(0, 233, 230),
                    new Color(233, 135, 0),
                    new Color(0, 0, 232),
                    new Color(233, 233, 0),
                    new Color(160, 0, 235),
                    new Color(0, 235, 0),
                    new Color(235, 0, 0)
            ));

    protected int state;
    protected int x = 7;
    protected int y = 0;

    public Shape(Color color) {
        this.color = color;
    }

    protected void generateState() {
        state = (int) (Math.random()*states.size());
    }

    public void fall() {
        y++;
    }

    public void rotate() {
        state++;
        if (state >= states.size()) {
            state = 0;
        }
    }

    public void moveLeft() {
        x--;
    }

    public void moveRight() {
        x++;
    }

    public int getWidth() {
        return getBlockMatrix()[0].length;
    }

    public int getHeight() {
        return getBlockMatrix().length;
    }

    public int[][] getBlockMatrix() {
        return states.get(state);
    }

    public int[][] getTemplateMatrix() {
        return template.get(state);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Color getColor() {
        return color;
    }

    public static Shape randomBlock() {
        switch (Math.abs(new Random().nextInt())%7) {
            case 0:
                return new IShape();

            case 1:
                return new LShape();

            case 2:
                return new JShape();

            case 3:
                return new OShape();

            case 4:
                return new TShape();

            case 5:
                return new SShape();

            default:
                return new ZShape();
        }
    }
}
