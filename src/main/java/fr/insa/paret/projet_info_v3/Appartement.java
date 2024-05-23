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

public class Appartement {
    int idAppartement;
    int idNiveauAppartement;
    List<Piece> listePieces;
    
    public Appartement(int idAppartement, List<Piece> listePieces, int idNiveauAppartement, double surface) {
        this.idAppartement= idAppartement;
        this.listePieces=listePieces;
        this.idNiveauAppartement=idNiveauAppartement;
    }
    public int getIdAppartement() {
        return idAppartement;
    }
    public int getIdNiveauAppartement() {
        return idNiveauAppartement;
    }
    public List<Piece> getListePieces() {
    return listePieces;
    }
    public void setIdNiveauAppartement(int idNiveauAppartement) {
        this.idNiveauAppartement= idNiveauAppartement;
    }
    public void setIdAppartement (int idAppartement){
        this.idAppartement=idAppartement;
    }
    
    public void setListePieces(List<Piece> listePieces) {
        this.listePieces = listePieces;
    }
   
    public double surface(){
        double surfaceTotale=0;
        if (listePieces != null) {
           for (Piece piece : listePieces) {
            surfaceTotale += piece.surface();
        }
        }
    return surfaceTotale;
    }
    
    public double montantRevetement(){
    double montantTotal = 0;
    if (listePieces != null) {
           for (Piece piece : listePieces) {
            montantTotal += piece.montantRevetement();
        }
    }
    return montantTotal;
    }
        public void afficher(){
        System.out.println("Informations sur l'appartement:");
        System.out.println("ID de l'appartement: "+ idAppartement);
        System.out.println("ID du niveau de l'appartement: " + idNiveauAppartement);
        System.out.println("Liste des pièces: ");
        if (listePieces != null) {
            for (Piece piece : listePieces) {
                System.out.println("-" + piece.getIdPiece());
            }
        }
    }
        
     @Override  
 public String toString() { 
        StringBuilder sb = new StringBuilder(); 
        sb.append("Appartement");
        sb.append(";").append(getIdAppartement());
        sb.append(";").append(getIdNiveauAppartement());
        return sb.toString();
    }
}