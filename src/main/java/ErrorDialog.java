import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.ResourceBundle;


public class ErrorDialog implements Initializable {

    private String title, details;
    private double xOffset = 0, yOffset = 0;

    @FXML
    private VBox errorDialogPane;
    @FXML
    private Pane dragPane;
    @FXML
    private Text titleText;
    @FXML
    private TitledPane detailsTitledPane;
    @FXML
    private TextArea detailsTextArea;



    public ErrorDialog(String errorTitle, StackTraceElement[] stackTrace) {

        StringBuilder stringBuilder = new StringBuilder();
        for(StackTraceElement ste : stackTrace)
            stringBuilder.append(ste.toString()).append("\n");

        title = errorTitle;
        details = stringBuilder.toString();

    }

    public ErrorDialog(String errorTitle, String errorDetails) {

        title = errorTitle;
        details = errorDetails;

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        titleText.setText(title);
        detailsTextArea.setText(details);

        detailsTitledPane.expandedProperty().addListener((observable, wasExpanded, nowExpanded) -> {
            if(nowExpanded) {
                errorDialogPane.setPrefHeight(errorDialogPane.getMaxHeight());
                errorDialogPane.getScene().getWindow().setHeight(errorDialogPane.getMaxHeight());
            } else {
                errorDialogPane.setPrefHeight(errorDialogPane.getMinHeight());
                errorDialogPane.getScene().getWindow().setHeight(errorDialogPane.getMinHeight());
            }
        });

        dragPane.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        dragPane.setOnMouseDragged(event -> {
            errorDialogPane.getScene().getWindow().setX(event.getScreenX() - xOffset);
            errorDialogPane.getScene().getWindow().setY(event.getScreenY() - yOffset);
        });

    }

    public void showAndWait() {

            try {

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("windows/ErrorDialog.fxml"));
                fxmlLoader.setController(this);
                Parent root = fxmlLoader.load();
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setMinWidth(500);
                stage.setMinHeight(200);
                stage.setMaxWidth(500);
                stage.setMaxHeight(330);
                stage.setResizable(false);
                stage.setScene(scene);
                stage.initStyle(StageStyle.UNDECORATED);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();

            } catch (Exception e) {
                System.out.println("Error in loading the ErrorDialog");
                e.printStackTrace();
            }

    }

    @FXML
    void closeBtnAction() {
        ((Stage) errorDialogPane.getScene().getWindow()).close();
    }

}
