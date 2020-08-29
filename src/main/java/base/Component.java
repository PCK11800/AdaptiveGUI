package base;

import components.Frame;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;
import tools.ObjectSizeHandler;

import java.awt.geom.Line2D;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class Component extends RectangleShape {

    protected float x, y, width, height, xCenter, yCenter, direction;
    protected Texture texture;
    protected String texturePath;
    protected boolean isVisible = true; //Default true

    public void setTexture(String texturePath)
    {
        this.texturePath = texturePath;
        Path imagePath = FileSystems.getDefault().getPath(texturePath);
        texture = new Texture();
        try
        {
            texture.loadFromFile(imagePath);
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
     * @param direction
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

    public void setLocation(float x, float y)
    {
        this.x = x;
        this.y = y;
        centerObject();
        setPosition(x, y);
    }

    public void setSize(float width, float height)
    {
        Vector2f size = new Vector2f(width, height);
        this.width = width;
        this.height = height;
        setSize(size);
    }

    public void resize()
    {
        float[] scale = ObjectSizeHandler.scale();
        setLocation(getX() * scale[0], getY() * scale[1]);
        setSize(getWidth() * scale[0], getHeight() * scale[1]);
    }

    public float getCornerCoordinates(String corner, String type)
    {
        float cx, cy; //Center of square coordinates
        float x, y; //Coordinates of a corner point of a square
        float tempX, tempY;

        cx = getX();
        cy = getY();

        if(corner.equals("topleft"))
        {
            x = getX() - getWidth()/2;
            y = getY() - getHeight()/2;
        }

        else if(corner.equals("topright"))
        {
            x = getX() + getWidth()/2;
            y = getY() - getHeight()/2;
        }

        else if(corner.equals("bottomleft"))
        {
            x = getX() - getWidth()/2;
            y = getY() + getHeight()/2;
        }

        else if(corner.equals("bottomright"))
        {
            x = getX() + getWidth()/2;
            y = getY() + getHeight()/2;
        }

        else
        {
            x = 0;
            y = 0;
        }

        tempX = x - cx;
        tempY = y - cy;

        float rotatedX =
                (tempX * (float)Math.cos(Math.toRadians(direction)))
                - (tempY * (float)Math.sin(Math.toRadians(direction)));

        float rotatedY =
                (tempX * (float)Math.sin(Math.toRadians(direction)))
                + (tempY * (float)Math.cos(Math.toRadians(direction)));

        x = rotatedX + cx;
        y = rotatedY + cy;

        if(type.equals("x"))
        {
            return x;
        }

        else if(type.equals("y"))
        {
            return y;
        }

        else
        {
            return 0;
        }
    }

    /**
     * Returns an array holding the four lines of an object.
     * @return Line2D[top, bottom, left, right]
     */
    public Line2D[] getObjectBounds()
    {
        float x1, y1, x2, y2, x3, y3, x4, y4;

        x1 = this.getCornerCoordinates("topleft", "x");
        y1 = this.getCornerCoordinates("topleft", "y") * -1;
        x2 = this.getCornerCoordinates("topright", "x");
        y2 = this.getCornerCoordinates("topright", "y") * -1;
        x3 = this.getCornerCoordinates("bottomleft", "x");
        y3 = this.getCornerCoordinates("bottomleft", "y") * -1;
        x4 = this.getCornerCoordinates("bottomright", "x");
        y4 = this.getCornerCoordinates("bottomright", "y") * -1;

        //Lines of tank hull
        Line2D top = new Line2D.Float(x1, y1, x2, y2);
        Line2D bottom = new Line2D.Float(x3, y3, x4, y4);
        Line2D left = new Line2D.Float(x1, y1, x3, y3);
        Line2D right = new Line2D.Float(x2, y2, x4, y4);

        Line2D linesArray[] = new Line2D[4];
        linesArray[0] = top;
        linesArray[1] = bottom;
        linesArray[2] = left;
        linesArray[3] = right;

        return linesArray;
    }

    public boolean contains(float x, float y)
    {
        float topLeftCorner_x = getCornerCoordinates("topleft", "x");
        float topLeftCorner_y = getCornerCoordinates("topleft", "y");
        float bottomRightCorner_x = getCornerCoordinates("bottomright", "x");
        float bottomRightCorner_y = getCornerCoordinates("bottomright", "y");

        if(x >= topLeftCorner_x && x <= bottomRightCorner_x)
        {
            if(y <= bottomRightCorner_y && y >= topLeftCorner_y)
            {
                return true;
            }
        }
        return false;
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

    public float getX() { return x; }

    public void setX(float x) { this.x = x; }

    public float getY(){ return y; }

    public void setY(float y) { this.y = y; }

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

    public void refresh(Frame frame) { frame.draw(this); }
}
