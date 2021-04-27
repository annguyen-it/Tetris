package shape;

import java.awt.*;
import java.util.Arrays;

public class TShape extends Shape {

    public TShape() {
        super(new Color(160, 0, 235));
        var a = new int[][][]{
                {
                        { 5, 0 },
                        { 5, 5 },
                        { 5, 0 },
                },
                {
                        { 5, 5, 5 },
                        { 0, 5, 0 }
                },
                {
                        { 0, 5 },
                        { 5, 5 },
                        { 0, 5 }
                },
                {
                        { 0, 5, 0 },
                        { 5, 5, 5 }
                }
        };

        var b = new int[][][]{
                {
                        { 0, 0, 0, 0 },
                        { 0, 5, 0, 0 },
                        { 0, 5, 5, 0 },
                        { 0, 5, 0, 0 },
                },
                {
                        { 0, 0, 0, 0 },
                        { 5, 5, 5, 0 },
                        { 0, 5, 0, 0 },
                        { 0, 0, 0, 0 },
                },
                {
                        { 0, 0, 0, 0 },
                        { 0, 0, 5, 0 },
                        { 0, 5, 5, 0 },
                        { 0, 0, 5, 0 },
                },
                {
                        { 0, 0, 0, 0 },
                        { 0, 5, 0, 0 },
                        { 5, 5, 5, 0 },
                        { 0, 0, 0, 0 },
                }
        };

        states = Arrays.asList(a);
        template = Arrays.asList(b);

        generateState();
    }

}
