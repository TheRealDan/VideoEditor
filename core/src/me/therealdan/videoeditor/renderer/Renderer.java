package me.therealdan.videoeditor.renderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Renderer {

    private ShapeRenderer shapeRenderer;
    private SpriteBatch spriteBatch;

    private HashMap<String, BitmapFont> fonts = new HashMap<>();

    private boolean textBegun = false;

    private List<Task> tasks = new ArrayList<>();

    public Renderer() {
        shapeRenderer = new ShapeRenderer();
        spriteBatch = new SpriteBatch();
    }

    public void draw() {
        if (textBegun) {
            spriteBatch.end();
            textBegun = false;
        }

        shapeRenderer.setAutoShapeType(true);
        shapeRenderer.begin();
        for (Task task : tasks)
            task.perform(shapeRenderer);
        shapeRenderer.end();

        spriteBatch.begin();
        for (Task task : tasks)
            task.perform(this, spriteBatch);
        spriteBatch.end();

        tasks.clear();
    }

    public void queue(Task task) {
        tasks.add(task);
    }

    public void resize() {
        shapeRenderer.dispose();
        spriteBatch.dispose();

        shapeRenderer = new ShapeRenderer();
        spriteBatch = new SpriteBatch();
    }

    public void dispose() {
        shapeRenderer.dispose();
        spriteBatch.dispose();

        for (BitmapFont bitmapFont : fonts.values())
            bitmapFont.dispose();
        fonts.clear();
    }

    public float getWidth(String text, int fontSize) {
        if (!textBegun && !spriteBatch.isDrawing()) {
            spriteBatch.begin();
            textBegun = true;
        }
        return getFont(fontSize).draw(spriteBatch, text, 0, -100).width;
    }

    public BitmapFont getFont(int fontSize) {
        String key = "Font_" + fontSize;

        if (!fonts.containsKey(key)) {
            FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("core/assets/Proxima Nova Reg.ttf"));
            FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
            parameter.size = fontSize;
            fonts.put(key, generator.generateFont(parameter));
        }

        return fonts.get(key);
    }
}