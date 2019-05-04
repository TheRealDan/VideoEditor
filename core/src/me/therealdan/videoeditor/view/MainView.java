package me.therealdan.videoeditor.view;

import com.badlogic.gdx.Gdx;
import me.therealdan.videoeditor.renderer.Renderer;
import me.therealdan.videoeditor.renderer.Visual;
import me.therealdan.videoeditor.view.panel.Working;

import java.util.ArrayList;
import java.util.List;

public class MainView implements Visual {

    private static MainView mainView;

    private List<Panel> panels = new ArrayList<>();
    private Panel dragging = null;
    private float xDifference, yDifference;

    public MainView() {
        mainView = this;

        panels.add(new Working());

        for (Panel panel : panels())
            panel.load();
    }

    public void save() {
        for (Panel panel : panels())
            panel.save();
    }

    @Override
    public boolean draw(Renderer renderer) {
        Panel allowInteract = null;
        for (Panel panel : panels()) {
            panel.setAllowInteract(false);
            if (panel.isVisible() && panel.containsMouse() && !panel.isDragging())
                allowInteract = panel;
        }
        if (allowInteract != null) allowInteract.setAllowInteract(true);

        for (Panel panel : panels()) {
            if (panel.isVisible()) {
                panel.draw(renderer, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
                renderer.draw();
            }
        }

        if (isDragging()) {
            getDragging().setLocation(Gdx.input.getX() - xDifference, Gdx.input.getY() - yDifference);
            if (!getDragging().containsMouse()) drag(null);
        }

        return true;
    }

    @Override
    public boolean scrolled(int amount) {
        for (Panel panel : panels())
            if (!panel.scrolled(amount))
                return false;

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        drag(null);

        return true;
    }

    @Override
    public boolean keyDown(int keycode) {
        for (Panel panel : panels())
            if (panel.isVisible() && panel.containsMouse() && panel.canInteract())
                if (panel.keyDown(keycode))
                    return false;

        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        for (Panel panel : panels())
            if (!panel.keyUp(keycode))
                return false;

        return true;
    }

    public static void drag(Panel panel) {
        if (mainView.dragging == null && panel != null) {
            mainView.xDifference = Math.abs(panel.getX() - Gdx.input.getX());
            mainView.yDifference = Math.abs(panel.getYString() - Gdx.input.getY());
            moveToTop(panel);
        }

        mainView.dragging = panel;
    }

    public static void moveToTop(Panel panel) {
        mainView.panels.remove(panel);
        mainView.panels.add(panel);
    }

    public static boolean isDragging() {
        return getDragging() != null;
    }

    public static Panel getDragging() {
        return mainView.dragging;
    }

    public static List<Panel> panels() {
        return new ArrayList<>(mainView.panels);
    }
}