package presenters;

import javafx.animation.AnimationTimer;
import models.Model;
import views.Canvas;
import views.View;

public class Presenter {

    private Model model;
    private View view;
    private Canvas canvas;

    public Presenter(Model model, View view) {
        this.model = model;
        this.view = view;
        this.canvas = new Canvas();
        bindControls();
        startAnimation();
    }

    private void bindControls() {
        view.getParticleSlider().valueProperty().addListener((observable, oldValue, newValue) -> {
            model.updateParticleCount(newValue.intValue());
            view.getParticleNumberText().setText(String.format("%.2f", newValue.doubleValue())); // Format to 2 decimal                                                                               // places
            updatePressure();
        });

        view.getTemperatureSlider().valueProperty().addListener((observable, oldValue, newValue) -> {
            view.getTemperatureNumberText().setText(String.format("%.2f", newValue.doubleValue())); // Format to 2                                                                                // decimal places
            model.setParticleSpeed((float) (6 + 14 * (newValue.doubleValue() - 200) / 499.99));
            updatePressure();
        });

        view.getVolumeSlider().valueProperty().addListener((observable, oldValue, newValue) -> {
            String value = String.format("%.2f", newValue.doubleValue()); // Format to 2 decimal places
            view.getVolumeNumberText().setText(value);
            model.setParticleRadius((float) (7 - 6.3 * (newValue.doubleValue() - 0.01) / 0.099));
            updatePressure();
        });
    }

    private void updatePressure() {
        try {
            float volume = Float.valueOf(view.getVolumeNumberText().getText().replace(",", ".")); // Replace commas with
                                                                                                  // dots
            float temperature = Float.valueOf(view.getTemperatureNumberText().getText().replace(",", ".")); // Replace
                                                                                                            // commas
                                                                                                            // with dots
            float substance = Float.valueOf(view.getParticleNumberText().getText().replace(",", ".")); // Replace commas
                                                                                                       // with dots

            if (volume == 0) {
                view.getPressureText().setText("Volume cannot be zero");
                return;
            }

            float pressure = model.calculatePressure(volume, temperature, substance);
            view.getPressureText().setText(String.format("%.2e", pressure));// Format to 2 decimal places
        } catch (NumberFormatException e) {
            view.getPressureText().setText("Invalid number format");
        } catch (Exception e) {
            view.getPressureText().setText("Error: " + e.getMessage());
        }
    }

    private void startAnimation() {
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                view.clearCanvas();
                model.updateParticles();
                canvas.drawParticles(view.getGraphicsContext(), model.getParticles(), model.getParticleRadius(),
                        view.getTemperatureSlider().getValue());
            }
        };
        animationTimer.start();
    }

}
