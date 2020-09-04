package components.textarea.fonts;

import java.io.IOException;
import java.io.InputStream;

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
