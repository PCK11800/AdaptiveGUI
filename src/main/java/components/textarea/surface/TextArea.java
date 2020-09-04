package components.textfield;

import base.Component;
import base.Specifications;
import components.Frame;
import components.textfield.fonts.Inconsolata;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.Text;
import org.jsfml.system.Vector2i;
import org.jsfml.window.Mouse;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TextArea extends Component {
    /*
     * Total rewrite, from the ground up, of TextField
     * A new design is required.
     */

    private static ArrayList<TextArea> textAreaArrayList = new ArrayList<>();
    private Sheet sheet;

    private TextMouseCursor textMouseCursor = new TextMouseCursor();
    private boolean cursorVisible = false;

    private Text content = new Text();
    private int fontSize = Specifications.DEFAULT_FONT_SIZE;
    private Color fontColor = Specifications.DEFAULT_FONT_COLOR;
    private float spaceWidth, spaceHeight;

    private Component caret = new Component();

    public TextArea()
    {
        textAreaArrayList.add(this);
        initKeyListener();
        setContent();
        setDimension();
    }

    @Override
    public void setBounds(float x, float y, float width, float height)
    {
        super.setBounds(x, y, width, height);
    }

    private void initKeyListener()
    {
        try{
            GlobalScreen.registerNativeHook();
            KeyListener listener = new KeyListener(this);
            GlobalScreen.addNativeKeyListener(listener);
            Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
            logger.setLevel(Level.OFF);
        } catch (NativeHookException e) {
            e.printStackTrace();
        }
    }

    private void setContent()
    {
        content.setPosition(getPosition());
        content.setCharacterSize(fontSize);
        content.setColor(fontColor);
        content.setFont(new Inconsolata());
    }

    private void setDimension()
    {
        content.setString("H");
        spaceWidth = content.getLocalBounds().width;
        spaceHeight = content.getLocalBounds().height;
        content.setString("");
    }

    private void updateCursor(Frame frame)
    {
        Vector2i mousePos = Mouse.getPosition(frame);
        cursorVisible = contains(mousePos.x, mousePos.y);

        boolean isCursorVisibleAnywhere = false;
        for(TextArea textArea : textAreaArrayList)
        {
            if(textArea.isCursorVisible())
            {
                isCursorVisibleAnywhere = true;
                break;
            }
        }

        if(isCursorVisibleAnywhere)
        {
            frame.setMouseCursorVisible(false);
            textMouseCursor.setVisible(true);
            textMouseCursor.refresh(frame);
        }
        else{
            frame.setMouseCursorVisible(true);
            textMouseCursor.setVisible(false);
        }
    }

    public void handleFocus(Frame frame)
    {
        Vector2i mousePos = Mouse.getPosition(frame);
        if (Mouse.isButtonPressed(Mouse.Button.LEFT))
        {
            isFocused = contains(mousePos.x, mousePos.y);
        }
    }

    public void setText(Sheet sheet)
    {
        content.setPosition(getPosition());
        content.setCharacterSize(fontSize);
        content.setColor(fontColor);

    }

    public void append(char character)
    {

    }

    public void handleCaret(Frame frame)
    {
        /*
        float caret_x = column * spaceWidth;
        float caret_y = row * spaceHeight;
        caret.setBounds(caret_x, caret_y + 5, 2, 10);
        caret.setFillColor(Specifications.WARM_WHITE);
        if(isFocused)
        {
            frame.draw(caret);
        }
        */
    }

    @Override
    public void refresh(Frame frame)
    {
        handleFocus(frame);
        updateCursor(frame);
        handleCaret(frame);
        frame.draw(content);
        frame.draw(this);
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public Color getFontColor() {
        return fontColor;
    }

    public void setFontColor(Color fontColor) {
        this.fontColor = fontColor;
    }

    public boolean isCursorVisible()
    {
        return cursorVisible;
    }

}
