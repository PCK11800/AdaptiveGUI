package example;

import base.Specifications;
import components.Frame;
import components.Panel;
import components.textarea.surface.TextArea;
import tools.OSHandler.OSChecker;

public class Main
{
    public static void main(String[] args)
    {
        OSChecker.handleLinux();
        Frame frame = new Frame(200, 500, "Test", 1000);
        frame.setBackgroundColor(Specifications.DARK_GRAY);

        Panel panel = new Panel();
        panel.setBounds(0, 0, 200, 500);
        panel.setVisible(true);
        frame.add(panel);

        TextArea textArea = new TextArea();
        textArea.setBounds(0, 0, 200, 500);
        textArea.setFillColor(Specifications.TRANSPARENT);
        textArea.setFontSize(16);
        textArea.setFontColor(Specifications.WARM_WHITE);
        panel.add(textArea);

        frame.showFrame();
    }
}
