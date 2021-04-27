package shape;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.List;

public abstract class Shape {

    protected static List<int[][]> states;

    public static final List<Color> COLORS = new ArrayList<>(
            Arrays.asList(
                    null,
                    new Color(0, 233, 230),
                    new Color(233, 135, 0),
                    new Color(0, 235, 0),
                    new Color(233, 233, 0),
                    new Color(160, 0, 235),
                    new Color(0, 235, 0),
                    new Color(235, 0, 0)
            ));

    protected int state = 0;
    protected int x;
    protected int y;

    //    protected Color color;
    protected int colorCode;

    public Shape(int x, int y, int colorCode) {
        this.x = x;
        this.y = y;
        this.colorCode = colorCode;
    }

    public abstract Shape copy();

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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Color getColor() {
        return COLORS.get(colorCode);
    }

    public static Shape randomBlock() {
        switch (Math.abs(new Random().nextInt())%7) {
            case 0:
                return new IShape(0, 0);

            case 1:
                return new LShape(0, 0);

            case 2:
                return new JShape(0, 0);

            case 3:
                return new OShape(0, 0);

            case 4:
                return new TShape(0, 0);

            case 5:
                return new SShape(0, 0);

            default:
                return new ZShape(0, 0);
        }
    }
}
