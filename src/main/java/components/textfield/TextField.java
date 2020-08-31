package components.textfield;

import base.Component;
import base.Specifications;
import components.Frame;
import components.fonts.Inconsolata;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jsfml.graphics.Text;

import java.util.logging.Level;
import java.util.logging.Logger;

public class TextField extends Component {

    private Text content = new Text();
    private StringBuilder content_str = new StringBuilder();
    TextFieldKeyboardListener listener = new TextFieldKeyboardListener(this);

    public TextField()
    {
        try{
            GlobalScreen.registerNativeHook();
            GlobalScreen.addNativeKeyListener(listener);
            Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
            logger.setLevel(Level.OFF);
        } catch (NativeHookException e) {
            e.printStackTrace();
        }
    }

    public void setText(String text)
    {
        content.setPosition(getPosition());
        content.setCharacterSize(16);
        content.setColor(Specifications.WARM_WHITE);
        content.setFont(new Inconsolata());
        content.setString(text);
    }

    public void append(String str)
    {
        if(str.equals("\b"))
        {
            content_str.deleteCharAt(content_str.length() - 1);
        }
        else
        {
            content_str.append(str);
        }

        setText(content_str.toString());
    }

    @Override
    public void refresh(Frame frame)
    {
        frame.draw(this);
        frame.draw(content);
    }
}
