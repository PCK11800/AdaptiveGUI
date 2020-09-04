package components.textarea.surface;

import base.Component;
import components.Frame;
import org.jsfml.system.Vector2i;
import org.jsfml.window.Mouse;

import java.nio.file.Path;
import java.nio.file.Paths;

public class TextMouseCursor extends Component {

    public TextMouseCursor()
    {
        Path path = Paths.get(getClass()
                .getClassLoader().getResource("cursors/textcursor.png").getPath());
        setTexture(path);
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
