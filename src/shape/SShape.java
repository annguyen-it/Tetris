package shape;

import java.util.Arrays;

public class SShape extends Shape {

    public SShape(int x, int y) {
        super(x, y, 6);
        var a = new int[][][]{
                {
                        { 6, 0 },
                        { 6, 6 },
                        { 0, 6 },
                },
                {
                        { 0, 6, 6 },
                        { 6, 6, 0 }
                }
        };

        states = Arrays.asList(a);
    }

    @Override
    public Shape copy() {
        return new SShape(x, y);
    }
}
