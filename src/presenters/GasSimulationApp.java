package presenters;

import javafx.application.Application;
import javafx.stage.Stage;
import models.Model;
import views.View;

public class GasSimulationApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Model model = new Model();
        View view = new View(primaryStage);
        new Presenter(model, view);
    }

}
