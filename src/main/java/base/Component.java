package base;

import components.Frame;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;
import tools.ObjectSizeHandler;

import java.awt.geom.Line2D;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class Component extends RectangleShape {

    protected float width, height, xCenter, yCenter, direction;
    protected Texture texture;
    protected String texturePath;
    protected boolean isVisible = true; //Default true
    protected boolean isFocused = false; //Default false

    public static final int TOP_LEFT = 0;
    public static final int TOP_RIGHT = 2;
    public static final int BOTTOM_LEFT = 4;
    public static final int BOTTOM_RIGHT = 6;
    public static final int X = 0;
    public static final int Y = 1;

    public void setTexture(Path path)
    {
        texture = new Texture();
        try
        {
            texture.loadFromFile(path);
            texture.setSmooth(true);
            super.setTexture(texture);

            width = getTextureWidth();
            height = getTextureHeight();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Moves the centroid to the center of the object.
     */
    private void centerObject()
    {
        this.xCenter = getWidth() / 2;
        this.yCenter = getHeight() / 2;
        setOrigin(xCenter, yCenter);
    }

    /**
     * Rotates the object to a specific direction.
     * Direction is from 0 to 360 starting north.
     * @param direction Direction in which the top of the component is facing
     */
    @Override
    public void setRotation(float direction)
    {
        centerObject();
        this.direction = direction;

        if(this.direction >= 360)
        {
            this.direction = this.direction - 360;
        }

        else if(this.direction < 0)
        {
            this.direction = this.direction + 360;
        }
        super.setRotation(direction);
    }

    public void setSize(float width, float height)
    {
        Vector2f size = new Vector2f(width, height);
        this.width = width;
        this.height = height;
        super.setSize(size);
    }

    public void setBounds(float x, float y, float width, float height)
    {
        setPosition(x, y);
        setSize(width, height);
    }

    public float getCornerCoordinates(int corner, int type)
    {
        float[] scale = ObjectSizeHandler.scale();
        int corner_type = corner + type;
        float cornerCoordinates;
        switch(corner_type){
            case 0:
            case 4:
                cornerCoordinates = getX() * scale[0];
                break;
            case 1:
            case 3:
                cornerCoordinates = getY() * scale[1];
                break;
            case 2:
            case 6:
                cornerCoordinates = (getX() + width) * scale[0];
                break;
            case 5:
            case 7:
                cornerCoordinates = (getY() + height) * scale[1];
                break;
            default:
                cornerCoordinates = -1;
                break;
        }
        return cornerCoordinates;
    }

    /**
     * Returns an array holding the four lines of an object.
     * @return Line2D[top, bottom, left, right]
     */
    public Line2D[] getObjectBounds()
    {
        float x1, y1, x2, y2, x3, y3, x4, y4;

        x1 = getCornerCoordinates(TOP_LEFT, X);
        y1 = getCornerCoordinates(TOP_LEFT, Y) * -1;
        x2 = getCornerCoordinates(TOP_RIGHT, X);
        y2 = getCornerCoordinates(TOP_RIGHT, Y) * -1;
        x3 = getCornerCoordinates(BOTTOM_LEFT, X);
        y3 = getCornerCoordinates(BOTTOM_LEFT, Y) * -1;
        x4 = getCornerCoordinates(BOTTOM_RIGHT, X);
        y4 = getCornerCoordinates(BOTTOM_RIGHT, Y) * -1;

        //Lines of tank hull
        Line2D top = new Line2D.Float(x1, y1, x2, y2);
        Line2D bottom = new Line2D.Float(x3, y3, x4, y4);
        Line2D left = new Line2D.Float(x1, y1, x3, y3);
        Line2D right = new Line2D.Float(x2, y2, x4, y4);

        Line2D[] linesArray = new Line2D[4];
        linesArray[0] = top;
        linesArray[1] = bottom;
        linesArray[2] = left;
        linesArray[3] = right;

        return linesArray;
    }

    public boolean contains(float x, float y)
    {
        float topLeftCorner_x = getCornerCoordinates(TOP_LEFT, X);
        float topLeftCorner_y = getCornerCoordinates(TOP_LEFT, Y);
        float bottomRightCorner_x = getCornerCoordinates(BOTTOM_RIGHT, X);
        float bottomRightCorner_y = getCornerCoordinates(BOTTOM_RIGHT, Y);

        if(x >= topLeftCorner_x && x <= bottomRightCorner_x)
        {
            return y <= bottomRightCorner_y && y >= topLeftCorner_y;
        }
        return false;
    }

    public void setOutline(Color color, float width)
    {
        setOutlineColor(color);
        setOutlineThickness(width);
    }

    public void draw(Frame f) { f.draw(this); }

    public float getTextureWidth()
    {
        return getTexture().getSize().x;
    }

    public float getTextureHeight()
    {
        return getTexture().getSize().y;
    }

    public float getX() { return getPosition().x; }

    public void setX(float x) { setPosition(x, getPosition().y); }

    public float getY(){ return getPosition().y; }

    public void setY(float y) { setPosition(getPosition().x, y); }

    public float getWidth() { return width; }

    public void setWidth(float width) { this.width = width; }

    public float getHeight() { return height; }

    public void setHeight(float height) { this.height = height; }

    public float getxCenter() { return xCenter; }

    public void setxCenter(float xCenter) { this.xCenter = xCenter; }

    public float getyCenter() { return yCenter; }

    public void setyCenter(float yCenter) { this.yCenter = yCenter; }

    public float getDirection() { return direction; }

    public String getTexturePath() { return texturePath; }

    public void setVisible(boolean isVisible)
    {
        this.isVisible = isVisible;
    }

    public boolean isVisible() { return isVisible; }

    public boolean isFocused() {
        return isFocused;
    }

    public void setFocused(boolean focused) {
        isFocused = focused;
    }

    public void refresh(Frame frame) { frame.draw(this); }
}
