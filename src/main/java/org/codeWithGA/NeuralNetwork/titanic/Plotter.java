package org.codeWithGA.NeuralNetwork.titanic;
import java.util.List;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Plotter {

    public static void generateLossCurve(List<Double> lossHistory) {
        int width = 800;
        int height = 600;
        int padding = 50;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = image.createGraphics();

        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, width, height);

        g2.setColor(Color.BLACK);
        g2.drawLine(padding, height - padding, width - padding, height - padding);
        g2.drawLine(padding, padding, padding, height - padding);

        g2.setColor(Color.BLUE);
        g2.setStroke(new BasicStroke(2f));

        double maxLoss = lossHistory.stream().max(Double::compare).orElse(1.0);
        int numEpochs = lossHistory.size();

        for (int i = 0; i < numEpochs - 1; i++) {
            int x1 = padding + (i * (width - 2 * padding) / numEpochs);
            int y1 = (height - padding) - (int) (lossHistory.get(i) * (height - 2 * padding) / maxLoss);
            int x2 = padding + ((i + 1) * (width - 2 * padding) / numEpochs);
            int y2 = (height - padding) - (int) (lossHistory.get(i + 1) * (height - 2 * padding) / maxLoss);

            g2.drawLine(x1, y1, x2, y2);
        }

        g2.setColor(Color.BLACK);
        g2.drawString("Epochs", width / 2, height - 10);
        g2.drawString("Loss", 10, height / 2);
        g2.drawString("Training Loss Curve", width / 2 - 50, 30);

        g2.dispose();

        try {
            File outputDir = new File("reports");
            if (!outputDir.exists()) outputDir.mkdir();
            ImageIO.write(image, "png", new File("reports/loss_curve.png"));
            System.out.println("Loss curve saved to: reports/loss_curve.png");
        } catch (IOException e) {
            System.err.println("Error saving plot: " + e.getMessage());
        }
    }
}
