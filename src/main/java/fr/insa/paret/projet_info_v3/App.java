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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import static javafx.application.Application.launch;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.text.Text;


public class App extends Application {
    private Canvas canvas;
    private GraphicsContext gc;
    private int currentLevel = 0;
    private int currentApartment = 0;
    private int coinId = 0;
    private int murId = 0;
    private int pieceId = 0;
    private int solId = 0;
    private int plafondId = 0;
    private int appartementId = 0;
    private int niveauId = 0;
    private int batimentId = 1;
    private double prixtot=0;
    private double prixmur=0;
    private double prixpiece=0;

    private List<Integer> niveaux = new ArrayList<>();
    private List<Integer> appartements = new ArrayList<>();
    private List<Integer> pieces = new ArrayList<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Devis batiment");

        canvas = new Canvas(400, 400);
        gc = canvas.getGraphicsContext2D();

        Button button1 = new Button("Supprimer");
        button1.setOnAction(e -> clearCanvas());

        Button button2 = new Button("Configurer le bâtiment");
        button2.setOnAction(e -> promptBuildingDetails());
        
        Label devis = new Label ("prix du batiment "+prixtot+"€");

        VBox buttons = new VBox(10, button1, button2, devis);
        buttons.setAlignment(Pos.CENTER);
        HBox root = new HBox(10, canvas, buttons);
        root.setPadding(new Insets(10));

        Scene scene = new Scene(root, 600, 450);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void clearCanvas() {
        
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\jeanb\\Documents\\Netbeansproject 2\\Projet_Info_V3\\src\\main\\java\\fr\\insa\\paret\\projet_info_v3\\batiment.txt"))) {
            bw.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void promptBuildingDetails() {
        Stage inputStage = new Stage();
        inputStage.setTitle("Configurer le bâtiment");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(10);
        grid.setHgap(10);

        Label labelNbNiveaux = new Label("Nombre de niveaux:");
        GridPane.setConstraints(labelNbNiveaux, 0, 0);
        TextField inputNbNiveaux = new TextField();
        GridPane.setConstraints(inputNbNiveaux, 1, 0);

        Button submitButton = new Button("Valider");
        GridPane.setConstraints(submitButton, 1, 1);
        submitButton.setOnAction(e -> {
            try {
                int nbNiveaux = Integer.parseInt(inputNbNiveaux.getText());
                inputStage.close();
                niveauId++;
                promptLevelDetails(nbNiveaux);
                saveBatimentDetails();
            } catch (NumberFormatException ex) {
                // Handle incorrect input
            }
        });

        grid.getChildren().addAll(labelNbNiveaux, inputNbNiveaux, submitButton);

        Scene scene = new Scene(grid, 300, 150);
        inputStage.setScene(scene);
        inputStage.initModality(Modality.APPLICATION_MODAL);
        inputStage.showAndWait();
    }

    private void promptLevelDetails(int nbNiveaux) {
        for (int i = 0; i < nbNiveaux; i++) {
            currentLevel = i + 1;
            Stage inputStage = new Stage();
            inputStage.setTitle("Détails du niveau " + currentLevel);

            GridPane grid = new GridPane();
            grid.setPadding(new Insets(10));
            grid.setVgap(10);
            grid.setHgap(10);

            Label labelNbAppartements = new Label("Nombre d'appartements pour le niveau " + currentLevel + ":");
            GridPane.setConstraints(labelNbAppartements, 0, 0);
            TextField inputNbAppartements = new TextField();
            GridPane.setConstraints(inputNbAppartements, 1, 0);

            Button submitButton = new Button("Valider");
            GridPane.setConstraints(submitButton, 1, 1);
            submitButton.setOnAction(e -> {
                try {
                    int nbAppartements = Integer.parseInt(inputNbAppartements.getText());
                    inputStage.close();
                    promptApartmentDetails(currentLevel, nbAppartements);
                    saveNiveauDetails(currentLevel);
                } catch (NumberFormatException ex) {
                    // Handle incorrect input
                }
            });

            grid.getChildren().addAll(labelNbAppartements, inputNbAppartements, submitButton);

            Scene scene = new Scene(grid, 300, 150);
            inputStage.setScene(scene);
            inputStage.initModality(Modality.APPLICATION_MODAL);
            inputStage.showAndWait();
        }
    }

    private void promptApartmentDetails(int levelIndex, int nbAppartements) {
        for (int i = 0; i < nbAppartements; i++) {
            currentApartment = i + 1;
            appartementId++;
            Stage inputStage = new Stage();
            inputStage.setTitle("Détails de l'appartement " + currentApartment + " du niveau " + levelIndex);

            GridPane grid = new GridPane();
            grid.setPadding(new Insets(10));
            grid.setVgap(10);
            grid.setHgap(10);

            Label labelNbPieces = new Label("Nombre de pièces pour l'appartement " + currentApartment + ":");
            GridPane.setConstraints(labelNbPieces, 0, 0);
            TextField inputNbPieces = new TextField();
            GridPane.setConstraints(inputNbPieces, 1, 0);

            Button submitButton = new Button("Valider");
            GridPane.setConstraints(submitButton, 1, 1);
            submitButton.setOnAction(e -> {
                try {
                    int nbPieces = Integer.parseInt(inputNbPieces.getText());
                    inputStage.close();
                    createPieces(levelIndex, currentApartment, nbPieces);
                    saveAppartementDetails(levelIndex, currentApartment);
                } catch (NumberFormatException ex) {
                    // Handle incorrect input
                }
            });

            grid.getChildren().addAll(labelNbPieces, inputNbPieces, submitButton);

            Scene scene = new Scene(grid, 300, 150);
            inputStage.setScene(scene);
            inputStage.initModality(Modality.APPLICATION_MODAL);
            inputStage.showAndWait();
        }
    }
    private double PrixMur (double X1, double Y1, double X2, double Y2, int nbporte, int nbfen, int idrev){
       double longueur = Math.sqrt(Math.pow(X2 - X1, 2)+ Math.pow(Y2- Y1, 2));
        double hauteur = 2.5; //quelle hauteur pour le mur?
        double surfaceComplete = longueur*hauteur;
        double surfacePorte = nbporte*(0.90*2.10);
        double surfaceFenetre = nbfen*(1.20*1.20);
        double prix =surfaceComplete-surfacePorte-surfaceFenetre;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("CatalogueRevetements.txt"))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] t = line.split(";"); 
                if(Integer.parseInt(t[0])==idrev){
                    prix = prix*Integer.parseInt(t[5]);
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return prix;
    }
    private double PrixSurf (double x1, double y1, double x2, double y2, int idrev){
        double prix =Math.abs(x1-x2)*Math.abs(y1-y2);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("CatalogueRevetements.txt"))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] t = line.split(";"); 
                if(Integer.parseInt(t[0])==idrev){
                    prix = prix*Integer.parseInt(t[5]);
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return prix;
    }
    private void createPieces(int levelIndex, int apartmentIndex, int nbPieces) {
        for (int i = 0; i < nbPieces; i++) {
            promptPieceDetails(levelIndex, apartmentIndex, i);
        }
    }

    private void promptPieceDetails(int levelIndex, int apartmentIndex, int pieceIndex) {
        Stage inputStage = new Stage();
        inputStage.setTitle("Détails de la pièce " + (pieceIndex + 1));

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

        Label labelNbPortes = new Label("Nombre de portes:");
        GridPane.setConstraints(labelNbPortes, 0, 4);
        TextField inputNbPortes = new TextField();
        GridPane.setConstraints(inputNbPortes, 1, 4);

        Label labelNbFenetres = new Label("Nombre de fenêtres:");
        GridPane.setConstraints(labelNbFenetres, 0, 5);
        TextField inputNbFenetres = new TextField();
        GridPane.setConstraints(inputNbFenetres, 1, 5);

        Label labelRevMurTop = new Label("ID revêtement du mur du haut:");
        GridPane.setConstraints(labelRevMurTop, 0, 6);
        TextField inputRevMurTop = new TextField();
        GridPane.setConstraints(inputRevMurTop, 1, 6);

        Label labelRevMurRight = new Label("ID revêtement du mur de droite:");
        GridPane.setConstraints(labelRevMurRight, 0, 7);
        TextField inputRevMurRight = new TextField();
        GridPane.setConstraints(inputRevMurRight, 1, 7);

        Label labelRevMurBottom = new Label("ID revêtement du mur du bas:");
        GridPane.setConstraints(labelRevMurBottom, 0, 8);
        TextField inputRevMurBottom = new TextField();
        GridPane.setConstraints(inputRevMurBottom, 1, 8);

        Label labelRevMurLeft = new Label("ID revêtement du mur de gauche:");
        GridPane.setConstraints(labelRevMurLeft, 0, 9);
        TextField inputRevMurLeft = new TextField();
        GridPane.setConstraints(inputRevMurLeft, 1, 9);

        Label labelRevSol = new Label("ID revêtement du sol:");
        GridPane.setConstraints(labelRevSol, 0, 10);
        TextField inputRevSol = new TextField();
        GridPane.setConstraints(inputRevSol, 1, 10);

        Label labelRevPlafond = new Label("ID revêtement du plafond:");
        GridPane.setConstraints(labelRevPlafond, 0, 11);
        TextField inputRevPlafond = new TextField();
        GridPane.setConstraints(inputRevPlafond, 1, 11);

        Button submitButton = new Button("Valider");
        GridPane.setConstraints(submitButton, 1, 12);
        submitButton.setOnAction(e -> {
            try {
                double x1 = Double.parseDouble(inputX1.getText());
                double y1 = Double.parseDouble(inputY1.getText());
                double x2 = Double.parseDouble(inputX2.getText());
                double y2 = Double.parseDouble(inputY2.getText());
                int nbPortes = Integer.parseInt(inputNbPortes.getText());
                int nbFenetres = Integer.parseInt(inputNbFenetres.getText());
                int revMurTop = Integer.parseInt(inputRevMurTop.getText());
                int revMurRight = Integer.parseInt(inputRevMurRight.getText());
                int revMurBottom = Integer.parseInt(inputRevMurBottom.getText());
                int revMurLeft = Integer.parseInt(inputRevMurLeft.getText());
                int revSol = Integer.parseInt(inputRevSol.getText());
                int revPlafond = Integer.parseInt(inputRevPlafond.getText());

                savePieceDetails(levelIndex, apartmentIndex, pieceIndex, x1, y1, x2, y2, nbPortes, nbFenetres, revMurTop, revMurRight, revMurBottom, revMurLeft, revSol, revPlafond);
                inputStage.close();
            } catch (NumberFormatException ex) {
                // Handle incorrect input
            }
        });

        grid.getChildren().addAll(labelX1, inputX1, labelY1, inputY1, labelX2, inputX2, labelY2, inputY2, labelNbPortes, inputNbPortes, labelNbFenetres, inputNbFenetres, labelRevMurTop, inputRevMurTop, labelRevMurRight, inputRevMurRight, labelRevMurBottom, inputRevMurBottom, labelRevMurLeft, inputRevMurLeft, labelRevSol, inputRevSol, labelRevPlafond, inputRevPlafond, submitButton);

        Scene scene = new Scene(grid, 500, 400);
        inputStage.setScene(scene);
        inputStage.initModality(Modality.APPLICATION_MODAL);
        inputStage.showAndWait();
    }

    private void savePieceDetails(int levelIndex, int apartmentIndex, int pieceIndex, double x1, double y1, double x2, double y2, int nbPortes, int nbFenetres, int revMurTop, int revMurRight, int revMurBottom, int revMurLeft, int revSol, int revPlafond) {
        int currentPieceId = ++pieceId;
        int currentSolId = ++solId;
        int currentPlafondId = ++plafondId;
        int idCoin1 = ++coinId;
        int idCoin2 = ++coinId;
        int idCoin3 = ++coinId;
        int idCoin4 = ++coinId;
        int idMurTop = ++murId;
        int idMurRight = ++murId;
        int idMurBottom = ++murId;
        int idMurLeft = ++murId;

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\jeanb\\Documents\\Netbeansproject 2\\Projet_Info_V3\\src\\main\\java\\fr\\insa\\paret\\projet_info_v3\\batiment.txt", true))) {
            bw.write("Coins;" + idCoin1 + ";" + x1 + ";" + y1 + "\n");
            bw.write("Coins;" + idCoin2 + ";" + x2 + ";" + y2 + "\n");
            bw.write("Coins;" + idCoin3 + ";" + x1 + ";" + y2 + "\n");
            bw.write("Coins;" + idCoin4 + ";" + x2 + ";" + y1 + "\n");
            
            bw.write("Mur;" + idMurTop + ";" + idCoin1 + ";" + idCoin4 + ";" + nbPortes + ";" + nbFenetres + ";" + revMurTop + "\n");
            bw.write("Mur;" + idMurRight + ";" + idCoin4 + ";" + idCoin2 + ";" + nbPortes + ";" + nbFenetres + ";" + revMurRight + "\n");
            bw.write("Mur;" + idMurBottom + ";" + idCoin2 + ";" + idCoin3 + ";" + nbPortes + ";" + nbFenetres + ";" + revMurBottom + "\n");
            bw.write("Mur;" + idMurLeft + ";" + idCoin3 + ";" + idCoin1 + ";" + nbPortes + ";" + nbFenetres + ";" + revMurLeft + "\n");

            bw.write("Sol;" + currentSolId + ";" + idCoin1 + ";" + idCoin2 + ";" + idCoin3 + ";" + idCoin4 + ";" + revSol + "\n");
            bw.write("Plafond;" + currentPlafondId + ";" + idCoin1 + ";" + idCoin2 + ";" + idCoin3 + ";" + idCoin4 + ";" + revPlafond + "\n");

            bw.write("Piece;" + currentPieceId + ";" + currentSolId + ";" + currentPlafondId + ";" + idMurTop + ";" + idMurRight + ";" + idMurBottom + ";" + idMurLeft + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        prixtot=prixtot+PrixMur(x1, y1, x2, y1, nbPortes, nbFenetres,revMurTop);
        prixtot=prixtot+PrixMur(x2, y1, x2, y2, nbPortes, nbFenetres,revMurRight);
        prixtot=prixtot+PrixMur(x2, y2, x1, y2, nbPortes, nbFenetres,revMurBottom);
        prixtot=prixtot+PrixMur(x1, y2, x1, y1, nbPortes, nbFenetres,revMurLeft);
        prixtot=prixtot+PrixSurf(x1, y1, x2, y2, revSol);
        prixtot=prixtot+PrixSurf(x1, y1, x2, y2, revPlafond);
        pieces.add(currentPieceId);

        drawPiece(x1, y1, x2, y2);
    }

    private void saveAppartementDetails(int levelIndex, int apartmentIndex) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\jeanb\\Documents\\Netbeansproject 2\\Projet_Info_V3\\src\\main\\java\\fr\\insa\\paret\\projet_info_v3\\batiment.txt", true))) {
            bw.write("Appartement;" + apartmentIndex + ";" + levelIndex + ";");
            for (Integer piece : pieces) {
                bw.write(piece + ";");
            }
            bw.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        appartements.add(appartementId);
        pieces.clear();
    }

    private void saveNiveauDetails(int niveauIndex) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\jeanb\\Documents\\Netbeansproject 2\\Projet_Info_V3\\src\\main\\java\\fr\\insa\\paret\\projet_info_v3\\batiment.txt", true))) {
            bw.write("Niveau;" + niveauIndex + ";");
            for (Integer appartement : appartements) {
                bw.write(appartement + ";");
            }
            bw.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        niveaux.add(niveauIndex);
        appartements.clear();
    }

    private void saveBatimentDetails() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\Utilisateur\\Documents\\NetBeansProjects\\Projet_Info\\src\\main\\java\\fr\\insa\\paret\\projet_info_v3\\batiment.txt", true))) {
            bw.write("Batiment;" + batimentId + ";");
            for (Integer niveau : niveaux) {
                bw.write(niveau + ";");
            }
            bw.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        niveaux.clear();
    }

    private void drawPiece(double x1, double y1, double x2, double y2) {
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
    
    public void devis(){
        try {
            // ici
            Batiment batiment = parseBatimentFile("C:\\Users\\jeanb\\Documents\\Netbeansproject 2\\Projet_Info_V3\\src\\main\\java\\fr\\insa\\paret\\projet_info_v3\\batiment.txt");
            double totalCost = batiment.montantRevetement();
            System.out.println("Total cost of all revêtements: " + totalCost);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static Batiment parseBatimentFile(String filePath) throws Exception {
        Map<Integer, Niveau> niveaux = new HashMap<>();
        Map<Integer, Appartement> appartements = new HashMap<>();
        Map<Integer, Piece> pieces = new HashMap<>();
        Map<Integer, Mur> murs = new HashMap<>();
        Map<Integer, Coin> coins = new HashMap<>();
        Map<Integer, Sol> sols = new HashMap<>();
        Map<Integer, Plafond> plafonds = new HashMap<>();
        List<Niveau> batimentNiveaux = new ArrayList<>();
        Batiment batiment = null;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                switch (parts[0]) {
                    case "Batiment":
                        int batimentId = Integer.parseInt(parts[1]);
                        batimentNiveaux = new ArrayList<>();
                        batiment = new Batiment(batimentId, batimentNiveaux);
                        for (int i = 2; i < parts.length; i++) {
                            int niveauId = Integer.parseInt(parts[i]);
                            Niveau niveau = niveaux.get(niveauId);
                            if (niveau != null) {
                                batimentNiveaux.add(niveau);
                            }
                        }
                        break;
                    case "Niveau":
                        int niveauId = Integer.parseInt(parts[1]);
                        List<Appartement> niveauAppartements = new ArrayList<>();
                        Niveau niveau = new Niveau(niveauId, niveauAppartements);
                        niveaux.put(niveauId, niveau);
                        for (int i = 2; i < parts.length; i++) {
                            int appartId = Integer.parseInt(parts[i]);
                            Appartement appartement = appartements.get(appartId);
                            if (appartement != null) {
                                niveauAppartements.add(appartement);
                            }
                        }
                        break;
                    case "Appartement":
                        int appartId = Integer.parseInt(parts[1]);
                        int idNiveauAppartement = Integer.parseInt(parts[2]);
                        List<Piece> appartementPieces = new ArrayList<>();
                        Appartement appartement = new Appartement(appartId, appartementPieces, idNiveauAppartement);
                        appartements.put(appartId, appartement);
                        for (int i = 3; i < parts.length; i++) {
                            int pieceId = Integer.parseInt(parts[i]);
                            Piece piece = pieces.get(pieceId);
                            if (piece != null) {
                                appartementPieces.add(piece);
                            }
                        }
                        break;
                    case "Piece":
                        int pieceId = Integer.parseInt(parts[1]);
                        int solId = Integer.parseInt(parts[2]);
                        int plafondId = Integer.parseInt(parts[3]);
                        List<Mur> pieceMurs = new ArrayList<>();
                        for (int i = 4; i < parts.length; i++) {
                            int murId = Integer.parseInt(parts[i]);
                            Mur mur = murs.get(murId);
                            if (mur != null) {
                                pieceMurs.add(mur);
                            }
                        }
                        Piece piece = new Piece(pieceId, pieceMurs, sols.get(solId), plafonds.get(plafondId), appartements.get(Integer.parseInt(parts[4])));
                        pieces.put(pieceId, piece);
                        break;
                    case "Mur":
                        int murId = Integer.parseInt(parts[1]);
                        int coinDebutId = Integer.parseInt(parts[2]);
                        int coinFinId = Integer.parseInt(parts[3]);
                        int nbrePortes = Integer.parseInt(parts[4]);
                        int nbreFenetres = Integer.parseInt(parts[5]);
                        List<Revetement> murRevetements = new ArrayList<>();
                        for (int i = 6; i < parts.length; i++) {
                            int revetementId = Integer.parseInt(parts[i]);
                            Revetement revetement = Revetement.createRevetementFromCatalogue(revetementId);
                            murRevetements.add(revetement);
                        }
                        Mur mur = new Mur(murId, coins.get(coinDebutId), coins.get(coinFinId), nbrePortes, nbreFenetres, murRevetements);
                        murs.put(murId, mur);
                        break;
                    case "Coin":
                        int coinId = Integer.parseInt(parts[1]);
                        double cx = Double.parseDouble(parts[2]);
                        double cy = Double.parseDouble(parts[3]);
                        Coin coin = new Coin(coinId, cx, cy);
                        coins.put(coinId, coin);
                        break;
                    case "Sol":
                        int Idsol = Integer.parseInt(parts[1]);
                        List<Coin> solCoins = new ArrayList<>();
                        for (int i = 2; i <= 5; i++) {
                            solCoins.add(coins.get(Integer.parseInt(parts[i])));
                        }
                        List<Revetement> solRevetements = new ArrayList<>();
                        int revSolId = Integer.parseInt(parts[6]);
                        Revetement solRevetement = Revetement.createRevetementFromCatalogue(revSolId);
                        solRevetements.add(solRevetement);
                        Sol sol = new Sol(Idsol, solCoins, solRevetements);
                        sols.put(Idsol, sol);
                        break;
                    case "Plafond":
                        int Idplafond = Integer.parseInt(parts[1]);
                        List<Coin> plafondCoins = new ArrayList<>();
                        for (int i = 2; i <= 5; i++) {
                            plafondCoins.add(coins.get(Integer.parseInt(parts[i])));
                        }
                        List<Revetement> plafondRevetements = new ArrayList<>();
                        int revPlafondId = Integer.parseInt(parts[6]);
                        Revetement plafondRevetement = Revetement.createRevetementFromCatalogue(revPlafondId);
                        plafondRevetements.add(plafondRevetement);
                        Plafond plafond = new Plafond(Idplafond, plafondCoins, plafondRevetements);
                        plafonds.put(Idplafond, plafond);
                        break;
                }
            }
        }
        return batiment;
    }
}
