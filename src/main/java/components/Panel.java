package components;

import base.Component;
import base.Specifications;
import org.jsfml.graphics.Color;

import java.util.ArrayList;

public class Panel extends Component {

    private ArrayList<Component> components = new ArrayList<>();
    private boolean updated = false;

    public Panel()
    {
        setFillColor(Specifications.TRANSPARENT);
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
            frame.draw(this);
            component.refresh(frame);
        }
    }

    private void updateComponentsLocation()
    {
        for(Component component : components)
        {
            component.setPosition(component.getX() + getX(), component.getY() + getY());
        }
        updated = true;
    }
}
