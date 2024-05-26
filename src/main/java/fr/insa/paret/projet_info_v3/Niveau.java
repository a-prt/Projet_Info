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

public class Niveau {
    int idNiveau;
    List<Appartement> listeApparts;
    
    public Niveau (int idNiveau, List<Appartement> listeApparts) {
        this.idNiveau= idNiveau;
        this.listeApparts=listeApparts;
    }
    public int getIdNiveau() {
        return idNiveau;
    }
    public List<Appartement> getListeApparts() {
    return listeApparts;
    }

    public void setIdNiveau (int idNiveau){
        this.idNiveau=idNiveau;
    }
    
    public void setListeApparts(List<Appartement> listeApparts) {
        this.listeApparts = listeApparts;
    }
    
    public double surface(){
        double totalSurface = 0.0;
        if (listeApparts != null) {
            for (Appartement appartement : listeApparts) {
                totalSurface += appartement.surface();
            }
        }
        return totalSurface;
    }
    
        public double montantRevetement(){
    double montantTotal = 0.0;
    if (listeApparts != null) {
        for (Appartement appartement : listeApparts) {
            montantTotal += appartement.montantRevetement();
        }
    }
    return montantTotal;
    }
    
        public void afficher(){
        System.out.println("Informations sur le niveau:");
        System.out.println("ID du niveau: "+ idNiveau);
        System.out.println("Liste des appartements: ");
        if (listeApparts != null) {
            for (Appartement appartement : listeApparts) {
                System.out.println("-" + appartement.getIdAppartement());
            }
        }
    }
        
             @Override  
 public String toString() { 
        StringBuilder sb = new StringBuilder(); 
        sb.append("Niveau");
        sb.append(";").append(getIdNiveau());
        sb.append(";").append("2,5");
        if (listeApparts!=null){
            for(Appartement appartement : listeApparts){
                sb.append(";").append(appartement.getIdAppartement());
            }
        }
        return sb.toString();
    }
}

