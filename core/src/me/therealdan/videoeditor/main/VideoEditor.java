package me.therealdan.videoeditor.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import me.therealdan.videoeditor.renderer.Renderer;
import me.therealdan.videoeditor.renderer.TheInputProcessor;
import me.therealdan.videoeditor.view.MainView;

public class VideoEditor extends ApplicationAdapter {

    public static Colour color;
    public static Keyboard keyboard;
    public static Mouse mouse;

    private Renderer renderer;
    private TheInputProcessor theInputProcessor;

    private MainView mainView;

    @Override
    public void create() {
        color = new Colour();
        keyboard = new Keyboard();
        mouse = new Mouse();

        renderer = new Renderer();
        theInputProcessor = new TheInputProcessor();

        mainView = new MainView();

        Gdx.input.setInputProcessor(theInputProcessor);

        theInputProcessor.add(mainView);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        theInputProcessor.draw(renderer);
        renderer.draw();
    }

    @Override
    public void resize(int width, int height) {
        renderer.resize();
    }

    @Override
    public void dispose() {
        renderer.dispose();
    }
}
