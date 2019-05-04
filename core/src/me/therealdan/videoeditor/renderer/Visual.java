package me.therealdan.videoeditor.renderer;

import com.badlogic.gdx.InputProcessor;

public interface Visual extends InputProcessor {

    default void update() {

    }

    boolean draw(Renderer renderer);

    default void resize(int width, int height) {

    }

    @Override
    default boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return true;
    }

    @Override
    default boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return true;
    }

    @Override
    default boolean touchDragged(int screenX, int screenY, int pointer) {
        return true;
    }

    @Override
    default boolean scrolled(int amount) {
        return true;
    }

    @Override
    default boolean mouseMoved(int screenX, int screenY) {
        return true;
    }

    @Override
    default boolean keyDown(int keycode) {
        return true;
    }

    @Override
    default boolean keyUp(int keycode) {
        return true;
    }

    @Override
    default boolean keyTyped(char character) {
        return true;
    }
}