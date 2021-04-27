package shape;

import java.awt.*;
import java.util.Arrays;

public class LShape extends Shape {

    public LShape() {
        super(new Color(233, 135, 0));
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

        var b = new int[][][]{
                {
                        { 0, 0, 0, 0 },
                        { 0, 2, 0, 0 },
                        { 0, 2, 0, 0 },
                        { 0, 2, 2, 0 }
                },
                {
                        { 0, 0, 0, 0 },
                        { 2, 2, 2, 0 },
                        { 2, 0, 0, 0 },
                        { 0, 0, 0, 0 },

                },
                {
                        { 0, 0, 0, 0 },
                        { 0, 2, 2, 0 },
                        { 0, 0, 2, 0 },
                        { 0, 0, 2, 0 },
                },
                {
                        { 0, 0, 0, 0 },
                        { 0, 0, 2, 0 },
                        { 2, 2, 2, 0 },
                        { 0, 0, 0, 0 },
                }
        };

        states = Arrays.asList(a);
        template = Arrays.asList(b);

        generateState();
    }

}
