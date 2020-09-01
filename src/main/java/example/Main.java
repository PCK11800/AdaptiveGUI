package example;

import base.Component;
import base.Specifications;
import components.Button;
import components.Frame;
import components.Panel;
import components.textfield.TextField;
import org.jsfml.graphics.Color;
import tools.OSHandler.OSChecker;

public class Main
{
    public static void main(String[] args)
    {
        OSChecker.handleLinux();
        Frame frame = new Frame(800, 500, "Test", 1000);
        frame.setBackgroundColor(Specifications.DARK_GRAY);

        Panel panel = new Panel();
        panel.setBounds(0, 0, 800, 500);
        panel.setVisible(true);
        frame.add(panel);

        TextField component = new TextField();
        component.setFillColor(Specifications.TRANSPARENT);
        component.setBounds(0, 0, 400, 400);
        component.setOutline(Specifications.WARM_WHITE, 1);
        component.setFontSize(15);
        panel.add(component);

        TextField component2 = new TextField();
        component2.setFillColor(Specifications.TRANSPARENT);
        component2.setBounds(400, 0, 400, 400);
        component2.setOutline(Specifications.WARM_WHITE, 1);
        component2.setFontSize(15);
        panel.add(component2);

        Component component3 = new Component();
        component3.setFillColor(Color.RED);
        component3.setBounds(0, 400, 800, 100);
        panel.add(component3);

        frame.showFrame();
    }
}
