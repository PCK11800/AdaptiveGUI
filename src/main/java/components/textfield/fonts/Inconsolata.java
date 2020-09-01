package components.fonts;

import java.awt.*;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Objects;

public class Inconsolata extends org.jsfml.graphics.Font {

    public Inconsolata()
    {
        try{
            InputStream is = getClass()
                    .getClassLoader().getResourceAsStream("fonts/Inconsolata.ttf");
            loadFromStream(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
