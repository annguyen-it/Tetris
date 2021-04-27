package shape;

import java.util.Arrays;

public class IShape extends Shape {

    public IShape(int x, int y) {
        super(x, y, 1);
        var a = new int[][][]{
                {
                        { 1, 1, 1, 1 }
                },
                {
                        { 1 },
                        { 1 },
                        { 1 },
                        { 1 }
                }
        };

        states = Arrays.asList(a);
    }

    @Override
    public IShape copy() {
        return new IShape(x, y);
    }
}
