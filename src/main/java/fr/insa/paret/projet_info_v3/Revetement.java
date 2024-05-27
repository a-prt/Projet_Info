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
    public void LectureRevetement(int id) {
            try{
                BufferedReader bufferedReader= new BufferedReader(new FileReader("CatalogueRevetements.txt"));
                String line;
                while((line = bufferedReader.readLine()) != null){
                        String [] t=line.split(";");
                        if (t[0].equals(Integer.toString(id))){
                            this.idRevetement=id;
                            this.nomrev=t[1];
                            if (t[2].equals("1")){
                                this.pourMur= true;
                            }else{
                                this.pourMur= false; 
                                        }
                            if (t[3].equals("1")){
                                this.pourSol= true;
                            }else{
                                this.pourSol= false; 
                                        }
                            if (t[4].equals("1")){
                                   this.pourPlafond= true;
                            }else{
                                this.pourPlafond= false; 
                            }
                            this.prixUnitaire = Double.parseDouble(t[5]);
                            break; // sort de la boucle lorsque le revetement est trouvé
                        }
                }
    }catch (Exception e){
                e.printStackTrace();
    }
    }

    public static List<String> getRevetementsPourMur() {
        List<String> revetementsPourMur = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("CatalogueRevetements.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] t = line.split(";");
                if (t.length == 6 && t[2].equals("1")) {
                    String nom = t[1];
                    revetementsPourMur.add(nom);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return revetementsPourMur;
    }
}