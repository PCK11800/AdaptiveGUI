package components;

import base.Component;
import base.Specifications;
import org.jsfml.graphics.Color;

import java.util.ArrayList;

public class Panel extends Component {

    private ArrayList<Component> components = new ArrayList<>();
    private int x, y, width, height;
    private boolean updated = false;

    public void setBounds(int x, int y, int width, int height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void add(Component component)
    {
        components.add(component);
    }

    public void remove(Component component)
    {
        components.remove(component);
    }

    @Override
    public void refresh(Frame frame)
    {
        if(!updated)
        {
            updateComponentsLocation();
        }

        for(Component component : components)
        {
            component.refresh(frame);
        }
    }

    private void updateComponentsLocation()
    {
        for(Component component : components)
        {
            component.setLocation(component.getX() + x, component.getY() + y);
        }
        updated = true;
    }
}
