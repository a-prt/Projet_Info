/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.paret.projet_info_v3;

/**
 *
 * @author jeanb
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Revetement {
    private int idRevetement;
    private String nomrev;
    private boolean pourMur, pourSol, pourPlafond;
    private double prixUnitaire;
    
    Revetement(int id, String nom, boolean prMur, boolean prSol, boolean prPlafond, double prix)
    {
        this.idRevetement=id;
        this.nomrev=nom;
        this.pourMur=prMur;
        this.pourSol=prSol;
        this.pourPlafond=prPlafond;
        this.prixUnitaire=prix;
    }
    public void setIdRevetement (int id){
        this.idRevetement=id;
    }
    public void setNomrev (String nom){
        this.nomrev=nom;
    }
    public void setPourMur (boolean prMur){
        this.pourMur=prMur;
    }
    public void setPourSol (boolean prSol){
        this.pourSol=prSol;
    }
    public void setPourPlafond (boolean prPlafond){
        this.pourPlafond=prPlafond;
    }
    public void setPrixUnitaire (double prix){
        this.prixUnitaire=prix;
    }
    public int getIdRevetement (){
        return idRevetement;
    } 
    public String getNomrev (){
        return nomrev;
    }
    public boolean getPourSol(){
        return pourSol;
    }
    public boolean getPourMur(){
        return pourMur;
    }
    public boolean getPourPlafond(){
        return pourPlafond;
    }
    public double getPrixUnitaire (){
        return prixUnitaire;
    }
 public static Revetement createRevetementFromCatalogue(int id) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("CatalogueRevetements.txt"))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] t = line.split(";");
                if (Integer.parseInt(t[0]) == id) {
                    boolean pourMur = t[2].equals("1");
                    boolean pourSol = t[3].equals("1");
                    boolean pourPlafond = t[4].equals("1");
                    double prixUnitaire = Double.parseDouble(t[5]);
                    return new Revetement(id, t[1], pourMur, pourSol, pourPlafond, prixUnitaire);
                }
            }
        }
        return null;
    }

   /* public static List<String> getRevetementsPourMur() {
        List<String> revetementsPourMur = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("CatalogueRevetements.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] t = line.split(";");
                if (t.length == 6 && t[2].equals("1")) {
                    String nomRevetement = "\"" + t[1] + "\"";
                    revetementsPourMur.add(nomRevetement);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return revetementsPourMur;
    }
    
        public static List<String> getRevetementsPourSol() {
        List<String> revetementsPourSol = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("CatalogueRevetements.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] t = line.split(";");
                if (t.length == 6 && t[3].equals("1")) {
                    String nomRevetement = "\"" + t[1] + "\"";
                    revetementsPourSol.add(nomRevetement);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return revetementsPourSol;
        }
            
        public static List<String> getRevetementsPourPlafond() {
        List<String> revetementsPourPlafond = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("CatalogueRevetements.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] t = line.split(";");
                if (t.length == 6 && t[4].equals("1")) {
                    String nomRevetement = "\"" + t[1] + "\"";
                    revetementsPourPlafond.add(nomRevetement);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return revetementsPourPlafond;
    }*/
        
}
    
