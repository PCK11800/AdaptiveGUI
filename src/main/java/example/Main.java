package example;

import base.Component;
import base.Specifications;
import components.Button;
import components.Frame;
import components.Panel;
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
        component.setRotation(45);

        Button button = new Button();
        button.setSize(100, 100);
        button.setLocation(100, 100);
        button.setOutline(Specifications.WARM_WHITE, 3);

        Panel panel = new Panel();
        panel.setBounds(200, 50, 50, 50);
        panel.add(component);
        panel.add(button);
        frame.add(panel);

        frame.showFrame();
    }
}
