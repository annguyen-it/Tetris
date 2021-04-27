package shape;

import java.util.Arrays;

public class ZShape extends Shape {

    public ZShape(int x, int y) {
        super(x, y, 7);
        var a = new int[][][]{
                {
                        { 0, 7 },
                        { 7, 7 },
                        { 7, 0 },
                },
                {
                        { 7, 7, 0 },
                        { 0, 7, 7 }
                }
        };

        states = Arrays.asList(a);
    }

    @Override
    public Shape copy() {
        return new ZShape(x, y);
    }
}
