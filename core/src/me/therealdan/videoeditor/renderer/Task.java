package me.therealdan.videoeditor.renderer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Task {

    private boolean color = false;
    private boolean rect = false;
    private boolean rectOutline = false;
    private boolean line = false;

    private float r, g, b, a;
    private float x, y, x1, y1;
    private float width, height;
    private float textWidth, textHeight;
    private String text;
    private int fontSize;
    private TextPosition textPosition;

    public Task(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Task(Color color) {
        setColor(color);
    }

    public Task(float r, float g, float b, float a) {
        setColor(r, g, b, a);
    }

    public Task setColor(Color color) {
        return setColor(color.r, color.g, color.b, color.a);
    }

    public Task setColor(float r, float g, float b, float a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
        this.color = true;
        return this;
    }

    public Task text(String text) {
        return text(text, TextPosition.DEFAULT);
    }

    public Task text(String text, TextPosition textPosition) {
        return text(text, textPosition, 18, 0, 0);
    }

    public Task text(String text, TextPosition textPosition, int fontSize) {
        return text(text, textPosition, fontSize, 0, 0);
    }

    public Task text(String text, TextPosition textPosition, int fontSize, float width, float height) {
        this.text = text;
        this.textPosition = textPosition;
        this.fontSize = fontSize;
        this.textWidth = width;
        this.textHeight = height;
        return this;
    }

    public Task rect(float width, float height) {
        this.width = width;
        this.height = height;
        this.rect = true;
        return this;
    }

    public Task rectOutline(float width, float height) {
        this.width = width;
        this.height = height;
        this.rectOutline = true;
        return this;
    }

    public Task line(float x1, float y1) {
        this.x1 = x1;
        this.y1 = y1;
        this.line = true;
        return this;
    }

    public void perform(ShapeRenderer shapeRenderer) {
        if (color) shapeRenderer.setColor(r, g, b, a);
        if (rect) {
            shapeRenderer.set(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.rect(x, y, width, height);
        }
        if (rectOutline) {
            shapeRenderer.set(ShapeRenderer.ShapeType.Line);
            shapeRenderer.rect(x, y, width, height);
        }
        if (line) {
            shapeRenderer.set(ShapeRenderer.ShapeType.Line);
            shapeRenderer.line(x, y, x1, y1);
        }
    }

    public void perform(Renderer renderer, SpriteBatch spriteBatch) {
        BitmapFont font = renderer.getFont(fontSize);
        if (color) font.setColor(r, g, b, a);
        if (text != null) {
            switch (textPosition) {
                case DEFAULT:
                    font.draw(spriteBatch, text, x, y);
                    break;
                case BOTTOM_LEFT:
                    font.draw(spriteBatch, text, x, y + font.getLineHeight());
                    break;
                case LEFT_CENTER:
                    font.draw(spriteBatch, text, x + 4, y - textHeight / 2 + font.getCapHeight() / 2);
                    break;
                case CENTER:
                    font.draw(spriteBatch, text, x + textWidth / 2 - renderer.getWidth(text, fontSize) / 2, y - textHeight / 2 + font.getCapHeight() / 2);
                    break;
            }
        }
    }

    public enum TextPosition {
        DEFAULT,
        BOTTOM_LEFT,
        LEFT_CENTER,
        CENTER,
    }
}