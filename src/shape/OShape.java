package shape;

import java.awt.*;
import java.util.Arrays;

public class OShape extends Shape {

    public OShape() {
        super(new Color(233, 233, 0));
        var a = new int[][][]{
                {
                        { 4, 4 },
                        { 4, 4 },
                }
        };

        var b = new int[][][]{
                {
                        { 0, 0, 0, 0 },
                        { 0, 4, 4, 0 },
                        { 0, 4, 4, 0 },
                        { 0, 0, 0, 0 },
                }
        };

        states = Arrays.asList(a);
        template = Arrays.asList(b);

        generateState();
    }

}
