package shape;

import java.awt.*;
import java.util.Arrays;

public class IShape extends Shape {

    public IShape() {
        super(new Color(0, 233, 230));

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

        var b = new int[][][]{
                {
                        { 0, 0, 0, 0 },
                        { 0, 0, 0, 0 },
                        { 1, 1, 1, 1 },
                        { 0, 0, 0, 0 },
                },
                {
                        { 0, 0, 1, 0 },
                        { 0, 0, 1, 0 },
                        { 0, 0, 1, 0 },
                        { 0, 0, 1, 0 },
                },
        };

        states = Arrays.asList(a);
        template = Arrays.asList(b);

        generateState();
    }

}
