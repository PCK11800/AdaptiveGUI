package components.textfield.legacy;

import base.Component;
import base.Specifications;
import components.Frame;
import components.textfield.TextMouseCursor;
import components.textfield.fonts.Inconsolata;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.Text;
import org.jsfml.system.Vector2i;
import org.jsfml.window.Mouse;

import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TextField extends Component {

    private Text content = new Text();
    private StringBuilder content_str = new StringBuilder();
    private StringBuilder content_raw = new StringBuilder();

    private int fontSize = Specifications.DEFAULT_FONT_SIZE;
    private Color fontColor = Specifications.DEFAULT_FONT_COLOR;
    private TextMouseCursor textMouseCursor = new TextMouseCursor();
    private boolean isCursor = false;
    private float spaceWidth, spaceHeight;

    private Component caret = new Component();

    private static ArrayList<TextField> textFieldArrayList = new ArrayList<>();

    public TextField()
    {
        try{
            textFieldArrayList.add(this);

            GlobalScreen.registerNativeHook();
            TextFieldKeyboardListener listener = new TextFieldKeyboardListener(this);
            GlobalScreen.addNativeKeyListener(listener);
            Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
            logger.setLevel(Level.OFF);

            setContent();
            getSpaceDimensions();
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

    private void setCursor(Frame frame)
    {
        Vector2i mousePos = Mouse.getPosition(frame);
        isCursor = contains(mousePos.x, mousePos.y);

        boolean allCursorOn = false;
        for(TextField textField : textFieldArrayList)
        {
            if (textField.isCursor) {
                allCursorOn = true;
                break;
            }
        }

        if(allCursorOn)
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

    public void setText(String text)
    {
        content.setPosition(getPosition());
        content.setCharacterSize(fontSize);
        content.setColor(fontColor);
        content.setString(text);
    }

    public void handleFocus(Frame frame)
    {
        Vector2i mousePos = Mouse.getPosition(frame);
        if (Mouse.isButtonPressed(Mouse.Button.LEFT))
        {
            isFocused = contains(mousePos.x, mousePos.y);
        }
    }

    public void append(String input)
    {
        //Handle backspace
        if(input.equals("\b") && content_str.length() > 0)
        {
            content_str.deleteCharAt(content_str.length() - 1);
            content_raw.deleteCharAt(content_raw.length() - 1);
        }
        else
        {
            //Handle string overflow
            if(content.getLocalBounds().width + fontSize > getWidth())
            {
                String lastWord = content_str.toString().substring(
                        content_str.toString().lastIndexOf(" ") + 1);

                content_str.delete(content_str.toString().lastIndexOf(" "), content_str.toString().length());
                content_str.append("\n");
                content_str.append(lastWord);
            }
            content_str.append(input);
            content_raw.append(input);
        }
        setText(content_str.toString());
    }

    public void loadTextFromFile(File file)
    {
        try{
            BufferedReader reader = new BufferedReader(new FileReader(file));
            int num = 0;
            char c;
            while((num = reader.read()) != -1)
            {
                c = (char) num;
                append(Character.toString(c));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getSpaceDimensions()
    {
        content.setString("H");
        spaceWidth = content.getLocalBounds().width;
        spaceHeight = content.getLocalBounds().height;
        content.setString("");
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

    public boolean isCursor()
    {
        return isCursor;
    }

    public String getContent()
    {
        return content_raw.toString();
    }

    public void resize()
    {
        if(content.getLocalBounds().height > getHeight())
        {
            setHeight(content.getLocalBounds().height);
        }
    }

    private void setCaret(Frame frame)
    {
        float lastCharacterPosition_x = 0;
        float lastCharacterPosition_y = 0;

        if(content_str.length() > 0)
        {
             lastCharacterPosition_x = content.findCharacterPos(content.getString().length() - 1).x;
             lastCharacterPosition_y = content.findCharacterPos(content.getString().length() - 1).y;
        }

        float caretHeight = 10;
        float caretPosition_x = lastCharacterPosition_x + spaceWidth;
        float caretPosition_y = lastCharacterPosition_y + (caretHeight / 2);
        caret.setBounds(caretPosition_x, caretPosition_y, 2, caretHeight);
        caret.setFillColor(Specifications.WARM_WHITE);

        if(isFocused)
        {
            frame.draw(caret);
        }
    }

    @Override
    public void refresh(Frame frame)
    {
        resize();
        setCursor(frame);
        handleFocus(frame);
        frame.draw(content);
        setCaret(frame);

        frame.draw(this);
    }
}
