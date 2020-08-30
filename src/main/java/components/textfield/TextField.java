package components.textfield;

import base.Component;
import components.Frame;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class TextField extends Component {

    public TextField()
    {
        try{
            GlobalScreen.registerNativeHook();
            TextFieldKeyboardListener listener = new TextFieldKeyboardListener(this);
            GlobalScreen.addNativeKeyListener(listener);
            Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
            logger.setLevel(Level.OFF);
        } catch (NativeHookException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void refresh(Frame frame)
    {
        frame.draw(this);
    }
}
