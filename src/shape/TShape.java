package shape;

import java.util.Arrays;

public class TShape extends Shape {

    public TShape(int x, int y) {
        super(x, y, 5);
        var a = new int[][][]{
                {
                        { 5, 0 },
                        { 5, 5 },
                        { 5, 0 },
                },
                {
                        { 5, 5, 5 },
                        { 0, 5, 0 }
                },
                {
                        { 0, 5 },
                        { 5, 5 },
                        { 0, 5 }
                },
                {
                        { 0, 5, 0 },
                        { 5, 5, 5 }
                }
        };

        states = Arrays.asList(a);
    }

    @Override
    public Shape copy() {
        return new TShape(x, y);
    }
}
