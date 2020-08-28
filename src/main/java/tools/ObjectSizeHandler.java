package tools;

import base.Specifications;

import static base.Specifications.*;

public class ObjectSizeHandler {

    public static float[] scale()
    {
        float width_mult = (float) CURRENT_SIZE.x / (float) PREVIOUS_SIZE.x;
        float height_mult = (float) CURRENT_SIZE.y / (float) PREVIOUS_SIZE.y;
        return new float[]{width_mult, height_mult};
    }
}
