package me.therealdan.videoeditor.main;

import com.badlogic.gdx.graphics.Color;

public class Colour {

    public Color TEXT;
    public Color LIGHT;
    public Color MEDIUM;
    public Color DARK;

    protected Colour() {
        TEXT = new Color(1, 1, 1, 1);
        LIGHT = new Color(0.6f, 0.6f, 0.6f, 1);
        MEDIUM = new Color(0.2f, 0.2f, 0.2f, 1);
        DARK = new Color(0.1f, 0.1f, 0.1f, 1);

        // TODO - Make these configurable
    }
}