package components;

import base.Component;
import base.Specifications;
import org.jsfml.graphics.Color;
import org.jsfml.system.Vector2i;
import org.jsfml.window.Mouse;

public class Button extends Component {

    private Color onScreen = Specifications.DEFAULT_BUTTON_ONSCREEN;
    private Color onHover = Specifications.DEFAULT_BUTTON_ONHOVER;
    private Color onClick = Specifications.DEFAULT_BUTTON_ONCLICK;
    private Color borderColor = Specifications.DEFAULT_BUTTON_ONCLICK;

    private boolean pressed = false; //Default false
    private Runnable action;

    public Button()
    {
        setFillColor(onScreen);
        setOutlineColor(borderColor);
        setOutlineThickness(Specifications.DEFAULT_BORDER_WIDTH);
    }

    public void setButtonColor(Color onScreen, Color onHover, Color onClick) {
        this.onScreen = onScreen;
        this.onHover = onHover;
        this.onClick = onClick;
        this.borderColor = onClick;

        setFillColor(onScreen);
    }

    public void setOnScreenColor(Color color)
    {
        this.onScreen = color;
        setFillColor(color);
    }

    public void setOnHoverColor(Color color)
    {
        this.onHover = color;
    }

    public void setOnClickColor(Color color)
    {
        this.onClick = color;
    }

    public void setOutline(Color color, float width)
    {
        this.borderColor = color;
        setOutlineColor(color);
        setOutlineThickness(width);
    }

    private void handleButton(Frame frame)
    {
        Vector2i mousePos = Mouse.getPosition(frame);
        if(contains(mousePos.x, mousePos.y))
        {
            if(Mouse.isButtonPressed(Mouse.Button.LEFT))
            {
                if(action != null)
                {
                    if(pressed == false)
                    {
                        action.run();
                        pressed = true;
                    }
                }
                setFillColor(onClick);
            }
            else{
                setFillColor(onHover);
                pressed = false;
            }
        }
        else{
            setFillColor(onScreen);
            pressed = false;
        }
    }

    public void setPressed(Runnable run)
    {
        action = run;
    }

    @Override
    public void refresh(Frame frame)
    {
        handleButton(frame);
        frame.draw(this);
    }

}
