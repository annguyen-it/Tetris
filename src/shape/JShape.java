package shape;

import java.util.Arrays;

public class JShape extends Shape {

    public JShape(int x, int y) {
        super(x, y, 3);
        var a = new int[][][]{
                {
                        { 0, 3 },
                        { 0, 3 },
                        { 3, 3 }
                },
                {
                        { 3, 0, 0 },
                        { 3, 3, 3 }
                },
                {
                        { 3, 3 },
                        { 3, 0 },
                        { 3, 0 },
                },
                {
                        { 3, 3, 3 },
                        { 0, 0, 3 }
                }
        };

        states = Arrays.asList(a);
    }

    @Override
    public Shape copy() {
        return new JShape(x, y);
    }
}
