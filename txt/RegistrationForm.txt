import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class RegistrationForm {
    private VBox view;
    private final Stage stage;

    public RegistrationForm(Stage stage) {
        this.stage = stage;
        createForm();
    }

    public VBox getView() {
        return view;
    }

    private void createForm() {
        TextField firstName = new TextField();
        firstName.setPromptText("First Name");

        TextField middleName = new TextField();
        middleName.setPromptText("Middle Name");

        TextField lastName = new TextField();
        lastName.setPromptText("Last Name");

        TextField email = new TextField();
        email.setPromptText("Email Address");

        TextField mobile = new TextField();
        mobile.setPromptText("Mobile Number");

        ComboBox<String> day = new ComboBox<>();
        for (int i = 1; i <= 31; i++) day.getItems().add(String.valueOf(i));
        day.setPromptText("Day");

        ComboBox<String> month = new ComboBox<>();
        month.getItems().addAll("Jan", "Feb", "Mar", "Apr", "May", "Jun",
                                "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");
        month.setPromptText("Month");

        ComboBox<String> year = new ComboBox<>();
        for (int i = 1980; i <= 2024; i++) year.getItems().add(String.valueOf(i));
        year.setPromptText("Year");

        ToggleGroup genderGroup = new ToggleGroup();
        RadioButton male = new RadioButton("Male");
        male.setToggleGroup(genderGroup);
        RadioButton female = new RadioButton("Female");
        female.setToggleGroup(genderGroup);

        CheckBox hindi = new CheckBox("Hindi");
        CheckBox gujarati = new CheckBox("Gujarati");
        CheckBox english = new CheckBox("English");

        Button submit = new Button("Submit");
        Button cancel = new Button("Cancel");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);

        grid.add(new Label("First Name:"), 0, 0);
        grid.add(firstName, 1, 0);

        grid.add(new Label("Middle Name:"), 0, 1);
        grid.add(middleName, 1, 1);

        grid.add(new Label("Last Name:"), 0, 2);
        grid.add(lastName, 1, 2);

        grid.add(new Label("Email:"), 0, 3);
        grid.add(email, 1, 3);

        grid.add(new Label("Mobile:"), 0, 4);
        grid.add(mobile, 1, 4);

        grid.add(new Label("DOB:"), 0, 5);
        HBox dobBox = new HBox(5, day, month, year);
        grid.add(dobBox, 1, 5);

        grid.add(new Label("Gender:"), 0, 6);
        HBox genderBox = new HBox(10, male, female);
        grid.add(genderBox, 1, 6);

        grid.add(new Label("Languages:"), 0, 7);
        HBox langBox = new HBox(10, hindi, gujarati, english);
        grid.add(langBox, 1, 7);

        HBox btnBox = new HBox(10, submit, cancel);
        btnBox.setAlignment(Pos.CENTER_RIGHT);
        grid.add(btnBox, 1, 8);

        submit.setOnAction(e -> {
            if (firstName.getText().isEmpty() || lastName.getText().isEmpty() || email.getText().isEmpty()
                    || mobile.getText().isEmpty() || day.getValue() == null || month.getValue() == null
                    || year.getValue() == null || genderGroup.getSelectedToggle() == null) {
                showAlert(Alert.AlertType.ERROR, "Please fill all required fields!");
            } else if (!email.getText().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                showAlert(Alert.AlertType.ERROR, "Invalid Email Address!");
            } else if (!mobile.getText().matches("\\d{10}")) {
                showAlert(Alert.AlertType.ERROR, "Mobile must be 10 digits!");
            } else {
                new WelcomePage(stage, firstName.getText());
            }
        });

        cancel.setOnAction(e -> {
            firstName.clear(); middleName.clear(); lastName.clear();
            email.clear(); mobile.clear();
            day.setValue(null); month.setValue(null); year.setValue(null);
            genderGroup.selectToggle(null);
            hindi.setSelected(false); gujarati.setSelected(false); english.setSelected(false);
        });

        view = new VBox(20);
        view.setPadding(new Insets(20));
        view.getStyleClass().add("form-container");
        view.getChildren().add(grid);
    }

    private void showAlert(Alert.AlertType type, String msg) {
        Alert alert = new Alert(type);
        alert.setTitle("Validation Error");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
