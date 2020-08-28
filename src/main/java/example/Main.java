package example;

import base.Component;
import base.Specifications;
import components.Button;
import components.Frame;
import org.jsfml.graphics.Color;

public class Main
{
    public static void main(String[] args)
    {
        Frame frame = new Frame(1280, 720, "Test", 60);
        frame.setBackgroundColor(Specifications.DARK_GRAY);

        Component component = new Component();
        component.setFillColor(Color.RED);
        component.setSize(100, 100);
        component.setLocation(50, 50);
        component.setDirection(45);
        frame.add(component);

        Button button = new Button();
        button.setSize(100, 100);
        button.setLocation(100, 100);
        frame.add(button);

        frame.showFrame();
    }
}
