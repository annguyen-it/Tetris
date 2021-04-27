package shape;

import java.util.Arrays;

public class OShape extends Shape {

    public OShape(int x, int y) {
        super(x, y, 4);
        var a = new int[][][]{
                {
                        { 4, 4 },
                        { 4, 4 },
                }
        };

        states = Arrays.asList(a);
    }

    @Override
    public Shape copy() {
        return new OShape(x, y);
    }
}
