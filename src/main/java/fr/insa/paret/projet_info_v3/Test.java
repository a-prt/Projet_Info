/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.paret.projet_info_v3;

import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author jeanb
 */
public class Test {
     private boolean topSelected = false;
    private boolean rightSelected = false;
    private boolean bottomSelected = false;
    private boolean leftSelected = false;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Main Application");

        Button openWindowButton = new Button("Open Window");
        openWindowButton.setOnAction(e -> openSquareWindow());

        VBox root = new VBox(10, openWindowButton);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));

        Scene scene = new Scene(root, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
