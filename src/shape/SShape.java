package shape;

import java.awt.*;
import java.util.Arrays;

public class SShape extends Shape {

    public SShape() {
        super(new Color(0, 235, 0));
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

        var b = new int[][][]{
                {
                        { 0, 0, 0, 0 },
                        { 0, 6, 0, 0 },
                        { 0, 6, 6, 0 },
                        { 0, 0, 6, 0 },
                },
                {
                        { 0, 0, 0, 0 },
                        { 0, 6, 6, 0 },
                        { 6, 6, 0, 0 },
                        { 0, 0, 0, 0 },
                }
        };

        states = Arrays.asList(a);
        template = Arrays.asList(b);

        generateState();
    }

}
