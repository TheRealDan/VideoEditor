package me.therealdan.videoeditor.view.panel;

import me.therealdan.videoeditor.main.VideoEditor;
import me.therealdan.videoeditor.renderer.Renderer;
import me.therealdan.videoeditor.renderer.Task;
import me.therealdan.videoeditor.view.Panel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Working implements Panel {

    private BufferedImage bufferedImage;

    public Working() {
        setWidth(1000);
        setHeight(1000);

        bufferedImage = new BufferedImage(1920, 1080, BufferedImage.TYPE_INT_RGB);

        Graphics2D graphics = bufferedImage.createGraphics();
        graphics.setColor(Color.WHITE);


    }

    @Override
    public boolean draw(Renderer renderer, float X, float Y, float WIDTH, float HEIGHT) {
        renderer.queue(new Task(200, 200).text("MoneyTrainer", Task.TextPosition.CENTER, 50).setColor(VideoEditor.color.TEXT));

        return true;
    }

    @Override
    public boolean isVisible() {
        return true;
    }
}
