package views;

import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import models.Particle;

public class Canvas extends javafx.scene.canvas.Canvas {
  private static final int WIDTH = 300;
  private static final int HEIGHT = 300;

  public Canvas() {
    super(WIDTH, HEIGHT);
  }

  public void drawParticles(GraphicsContext gc, List<Particle> particles, float particleRadius, double temperature) {
    double minTemperature = 200.0;
    double maxTemperature = 500.0;

    // Normalize temperature within the range of 0 to 1
    float temperatureRatio = (float) ((temperature - minTemperature) / (maxTemperature - minTemperature));

    // Make sure that temperatureRatio is within the range [0, 1].
    temperatureRatio = Math.max(0.0f, Math.min(1.0f, temperatureRatio));

    for (Particle particle : particles) {
      // Change the color from blue to red according to the temperature ratio
      Color color = new Color(temperatureRatio, 0.0f, 1.0f - temperatureRatio, 1.0f);
      gc.setFill(color);
      gc.fillOval(particle.x - particleRadius, particle.y - particleRadius, 2 * particleRadius, 2 * particleRadius);
    }
  }

}
