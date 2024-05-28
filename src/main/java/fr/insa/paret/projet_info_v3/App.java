package fr.insa.paret.projet_info_v3;

import java.io.BufferedReader;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.text.Text;

public class App extends Application {
    private Canvas canvas;
    private GraphicsContext gc;
    private boolean topSelected = false;
    private boolean rightSelected = false;
    private boolean bottomSelected = false;
    private boolean leftSelected = false;
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Rectangle Drawing App");
        
        canvas = new Canvas(400, 400);
        gc = canvas.getGraphicsContext2D();
        
       

        Button button1 = new Button("Supprimer");
        button1.setOnAction(e -> clearCanvas());

        Button button2 = new Button("Creation de piece");
        button2.setOnAction(e -> CreaPiece());
        
        Button button3 = new Button("Sauvegarder");
        button3.setOnAction(e -> Recup());

        VBox buttons = new VBox(10, button1, button2, button3);
        buttons.setAlignment(Pos.CENTER);
        HBox root = new HBox(10, canvas, buttons);
        root.setPadding(new Insets(10));

        Scene scene = new Scene(root, 600, 450);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void clearCanvas() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    private void CreaPiece() {
        Stage inputStage = new Stage();
        inputStage.setTitle("Entrer les Coordonnes");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(10);
        grid.setHgap(10);

        Label labelX1 = new Label("X1:");
        GridPane.setConstraints(labelX1, 0, 0);
        TextField inputX1 = new TextField();
        GridPane.setConstraints(inputX1, 1, 0);

        Label labelY1 = new Label("Y1:");
        GridPane.setConstraints(labelY1, 0, 1);
        TextField inputY1 = new TextField();
        GridPane.setConstraints(inputY1, 1, 1);

        Label labelX2 = new Label("X2:");
        GridPane.setConstraints(labelX2, 0, 2);
        TextField inputX2 = new TextField();
        GridPane.setConstraints(inputX2, 1, 2);

        Label labelY2 = new Label("Y2:");
        GridPane.setConstraints(labelY2, 0, 3);
        TextField inputY2 = new TextField();
        GridPane.setConstraints(inputY2, 1, 3);

        Button submitButton = new Button("Valider");
        GridPane.setConstraints(submitButton, 1, 4);
        submitButton.setOnAction(e -> {
            try {
                double x1 = Double.parseDouble(inputX1.getText());
                double y1 = Double.parseDouble(inputY1.getText());
                double x2 = Double.parseDouble(inputX2.getText());
                double y2 = Double.parseDouble(inputY2.getText());
                //choix revetement
                MakeRoom(x1, y1, x2, y2);
                inputStage.close();
            } catch (NumberFormatException ex) {
                // Handle invalid input
            }
            Choixmurs();
        });

        grid.getChildren().addAll(labelX1, inputX1, labelY1, inputY1, labelX2, inputX2, labelY2, inputY2, submitButton);

        Scene scene = new Scene(grid, 300, 200);
        inputStage.setScene(scene);
        inputStage.initModality(Modality.APPLICATION_MODAL);
        inputStage.showAndWait();
    }

    private void MakeRoom(double x1, double y1, double x2, double y2) {
        
        double x3 = x1;
        double y3 = y2;
        double x4 = x2;
        double y4 = y1;

        gc.setFill(Color.RED);
        gc.fillOval(x1 - 2.5, y1 - 2.5, 5, 5);
        gc.fillOval(x2 - 2.5, y2 - 2.5, 5, 5);
        gc.fillOval(x3 - 2.5, y3 - 2.5, 5, 5);
        gc.fillOval(x4 - 2.5, y4 - 2.5, 5, 5);

        gc.strokeLine(x1, y1, x4, y4);
        gc.strokeLine(x4, y4, x2, y2);
        gc.strokeLine(x2, y2, x3, y3);
        gc.strokeLine(x3, y3, x1, y1);
        
        
        
        // il faut crée les coin avec les coords (x1, y1) etc et les murs
        
       
    }
     private void Recup() {
        try (BufferedReader br = new BufferedReader(new FileReader("batiment.txt"))) {
            String line;
            List<Double[]> coords = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 4 && parts[0].equals("Coin")) {
                    try {
                        double cx = Double.parseDouble(parts[2]);
                        double cy = Double.parseDouble(parts[3]);
                        coords.add(new Double[]{cx, cy});
                    } catch (NumberFormatException e) {
                        // Handle parse error
                        System.out.println("Erreur de format dans le fichier : " + line);
                    }
                }
            }
            for (int i = 0; i < coords.size() - 1; i += 2) {
                Double[] coin1 = coords.get(i);
                Double[] coin2 = coords.get(i + 1);
                MakeRoom(coin1[0], coin1[1], coin2[0], coin2[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
        public static void Fichierbatiment (Batiment batiment) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("batiment.txt"))) {
            writer.write(batiment.toString());
            writer.newLine();

            for (Niveau niveau : batiment.getListeNiveaux()) {
                writer.write(niveau.toString());
                writer.newLine();

                for (Appartement appartement : niveau.getListeApparts()) {
                    writer.write(appartement.toString());
                    writer.newLine();

                    for (Piece piece : appartement.getListePieces()) {
                        writer.write(piece.toString());
                        writer.newLine();

                        for (Mur mur : piece.getListeMurs()) {
                            writer.write(mur.toString());
                            writer.newLine();
                        }

                        writer.write(piece.getSol().toString());
                        writer.newLine();
                        writer.write(piece.getPlafond().toString());
                        writer.newLine();
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        private void Choixmurs() {
        Stage squareStage = new Stage();
        squareStage.setTitle("Square Window");

        // Create Canvas and GraphicsContext for drawing
        Canvas canvas = new Canvas(100, 100);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Draw initial square
        drawSquare(gc);
        
        //Name the sides of the square
        Text Side1 = new Text("1");
        Text Side2 = new Text("4");
        Text Side3 = new Text("2");
        Text Side4 = new Text("3");
        
        //place the name with the coresponding side
        VBox vertsquare = new VBox (10,Side1, canvas, Side4);
        vertsquare.setAlignment(Pos.CENTER);
        HBox totsquare = new HBox (10, Side2, vertsquare, Side3);
        totsquare.setAlignment(Pos.CENTER);

        // Text and CheckBoxes
        Text instructionText = new Text("Select sides to turn black:");
        CheckBox side1 = new CheckBox("1");
        CheckBox side2 = new CheckBox("2");
        CheckBox side3 = new CheckBox("3");
        CheckBox side4 = new CheckBox("4");

        side1.setOnAction(e -> {
            topSelected = side1.isSelected();
            drawSquare(gc);
            DemandePorteFen();
        });
        side2.setOnAction(e -> {
            rightSelected = side2.isSelected();
            drawSquare(gc);
            DemandePorteFen();
        });
        side3.setOnAction(e -> {
            bottomSelected = side3.isSelected();
            drawSquare(gc);
            DemandePorteFen();
        });
        side4.setOnAction(e -> {
            leftSelected = side4.isSelected();
            drawSquare(gc);
            DemandePorteFen();
        });

        VBox checkboxes = new VBox(10, instructionText, side1, side2, side3, side4);
        checkboxes.setAlignment(Pos.CENTER_LEFT);
        checkboxes.setPadding(new Insets(20));

        // Submit button to close the window
        Button submitButton = new Button("Valider");
        submitButton.setOnAction(e -> squareStage.close());

        VBox controls = new VBox(10, checkboxes, submitButton);
        controls.setAlignment(Pos.CENTER);

        HBox layout = new HBox(20, totsquare, controls);
        layout.setPadding(new Insets(20));

        Scene scene = new Scene(layout, 400, 300);
        squareStage.setScene(scene);
        squareStage.initModality(Modality.APPLICATION_MODAL);
        squareStage.show();
    }

    private void drawSquare(GraphicsContext gc) {
        gc.clearRect(0, 0, 100, 100);
        gc.setStroke(topSelected ? Color.BLACK : Color.GREY);
        gc.strokeLine(0, 0, 100, 0); // Top

        gc.setStroke(rightSelected ? Color.BLACK : Color.GREY);
        gc.strokeLine(100, 0, 100, 100); // Right

        gc.setStroke(bottomSelected ? Color.BLACK : Color.GREY);
        gc.strokeLine(100, 100, 0, 100); // Bottom

        gc.setStroke(leftSelected ? Color.BLACK : Color.GREY);
        gc.strokeLine(0, 100, 0, 0); // Left
    }

    private void DemandePorteFen(){
        Stage inputStage = new Stage();
        inputStage.setTitle("Input Window");

        Label nbport = new Label("Nombre de porte sur ce mur?");
        TextField inputPorte = new TextField();
        Label nbfen = new Label("Nombre de fenetre sur ce mur?");
        TextField inputFenetre = new TextField();
        Label errorLabel = new Label();
        Label Rev = new Label("Quelle revetement pour ce mur?");
        ChoiceBox<String> rev = new ChoiceBox<>();
        rev.getItems().addAll(Revetement.getRevetementsPourMur());
        
        errorLabel.setTextFill(Color.RED);
        Button fin = new Button("Valider");

        fin.setOnAction(e -> {
            if (isValidInput(inputPorte.getText()) && isValidInput(inputFenetre.getText())) {
                inputStage.close();
            } else {
                errorLabel.setText("Entrer des nombres entiers valides.");
            }
        });

        VBox choix = new VBox(10, nbport, inputPorte, nbfen, inputFenetre, errorLabel, Rev, rev, fin);
        choix.setAlignment(Pos.CENTER);
        choix.setPadding(new Insets(20));

        Scene scene = new Scene(choix, 400, 300);
        inputStage.setScene(scene);
        inputStage.initModality(Modality.APPLICATION_MODAL);
        inputStage.show();
    }

    private boolean isValidInput(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }
        try {
            int number = Integer.parseInt(input);
            return number >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
