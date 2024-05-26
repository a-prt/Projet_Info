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

public class Piece {
    int idPiece;
    Sol sol;
    Plafond plafond;
    List<Mur> listeMurs;
    Appartement appartement;
    
    public Piece(int idPiece, List<Mur> listeMurs, Sol sol, Plafond plafond, Appartement appartement) {
        this.idPiece= idPiece;
        this.listeMurs=listeMurs;
        this.sol=sol;
        this.plafond=plafond;
        this.appartement= appartement;
    }
    
    public int getIdPiece() {
        return idPiece;
    }

    public Sol getSol() {
        return sol;
    }
    public Plafond getPlafond(){
    return plafond;
    }
    public List<Mur> getListeMurs() {
    return listeMurs;
    }
       public Appartement getAppartement() {
        return appartement;
    }

    public void setSol(Sol sol) {
        this.sol = sol;
    }
    public void setPlafond(Plafond plafond) {
        this.plafond= plafond;
    }
    public void setIdPiece (int idPiece){
        this.idPiece=idPiece;
    }
    
    public void setListeMurs(List<Mur> listeMurs) {
        this.listeMurs = listeMurs;
    }
        public void setAppartement (Appartement appartement){
        this.appartement= appartement;
    }
    
    public double surface(){
        double surfaceTotale = sol.surface()+ plafond.surface();
        if (listeMurs != null) {
        for (Mur mur : listeMurs) {
            surfaceTotale += mur.surface();
        }
        }
        return surfaceTotale;
        }
    
    public double montantRevetement(){
    double montantTotal;
    montantTotal = plafond.montantRevetement()+ sol.montantRevetement();
    if (listeMurs != null) {
        for (Mur mur : listeMurs) {
            montantTotal += mur.montantRevetement();
        }
    }
    return montantTotal;
    }
    
        public void afficher(){
        System.out.println("Informations sur la pièce:");
        System.out.println("ID de la pièce: "+ idPiece);
        System.out.println("ID du Sol: " + sol);
        System.out.println("ID du Plafond: " + plafond);
        System.out.println("Liste des murs: ");
        if (listeMurs != null) {
            for (Mur mur : listeMurs) {
                System.out.println("- Mur ID" + mur.getIdMur()+ ",Surface:"+ mur.surface());
            }
        }
    }
        
                 @Override  
 public String toString() { 
        StringBuilder sb = new StringBuilder(); 
        sb.append("Piece");
        sb.append(";").append(appartement.getIdAppartement());
        sb.append(";").append(getIdPiece());
        sb.append(";").append(sol.getIdSol());
        sb.append(";").append(plafond.getIdPlafond());
        if (listeMurs!=null){
            for(Mur mur : listeMurs){
                sb.append(";").append(mur.getIdMur());
            }
        }
        return sb.toString();
    }
    
}

