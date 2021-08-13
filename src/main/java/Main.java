import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.mail.MessagingException;
import java.io.File;

public class Main extends Application {

    Stage window;
    Label list;


    @Override
    public void start(Stage primaryStage) {

        window = primaryStage;
        window.setTitle("code title");

        GridPane grip = new GridPane();
        grip.setPadding(new Insets(10, 10, 10, 10));
        grip.setVgap(8);


        Label subjectLable = new Label("Enter subject mail : ");
        GridPane.setConstraints(subjectLable, 0, 1);

        TextField subject = new TextField();
        subject.setPromptText("Subject Mail");
        subject.setFont(new Font("Arial", 25));

        Label textLable = new Label("Enter text mail : ");
        GridPane.setConstraints(textLable, 0, 3);

        TextField textMail = new TextField();
        textMail.setPromptText("Text Mail");
        textMail.setFont(new Font("Arial", 25));

        GridPane.setConstraints(subject, 0, 2);
        GridPane.setConstraints(textMail, 0, 4);


        Label label = new Label("File:");
        TextField tf = new TextField();
        Button btn = new Button("Browse");
        FileChooser fileChooser = new FileChooser();

        final String[] fileAddress = {""};


        btn.setOnAction(e ->
        {
            fileChooser.setTitle("Open File");
            File selectedDirectory = fileChooser.showOpenDialog(primaryStage);
            System.out.println(selectedDirectory.getAbsolutePath());
            fileAddress[0] = selectedDirectory.getAbsolutePath();
        });


        GridPane.setConstraints(label, 0, 5);
        GridPane.setConstraints(tf, 0, 6);
        GridPane.setConstraints(btn, 0, 7);


        Button button = new Button("Start");

        button.setOnAction(e -> {
            try {
                handle(textMail.getText(), subject.getText(), fileAddress);
            } catch (MessagingException ex) {
                ex.printStackTrace();
            }
        });

        GridPane.setConstraints(button, 0, 8);

        list = new Label();
        GridPane.setConstraints(list, 0, 9);

        grip.getChildren().addAll(subject, textMail, button, btn, textLable, subjectLable, list);

        Scene scene = new Scene(grip, 600, 400);
        window.setScene(scene);
        window.show();
    }

    public void handle(String emailBody, String emailSubject, String[] fileAddress) throws MessagingException {

        Controller controller = new Controller();
        String result = Controller.RunMainLogic(emailBody, emailSubject, fileAddress);

        list.setText("Result : " + result);
        list.setFont(new Font("Arial", 25));

    }

    public static void main(String[] args) {
        launch(args);
    }


}
