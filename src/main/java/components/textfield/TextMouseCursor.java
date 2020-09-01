package components.textfield;

import base.Component;
import components.Frame;
import org.jsfml.system.Vector2i;
import org.jsfml.window.Mouse;

import java.io.InputStream;

public class TextMouseCursor extends Component {

    public TextMouseCursor()
    {
        InputStream is = getClass()
                .getClassLoader().getResourceAsStream("cursors/textcursor.png");
        setTexture(is);
        setSize(15, 15);
    }

    @Override
    public void refresh(Frame frame)
    {
        Vector2i mousePos = Mouse.getPosition(frame);
        setPosition(mousePos.x, mousePos.y);
        frame.draw(this);
    }
}
