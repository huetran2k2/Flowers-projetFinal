package com.example.pnvproject;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Login extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Stage window = primaryStage;
         window.setTitle("Login");
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(8);
        grid.setHgap(10);

        Label nameLabel = new Label("Username:");
        GridPane.setConstraints(nameLabel,0,1);

        //name inpput
        TextField nameInput = new TextField("Bucky");
        GridPane.setConstraints(nameInput, 1,0);

        //password label
        Label passLabel = new Label ("Password:");
        GridPane.setConstraints(nameLabel, 0,1);

        //pass input
        TextField passInput = new TextField();
        passInput.setPromptText("password");
        GridPane.setConstraints(passInput,1,1);

        Button loginButton = new Button("Log in");
        GridPane.setConstraints(loginButton,1,2);

        grid.getChildren().addAll(nameLabel,nameInput, passLabel, passInput,loginButton);
        Scene scene = new Scene (grid, 300, 300);
        window.setScene(scene);
        
        
        
        window.show();

    }
}
