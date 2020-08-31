package example;

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
        Frame frame = new Frame(1280, 720, "Test", 60);
        frame.setBackgroundColor(Specifications.DARK_GRAY);

        TextField component = new TextField();
        component.setFillColor(Specifications.TRANSPARENT);
        component.setBounds(50, 50, 100, 100);
        component.setRotation(45);

        Button button = new Button();
        button.setBounds(100, 100, 100, 100);
        button.setOutline(Specifications.WARM_WHITE, 3);
        button.setPressed(new Runnable() {
            @Override
            public void run() {
                if(component.getFillColor() == Color.GREEN) {
                    component.setFillColor(Color.RED);
                }
                else{
                    component.setFillColor(Color.GREEN);
                }
            }
        });

        Panel panel = new Panel();
        panel.setBounds(200, 50, 500, 500);
        panel.add(component);
        panel.add(button);
        panel.setOutline(Specifications.WARM_WHITE, 3);
        panel.setVisible(true);
        frame.add(panel);

        frame.showFrame();
    }
}
