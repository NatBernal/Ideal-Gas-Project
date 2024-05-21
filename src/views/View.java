package views;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class View {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;
    private Slider particleSlider, temperatureSlider, volumeSlider;
    private TextField particleNumberText, temperatureNumberText, volumeNumberText, pressureText;
    private Canvas canvas;

    public View(Stage primaryStage) {
        BorderPane borderPane = new BorderPane();
        borderPane.setStyle("-fx-background-color: #FAFAFA;");

        borderPane.setTop(createHeader());
        borderPane.setCenter(setupCanvas());
        borderPane.setBottom(createControlPanel());

        Scene scene = new Scene(borderPane, WIDTH, HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Simulación de Gas Ideal");
        primaryStage.show();
    }

    private HBox createHeader() {
        Text headerText = new Text("Simulación de Gas Ideal");
        headerText.setFont(Font.font("San Francisco", FontWeight.BOLD, 26));
        headerText.setFill(Color.DARKSLATEGRAY);
        HBox header = new HBox(headerText);
        header.setAlignment(Pos.CENTER);
        header.setPadding(new Insets(15));
        header.setStyle("-fx-background-color: #F9F9F9; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 10, 0, 0, 2);");
        return header;
    }

    private GridPane createControlPanel() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);

        initializeTextFields();
        initializeSliders();

        grid.add(new Label("Volumen (m³):"), 0, 0);
        grid.add(volumeSlider, 1, 0);
        grid.add(volumeNumberText, 2, 0);

        grid.add(new Label("Temperatura (K):"), 0, 1);
        grid.add(temperatureSlider, 1, 1);
        grid.add(temperatureNumberText, 2, 1);

        grid.add(new Label("Partículas (moléculas):"), 0, 2);
        grid.add(particleSlider, 1, 2);
        grid.add(particleNumberText, 2, 2);

        grid.add(new Label("Presión (Pa):"), 0, 3);
        grid.add(pressureText, 1, 3, 2, 1); // Span two columns


        for (Node node : grid.getChildren()) {
            if (node instanceof Label) {
                ((Label)node).setFont(Font.font("Arial", FontWeight.BOLD, 14));
                ((Label)node).setTextFill(Color.DARKGRAY);
            }
        }

        return grid;
    }

    private void initializeTextFields() {
        particleNumberText = new TextField();
        temperatureNumberText = new TextField();
        volumeNumberText = new TextField();
        pressureText = new TextField();
        pressureText.setEditable(false); // Prevent editing of pressure field
        pressureText.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        pressureText.setPrefWidth(200);
    }

    private void initializeSliders() {
        particleSlider = createSlider(0, 4000, 0, Orientation.HORIZONTAL);
        temperatureSlider = createSlider(200, 500, 1, Orientation.HORIZONTAL);
        volumeSlider = createSlider(0.001, 0.1, 0, Orientation.HORIZONTAL);
    }

    private Slider createSlider(double min, double max, double value, Orientation orientation) {
        Slider slider = new Slider(min, max, value);
        slider.setStyle("-fx-control-inner-background: #E1E1E1; -fx-accent: #007AFF;");
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit((max - min) / 10);
        slider.setBlockIncrement((max - min) / 100);
        slider.setOrientation(orientation);
        return slider;
    }

    private Canvas setupCanvas() {
        canvas = new Canvas(WIDTH, HEIGHT - 250);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, WIDTH, HEIGHT - 250);
        return canvas;
    }

    

    public Slider getParticleSlider() {
        return particleSlider;
    }

    public Slider getTemperatureSlider() {
        return temperatureSlider;
    }

    public Slider getVolumeSlider() {
        return volumeSlider;
    }

    public TextField getParticleNumberText() {
        return particleNumberText;
    }

    public TextField getTemperatureNumberText() {
        return temperatureNumberText;
    }

    public TextField getVolumeNumberText() {
        return volumeNumberText;
    }

    public TextField getPressureText() {
        return pressureText;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public GraphicsContext getGraphicsContext() {
        return canvas.getGraphicsContext2D();
    }

    public void clearCanvas() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, WIDTH, HEIGHT);
    }
}
