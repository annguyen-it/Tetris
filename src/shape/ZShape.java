package shape;

import java.awt.*;
import java.util.Arrays;

public class ZShape extends Shape {

    public ZShape() {
        super(new Color(235, 0, 0));
        var a = new int[][][]{
                {
                        {0, 7,},
                        {7, 7,},
                        {7, 0,},
                },
                {
                        { 7, 7, 0 },
                        { 0, 7, 7 },
                }
        };

        var b = new int[][][]{
                {
                        { 0, 0, 0, 0 },
                        { 0, 0, 7, 0 },
                        { 0, 7, 7, 0 },
                        { 0, 7, 0, 0 },
                },
                {
                        { 0, 0, 0, 0 },
                        { 0, 7, 7, 0 },
                        { 0, 0, 7, 7 },
                        { 0, 0, 0, 0 },
                }
        };

        states = Arrays.asList(a);
        template = Arrays.asList(b);

        generateState();
    }

}
