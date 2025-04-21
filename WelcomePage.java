import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class WelcomePage {
    public WelcomePage(Stage stage, String name) {
        VBox box = new VBox(20);
        box.setAlignment(Pos.CENTER);
        box.getStyleClass().add("welcome-box");

        Label message = new Label("Welcome, " + name + "!");
        message.getStyleClass().add("welcome-text");

        box.getChildren().add(message);
        Scene scene = new Scene(box, 400, 200);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
    }
}
