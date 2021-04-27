package shape;

import java.awt.*;
import java.util.Arrays;

public class JShape extends Shape {

    public JShape() {
        super(new Color(0, 0, 232));
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

        var b = new int[][][]{
                {
                        { 0, 0, 0, 0 },
                        { 0, 0, 3, 0 },
                        { 0, 0, 3, 0 },
                        { 0, 3, 3, 0 },
                },
                {
                        { 0, 0, 0, 0 },
                        { 0, 3, 0, 0 },
                        { 0, 3, 3, 3 },
                        { 0, 0, 0, 0 },
                },
                {
                        { 0, 0, 0, 0 },
                        { 0, 3, 3, 0 },
                        { 0, 3, 0, 0 },
                        { 0, 3, 0, 0 },
                },
                {
                        { 0, 0, 0, 0 },
                        { 0, 3, 3, 3 },
                        { 0, 0, 0, 3 },
                        { 0, 0, 0, 0 },
                }
        };

        states = Arrays.asList(a);
        template = Arrays.asList(b);

        generateState();
    }

}
