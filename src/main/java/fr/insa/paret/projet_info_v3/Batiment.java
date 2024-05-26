/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.paret.projet_info_v3;

/**
 *
 * @author Utilisateur
 */
import java.util.List;

public class Batiment{
    int idBatiment;
    List<Niveau> listeNiveaux;
    
    public Batiment (int idBatiment, List<Niveau> listeNiveaux) {
        this.idBatiment= idBatiment;
        this.listeNiveaux=listeNiveaux;
    }
    public int getIdBatiment() {
        return idBatiment;
    }
    public List<Niveau> getListeNiveaux() {
    return listeNiveaux;
    }

    public void setIdBatiment (int idBatiment){
        this.idBatiment=idBatiment;
    }
    
    public void setListeNiveaux(List<Niveau> listeNiveaux) {
        this.listeNiveaux = listeNiveaux;
    }
    
    public double surface(){
        double surfaceTotale=0;
        if (listeNiveaux != null) {
           for (Niveau niveau : listeNiveaux) {
            surfaceTotale += niveau.surface();
        }
        }
    return surfaceTotale;
    }
    
    public double montantRevetement(){
    double montantTotal = 0;
    if (listeNiveaux != null) {
           for (Niveau niveau : listeNiveaux) {
            montantTotal += niveau.montantRevetement();
        }
    }
    return montantTotal;
    }
    
        public void afficher(){
        System.out.println("Informations sur le bâtiment:");
        System.out.println("ID du bâtiment: "+ idBatiment);
        System.out.println("Liste des niveaux: ");
        if (listeNiveaux != null) {
            for (Niveau Niveau : listeNiveaux) {
                System.out.println("-" + Niveau.getIdNiveau());
            }
        }
    }
    
         @Override  
 public String toString() { 
        StringBuilder sb = new StringBuilder(); 
        sb.append("Batiment");
        sb.append(";").append(getIdBatiment());
        if (listeNiveaux!=null){
            for(Niveau niveau : listeNiveaux){
                sb.append(";").append(niveau.getIdNiveau());
            }
        }
        return sb.toString();
    }
}

