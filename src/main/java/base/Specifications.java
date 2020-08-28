package base;

import org.jsfml.graphics.Color;
import org.jsfml.system.Vector2i;

public class Specifications {

    public static int DEFAULT_WIDTH = 1280;
    public static int DEFAULT_HEIGHT = 720;

    public static Vector2i CURRENT_SIZE = null;
    public static Vector2i PREVIOUS_SIZE = new Vector2i(DEFAULT_WIDTH, DEFAULT_HEIGHT);

    public static final Color DEFAULT_BACKGROUND_COLOR = new Color(255, 255, 255, 0);
    public static final Color DARK_GRAY = new Color(32, 32, 32, 255);
    public static final Color WARM_WHITE = new Color(190, 190, 190, 255);

    //Button Defaults
    public static final Color DEFAULT_BUTTON_ONSCREEN = DARK_GRAY;
    public static final Color DEFAULT_BUTTON_ONHOVER = DARK_GRAY;
    public static final Color DEFAULT_BUTTON_ONCLICK = WARM_WHITE;
    public static final float DEFAULT_BORDER_WIDTH = 1;
}
