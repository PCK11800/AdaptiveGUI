package tools;

import base.Specifications;

import static base.Specifications.*;

public class ObjectSizeHandler {

    public static float[] scale()
    {
        float width_mult = (float) CURRENT_SIZE.x / (float) DEFAULT_WIDTH;
        float height_mult = (float) CURRENT_SIZE.y / (float) DEFAULT_HEIGHT;
        return new float[]{width_mult, height_mult};
    }
}
