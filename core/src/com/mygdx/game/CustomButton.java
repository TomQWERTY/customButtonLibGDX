package com.mygdx.game;

public class CustomButton {
    float[] colorInsideRGB;
    float[] colorFrameRGB;
    int width, height, x, y;
    String text;
    public CustomButton(int x, int y, int width, int height, String text,
                        float colorInR, float colorInG, float colorInB,
                        float colorFrR, float colorFrG, float colorFrB)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = text;
        colorInsideRGB = new float[3];
        colorInsideRGB[0] = colorInR;
        colorInsideRGB[1] = colorInG;
        colorInsideRGB[2] = colorInB;
        colorFrameRGB = new float[3];
        colorFrameRGB[0] = colorFrR;
        colorFrameRGB[1] = colorFrG;
        colorFrameRGB[2] = colorFrB;
    }

    public void setText(String newText)
    {
        text = newText;
    }

    public float[] getInsideRGB()
    {
        return colorInsideRGB;
    }
    public float[] getFrameRGB()
    {
        return colorFrameRGB;
    }
    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }
    public int getHeight()
    {
        return height;
    }
    public int getWidth()
    {
        return width;
    }
    public String getText()
    {
        return text;
    }
    public void setHeight(int newHeight)
    {
        height = newHeight;
    }
    public void setWidth(int newWidth)
    {
        width = newWidth;
    }
}
