package components;

import base.Component;
import base.Specifications;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.system.Vector2i;
import org.jsfml.window.VideoMode;
import org.jsfml.window.WindowStyle;
import org.jsfml.window.event.Event;

import java.util.ArrayList;

public class Frame extends RenderWindow {

    private ArrayList<Component> components = new ArrayList<>();
    private Color backgroundColor = Specifications.DEFAULT_BACKGROUND_COLOR; //Default white

    public Frame(int width, int height, String name, int frameRate)
    {
        initFrame(width, height, name, frameRate);
    }

    private void initFrame(int width, int height, String name, int frameRate)
    {
        Specifications.DEFAULT_WIDTH = width;
        Specifications.DEFAULT_HEIGHT = height;
        create(new VideoMode(Specifications.DEFAULT_WIDTH, Specifications.DEFAULT_HEIGHT)
        , name, WindowStyle.DEFAULT);
        setFramerateLimit(frameRate);
    }

    public void setBackgroundColor(Color color)
    {
        backgroundColor = color;
    }

    public void add(Component component)
    {
        components.add(component);
    }

    public void remove(Component component)
    {
        components.remove(component);
        components.trimToSize();
    }

    private void refreshComponents()
    {
        for(Component component : components)
        {
            if(component.isVisible())
            {
                component.refresh(this);
            }
        }
    }

    private void frameStart()
    {
        clear(backgroundColor);
    }

    private void frameEnd()
    {
        display();
        for (Event event : pollEvents( ))
        {
            if (event.type == Event.Type.CLOSED) {
                close();
            }
        }
    }

    public void showFrame()
    {
        refresh();
    }

    private Vector2i handleResize(Vector2i size)
    {
        Specifications.CURRENT_SIZE = getSize();
        return getSize();
    }

    private void refresh()
    {
        Vector2i windowSize = getSize();

        while(isOpen())
        {
            frameStart();
            //Size Handling
            windowSize = handleResize(windowSize);
            refreshComponents();

            frameEnd();
        }
    }
}
