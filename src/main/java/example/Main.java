package example;

import base.Specifications;
import components.Frame;

public class Main
{
    public static void main(String[] args)
    {
        Frame frame = new Frame(1280, 720, "Test", 60);
        frame.setBackgroundColor(Specifications.DARK_GRAY);
        frame.showFrame();
    }
}
