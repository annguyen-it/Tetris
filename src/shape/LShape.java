package shape;

import java.util.Arrays;

public class LShape extends Shape {

    public LShape(int x, int y) {
        super(x, y, 2);
        var a = new int[][][]{
                {
                        { 2, 0 },
                        { 2, 0 },
                        { 2, 2 }
                },
                {
                        { 2, 2, 2 },
                        { 2, 0, 0 }
                },
                {
                        { 2, 2 },
                        { 0, 2 },
                        { 0, 2 },
                },
                {
                        { 0, 0, 2 },
                        { 2, 2, 2 }
                }
        };

        states = Arrays.asList(a);
    }

    @Override
    public Shape copy() {
        return new LShape(x, y);
    }
}
